package Views;

import Controllers.CTAccount;
import Controllers.CTEmployee;
import Helpers.*;
import Main.*;
import Models.Account;
import Models.Employee;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Vidush H. Namah on 07 Mar 2016.
 **/

public class EmployeeCard extends JPanel {

    private static ETextField TXTId = null;
    private static ETextField TXTEmail = null;
    private static ETextField TXTLName = null;
    private static ETextField TXTFName = null;
    private static ETextField TXTAddress = null;
    private static ETextField TXTGender = null;
    private static ETextField TXTPhone = null;
    private static ESpinner TXTDBirth = null;
    private static ESpinner TXTDJoined = null;
    private static ESpinner TXTSalary = null;

    private static ArrayList<Employee> LOCALEmployee = new ArrayList<>();
    private static Employee Current = null;

    private static JPanel PANView = new JPanel();
    private static JPanel PANDetails = new JPanel(new GridBagLayout());
    private static JPanel PANTable = new JPanel(new GridLayout());
    private static JPanel PANGenders = new JPanel(new FlowLayout());
    private static ButtonGroup RDOGender = new ButtonGroup();
    private static EButton BTNBarcode = new EButton("SCAN BARCODE");

    private static DefaultTableModel TBLModel = null;
    private static JTable TBLRecords = null;

    private static MouseAdapter TBLMouseAdapter;
    private static KeyListener TBLKeyAdapter;

