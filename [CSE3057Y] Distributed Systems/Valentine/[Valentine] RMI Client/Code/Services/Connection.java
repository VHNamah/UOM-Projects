package Services;

import Interfaces.RMIContract;
import Models.Data;
import Whiteboard.UIThread;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Connection {
    public static String Server = "127.0.0.1";
    public static int ServerPort = 9000;
    public static int Port = 12345;

    private static String ServiceName = "Valentine";
    public static RMIContract Service;

    public static void ConnectToServer() {
        try {
            Registry R = LocateRegistry.getRegistry(Server, ServerPort);
            Service = (RMIContract) R.lookup(ServiceName);

            System.out.println("[CLIENT] Service connected.");

            //INITIAL RETRIEVE
            Data IN = Service.Retrieve(null);
            Storage.LastUpdate = IN.LastUpdate;
            Storage.setShapes(IN.Content);

            new Thread(() -> UIThread.Refresh()).start();

        } catch (Exception E) {
            System.out.println("[CLIENT] Something went wrong.");
            E.printStackTrace();
        }
    }
}
