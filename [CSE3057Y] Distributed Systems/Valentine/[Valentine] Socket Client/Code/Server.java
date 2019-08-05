import Helpers.Serializer;
import Packet.Type;
import Packet.Wrapper;
import Services.Connection;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private volatile static ArrayList<InetSocketAddress> Clients = new ArrayList<>();
    private volatile static ArrayList<Wrapper> Packets = new ArrayList<>();

    private static DatagramSocket Socket;

    public static void main (String[] args) throws Exception {
        Socket = new DatagramSocket(Connection.ServerPort);
        System.out.println("[SERVER] Version 1.9 Online\n");

        ExecutorService Pool = Executors.newCachedThreadPool();
        //ExecutorService Pool = Executors.newFixedThreadPool(10);


        while (true) {
            byte[] IN = new byte[4096];

            DatagramPacket Packet = new DatagramPacket(IN, IN.length);
            Socket.receive(Packet);

            Pool.submit(() -> handler(Packet));
            //new Thread(() -> handler(Packet)).start();
        }
    }

    public synchronized static void handler(DatagramPacket Packet) {
        try {
            Wrapper INPacket = (Wrapper) Serializer.Deserialize(Packet.getData());
            InetSocketAddress SenderIP = new InetSocketAddress(Packet.getAddress().getHostAddress(), Connection.Port);

            if (INPacket.getType() == Type.HELLO) {
                System.out.println("[SERVER] HELLO PACKET [" + SenderIP.toString() + "]");
                Clients.add(SenderIP);
            }

            else if (INPacket.getType() == Type.GOODBYE) {
                if (Clients.contains(SenderIP)) {
                    Clients.remove(SenderIP);
                    System.out.println("[SERVER] GOODBYE PACKET [" + SenderIP.toString() + "]");
                }
            }

            else {
                System.out.println("[SERVER] BROADCASTING FROM [" + SenderIP.toString() + "]");

                Packets.add(INPacket);

                for (InetSocketAddress IP : Clients) {
                    if (!IP.equals(SenderIP)) {
                        byte[] OUT = Packet.getData();
                        DatagramPacket OUTPacket = new DatagramPacket(OUT, OUT.length, IP);

                        Socket.send(OUTPacket);
                    }
                }
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
}
