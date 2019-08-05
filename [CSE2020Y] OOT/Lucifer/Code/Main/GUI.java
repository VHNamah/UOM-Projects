package Main;

/**
 * Created by Vidush H. Namah on 05 Mar 2016.
 **/


import Controllers.CTAccount;
import Controllers.CTEmployee;
import Views.*;
import Helpers.Constants;

import java.awt.*;

import javax.swing.*;

public class GUI extends JPanel {

    private static Hamburger Menu = null;
    private static JPanel View = null;
    public static Toolbar Tools = null;
    private static boolean Instance = false;

    private GUI() {
        super(new BorderLayout());

        //SPLIT SCREEN IN 2 - MENU(LEFT) AND VIEW(RIGHT)
        Menu = new Hamburger();
        View = new JPanel(new BorderLayout());

        //INITIALIZE TOOLBAR AND CARD VIEWER
        Tools = new Toolbar();
        Tools.setVisible(false);
        Constants.CardDeck = new JPanel(new CardLayout());

        //CREATE STACK OF CARDS
        LoginCard LoginLayer = new LoginCard(new GridBagLayout());
        this.add(LoginLayer);

        LoginLayer.connectSQLServer();
    }

    public static GUI initGUI() {
        if(!Instance) {
            Instance = true;
            return new GUI();
        } else {
            JOptionPane.showMessageDialog(null, "ERROR: AN INSTANCE OF LUCIFER IS ALREADY RUNNING.");
            return null;
        }
    }

    public void initCardDeck() {
        //PULLING SERVER DATA
        //TODO: Improve Try-Catch
        try {
            CTEmployee.fetchEmployees();
            CTAccount.fetchPermissions();
        } catch (Exception E) {
            E.printStackTrace();
        }


        //STACK OF CARDS
        HomeCard CDHome = new HomeCard(new GridBagLayout());
        EmployeeCard CDEmployee = new EmployeeCard(new GridLayout(2,1));
        ProductCard CDProduct = new ProductCard(new GridBagLayout());
        StockCard CDStock = new StockCard(new GridBagLayout());
        PoSCard CDPoS = new PoSCard(new GridBagLayout());
        SupplierCard CDSupplier = new SupplierCard(new GridBagLayout());
        ReportCard CDReport = new ReportCard(new GridBagLayout());

        //ADDING CARDS TO DECK
        Constants.CardDeck.add(CDHome, Constants.HomeCardID);
        Constants.CardDeck.add(CDEmployee, Constants.EmployeeCardID);
        Constants.CardDeck.add(CDProduct, Constants.ProductCardID);
        Constants.CardDeck.add(CDStock, Constants.StockCardID);
        Constants.CardDeck.add(CDPoS, Constants.POSCardID);
        Constants.CardDeck.add(CDSupplier, Constants.SupplierCardID);
        Constants.CardDeck.add(CDReport, Constants.ReportCardID);

        //CREATING THE MAIN VIEW
        View.add(Tools, BorderLayout.SOUTH);
        View.add(Constants.CardDeck, null);

        //ADDING EVERYTHING TO GUI
        add(Menu, BorderLayout.WEST);
        add(View, null);
    }
}
