package Views;

import Main.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vidush H. Namah on 07 Mar 2016.
 **/

public class HomeCard extends JPanel {

    public HomeCard(LayoutManager G) {
        super(G);
        GridBagConstraints C = new GridBagConstraints();

        this.add(new JLabel("HOME", JLabel.CENTER));
    }

    public static void loadToolbar() {
        //HOME PAGE: NO TOOL BAR
        GUI.Tools.removeAll();
        GUI.Tools.setVisible(false);

        GUI.Tools.repaint();
    }
}
