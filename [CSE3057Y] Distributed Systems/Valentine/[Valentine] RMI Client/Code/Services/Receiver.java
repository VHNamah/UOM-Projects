package Services;

/* Created by Vidush Namah on 2/15/2017 */

import Models.Data;
import Packet.Wrapper;
import Whiteboard.UIThread;

import java.awt.*;

public class Receiver implements Runnable {

    private static Boolean IsRunning = true;

    @Override
    public void run() {
        while (IsRunning) {
            try {
                Data IN = Connection.Service.Retrieve(Storage.LastUpdate);

                if (!Storage.LastUpdate.equals(IN.LastUpdate)) {
                    for (Wrapper P : IN.Content) {
                        Storage.addItem(P);

                        System.out.print("\n[+] DRAW:" + P.getType());
                        new Thread(() -> UIThread.Canvas.repaint()).start();
                    }
                }

                Storage.LastUpdate = IN.LastUpdate;

                Thread.sleep(2500);
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
    }
}
