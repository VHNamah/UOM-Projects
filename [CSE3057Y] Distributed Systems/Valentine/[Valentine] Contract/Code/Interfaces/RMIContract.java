package Interfaces;/* Created by Vidush Namah on 4/26/2017 */

import Models.Data;
import Packet.Wrapper;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIContract extends Remote {
    Data Update(Wrapper S1, String S2) throws RemoteException;
    Data Retrieve(String LastUpdate) throws RemoteException;
}
