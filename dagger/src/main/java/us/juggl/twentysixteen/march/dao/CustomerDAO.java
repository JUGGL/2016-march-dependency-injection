package us.juggl.twentysixteen.march.dao;

import lombok.extern.slf4j.Slf4j;
import us.juggl.twentysixteen.march.model.Customer;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dphillips on 3/12/16.
 */
@Slf4j
public class CustomerDAO {

    private DataSource ds;

    @Inject
    public CustomerDAO(DataSource ds) {
        this.ds = ds;
    }

    public static final String INSERT_CUSTOMER = new StringBuilder()
            .append("INSERT INTO CUSTOMERS ")
            .append("   (ID, CUSTNAME, ADDRESS1, ADDRESS2, CITY, PROVINCE, POSTALCODE, PHONE) ")
            .append("VALUES ")
            .append("   (?, ?, ?, ?, ?, ?, ?, ?)")
            .toString();

    public Customer getCustomerByID(long id) {
        try {
            Connection conn = ds.getConnection();
            PreparedStatement s = conn.prepareStatement("SELECT * FROM CUSTOMERS WHERE id=?");
            s.setLong(1, id);
            ResultSet r = s.executeQuery();
            while (r.next()) {
                return mapCustomerFromResultSet(r);
            }
        } catch (SQLException sqle) {
            LOG.error("SQL Exception: ", sqle);
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        try {
            Connection conn = ds.getConnection();
            PreparedStatement s = conn.prepareStatement("SELECT * FROM CUSTOMERS");
            ResultSet r = s.executeQuery();
            while (r.next()) {
                customers.add(mapCustomerFromResultSet(r));
            }
        } catch (SQLException sqle) {
            LOG.error("SQL Exception: ", sqle);
        }
        return customers;
    }

    private Customer mapCustomerFromResultSet(ResultSet r) throws SQLException {
        Customer c = new Customer();
        c.addr1(r.getString("ADDRESS1"));
        c.addr2(r.getString("ADDRESS2"));
        c.city(r.getString("CITY"));
        c.province(r.getString("PROVINCE"));
        c.name(r.getString("CUSTNAME"));
        c.postalCode(r.getString("POSTALCODE"));
        c.id(r.getLong("ID"));
        c.phone(r.getString("PHONE"));
        return c;
    }

    public boolean createNewCustomer(Customer c) {
        try {
            Connection conn = ds.getConnection();
            PreparedStatement s = conn.prepareStatement(INSERT_CUSTOMER);
            s.setLong(1, c.id());
            s.setString(2, c.name());
            s.setString(3, c.addr1());
            s.setString(4, c.addr2());
            s.setString(5, c.city());
            s.setString(6, c.province());
            s.setString(7, c.postalCode());
            s.setString(8, c.phone());
            return s.executeUpdate()>0;
        } catch (SQLException sqle) {
            LOG.error("SQL Exception: ", sqle);
        }
        return false;
    }
}
