import Services.Connection;
import Services.Receiver;
import Whiteboard.UIThread;

import java.util.Scanner;

public class Executable {
    public static Thread UI;
    public static Thread IN;


    public static void main (String[] args) {
        //INITIALISES SERVER
        Scanner Input = new Scanner(System.in);
        System.out.print("[CLIENT] Enter Server IP: ");
        String IP = Input.next();

        Connection.Server = IP;

        Connection.ConnectToServer();

        //INITIALISES CLIENT
        System.out.print("[CLIENT] Version 1.9 Online\n");
        UI = new Thread(new UIThread());
        UI.start();

        IN = new Thread(new Receiver());
        IN.start();
    }
}
