package us.juggl.twentysixteen.march;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import us.juggl.twentysixteen.march.dao.CustomerDAO;
import us.juggl.twentysixteen.march.model.Customer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppTest extends TestCase {

    @Mock(answer = Answers.RETURNS_MOCKS)
    private DataSource ds;

    @InjectMocks
    private CustomerDAO dao;

    @Test
    public void testCreateCustomer() throws Exception {
        Connection conn = mock(Connection.class);
        when(ds.getConnection()).thenReturn(conn);
        PreparedStatement s = mock(PreparedStatement.class);
        when(conn.prepareStatement(anyString())).thenReturn(s);
        when(s.executeUpdate()).thenReturn(2);

        Customer newCustomer = new Customer()
                .name("Acme Widgets, LLC")
                .addr1("123 Main St.")
                .addr2("Building 4")
                .city("Hometown")
                .province("Kentucky")
                .postalCode("40202")
                .phone("502-555-1212");
        dao.createNewCustomer(newCustomer);
        verify(ds, times(1)).getConnection();
        verify(conn, times(1)).prepareStatement(anyString());
        verify(s, times(1)).setLong(anyInt(), anyLong());
        verify(s, times(7)).setString(anyInt(), anyString());
    }

    @Test
    public void testGetCustomerList() throws Exception {
        Connection conn = mock(Connection.class);
        when(ds.getConnection()).thenReturn(conn);
        PreparedStatement s = mock(PreparedStatement.class);
        when(conn.prepareStatement(anyString())).thenReturn(s);
        ResultSet r = mock(ResultSet.class);
        when(s.executeQuery()).thenReturn(r);
        when(r.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(r.getLong(eq("ID"))).thenReturn(1L).thenReturn(2L);
        when(r.getString(eq("CUSTNAME"))).thenReturn("Acme Widgets, LLC").thenReturn("Acme Welding, Inc.");
        when(r.getString(eq("ADDRESS1"))).thenReturn("123 Main St.").thenReturn("1212 Industry Ave.");
        when(r.getString(eq("ADDRESS2"))).thenReturn("Building 4").thenReturn(null);
        when(r.getString(eq("CITY"))).thenReturn("Hometown").thenReturn("Vancouver");
        when(r.getString(eq("PROVINCE"))).thenReturn("Illinois").thenReturn("British Columbia");
        when(r.getString(eq("POSTALCODE"))).thenReturn("42032").thenReturn("VA3987");
        when(r.getString(eq("PHONE"))).thenReturn("502-555-1212").thenReturn("+22 24 2321 243");

        List<Customer> customers = dao.getAllCustomers();
        assertEquals("Size of customer list MUST be 2", customers.size(), 2);
        assertEquals("Customer names MUST match:", customers.get(0).name(), "Acme Widgets, LLC");
        assertEquals("Customer names MUST match:", customers.get(1).name(), "Acme Welding, Inc.");
        verify(ds, times(1)).getConnection();
        verify(conn, times(1)).prepareStatement(anyString());
        verify(r, times(3)).next();
        verify(r, times(2)).getLong(eq("ID"));
        verify(r, times(2)).getString(eq("CUSTNAME"));
        verify(r, times(2)).getString(eq("ADDRESS1"));
        verify(r, times(2)).getString(eq("ADDRESS2"));
        verify(r, times(2)).getString(eq("CITY"));
        verify(r, times(2)).getString(eq("PROVINCE"));
        verify(r, times(2)).getString(eq("POSTALCODE"));
        verify(r, times(2)).getString(eq("PHONE"));
    }
}
