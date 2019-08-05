package Views;

import Main.GUI;

import javax.swing.*;
import javax.swing.border.*;

import Helpers.Constants;
import Helpers.EButton;

import java.awt.*;

public class OrderCard extends JPanel {

    public OrderCard(LayoutManager G) {
        super(G);
        
        GridLayout VendorLayout = new GridLayout(2, 1);
        this.setLayout(VendorLayout);

        JPanel TopPanel = new JPanel(new GridBagLayout());

        //TitledBorder TPBorder = new TitledBorder("Order Details");
        //TPBorder.setTitleJustification(TitledBorder.CENTER);
        LineBorder TPBorder = new LineBorder(Color.white);
        TopPanel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 5, 10), TPBorder));

        JPanel BottomPanel = new JPanel(new GridBagLayout());

        TitledBorder BPBorder = new TitledBorder("All Supplier(s)");
        BPBorder.setTitleJustification(TitledBorder.CENTER);
        BottomPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 10, 10, 10), BPBorder));

        this.add(TopPanel);
        this.add(BottomPanel);
        
        
        
        
        JPanel TLPanel = new JPanel();
        TLPanel.setBorder(new LineBorder(Color.blue));
        
        JPanel TRPanel = new JPanel();
        TRPanel.setBorder(new LineBorder(Color.green));

        GridBagConstraints C = new GridBagConstraints();
        C.fill = GridBagConstraints.BOTH;
        C.weightx = 0.7;
        C.weighty = 1;
        
        C.gridx = 0;
        C.gridy = 0;
        TopPanel.add(TLPanel, C);
        
        C.gridx = 1;
        C.gridy = 0;
        C.weightx = 0.3;
        TopPanel.add(TRPanel, C);
        

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
