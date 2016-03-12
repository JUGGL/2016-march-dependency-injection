package us.juggl.twentysixteen.march;

/**
 * Hello world!
 *
 */
public class HelloSpring {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void printHello() {
        System.out.println(String.format("Hello %s!", name));
    }
}
