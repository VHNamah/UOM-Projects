package Helpers;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Vidush H. Namah on 07 Mar 2016.
 **/

public class ESpinner extends JSpinner{

    private Color DefaultColor = new Color(80, 80, 80);
    private boolean isEditable = false;
    private boolean Valid = true;

    public ESpinner() {
        super();
        setEditFlag(false);
    }

    public ESpinner(SpinnerNumberModel M) {
        super(M);
        setEditFlag(false);
    }

    public ESpinner(SpinnerDateModel M) {
        super(M);
        this.setEditor(new JSpinner.DateEditor(this, "dd/MM/yyyy"));
        setEditFlag(false);
    }

    public void setValid(boolean Flag) {
        Border Margin = new EmptyBorder(0,5,0,5);
        if (isEditable) {
            if (Flag)
                this.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY), Margin));
            else
                this.setBorder(new CompoundBorder(new LineBorder(Color.RED), Margin));

            this.Valid=Flag;
        }
    }

    public boolean getValid() {
        return this.Valid;
    }

    public void setEditFlag(boolean Flag){
        isEditable=Flag;
        JTextField Text = ((JSpinner.DefaultEditor) super.getEditor()).getTextField();

        this.setEnabled(Flag);
        Text.setEditable(Flag);
        Text.setEnabled(true);

        Border Margin = new EmptyBorder(0,5,0,5);

        if(Flag){
            this.setBackground(Color.WHITE);
            Text.setBackground(Color.WHITE);
            this.setBorder(new CompoundBorder(new LineBorder(Color.lightGray), Margin));
            Text.setForeground(Color.BLACK);
        }
        else{
            this.setBackground(null);
            Text.setBackground(null);
            this.setBorder(new CompoundBorder(new LineBorder(new Color(150,175,255),1), Margin));
            Text.setForeground(DefaultColor);
        }
    }
}
