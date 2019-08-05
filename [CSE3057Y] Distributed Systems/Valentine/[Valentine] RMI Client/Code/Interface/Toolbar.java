package Interface;

/* Created by Vidush Namah on 2/15/2017 */

import Packet.Type;

public class Toolbar extends EPanel {

    public static EButton BTNFree = new EButton("FREE", "Free.png");
    public static EButton BTNLine = new EButton("LINE", "Line.png");
    public static EButton BTNSquare = new EButton("RECTANGLE", "Square.png");
    public static EButton BTNCircle = new EButton("ELLIPSE", "Circle.png");

    public Toolbar() {
        super();

        Board.Choice = Type.FREE;

        //SETTING LISTENERS
        BTNFree.addActionListener(e -> {
            Board.Status = true;
            Board.Choice = Type.FREE;

            BTNFree.SetFocus(true);
        });

        BTNLine.addActionListener(e -> {
            Board.Status = true;
            Board.Choice = Type.LINE;

            BTNLine.SetFocus(true);
        });

        BTNSquare.addActionListener(e -> {
            Board.Status = true;
            Board.Choice = Type.SQUARE;

            BTNSquare.SetFocus(true);
        });

        BTNCircle.addActionListener(e -> {
            Board.Status = true;
            Board.Choice = Type.CIRCLE;

            BTNCircle.SetFocus(true);
        });

        this.add(BTNFree);
        this.add(BTNLine);
        this.add(BTNSquare);
        this.add(BTNCircle);
    }

    public void Reset() {
        BTNFree.SetFocus(false);
        BTNCircle.SetFocus(false);
        BTNLine.SetFocus(false);
        BTNSquare.SetFocus(false);
    }
}
