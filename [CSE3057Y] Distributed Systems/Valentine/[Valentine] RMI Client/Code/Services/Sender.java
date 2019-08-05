package Services;/* Created by Vidush Namah on 2/15/2017 */

import Models.Data;
import Packet.Type;
import Packet.Wrapper;
import Whiteboard.UIFrame;
import Whiteboard.UIThread;

import java.awt.*;

public class Sender implements Runnable {

    private Wrapper Data;

    public Sender(Type T, Shape S, Color C) {
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
            Data IN = Connection.Service.Update(Data, Storage.LastUpdate);

            Storage.LastUpdate = IN.LastUpdate;
            for (Wrapper W : IN.Content) Storage.addItem(W);

            new Thread(() -> UIThread.Refresh()).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
