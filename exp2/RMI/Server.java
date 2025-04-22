
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            // Start the RMI registry on port 1099
            LocateRegistry.createRegistry(1099);

            // Create and bind the Calculator implementation
            Calculator calculator = new CalculatorImpl();
            Naming.rebind("CalculatorService", calculator);

            System.out.println("Calculator Service is ready and running.");
        } catch (Exception e) {
            System.err.println("Server error: ");
            e.printStackTrace();
        }
    }
}
