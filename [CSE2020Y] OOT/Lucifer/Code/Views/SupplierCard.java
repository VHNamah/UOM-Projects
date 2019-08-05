package Views;

import Helpers.EButton;
import Helpers.ETextField;
import Main.GUI;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Created by Vidush H. Namah on 07 Mar 2016.
 **/

public class SupplierCard extends JPanel {
    private static ETextField EFId = null;
    private static ETextField EFCompany = null;
    private static ETextField EFAgent = null;
    private static ETextField EFAddress = null;
    private static ETextField EFCity = null;
    private static ETextField EFZip = null;
    private static ETextField EFTel = null;
    private static ETextField EFEmail = null;

    private static ETextField[] EFields;

    private static boolean Edit = false;

    public SupplierCard(LayoutManager G) {
        super(G);

        GridLayout VendorLayout = new GridLayout(2, 1);
        this.setLayout(VendorLayout);

        JPanel TopPanel = new JPanel(new GridLayout(8, 4));

        TitledBorder TPBorder = new TitledBorder("Vendor Details");
        TPBorder.setTitleJustification(TitledBorder.CENTER);
        TopPanel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), TPBorder));

        JPanel BottomPanel = new JPanel(new GridLayout());

        TitledBorder BPBorder = new TitledBorder("Current Vendor(s)");
        BPBorder.setTitleJustification(TitledBorder.CENTER);
        BottomPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 10, 10, 10), BPBorder));

        Dimension size = new Dimension(120, 30);
        GridBagConstraints C = new GridBagConstraints();

        JLabel LId = new JLabel("Vendor ID", JLabel.LEFT);
        JLabel LCompany = new JLabel("Company Name", JLabel.LEFT);
        JLabel LAgent = new JLabel("Agent Name", JLabel.LEFT);
        JLabel LAddress = new JLabel("Address", JLabel.LEFT);
        JLabel LCity = new JLabel("City", JLabel.LEFT);
        JLabel LZip = new JLabel("Zip", JLabel.LEFT);
        JLabel LTel = new JLabel("Contact Number", JLabel.LEFT);
        JLabel LEmail = new JLabel("Contact Email", JLabel.LEFT);

        JLabel[] Labels = {LId, LCompany, LAgent, LAddress, LCity, LZip, LTel, LEmail};

        EFId = new ETextField(Edit, "V001", 30);
        EFCompany = new ETextField(Edit, "The Company", 30);
        EFAgent = new ETextField(Edit, "47", 30);
        EFAddress = new ETextField(Edit, "Royal Road", 30);
        EFCity = new ETextField(Edit, "Phoenix", 30);
        EFZip = new ETextField(Edit, "73000", 30);
        EFTel = new ETextField(Edit, "666", 30);
        EFEmail = new ETextField(Edit, "47@666.com", 30);

        ETextField[] EFTemp = {EFId, EFCompany, EFAgent, EFAddress, EFCity, EFZip, EFTel, EFEmail};
        EFields = EFTemp;

        for(JLabel 		label: Labels)	label.setPreferredSize(size);
        for(ETextField 	field: EFields)	field.setPreferredSize(size);

        for(int i=0; i<Labels.length; i++){
            C.gridy = i;

            C.gridx = 0;
            TopPanel.add(Labels[i], C);
            C.gridx = 1;
            TopPanel.add(EFields[i], C);

            Labels[i].setFont(new Font("Jokerman", Font.ITALIC, 15));//TODO remove this joke
            Labels[i].setBorder(new EmptyBorder(0, 100, 0, 0));
        }

        BottomPanel.add(new JLabel("Table with Database records", JLabel.CENTER));

        this.add(TopPanel);
        this.add(BottomPanel);
    }

    public static void VendorModify(){
        Edit = true;

        for(int i=1; i<EFields.length; i++)//leaving EFId unchanged
            EFields[i].setEditFlag(Edit);
    }

    public static void VendorSave(){
        Edit = false;

        for(ETextField field: EFields)
            field.setEditFlag(Edit);
    }

    public static void VendorAdd(){
        Edit = true;

        for(ETextField field: EFields)
            field.setEditFlag(Edit);

        //Getting next ID. If current ID is V665, next ID becomes V666 - still changeable
        String NewId = EFId.getText();
        NewId = NewId.equals("")? "999": NewId.substring(1);
        int NextId = Integer.parseInt(NewId) % 999;
        NewId = "V" + new DecimalFormat("000").format(++NextId);

        for(ETextField field: EFields)
            field.setText("");

        EFId.setText(NewId);//override EFId with new possible ID
    }

    public static void VendorDelete(){
        for(ETextField field: EFields)
            field.setText("");
    }

    public static void loadToolbar() {
        Dimension Size = GUI.Tools.Size;

        GUI.Tools.removeAll();
        GUI.Tools.setVisible(true);


        EButton BTNAdd = new EButton("A");
        EButton BTNDelete = new EButton("D");
        EButton BTNModify = new EButton("M");
        EButton BTNSave = new EButton("S");

        BTNSave.setVisible(false);

        BTNAdd.setPreferredSize(Size);
        BTNDelete.setPreferredSize(Size);
        BTNModify.setPreferredSize(Size);
        BTNSave.setPreferredSize(Size);

        BTNAdd.addActionListener(e -> {
            SupplierCard.VendorAdd();
            BTNAdd.setEnabled(false);
            BTNDelete.setEnabled(false);
            BTNModify.setEnabled(false);
            BTNSave.setVisible(true);
        });

        BTNDelete.addActionListener(e -> {
            SupplierCard.VendorDelete();
        });

        BTNModify.addActionListener(e -> {
            SupplierCard.VendorModify();
            BTNAdd.setEnabled(false);
            BTNDelete.setEnabled(false);
            BTNModify.setEnabled(false);
            BTNSave.setVisible(true);
        });

        BTNSave.addActionListener(e -> {
            SupplierCard.VendorSave();
            BTNAdd.setEnabled(true);
            BTNDelete.setEnabled(true);
            BTNModify.setEnabled(true);
            BTNSave.setVisible(false);
        });

        GUI.Tools.add(BTNAdd);
        GUI.Tools.add(BTNDelete);
        GUI.Tools.add(BTNModify);
        GUI.Tools.add(BTNSave);

        GUI.Tools.repaint();
    }
}
