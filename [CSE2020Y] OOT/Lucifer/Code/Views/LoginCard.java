package Views;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import Controllers.CTAccount;
import Helpers.*;
import Main.*;

public class LoginCard extends JPanel {

    private static JLabel Message = new JLabel("", JLabel.CENTER);

    private static TextBox TXTUsername = new TextBox("USER ID",32,JTextField.CENTER);
    private static PasswordBox TXTPassword = new PasswordBox("PASSWORD",32,JPasswordField.CENTER);

    private static EButton BTNLogin = new EButton("LOGIN");

    private static EButton BTNRefresh = new EButton("REFRESH");

    private String UserID;
    private int UserAuth;

    public String getUserID() {
        return UserID;
    }

    public int getUserAuth() {
        return UserAuth;
    }

    private static int Trials = 5;

	public LoginCard(LayoutManager G) {
		super(G);

		GridBagConstraints C = new GridBagConstraints();

        //TODO: Change Messages

        Message.setPreferredSize(new Dimension(350,40));
		TXTUsername.setPreferredSize(new Dimension(0,40));
		TXTPassword.setPreferredSize(new Dimension(0,40));

        TXTPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent K) {
                if (K.getKeyCode()==KeyEvent.VK_ENTER) Login();
            }
        });

        //CUSTOMIZING LOGIN OPTION
        BTNLogin.setPreferredSize(new Dimension(355,40));
        BTNLogin.addActionListener(e -> {
            Login();
        });


        BTNRefresh.setPreferredSize(new Dimension(128,40));
        BTNRefresh.addActionListener(e -> {
            BTNRefresh.setVisible(false);
            Message.setText("CONNECTING TO DATABASE...");
            connectSQLServer();
        });

        TXTUsername.setVisible(false);
        TXTPassword.setVisible(false);
        BTNLogin.setVisible(false);
        BTNRefresh.setVisible(false);

        //PLACING MESSAGE
        C.gridy=0;
        C.gridx=0;
        C.gridwidth=2;
        this.add(Message, C);

        //AFTER CONNECTION - PLACING REMAINING COMPONENTS
		C.gridy++;
        this.add(BTNRefresh, C);
		this.add(TXTUsername, C);

		C.gridy++;
		this.add(TXTPassword, C);

		C.gridy++;
		this.add(BTNLogin, C);

        Message.setText("CONNECTING TO DATABASE...");
	}

    public void Login() {
        if (Trials==0) {
            //NO MORE TRIALS
            TXTUsername.setVisible(false);
            TXTPassword.setVisible(false);
            BTNLogin.setVisible(false);

            Message.setText("YOU HAVE EXCEEDED YOUR TRIAL LIMIT.");
        } else {
            try {
                int TempAuth = CTAccount.validateLogin(TXTUsername.getText(), String.valueOf(TXTPassword.getPassword()));
                if (TempAuth!=-1) {
                    //LOGIN SUCCESSFUL
                    this.UserID = TXTUsername.getText();
                    this.UserAuth = TempAuth;

                    this.setVisible(false);
                    Main.UI.initCardDeck();
                } else {
                    //LOGIN INVALID
                    Message.setForeground(Color.RED);
                    Message.setText("INVALID CREDENTIALS: TRY AGAIN [" + (--Trials) + " LEFT].");
                    TXTUsername.initPlaceholder();
                    TXTPassword.initPlaceholder();

                    if (Trials==0) {
                        TXTUsername.setVisible(false);
                        TXTPassword.setVisible(false);
                        BTNLogin.setVisible(false);

                        Message.setText("YOU HAVE EXCEEDED YOUR TRIAL LIMIT.");
                    }
                }
            } catch (Exception E) {
                System.out.println(E);
            }
        }
    }

    public void connectSQLServer() {
        //CONNECTING TO DB

        Thread attemptConnection = new Thread(() -> {
            if (!SQLServer.dbConnect()) {
                Message.setText("CONNECTION FAILED");
                BTNRefresh.setVisible(true);
            }
            else {
                Message.setText("CONNECTED");
                TXTUsername.setVisible(true);
                TXTPassword.setVisible(true);
                BTNLogin.setVisible(true);
            }
        });
        attemptConnection.start();
    }
}
