package Views;

import Main.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vidush H. Namah on 07 Mar 2016.
 **/

public class StockCard extends JPanel {


    private JPanel StockHome = null;
    private JPanel StockLevel = null;
    private JPanel StockOrdered = null;
    private JPanel StockReceived = null;


    public StockCard(LayoutManager G) {
        super(G);
        GridBagConstraints C = new GridBagConstraints();

        this.add(new JLabel("STOCK", JLabel.CENTER));

    }

    public JPanel StockHome(){


        return null;
    }

    public JPanel StockLevel(){


        return null;
    }

    public JPanel StockOrdered(){


        return null;
    }

    public JPanel StockReceived(){


        return null;
    }

    public static void loadToolbar() {
        GUI.Tools.removeAll();
        GUI.Tools.setVisible(true);

        GUI.Tools.repaint();
    }
}
