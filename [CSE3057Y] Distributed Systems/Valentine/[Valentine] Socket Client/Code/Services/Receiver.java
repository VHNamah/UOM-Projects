package Services;

/* Created by Vidush Namah on 2/15/2017 */

import Helpers.Serializer;
import Packet.Wrapper;
import Whiteboard.UIThread;

import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver implements Runnable {

    private DatagramSocket Socket;
    private static Boolean IsRunning = true;

    private String Name;

    public Receiver(String Key) {
        Name = Key;
    }

    public Receiver() {}

    public static void Cleanup() {
        IsRunning = false;
    }

    @Override
    public void run() {
        while (IsRunning) {
            try {
                Socket = new DatagramSocket(Connection.Port);

                byte[] IN = new byte[2048];

                while (true) {
                    DatagramPacket Incoming = new DatagramPacket(IN, IN.length);
                    Socket.receive(Incoming);

                    Wrapper P = (Wrapper) Serializer.Deserialize(IN);
                    Shape S = P.getData();
                    Color C = P.getInk();
                    System.out.println("\n[+] LINE:" + P.getType());

                    new Thread(() -> UIThread.Canvas.Draw(S,C)).start();
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
        }

        Socket.close();
    }

}
