package Whiteboard;

/* Created by Vidush Namah on 2/15/2017 */

import Interface.Board;
import Interface.Toolbar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UIFrame extends JFrame {

    public static volatile Board Canvas;
    public static Toolbar Tools = new Toolbar();

    //STATIC HARDCODED VALUES THAT I HATE
    int ToolbarHeight = 88;

    public UIFrame(Board B) {
        Canvas = B;

        Dimension Screen = Toolkit.getDefaultToolkit().getScreenSize();
        Insets Bar = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());

        //Initializing FRAME
        setTitle("[CLIENT] Valentine 1.0");
        setBounds(0, 0, Screen.width, Screen.height - Bar.bottom);
        setMinimumSize(new Dimension(1074, Screen.height - Bar.bottom));

        //TOOLBAR
        Tools.setBorder(new LineBorder(Color.WHITE));
        this.getContentPane().add(Tools, BorderLayout.NORTH);

        Canvas.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()-ToolbarHeight));
        this.getContentPane().add(Canvas, BorderLayout.SOUTH);
    }

}
