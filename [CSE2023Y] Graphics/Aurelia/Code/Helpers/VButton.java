package Helpers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VButton extends JButton implements MouseListener {
	
	private static final long serialVersionUID = 88883257800847348L;

	public VButton(String Tip) {
		super();

		this.setPreferredSize(new Dimension(64, 96));
		
		Color Default = new Color(211,211,211);
		setBackground(Default);
		setBorderPainted(false);
		
		getModel().addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent E) {
	            ButtonModel Model = (ButtonModel) E.getSource();
	            if (Model.isRollover()) 
	                setBackground(Color.LIGHT_GRAY);
	            else
	            	setBackground(Default);
	         }
	    });
		
		this.setToolTipText(Tip);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
