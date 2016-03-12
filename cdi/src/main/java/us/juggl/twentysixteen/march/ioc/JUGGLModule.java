package us.juggl.twentysixteen.march.ioc;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

/**
 * Created by dphillips on 3/12/16.
 */
@ApplicationScoped
public class JUGGLModule {

    @Produces
    public DataSource datasource() throws Exception {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
        ds.setUsername("SA");
        ds.setPassword("");
        ds.setInitialSize(5);
        ds.setMaxTotal(10);
        ds.setUrl("jdbc:hsqldb:mem:mydb");

        Database db = DatabaseFactory
                            .getInstance()
                            .findCorrectDatabaseImplementation(new JdbcConnection(ds.getConnection()));
        Liquibase l = new Liquibase("db-changelog.xml", new ClassLoaderResourceAccessor(), db);
        l.update("");

        return ds;
    }
}
