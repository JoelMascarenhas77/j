
import HelloApp.*;
import org.omg.CORBA.*;
public class Client {
public static void main(String[] args) throws Exception {
        ORB orb = ORB.init(args, null);
        org.omg.CORBA.Object obj = orb.string_to_object("IOR:...");
        Hello hello = HelloHelper.narrow(obj);
        System.out.println(hello.sayHello());
    }
} 
    

