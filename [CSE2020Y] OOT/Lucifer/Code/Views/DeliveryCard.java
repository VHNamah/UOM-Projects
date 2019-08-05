package Views;

import Main.GUI;

import javax.swing.*;

import Helpers.Constants;
import Helpers.EButton;

import java.awt.*;

public class DeliveryCard extends JPanel {

    public DeliveryCard(LayoutManager G) {
        super(G);
        GridBagConstraints C = new GridBagConstraints();

        this.add(new JLabel("DELIVERY", JLabel.CENTER));
    }

    public static void loadToolbar() {
        GUI.Tools.removeAll();
        GUI.Tools.setVisible(true);
        
        EButton BTNBack = new EButton("Home");
        BTNBack.setPreferredSize(new Dimension(68, 64));
        BTNBack.addActionListener(e -> {
        	StockCard.loadToolbar();
            CardLayout Card = (CardLayout) Constants.CardDeck.getLayout();
            Card.show(Constants.CardDeck, Constants.StockCardID);
        });
        
        GUI.Tools.add(BTNBack);
        
        GUI.Tools.repaint();
    }
}
