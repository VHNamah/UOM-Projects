import Implementation.RMIService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Server {
    private static String Server = "127.0.0.1";
    private static int Port = 9000;
    private static String ServiceName = "Valentine";

    public static void main(String[] args) {
        try {
            System.out.print("[SERVER] Enter IP: ");
            Server = new Scanner(System.in).nextLine();

            System.out.println("[SERVER] Online.");
            System.out.println("[SERVER] Registered Service on Port 9000.");
            Registry R = LocateRegistry.createRegistry(Port);
            RMIService Service = new RMIService();

            String URI = "rmi://" + Server + ":" + Port + "/" + ServiceName;
            Naming.rebind(URI, Service);

            System.out.println("[SERVER] Standby.");
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
}
