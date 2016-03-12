package us.juggl.twentysixteen.march;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import us.juggl.twentysixteen.march.dao.CustomerDAO;
import us.juggl.twentysixteen.march.model.Customer;

/**
 * Created by dphillips on 3/12/16.
 */
public class Application {

    public static void main(String... args) {

        // Initialize the Spring module descriptors from the XML Config
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-Module.xml");

        // Create an instance of the HelloSpring class using Spring IoC
        HelloSpring obj = (HelloSpring) ctx.getBean("helloBean", HelloSpring.class);  // Ensure type safety!!!
        obj.printHello();

        // Create an instance of the CustomerDAO class using Spring IoC
        CustomerDAO dao = (CustomerDAO) ctx.getBean("customerDao", CustomerDAO.class);
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
    }
}
