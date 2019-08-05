package Packet;

/* Created by Vidush Namah on 2/15/2017 */

import java.awt.*;
import java.io.Serializable;

public class Wrapper implements Serializable {

    private Type Type;
    private Shape Data;
    private Color Ink;

    public Wrapper(Type T) {
        Type = T;
        Data = null;
    }

    public Wrapper(Type T, Shape S, Color C) {
        Type = T;
        Data = S;
        Ink = C;
    }

    public Type getType() { return Type; }
    public Shape getData() { return Data; }
    public Color getInk() { return Ink; }

    public void setType(Type T) { Type = T; }
    public void setData(Shape D) { Data = D; }
    public void setData(Color C) { Ink = C; }
}
