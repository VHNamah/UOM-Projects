package Helpers;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Created by Vidush H. Namah on 07 Mar 2016.
 **/

public class ETextField extends JTextField implements FocusListener{

    private boolean isEditable = true;
    private Color BGDefault = new Color(80, 80, 80);
    private Color BDBorder = new Color(150,175,255);

    private boolean Valid = true;

    //CONSTRUCTORS
    public ETextField(){
        super(5);
        addFocusListener(this);
        setEditFlag(true);
        setText("");
    }

    public ETextField(String S){
        super(5);
        addFocusListener(this);
        setEditFlag(true);
        setText(S);
    }

    public ETextField(boolean Flag, String S){
        super();
        addFocusListener(this);
        setEditFlag(Flag);
        setText(S);
    }

    public ETextField(boolean Flag, int Col){
        super(Col);
        addFocusListener(this);
        setEditFlag(Flag);
        setText("");
    }

    public ETextField(boolean Flag, String S, int Col){
        super(Col);
        addFocusListener(this);
        setEditFlag(Flag);
        setText(S);
    }

    public void setValid(boolean Flag) {
        Border Margin = new EmptyBorder(0,5,0,5);
        if (isEditable) {
            if (Flag)
                this.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY), Margin));
            else
                this.setBorder(new CompoundBorder(new LineBorder(Color.RED), Margin));

            this.Valid = Flag;
        }
    }

    public boolean getValid() {
        return this.Valid;
    }

    public void setEditFlag(boolean Flag){
        this.setEditable(Flag);

        Border Margin = new EmptyBorder(0,5,0,5);

        if(Flag){
            this.setBackground(Color.white);
            this.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY), Margin));
            this.setForeground(Color.black);
        }
        else{
            this.setBackground(null);
            this.setBorder(new CompoundBorder(new LineBorder(BDBorder), Margin));
            this.setForeground(BGDefault);
        }

        this.isEditable = Flag;
    }

    @Override
    public void focusGained(FocusEvent e) {
        this.setValid(true);
        this.setToolTipText(null);
    }

    @Override
    public void focusLost(FocusEvent e) {
        //DO NOTHING
    }
}
