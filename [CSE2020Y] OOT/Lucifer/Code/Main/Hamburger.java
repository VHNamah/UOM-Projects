package Main;

import java.awt.*;

import javax.swing.JPanel;

import Helpers.EButton;
import Helpers.Constants;
import Models.Employee;
import Views.*;

public class Hamburger extends JPanel {

    //TODO: Disable clicked buttons

	private static final EButton BTNHome = new EButton("HOME");
	private static final EButton BTNEmployee = new EButton("EMPLOYEE MANAGEMENT");
	private static final EButton BTNProduct = new EButton("PRODUCT DETAILS");
	private static final EButton BTNStock = new EButton("STOCK CONTROL");
	private static final EButton BTNPoS = new EButton("POS SYSTEM");
	private static final EButton BTNSupplier = new EButton("VENDOR INFORMATION");
	private static final EButton BTNReport = new EButton("GENERATE REPORTS");

    private static final EButton BTNLogout = new EButton("LOG OUT");
	
	public Hamburger() {
		super();
		setBackground(new Color(211,211,211));
		setPreferredSize(new Dimension(200,0));
		
		initHamburger();
	}

    public void initHamburger() {
        Dimension Size = new Dimension(200,48);

        //TODO: HOME MATCHES TO LOGIN JUST FOR TESTING
        //CUSTOMIZING HOME BUTTON
        BTNHome.setPreferredSize(Size);
        BTNHome.addActionListener(e -> {
            EnableBTNs(true);
            BTNHome.setEnabled(false);
            HomeCard.loadToolbar();
            BTNHome.setBackground(Color.CYAN);
            CardLayout Cards = (CardLayout) Constants.CardDeck.getLayout();
            Cards.show(Constants.CardDeck, Constants.HomeCardID);
        });

        //CUSTOMIZING EMPLOYEE BUTTON
        BTNEmployee.setPreferredSize(Size);
        BTNEmployee.addActionListener(e -> {
            EnableBTNs(true);
            BTNEmployee.setEnabled(false);
            EmployeeCard.loadToolbar();
            EmployeeCard.initCard();
            CardLayout Cards = (CardLayout) Constants.CardDeck.getLayout();
            Cards.show(Constants.CardDeck, Constants.EmployeeCardID);
        });

        //CUSTOMIZING PRODUCT BUTTON
        BTNProduct.setPreferredSize(Size);
        BTNProduct.addActionListener(e -> {
            EnableBTNs(true);
            BTNProduct.setEnabled(false);
            ProductCard.loadToolbar();
            CardLayout Cards = (CardLayout) Constants.CardDeck.getLayout();
            Cards.show(Constants.CardDeck, Constants.ProductCardID);
        });

        //CUSTOMIZING STOCK BUTTON
        BTNStock.setPreferredSize(Size);
        BTNStock.addActionListener(e -> {
            EnableBTNs(true);
            BTNStock.setEnabled(false);
            StockCard.loadToolbar();
            CardLayout Cards = (CardLayout) Constants.CardDeck.getLayout();
            Cards.show(Constants.CardDeck, Constants.StockCardID);
        });

        //CUSTOMIZING POS BUTTON
        BTNPoS.setPreferredSize(Size);
        BTNPoS.addActionListener(e -> {
            EnableBTNs(true);
            BTNPoS.setEnabled(false);
            PoSCard.loadToolbar();
            CardLayout Cards = (CardLayout) Constants.CardDeck.getLayout();
            Cards.show(Constants.CardDeck, Constants.POSCardID);
        });

        //CUSTOMIZING VENDORS BUTTON
        BTNSupplier.setPreferredSize(Size);
        BTNSupplier.addActionListener(e -> {
            EnableBTNs(true);
            BTNSupplier.setEnabled(false);
            SupplierCard.loadToolbar();
            CardLayout Cards = (CardLayout) Constants.CardDeck.getLayout();
            Cards.show(Constants.CardDeck, Constants.SupplierCardID);
        });

        //CUSTOMIZING REPORT BUTTON
        BTNReport.setPreferredSize(Size);
        BTNReport.addActionListener(e -> {
            EnableBTNs(true);
            BTNReport.setEnabled(false);
            ReportCard.loadToolbar();
            CardLayout Cards = (CardLayout) Constants.CardDeck.getLayout();
            Cards.show(Constants.CardDeck, Constants.ReportCardID);
        });

        BTNLogout.setPreferredSize(Size);
        BTNLogout.addActionListener(e -> {
            Main.destroy();
        });

        add(BTNHome);
        add(BTNEmployee);
        add(BTNProduct);
        add(BTNStock);
        add(BTNPoS);
        add(BTNSupplier);
        add(BTNReport);

        add(BTNLogout, BorderLayout.SOUTH);

        //ON FIRST RUN
        EnableBTNs(true);
        BTNHome.setEnabled(false);
    }

    public static void EnableBTNs(boolean Flag) {
        BTNHome.setEnabled(Flag);
        BTNEmployee.setEnabled(Flag);
        BTNProduct.setEnabled(Flag);
        BTNStock.setEnabled(Flag);
        BTNPoS.setEnabled(Flag);
        BTNSupplier.setEnabled(Flag);
        BTNReport.setEnabled(Flag);
    }
}
