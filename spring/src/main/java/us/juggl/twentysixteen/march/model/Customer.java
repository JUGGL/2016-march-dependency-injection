package us.juggl.twentysixteen.march.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by dphillips on 3/12/16.
 */
@Data
@Accessors(chain = true, fluent = true)
public class Customer {

    private long id;
    private String name;
    private String addr1;
    private String addr2;
    private String city;
    private String province;
    private String postalCode;
    private String phone;
}
