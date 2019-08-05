package Services;/* Created by Vidush Namah on 4/26/2017 */

import Packet.Wrapper;

import java.util.ArrayList;

public class Storage {
    public static String LastUpdate = new String();
    private static ArrayList<Wrapper> Shapes = new ArrayList<>();
    public static Object Lock = new Object();

    public static void addItem(Wrapper W) {
        synchronized (Lock) {
            Shapes.add(W);
        }
    }

    public static void setShapes(ArrayList<Wrapper> A) {
        synchronized (Lock) {
            Shapes = A;
        }
    }

    public static ArrayList<Wrapper> getShapes() {
        synchronized (Lock) {
            return Shapes;
        }
    }
}
