package Helpers;

import java.awt.Color;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EButton extends JButton{

	public EButton(String S) {
		super(S);
		
		Color Default = new Color(211,211,211);
		setBackground(Default);
		setBorderPainted(false);
		
		getModel().addChangeListener(E -> {
            ButtonModel Model = (ButtonModel) E.getSource();
            if (Model.isRollover())
                setBackground(Color.LIGHT_GRAY);
            else
                setBackground(Default);
         });
	}

	public EButton() {
		super();

		Color Default = new Color(211,211,211);
		setBackground(Default);
		setBorderPainted(false);

		getModel().addChangeListener(E -> {
            ButtonModel Model = (ButtonModel) E.getSource();
            if (Model.isRollover())
                setBackground(Color.LIGHT_GRAY);
            else
                setBackground(Default);
        });
	}
}