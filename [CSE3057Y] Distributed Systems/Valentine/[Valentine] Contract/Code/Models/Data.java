package Models;/* Created by Vidush Namah on 4/26/2017 */

import Packet.Wrapper;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
    public String LastUpdate;
    public ArrayList<Wrapper> Content;

    public Data(String LastUpdate, ArrayList<Wrapper> Shapes) {
        this.LastUpdate = LastUpdate;
        this.Content = Shapes;
    }
}
