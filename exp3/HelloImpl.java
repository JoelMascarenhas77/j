import HelloApp.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;


public class HelloImpl extends HelloPOA {
    public String sayHello() {
        return "Hello from Server!";
    }
}