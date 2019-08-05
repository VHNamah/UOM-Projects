package Interface;/* Created by Vidush Namah on 2/15/2017 */


import javax.swing.*;
import java.awt.*;

public class EButton extends JButton {

    private Color Default = new Color(211, 211, 211);

    public EButton(String Tip, String Icon) {
        super();

        ImageIcon IMG = new ImageIcon(EButton.class.getResource("/Assets/" + Icon));

        this.setPreferredSize(new Dimension(48, 48));
        this.setIcon(IMG);

        SetFocus(false);
        setBorderPainted(false);

        getModel().addChangeListener(E -> {
            ButtonModel Model = (ButtonModel) E.getSource();
            if (Model.isRollover())
                SetFocus(true);
            else
                SetFocus(false);
        });

        this.setToolTipText(Tip);
    }

    public void SetFocus(Boolean Flag) {
        if (Flag) setBackground(Color.LIGHT_GRAY);
        else setBackground(Default);
    }
}