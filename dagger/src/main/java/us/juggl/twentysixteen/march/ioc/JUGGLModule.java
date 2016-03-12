package us.juggl.twentysixteen.march.ioc;

import dagger.Module;
import dagger.Provides;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.inject.Singleton;
import javax.sql.DataSource;

/**
 * Created by dphillips on 3/12/16.
 */
@Module
@Slf4j
public class JUGGLModule {

    @Provides @Singleton
    public DataSource datasource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
        ds.setUsername("SA");
        ds.setPassword("");
        ds.setInitialSize(5);
        ds.setMaxTotal(10);
        ds.setUrl("jdbc:hsqldb:mem:mydb");

        try {
            Database db = DatabaseFactory
                    .getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(ds.getConnection()));
            Liquibase l = new Liquibase("db-changelog.xml", new ClassLoaderResourceAccessor(), db);
            l.update("");
        } catch (Exception e) {
            LOG.error("Error initializing database", e);
        }
        return ds;
    }
}
