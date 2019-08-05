package Helpers;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class VPanel extends JPanel {

	private static final long serialVersionUID = -7840570175976278880L;

	public VPanel() {
		super();
		FlowLayout Layout = (FlowLayout) this.getLayout();
		Layout.setAlignment(FlowLayout.LEFT);
	}
	
}
