package Views;

import Helpers.EButton;
import Main.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vidush H. Namah on 07 Mar 2016.
 **/

public class ProductCard extends JPanel {

    public ProductCard(LayoutManager G) {
        super(G);
        GridBagConstraints C = new GridBagConstraints();

        this.add(new JLabel("PRODUCT", JLabel.CENTER));
    }

    public static void loadToolbar() {
        GUI.Tools.removeAll();
        GUI.Tools.setVisible(true);

        EButton BTNAdd = new EButton("Y");
        EButton BTNDelete = new EButton("Y");
        EButton BTNModify = new EButton("Y");

        Dimension Size = new Dimension(48,48);

        BTNAdd.setPreferredSize(Size);
        BTNDelete.setPreferredSize(Size);
        BTNModify.setPreferredSize(Size);

        GUI.Tools.add(BTNAdd);
        GUI.Tools.add(BTNDelete);
        GUI.Tools.add(BTNModify);

        GUI.Tools.repaint();
    }
}
