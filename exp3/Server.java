import HelloApp.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;


public class Server {
public static void main(String[] args) throws Exception {
        ORB orb = ORB.init(args, null);
        POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        HelloImpl hello = new HelloImpl();
        rootPOA.activate_object(hello);
        org.omg.CORBA.Object ref = rootPOA.servant_to_reference(hello);
        Hello href = HelloHelper.narrow(ref);
        String ior = orb.object_to_string(href);
        System.out.println("Server is running...");
        System.out.println("Ior:"+ior);
        orb.run();
        }
}  
    

