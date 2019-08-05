package Implementation;/* Created by Vidush Namah on 4/26/2017 */

import Interfaces.RMIContract;
import Models.Data;
import Packet.Wrapper;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RMIService extends UnicastRemoteObject implements RMIContract {
    private Lock Key = new ReentrantLock();

    private ArrayList<Wrapper> Shapes = new ArrayList<>();
    private ArrayList<String> Updates = new ArrayList<>();
    private String LastUpdate;

    public RMIService() throws RemoteException {
        super();
        LastUpdate = new Date().toString();

        System.out.println("[SERVICE] Online at " + LastUpdate + ".");
    }

    @Override
    public synchronized Data Update(Wrapper S1, String S2) throws RemoteException {
        String Now;
        ArrayList<Wrapper> Subset = new ArrayList<>();

        Key.tryLock();
        try {
            Now = new Date().toString();

            System.out.println("[SERVICE] " + S1.getType() + " at " + Now);

            //RETRIEVING PENDING UPDATES
            int Index = Updates.indexOf(S2);

            //UPDATING LIST
            Shapes.add(S1);
            Updates.add(Now);
            LastUpdate = Now;

            //COMPILING NEW UPDATES
            for (int I=Index+1; I<Shapes.size(); I++)
                Subset.add(Shapes.get(I));

        } finally {
            Key.unlock();
        }

        return new Data(Now, Subset);
    }

    @Override
    public Data Retrieve(String S) throws RemoteException {
        int Index = Updates.indexOf(S);
        ArrayList<Wrapper> Subset = new ArrayList<>();

        for (int I=Index+1; I<Shapes.size(); I++)
            Subset.add(Shapes.get(I));

        return new Data(LastUpdate, Subset);
    }
}
