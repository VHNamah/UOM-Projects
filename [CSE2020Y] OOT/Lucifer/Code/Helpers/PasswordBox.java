package Helpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PasswordBox extends JPasswordField implements FocusListener {

    private String phValue=null;
    private boolean isModified=false;

    //SETS PLACEHOLDER
    public void initPlaceholder() {
        //Set the placeholder in a light shade of grey
        this.setForeground(new Color(211,211,211));
        this.setText(this.phValue);

        this.setEchoChar((char) 0);

        //Reset the flag
        this.isModified = false;
    }

	public PasswordBox(String S) {
		super(S);

        this.phValue=S;
        this.initPlaceholder();

        addFocusListener(this);
	}

	public PasswordBox(String S, int C) {
        super(S,C);

        this.phValue=S;
        this.initPlaceholder();

        addFocusListener(this);
	}

    public PasswordBox(String S, int C, int A) {
        super(S,C);
        this.setHorizontalAlignment(A);

        this.phValue=S;
        this.initPlaceholder();

        addFocusListener(this);
	}

    @Override
    public void focusGained(FocusEvent e) {
        if (!isModified) {
            //Prepare the empty field for input
            this.isModified=true;
            this.setText("");
            this.setEchoChar('*');
            this.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getPassword().length==0) {
            //Empty field
            this.isModified=false;
            this.initPlaceholder();
        }
    }
}
