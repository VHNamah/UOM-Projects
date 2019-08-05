package Views;

import Main.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vidush H. Namah on 07 Mar 2016.
 **/

public class PoSCard extends JPanel {

    public PoSCard(LayoutManager G) {
        super(G);
        GridBagConstraints C = new GridBagConstraints();



        this.add(new JLabel("POS", JLabel.CENTER));
    }

    public static void loadToolbar() {
        GUI.Tools.removeAll();
        GUI.Tools.setVisible(true);



        GUI.Tools.repaint();
    }
}
