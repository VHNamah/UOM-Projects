package Services;/* Created by Vidush Namah on 2/15/2017 */

import Helpers.Serializer;
import Packet.Type;
import Packet.Wrapper;
import Whiteboard.UIFrame;

import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Sender implements Runnable {

    private static Boolean IsRunning = false;

    private DatagramSocket Socket;

    private String Name;
    private Wrapper Data;

    public Sender(String Key) {
        Name = Key;
    }

    public Sender(Type T, Shape S, Color C) {
        UIFrame.Canvas.Draw(S, C);
        Data = new Wrapper(T, S, C);

        Send();
    }

    public void Send() {
        Thread T = new Thread(this);
        T.start();
    }

    @Override
    public void run() {
        try {
            Socket = new DatagramSocket();
            InetSocketAddress ServerIP = new InetSocketAddress(Connection.Server, Connection.ServerPort);

            byte[] OUT = Serializer.Serialize(Data);

            DatagramPacket OUTPacket = new DatagramPacket(OUT, OUT.length, ServerIP);
            Socket.send(OUTPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Socket.close();
        IsRunning = false;
    }

}
