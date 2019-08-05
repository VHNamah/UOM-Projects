package Services;

import Helpers.Serializer;
import Packet.Type;
import Packet.Wrapper;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Connection {
    public static String Server = "127.0.0.1";
    public static int ServerPort = 1257;
    public static int Port = 12345;


    public static void ConnectToServer() {
        try {
            DatagramSocket Socket = new DatagramSocket();

            System.out.println("[CLIENT] SUBSCRIBING TO SERVER");

            Wrapper P = new Wrapper(Type.HELLO);
            byte[] OUT = Serializer.Serialize(P);

            InetSocketAddress IP = new InetSocketAddress(Connection.Server, Connection.ServerPort);
            DatagramPacket Packet = new DatagramPacket(OUT, OUT.length, IP);

            Socket.send(Packet);
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void DisconnectFromServer() {
        try {
            DatagramSocket Socket = new DatagramSocket();

            System.out.println("[CLIENT] SUBSCRIBING TO SERVER");

            Wrapper P = new Wrapper(Type.GOODBYE);
            byte[] OUT = Serializer.Serialize(P);

            InetSocketAddress IP = new InetSocketAddress(Connection.Server, Connection.ServerPort);
            DatagramPacket Packet = new DatagramPacket(OUT, OUT.length, IP);

            Socket.send(Packet);
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
}