    public EmployeeCard(LayoutManager G) {
        super(new BorderLayout());
        PANView.setLayout(G);

        LOCALEmployee = CTEmployee.LISTEmployee;

        GridBagConstraints C = new GridBagConstraints();

        Dimension LBLSize = new Dimension(128, 40);
        Dimension TXTSize = new Dimension(232, 30);

        //START OF TOP PANEL
        TitledBorder SETDetails = new TitledBorder("EMPLOYEE DETAILS");
        SETDetails.setTitleJustification(TitledBorder.CENTER);
        PANDetails.setBorder(new CompoundBorder(new EmptyBorder(5,10,5,10),SETDetails));

        //ID
        JLabel LBLId = new JLabel("ID: ");
        TXTId = new ETextField(false, 20);
        LBLId.setPreferredSize(LBLSize);
        TXTId.setPreferredSize(TXTSize);
        LBLId.setLabelFor(TXTId);
        TXTId.setValid(true);

        BTNBarcode.setPreferredSize(TXTSize);
        BTNBarcode.addActionListener(e -> {
            //TODO:Call Scan Barcode
            popupBarcode();
        });

        //LAST NAME
        JLabel LBLLName = new JLabel("LAST NAME: ");
        TXTLName = new ETextField(false, 20);
        LBLLName.setPreferredSize(LBLSize);
        TXTLName.setPreferredSize(TXTSize);
        LBLLName.setLabelFor(TXTLName);
        TXTLName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (TXTLName.getText().length()==0) {
                    TXTLName.setValid(false);
                    TXTLName.setToolTipText("INVALID: Cannot be blank");
                } else if (TXTLName.getText().length()>30) {
                    TXTLName.setValid(false);
                    TXTLName.setToolTipText("INVALID: Cannot exceed 30 Letters");
                } else if (!TXTLName.getText().matches("[a-z A-Z]+")) {
                    TXTLName.setValid(false);
                    TXTLName.setToolTipText("INVALID: Incorrect email address");
                }
            }
        });

        //FIRST NAME
        JLabel LBLFName = new JLabel("FIRST NAME: ");
        TXTFName = new ETextField(false, 20);
        LBLFName.setPreferredSize(LBLSize);
        TXTFName.setPreferredSize(TXTSize);
        LBLFName.setLabelFor(TXTFName);
        TXTFName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (TXTFName.getText().length()==0) {
                    TXTFName.setValid(false);
                    TXTFName.setToolTipText("INVALID: Cannot be blank");
                } else if (TXTFName.getText().length()>30) {
                        TXTFName.setValid(false);
                        TXTFName.setToolTipText("INVALID: Cannot exceed 30 Letters");
                } else if (!TXTFName.getText().matches("[a-z A-Z]+")) {
                    TXTFName.setValid(false);
                    TXTFName.setToolTipText("INVALID: Letters only");
                }
            }
        });

        //POSITION
        JLabel LBLEmail = new JLabel("EMAIL: ");
        TXTEmail = new ETextField(false, 20);
        LBLEmail.setPreferredSize(LBLSize);
        TXTEmail.setPreferredSize(TXTSize);
        LBLEmail.setLabelFor(TXTEmail);
        TXTEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {

                if (TXTEmail.getText().length()==0) {
                    TXTEmail.setValid(false);
                    TXTEmail.setToolTipText("INVALID: Cannot be blank");
                } else if (TXTEmail.getText().length()>50) {
                    TXTEmail.setValid(false);
                    TXTEmail.setToolTipText("INVALID: Cannot exceed 50 Characters");
                } else {
                    try {
                        InternetAddress Email = new InternetAddress(TXTEmail.getText());
                        Email.validate();
                    } catch (AddressException ex) {
                        TXTEmail.setValid(false);
                        TXTEmail.setToolTipText("INVALID: Incorrect Email format");
                    }
                }
            }
        });

        //ADDRESS
        JLabel LBLAddress = new JLabel("ADDRESS: ");
        TXTAddress = new ETextField(false, 20);
        LBLAddress.setPreferredSize(LBLSize);
        TXTAddress.setPreferredSize(TXTSize);
        LBLAddress.setLabelFor(TXTAddress);
        TXTAddress.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (TXTAddress.getText().length()==0) {
                    TXTAddress.setValid(false);
                    TXTAddress.setToolTipText("INVALID: Cannot be blank");
                } else if (TXTAddress.getText().length()>50) {
                    TXTAddress.setValid(false);
                    TXTAddress.setToolTipText("INVALID: Cannot exceed 50 Characters");
                }
            }
        });

        //GENDER
        JLabel LBLGender = new JLabel("GENDER: ");
        TXTGender = new ETextField(false, 20);
        LBLGender.setPreferredSize(LBLSize);
        TXTGender.setPreferredSize(TXTSize);
        LBLGender.setLabelFor(TXTGender);

        JRadioButton RDOMale = new JRadioButton("Male");
        RDOMale.setMnemonic(KeyEvent.VK_M);
        RDOMale.setSelected(true);

        JRadioButton RDOFemale = new JRadioButton("Female");
        RDOFemale.setMnemonic(KeyEvent.VK_F);

        RDOGender.add(RDOMale);
        RDOGender.add(RDOFemale);

        //TELEPHONE
        JLabel LBLTel = new JLabel("CONTACT: ");
        TXTPhone = new ETextField(false, 20);
        LBLTel.setPreferredSize(LBLSize);
        TXTPhone.setPreferredSize(TXTSize);
        LBLTel.setLabelFor(TXTPhone);
        TXTPhone.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (TXTPhone.getText().length()==0) {
                    TXTPhone.setValid(false);
                    TXTPhone.setToolTipText("INVALID: Cannot be blank");
                } else if (TXTPhone.getText().matches("[0-9]+}")) {
                    TXTPhone.setValid(false);
                    TXTPhone.setToolTipText("INVALID: Numbers only");
                } else if (TXTPhone.getText().length()<7) {
                    TXTPhone.setValid(false);
                    TXTPhone.setToolTipText("INVALID: Cannot be less that 7-digits long");
                } else if (TXTPhone.getText().length()>8) {
                    TXTPhone.setValid(false);
                    TXTPhone.setToolTipText("INVALID: Cannot exceed 8-digits");
                }
            }
        });

        //DOB
        JLabel LBLDBirth = new JLabel("DATE OF BIRTH: ");
        TXTDBirth = new ESpinner(new SpinnerDateModel());
        LBLDBirth.setPreferredSize(LBLSize);
        TXTDBirth.setPreferredSize(TXTSize);
        LBLDBirth.setLabelFor(TXTDBirth);

        TXTDBirth.setValid(true);
        TXTDBirth.addChangeListener(e -> {
            TXTDBirth.setValid(true);
            SimpleDateFormat DFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat YFormat = new SimpleDateFormat("yyyy");
            Date Today = new Date();

            if (((Date) TXTDBirth.getValue()).getTime()>Today.getTime()) {
                TXTDBirth.setValid(false);
                TXTDBirth.setToolTipText("INVALID: Cannot be after " + DFormat.format(Today) + " (Today)");
            } else if (Integer.valueOf(YFormat.format(Today))-Integer.valueOf(YFormat.format((Date) TXTDBirth.getValue()))<18) {
                TXTDBirth.setValid(false);
                TXTDBirth.setToolTipText("INVALID: Employee is not of age");
            }
        });

        //DATE JOINED
        JLabel LBLDJoined = new JLabel("DATE JOINED: ");
        TXTDJoined = new ESpinner(new SpinnerDateModel());
        LBLDJoined.setPreferredSize(LBLSize);
        TXTDJoined.setPreferredSize(TXTSize);
        LBLDJoined.setLabelFor(TXTDJoined);
        TXTDJoined.setValid(true);

        //SALARY
        JLabel LBLSalary = new JLabel("SALARY: ");
        TXTSalary = new ESpinner(new SpinnerNumberModel(0, 0, Double.MAX_VALUE, 0.01));
        LBLSalary.setPreferredSize(LBLSize);
        TXTSalary.setPreferredSize(TXTSize);
        LBLSalary.setLabelFor(TXTSalary);
        TXTSalary.setValid(true);

        //PLACING COMPONENTS
        C.gridx=0;
        C.gridy=0;

        PANDetails.add(LBLId, C);

        C.gridy++;
        PANDetails.add(LBLLName, C);

        C.gridy++;
        PANDetails.add(LBLFName, C);

        C.gridy++;
        PANDetails.add(LBLGender, C);

        C.gridy++;
        PANDetails.add(LBLEmail, C);

        C.gridx+=2;
        C.gridy=0;
        C.insets=new Insets(0,20,0,0);

        PANDetails.add(LBLAddress, C);

        C.gridy++;
        PANDetails.add(LBLTel, C);

        C.gridy++;
        PANDetails.add(LBLDBirth, C);

        C.gridy++;
        PANDetails.add(LBLDJoined, C);

        C.gridy++;
        PANDetails.add(LBLSalary, C);

        C.gridx=1;
        C.gridy=0;
        C.insets=new Insets(0,0,0,0);

        PANDetails.add(TXTId, C);
        PANDetails.add(BTNBarcode, C);
        BTNBarcode.setVisible(false);

        C.gridy++;
        PANDetails.add(TXTLName, C);

        C.gridy++;
        PANDetails.add(TXTFName, C);

        C.gridy++;
        PANDetails.add(TXTGender, C);

        PANGenders.add(RDOMale);
        PANGenders.add(RDOFemale);
        PANDetails.add(PANGenders, C);
        PANGenders.setVisible(false);

        C.gridy++;
        PANDetails.add(TXTEmail, C);

        C.gridx+=2;
        C.gridy=0;
        PANDetails.add(TXTAddress, C);

        C.gridy++;
        PANDetails.add(TXTPhone, C);

        C.gridy++;
        PANDetails.add(TXTDBirth, C);

        C.gridy++;
        PANDetails.add(TXTDJoined, C);

        C.gridy++;
        PANDetails.add(TXTSalary, C);

        PANView.add(PANDetails);
        //END OF TOP PANEL

        //START OF BOTTOM PANEL
        TitledBorder SETTable = new TitledBorder("DATABASE RECORDS");
        SETTable.setTitleJustification(TitledBorder.CENTER);
        PANTable.setBorder(new CompoundBorder(new EmptyBorder(5,10,5,10),SETTable));

        TBLModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TBLModel.setColumnIdentifiers(new String[] {"ID", "Last Name", "First Name(s)", "Email"});

        populateModel(LOCALEmployee);

        TBLRecords = new JTable(TBLModel);
        TBLRecords.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TBLRecords.setColumnSelectionAllowed(false);
        TBLRecords.setRowHeight(30);

        Current=LOCALEmployee.get(0);
        setEmployee(Current);
        TBLRow=0;
        TBLRecords.getSelectionModel().setSelectionInterval(0,0);

        //SETTING TABLE LISTENERS
        TBLMouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent E) {
                int Row = TBLRecords.getSelectedRow();
                Current=LOCALEmployee.get(Row);
                setEmployee(Current);
                TBLRow=Row;
            }
        };

        TBLKeyAdapter = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent E) {
                if (E.getKeyCode()==KeyEvent.VK_UP || E.getKeyCode()==KeyEvent.VK_DOWN) {
                    int Row = TBLRecords.getSelectedRow();
                    Current=LOCALEmployee.get(Row);
                    setEmployee(Current);
                    TBLRow=Row;
                }
            }
        };

        TBLRecords.addKeyListener(TBLKeyAdapter);
        TBLRecords.addMouseListener(TBLMouseAdapter);
        //END OF BOTTOM PANEL

        JScrollPane PANRecords = new JScrollPane(TBLRecords);
        PANRecords.setBorder(new EmptyBorder(10,20,20,20));
        PANTable.add(PANRecords);
        PANView.add(PANTable);

        this.add(PANView);
    }

    public static void initCard() {
        LOCALEmployee=CTEmployee.LISTEmployee;
        Current=LOCALEmployee.get(0);
        populateModel(LOCALEmployee);
    }

    //LOAD DETAILS OF CURRENT EMPLOYEE
    private static void setEmployee(Employee E) {
        TXTId.setText(E.getID());
        TXTLName.setText(E.getLName());
        TXTFName.setText(E.getFName());
        TXTGender.setText(E.getGender()=='F' ? "Female" : "Male");
        TXTEmail.setText(E.getEmail());
        TXTAddress.setText(E.getAddress());
        TXTPhone.setText(String.valueOf(E.getPhone()));
        TXTDBirth.setValue(E.getDOB());
        TXTDJoined.setValue(E.getDateJoined());
        TXTSalary.setValue(E.getSalary());
    }

    //SAVE DETAILS OF EMPLOYEE TO OBJECT
    private static void getEmployee(Employee E) {
        E.setID(TXTId.getText());
        E.setLName(TXTLName.getText());
        E.setFName(TXTFName.getText());
        E.setGender((char) RDOGender.getSelection().getMnemonic());
        E.setEmail(TXTEmail.getText());
        E.setAddress(TXTAddress.getText());
        E.setPhone(Integer.valueOf(TXTPhone.getText()));
        E.setDOB((Date) TXTDBirth.getValue());
        E.setDateJoined((Date) TXTDJoined.getValue());
        E.setSalary((Double) TXTSalary.getValue());
    }

    //LOAD TABLE MODEL
    public static void populateModel(ArrayList<Employee> EList) {
        int I=0;
        TBLModel.setRowCount(I);

        for (Employee E : EList) {
            String ID = E.getID();
            String LName = E.getLName();
            String FName = E.getFName();
            String Email = E.getEmail();

            TBLModel.addRow(new String[]{ID, LName, FName, Email});

            //SETTING THE NEXT ROW SELECTION
            if(Current!=null && Current.compareTo(EList.get(I))==0)
                TBLRecords.getSelectionModel().setSelectionInterval(0,I);

            I++;
        }
    }

    //START EMPLOYEE TOOLBAR
    //INITIALIZING BUTTONS
    private static EButton BTNAdd = new EButton();
    private static EButton BTNDelete = new EButton();
    private static EButton BTNModify = new EButton();
    private static EButton BTNSave = new EButton();
    private static EButton BTNAccount = new EButton();
    private static EButton BTNCancel = new EButton();
    private static EButton BTNFirst = new EButton();
    private static EButton BTNPrevious = new EButton();
    private static EButton BTNFind = new EButton();
    private static EButton BTNNext = new EButton();
    private static EButton BTNLast = new EButton();

    private static int TBLRow;
    private static boolean FLAGNew;

    public static void editMode(boolean Flag) {
        //ADJUSTING TEXT FIELDS FOR EDIT
        if(FLAGNew) {
            TXTId.setVisible(!Flag);
            BTNBarcode.setVisible(Flag);
        } else {
            TXTId.setVisible(true);
            BTNBarcode.setVisible(false);
        }

        TXTLName.setEditFlag(Flag);
        TXTFName.setEditFlag(Flag);

        TXTGender.setVisible(!Flag);
        PANGenders.setVisible(Flag);

        TXTEmail.setEditFlag(Flag);
        TXTAddress.setEditFlag(Flag);
        TXTPhone.setEditFlag(Flag);
        TXTDBirth.setEditFlag(Flag);
        TXTSalary.setEditFlag(Flag);

        //ADJUSTING BUTTONS FOR EDIT
        BTNAdd.setVisible(!Flag);
        BTNDelete.setVisible(!Flag);
        BTNAccount.setVisible(!Flag);
        BTNModify.setVisible(!Flag);

        //SETTING SAVE AND CANCEL
        BTNSave.setVisible(Flag);
        BTNCancel.setVisible(Flag);

        //SETTING NAVIGATION OFF
        BTNFirst.setVisible(!Flag);
        BTNPrevious.setVisible(!Flag);
        BTNFind.setVisible(!Flag);
        BTNNext.setVisible(!Flag);
        BTNLast.setVisible(!Flag);

        if (Flag) {
            TBLRecords.removeKeyListener(TBLKeyAdapter);
            TBLRecords.removeMouseListener(TBLMouseAdapter);
        } else {
            TBLRecords.addKeyListener(TBLKeyAdapter);
            TBLRecords.addMouseListener(TBLMouseAdapter);
        }
    }

    public static void loadToolbar() {
        Dimension Size = Toolbar.Size;

        GUI.Tools.removeAll();
        GUI.Tools.setVisible(true);
        GUI.Tools.setLayout(new BorderLayout());

        BTNAdd = new EButton();
        BTNDelete = new EButton();
        BTNModify = new EButton();
        BTNSave = new EButton();
        BTNAccount = new EButton();
        BTNCancel = new EButton();
        BTNFirst = new EButton();
        BTNPrevious = new EButton();
        BTNFind = new EButton();
        BTNNext = new EButton();
        BTNLast = new EButton();

        BTNAdd.setPreferredSize(Size);
        BTNDelete.setPreferredSize(Size);
        BTNModify.setPreferredSize(Size);
        BTNSave.setPreferredSize(Size);
        BTNAccount.setPreferredSize(Size);
        BTNCancel.setPreferredSize(Size);

        BTNFirst.setPreferredSize(Size);
        BTNPrevious.setPreferredSize(Size);
        BTNFind.setPreferredSize(Size);
        BTNNext.setPreferredSize(Size);
        BTNLast.setPreferredSize(Size);

        BTNAdd.setToolTipText("ADD");
        BTNDelete.setToolTipText("REMOVE");
        BTNModify.setToolTipText("EDIT");
        BTNSave.setToolTipText("SAVE");
        BTNAccount.setToolTipText("ACCOUNT SETTINGS");
        BTNCancel.setToolTipText("CANCEL");

        BTNFirst.setToolTipText("FIRST");
        BTNPrevious.setToolTipText("PREVIOUS");
        BTNFind.setToolTipText("SEARCH");
        BTNNext.setToolTipText("NEXT");
        BTNLast.setToolTipText("LAST");

        BTNAdd.setIcon(new ImageIcon(Main.class.getResource("/Resources/UserAdd.png")));
        BTNDelete.setIcon(new ImageIcon(Main.class.getResource("/Resources/UserRemove.png")));
        BTNModify.setIcon(new ImageIcon(Main.class.getResource("/Resources/UserEdit.png")));
        BTNSave.setIcon(new ImageIcon(Main.class.getResource("/Resources/UserSave.png")));
        BTNCancel.setIcon(new ImageIcon(Main.class.getResource("/Resources/UserCancel.png")));
        BTNAccount.setIcon(new ImageIcon(Main.class.getResource("/Resources/UserAccount.png")));

        BTNFirst.setIcon(new ImageIcon(Main.class.getResource("/Resources/First.png")));
        BTNPrevious.setIcon(new ImageIcon(Main.class.getResource("/Resources/Previous.png")));
        BTNFind.setIcon(new ImageIcon(Main.class.getResource("/Resources/Find.png")));
        BTNNext.setIcon(new ImageIcon(Main.class.getResource("/Resources/Next.png")));
        BTNLast.setIcon(new ImageIcon(Main.class.getResource("/Resources/Last.png")));

        //NAVIGATION OPTIONS
        BTNNext.addActionListener(e -> {
            if (TBLRow<TBLModel.getRowCount()-1) {
                TBLRow++;
                Current=LOCALEmployee.get(TBLRow);
                setEmployee(Current);
                TBLRecords.getSelectionModel().setSelectionInterval(0,TBLRow);
            }
        });

        BTNPrevious.addActionListener(e -> {
            if (TBLRow>0) {
                TBLRow--;
                Current=LOCALEmployee.get(TBLRow);
                setEmployee(Current);
                TBLRecords.getSelectionModel().setSelectionInterval(0,TBLRow);
            }
        });

        BTNFirst.addActionListener(e -> {
            TBLRow=0;
            Current = LOCALEmployee.get(TBLRow);
            setEmployee(Current);
            TBLRecords.getSelectionModel().setSelectionInterval(0, TBLRow);
        });

        BTNLast.addActionListener(e -> {
            TBLRow=TBLRecords.getRowCount()-1;
            Current = LOCALEmployee.get(TBLRow);
            setEmployee(Current);
            TBLRecords.getSelectionModel().setSelectionInterval(0, TBLRow);
        });

        BTNSave.setVisible(false);
        BTNCancel.setVisible(false);

        //DATA MANAGEMENT
        BTNAdd.addActionListener(e -> {
            TXTLName.setValid(false);
            TXTFName.setValid(false);
            TXTAddress.setValid(false);
            TXTEmail.setValid(false);
            TXTPhone.setValid(false);
            TXTDBirth.setValid(false);
            TXTSalary.setValid(false);

            FLAGNew=true;
            Employee Buffer=new Employee();
            TBLRow = TBLRecords.getSelectedRow();
            TBLRecords.getSelectionModel().setSelectionInterval(0,TBLModel.getRowCount());
            TBLModel.addRow(new String[] {"","","",""});

            setEmployee(Buffer);
            EmployeeCard.editMode(true);
        });

        //PROGRAMMING LISTENERS
        BTNModify.addActionListener(e -> {
            EmployeeCard.editMode(true);
            FLAGNew=false;
        });

        BTNDelete.addActionListener(e -> {
            int OPTConfirm = JOptionPane.showOptionDialog(null,
                    "ARE YOU SURE YOU WISH TO DELETE EMPLOYEE "+Current.getID()+" "+Current.getLName()+" "+Current.getFName()+"?",
                    "Confirm Operation",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    new String[] {"OK", "CANCEL"},
                    null);

            if (OPTConfirm==0) {
                try {
                    int ROWCurrent = TBLRecords.getSelectedRow();

                    if (ROWCurrent==TBLRecords.getRowCount()-1) {
                        ROWCurrent--;
                    } else ROWCurrent++;

                    CTAccount.retireEmployee(Current.getID());
                    CTEmployee.fetchEmployees();
                    LOCALEmployee=CTEmployee.LISTEmployee;
                    populateModel(LOCALEmployee);

                    TBLRecords.setRowSelectionInterval(ROWCurrent, 0);
                } catch(Exception E) {
                    E.printStackTrace();
                }
            }
        });

        BTNSave.addActionListener(e -> {
            //VALIDATING INPUT
            boolean Valid = true;
            String ERRFields[] = new String[10];
            int I=0;

            if (!TXTLName.getValid()) {
                Valid=false;
                ERRFields[I++] = "Last Name";
            }

            if (!TXTFName.getValid()) {
                Valid = false;
                ERRFields[I++] = "First Name";
            }

            if (!TXTEmail.getValid()) {
                Valid = false;
                ERRFields[I++] = "Email";
            }

            if (!TXTAddress.getValid()) {
                Valid = false;
                ERRFields[I++] = "Address";
            }

            if (!TXTPhone.getValid()) {
                Valid = false;
                ERRFields[I++] = "Phone";
            }

            if (!TXTDBirth.getValid()) {
                Valid = false;
                ERRFields[I++] = "Date of Birth";
            }

            if (!TXTSalary.getValid()) {
                Valid = false;
                ERRFields[I++] = "Salary";
            }

            if (Valid) {
                //TEMPORARY EMPLOYEE
                Employee Buffer = new Employee();
                getEmployee(Buffer);

                Current=Buffer;
                try {
                    //ADDING OR UPDATING DB ACCORDINGLY
                    if (FLAGNew) {
                        FLAGNew=false;
                        setupAccount();
                    }
                    else {
                        CTEmployee.updateEmployee(Current);
                        CTEmployee.fetchEmployees();
                        LOCALEmployee=CTEmployee.LISTEmployee;
                        populateModel(LOCALEmployee);
                        EmployeeCard.editMode(false);
                    }
                } catch (SQLException E) {
                    E.printStackTrace();
                }
            } else {
                String ERRMessage = "ERROR: Invalid entries detected.\n";

                for (int n=0; n<I; n++) {
                    ERRMessage+="\n- ";
                    ERRMessage+=ERRFields[n];
                }

                JOptionPane.showMessageDialog(null,
                        ERRMessage,
                        "Save Failed",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        BTNCancel.addActionListener(e -> {
            FLAGNew=false;
            populateModel(LOCALEmployee);
            TBLRecords.getSelectionModel().setSelectionInterval(0, TBLRow);
            setEmployee(Current);

            EmployeeCard.editMode(false);
        });

        //ACCOUNT SETTINGS
        BTNAccount.addActionListener(e -> {
            //TODO: Call Barcode FX
            popupAccount();
        });

        TextBox TXTSearch = new TextBox("SEARCH BY ID OR NAME",20);
        ArrayList<Employee> LISTSearch = new ArrayList<>();
        TXTSearch.setPreferredSize(new Dimension(0,40));

        EButton BTNStop = new EButton();
        BTNStop.setIcon(new ImageIcon(Main.class.getResource("/Resources/UserCancel.png")));
        BTNStop.setPreferredSize(Size);

        TXTSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent K) {
                if (K.getKeyCode()==KeyEvent.VK_ESCAPE) {
                    TXTSearch.setText(null);
                    TXTSearch.setVisible(false);
                    BTNStop.setVisible(false);

                    BTNFind.setVisible(true);
                    BTNAdd.setVisible(true);

                    LOCALEmployee=CTEmployee.LISTEmployee;
                    populateModel(LOCALEmployee);

                } else {

                    Thread THSearch = new Thread(() -> {
                        LISTSearch.clear();
                        String Target = TXTSearch.getText();

                        for (Employee E : CTEmployee.LISTEmployee)
                            if (E.getID().contains(Target) || E.getFName().contains(Target) || E.getLName().contains(Target))
                                LISTSearch.add(E);

                        LOCALEmployee=LISTSearch;
                    });
                    THSearch.start();

                    Thread THPopulate = new Thread(() -> {
                        populateModel(LOCALEmployee);
                    });
                    THPopulate.start();
                }
            }
        });

        BTNStop.addActionListener(e -> {
            TXTSearch.setText(null);
            TXTSearch.setVisible(false);
            BTNStop.setVisible(false);

            BTNFind.setVisible(true);
            BTNAdd.setVisible(true);

            LOCALEmployee=CTEmployee.LISTEmployee;
            populateModel(LOCALEmployee);
        });

        TXTSearch.setVisible(false);
        BTNStop.setVisible(false);

        //ADDING COMPONENTS TO TOOLBAR
        JPanel PANLTools = new JPanel(new FlowLayout());
        PANLTools.setBackground(null);
        PANLTools.add(BTNFirst);
        PANLTools.add(BTNPrevious);

        JPanel PANCTools = new JPanel(new FlowLayout());
        PANCTools.setBackground(null);
        PANCTools.add(BTNAdd);
        PANCTools.add(BTNModify);
        PANCTools.add(BTNFind);
        PANCTools.add(BTNDelete);

        PANCTools.add(TXTSearch);
        PANCTools.add(BTNStop);

        PANCTools.add(BTNAccount);
        PANCTools.add(BTNSave);
        PANCTools.add(BTNCancel);

        BTNFind.addActionListener(e -> {
            BTNAdd.setVisible(false);
            BTNFind.setVisible(false);

            TXTSearch.setVisible(true);
            BTNStop.setVisible(true);
        });

        JPanel PANRTools = new JPanel(new FlowLayout());
        PANRTools.setBackground(null);
        PANRTools.add(BTNNext);
        PANRTools.add(BTNLast);

        GUI.Tools.add(PANLTools, BorderLayout.WEST);
        GUI.Tools.add(PANCTools, BorderLayout.CENTER);
        GUI.Tools.add(PANRTools, BorderLayout.EAST);

        GUI.Tools.repaint();
    }
    //END EMPLOYEE
    private static int Permission=0;

    public static void popupAccount() {
        JFrame POPAccount=new JFrame("ACCOUNT");

        Employee E=Current;

        EButton BTNResetPassword = new EButton("RESET PASSWORD");
        EButton BTNEditPosition = new EButton("PROMOTE/DEMOTE EMPLOYEE");

        JLabel LBLEmployee = new JLabel("", JLabel.CENTER);
        JLabel LBLPosition = new JLabel("", JLabel.CENTER);

        JPanel PANAccount = new JPanel(new GridBagLayout());

        GridBagConstraints C = new GridBagConstraints();

        BTNResetPassword.setPreferredSize(new Dimension(256,40));
        BTNEditPosition.setPreferredSize(new Dimension(256,40));

        LBLEmployee.setText(E.getID()+" "+E.getFName()+" "+E.getLName());
        LBLEmployee.setPreferredSize(new Dimension(256,40));
        LBLPosition.setPreferredSize(new Dimension(256,40));

        Permission = E.getEAccount().getPermission();

        switch(Permission) {
            case 1: LBLPosition.setText("CASHIER");
                break;

            case 2: LBLPosition.setText("STORE MANAGER");
                break;

            case 3: LBLPosition.setText("PRODUCT MANAGER");
                break;

            case 4: LBLPosition.setText("SYSTEM ADMINISTRATOR");
                break;
        }

        BTNResetPassword.addActionListener(e -> {
            BTNEditPosition.setVisible(false);
            BTNResetPassword.setVisible(false);

            PasswordBox TXTPassword = new PasswordBox("ENTER NEW PASSWORD", 32);
            TXTPassword.setPreferredSize(new Dimension(0,40));

            C.gridx=0;
            C.gridy=2;
            C.gridwidth=2;
            PANAccount.add(TXTPassword,C);

            PasswordBox TXTConfirm = new PasswordBox("CONFIRM NEW PASSWORD", 32);
            TXTConfirm.setPreferredSize(new Dimension(0,40));
            C.gridy++;
            PANAccount.add(TXTConfirm,C);

            EButton BTNAccept = new EButton("ACCEPT");
            BTNAccept.setPreferredSize(new Dimension(177,40));

            EButton BTNCancel = new EButton("CANCEL");
            BTNCancel.setPreferredSize(new Dimension(178,40));

            BTNAccept.addActionListener(X -> {
                if((String.valueOf(TXTPassword.getPassword()).length()>0) && (String.valueOf(TXTPassword.getPassword()).equals(String.valueOf(TXTConfirm.getPassword())))) {

                    try {
                        CTAccount.updatePassword(E.getID(), String.valueOf(TXTPassword.getPassword()));

                        PANAccount.remove(TXTConfirm);
                        PANAccount.remove(TXTPassword);
                        PANAccount.remove(BTNAccept);
                        PANAccount.remove(BTNCancel);

                        BTNResetPassword.setVisible(true);
                        BTNEditPosition.setVisible(true);

                        try {
                            EMail.sendMail(E.getEmail(), "AUTOMATED EMAIL: Password Reset", "Your account password has been reset.\nPlease contact the administration to get your new login credentials.");
                        } catch (Exception Ex) {
                            JOptionPane.showMessageDialog(null, "Email could not be sent. Check connectivity.", "Error", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (Exception Ex) {
                        Ex.printStackTrace();
                    }
                    POPAccount.repaint();
                } else
                    JOptionPane.showMessageDialog(null, "ERROR: Password confirmation failed.", "Invalid Password", JOptionPane.WARNING_MESSAGE);
            });

            BTNCancel.addActionListener(X -> {
                PANAccount.remove(TXTConfirm);
                PANAccount.remove(TXTPassword);
                PANAccount.remove(BTNAccept);
                PANAccount.remove(BTNCancel);

                BTNResetPassword.setVisible(true);
                BTNEditPosition.setVisible(true);
                POPAccount.repaint();
            });

            C.gridy++;
            C.gridwidth=1;
            C.anchor=C.WEST;
            PANAccount.add(BTNAccept,C);

            C.gridx++;
            C.anchor=C.EAST;
            PANAccount.add(BTNCancel,C);
        });

        BTNEditPosition.addActionListener(e -> {
            JComboBox CBOPosition = new JComboBox();
            CBOPosition.setPreferredSize(new Dimension(300,40));

            for (String S : CTEmployee.ARRPermission)
                CBOPosition.addItem(S);

            CBOPosition.setSelectedIndex(Permission-1);

            BTNEditPosition.setVisible(false);
            BTNResetPassword.setVisible(false);

            C.gridx=0;
            C.gridy=2;
            C.gridwidth=2;
            PANAccount.add(CBOPosition,C);

            EButton BTNAccept = new EButton("ACCEPT");
            BTNAccept.setPreferredSize(new Dimension(150,40));

            EButton BTNCancel = new EButton("CANCEL");
            BTNCancel.setPreferredSize(new Dimension(150,40));

            BTNAccept.addActionListener(X -> {
                try {
                    Permission=CBOPosition.getSelectedIndex()+1;
                    CTAccount.updatePermission(E.getID(), Permission);
                    LBLPosition.setText(String.valueOf(CBOPosition.getItemAt(Permission-1)));

                    PANAccount.remove(CBOPosition);
                    PANAccount.remove(BTNAccept);
                    PANAccount.remove(BTNCancel);

                    BTNResetPassword.setVisible(true);
                    BTNEditPosition.setVisible(true);
                    POPAccount.repaint();
                } catch (Exception Ex) {
                    Ex.printStackTrace();
                }
            });

            BTNCancel.addActionListener(X -> {
                PANAccount.remove(CBOPosition);
                PANAccount.remove(BTNAccept);
                PANAccount.remove(BTNCancel);

                BTNResetPassword.setVisible(true);
                BTNEditPosition.setVisible(true);
                POPAccount.repaint();
            });

            C.gridy++;
            C.gridwidth=1;
            C.anchor= GridBagConstraints.EAST;
            PANAccount.add(BTNAccept,C);

            C.gridx++;
            C.anchor=C.WEST;
            PANAccount.add(BTNCancel,C);
        });

        //PLACING INTO VIEW
        C.gridx=0;
        C.gridy=0;
        C.gridwidth=2;
        PANAccount.add(LBLEmployee,C);

        C.gridy++;
        C.insets=new Insets(0,0,10,0);
        PANAccount.add(LBLPosition,C);

        C.gridy++;
        PANAccount.add(BTNResetPassword,C);

        C.gridy++;
        PANAccount.add(BTNEditPosition,C);

        POPAccount.add(PANAccount);
        POPAccount.setVisible(true);
        POPAccount.setPreferredSize(new Dimension(512,370));
        POPAccount.setResizable(false);

        Point MAINLocation = Main.getMotherOfAllFrames().getLocation();

        POPAccount.setLocation(MAINLocation.x+256, MAINLocation.y+128);
        POPAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        POPAccount.pack();
    }

    public static void setupAccount(){
        Main.getMotherOfAllFrames().setEnabled(false);

        JFrame POPAccount = new JFrame("ACCOUNT");

        JLabel LBLHeader = new JLabel(Current.getID() + " " + Current.getFName() + " " + Current.getLName());
        LBLHeader.setPreferredSize(new Dimension(128,40));

        PasswordBox TXTPassword = new PasswordBox("PASSWORD", 20);
        PasswordBox TXTConfirm = new PasswordBox("CONFIRM PASSWORD", 20);

        TXTPassword.setMargin(new Insets(0,0,5,0));
        TXTPassword.setPreferredSize(new Dimension(128,30));
        TXTConfirm.setMargin(new Insets(5,0,0,0));
        TXTConfirm.setPreferredSize(new Dimension(128,30));

        JComboBox<String> CBOPermission= new JComboBox<>();
        CBOPermission.setPreferredSize(new Dimension(128,40));
        for (String S : CTEmployee.ARRPermission)
            CBOPermission.addItem(S);

        EButton BTNCreate = new EButton("CREATE ACCOUNT");
        BTNCreate.setPreferredSize(new Dimension(128,40));
        BTNCreate.addActionListener(e -> {
            if((String.valueOf(TXTPassword.getPassword()).length()>0) && (String.valueOf(TXTPassword.getPassword()).equals(String.valueOf(TXTConfirm.getPassword())))) {
                try {
                    CTEmployee.insertEmployee(Current);
                    CTAccount.insertAccount(Current.getID(), TXTPassword.getPassword().toString(), CBOPermission.getSelectedIndex()+1);
                    CTEmployee.fetchEmployees();
                    LOCALEmployee=CTEmployee.LISTEmployee;
                    populateModel(LOCALEmployee);
                    Main.getMotherOfAllFrames().setEnabled(true);
                    EmployeeCard.editMode(false);
                    POPAccount.dispose();
                } catch (Exception E) {
                    E.printStackTrace();
                }
            } else
                JOptionPane.showMessageDialog(null, "ERROR: Password confirmation failed.", "Invalid Password", JOptionPane.WARNING_MESSAGE);
        });

        JPanel PANFinalize = new JPanel(new GridLayout(5,1));

        PANFinalize.add(LBLHeader);
        PANFinalize.add(TXTPassword);
        PANFinalize.add(TXTConfirm);
        PANFinalize.add(CBOPermission);
        PANFinalize.add(BTNCreate);

        PANFinalize.setBorder(new EmptyBorder(15, 20, 15, 20));
        POPAccount.add(PANFinalize);

        POPAccount.setVisible(true);
        POPAccount.setSize(640, 640);
        POPAccount.setResizable(false);

        Point MAINLocation = Main.getMotherOfAllFrames().getLocation();

        POPAccount.setLocation(MAINLocation.x+128, MAINLocation.y+128);
        POPAccount.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Main.getMotherOfAllFrames().setEnabled(true);
                POPAccount.dispose();
            }
        });
        POPAccount.pack();
    }

    public static void popupBarcode(){
        JFrame POPBarcode = new JFrame();

        JLabel LBLBarcode = new JLabel("BARCODE");
        ETextField TXTBarcode = new ETextField(true, "");
        TXTBarcode.setPreferredSize(new Dimension(128,30));
        TXTBarcode.setMargin(new Insets(0,0,10,0));

        EButton BTNCreate = new EButton("SAVE");
        BTNCreate.setPreferredSize(new Dimension(128,40));
        BTNCreate.addActionListener(e -> {
            if (TXTBarcode.getValid()) {
                BTNBarcode.setVisible(false);
                TXTId.setVisible(true);
                TXTId.setText(TXTBarcode.getText());
                POPBarcode.dispose();
            } else
                JOptionPane.showMessageDialog(null, "Invalid User ID", "Error", JOptionPane.WARNING_MESSAGE);
        });

        TXTBarcode.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(TXTBarcode.getText().length()!=7) {
                    TXTBarcode.setValid(false);
                    TXTBarcode.setToolTipText("INVALID: Must be 7-Characters long");
                }
            }
        });

        JPanel PANBarcode = new JPanel(new GridLayout(3,1));

        PANBarcode.add(LBLBarcode);
        PANBarcode.add(TXTBarcode);
        PANBarcode.add(BTNCreate);

        PANBarcode.setBorder(new EmptyBorder(15, 20, 15, 20));
        POPBarcode.add(PANBarcode);

        POPBarcode.setVisible(true);
        POPBarcode.setResizable(false);

        Point MAINLocation = Main.getMotherOfAllFrames().getLocation();

        POPBarcode.setLocation(MAINLocation.x+512, MAINLocation.y+256);
        POPBarcode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        POPBarcode.pack();
    }
}
