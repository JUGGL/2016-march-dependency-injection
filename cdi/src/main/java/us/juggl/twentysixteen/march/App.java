package us.juggl.twentysixteen.march;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import us.juggl.twentysixteen.march.dao.CustomerDAO;
import us.juggl.twentysixteen.march.model.Customer;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String... args) {

        CdiContainer cdiContainer = CdiContainerLoader.getCdiContainer();
        cdiContainer.boot();

        CustomerDAO dao = BeanProvider.getContextualReference(CustomerDAO.class);

        Customer newCustomer = new Customer()
                .name("Acme Widgets, LLC")
                .addr1("123 Main St.")
                .addr2("Building 4")
                .city("Hometown")
                .province("Kentucky")
                .postalCode("40202")
                .phone("502-555-1212");
        boolean result = dao.createNewCustomer(newCustomer);
        if (result) {
            Customer c = dao.getAllCustomers().get(0);
            System.out.println("ID: "+c.id());
            System.out.println("NAME: "+c.name());
            System.out.println("ADDR1: "+c.addr1());
            System.out.println("ADDR2: "+c.addr2());
            System.out.println("CITY: "+c.city());
            System.out.println("PROVINCE: "+c.province());
            System.out.println("POSTAL CODE: "+c.postalCode());
            System.out.println("PHONE: "+c.phone());
        }

        cdiContainer.shutdown();
    }
}
