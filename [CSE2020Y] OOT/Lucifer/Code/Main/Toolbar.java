package Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vidush H. Namah on 06 Mar 2016.
 **/

public class Toolbar extends JPanel {

    public static final Dimension Size = new Dimension(48,48);

    public Toolbar() {
        super();

        setBackground(new Color(211,211,211));
        setPreferredSize(new Dimension(0,58));

        this.setLayout(new FlowLayout());
    }
}
