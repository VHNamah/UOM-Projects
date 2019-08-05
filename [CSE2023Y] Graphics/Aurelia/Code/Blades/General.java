package Blades;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import Executable.*;
import Helpers.*;

public class General extends VPanel {

	private static final long serialVersionUID = -2823762857011473111L;
	
	private static VButton BTNScale = new VButton("SCALE");

	public static void Disable() {
		BTNScale.setEnabled(false);
	}
	
	public static void Enable() {
		BTNScale.setEnabled(true);
	}
	
	public General() {
		super();
		
		//SETTING AESTHETICS
		BTNScale.setIcon(new ImageIcon(Main.class.getResource("/Resources/Settings.png")));
		
		//INITIALIZING BLADES
		JPanel PANScale = ScaleBlade();
		
		//SETTING LISTENERS
		BTNScale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent E) {
				Interface.Tools.add(PANScale);
				PANScale.setVisible(true);
				Toolbar.Disable();
			}
		});
		
		//ADDING COMPONENTS
		this.add(BTNScale);
	}
	
	public static JPanel ScaleBlade() {
		JPanel Blade = new JPanel();
		
		FlowLayout Layout = (FlowLayout) Blade.getLayout();
		Layout.setAlignment(FlowLayout.LEFT);
		Blade.setPreferredSize(new Dimension(400, 106));
		Blade.setVisible(false);
		
		//---NEW SCALE PANEL
		JPanel ScaleInput = new JPanel();
		ScaleInput.setPreferredSize(new Dimension(166, 92));
		ScaleInput.setBorder(BorderFactory.createTitledBorder("NEW SCALE"));
		Blade.add(ScaleInput);
		
		//----NEW X SCALE
		JLabel LBLXAxis = new JLabel("X-Axis");
		ScaleInput.add(LBLXAxis);
		
		JSpinner XAxis = new JSpinner();
		LBLXAxis.setLabelFor(XAxis);
		XAxis.setToolTipText("X-SCALE");
		XAxis.setPreferredSize(new Dimension(96,26));
		ScaleInput.add(XAxis);
	
		//----NEW Y SCALE
		JLabel LBLYAxis = new JLabel("Y-Axis");
		ScaleInput.add(LBLYAxis);
		
		JSpinner YAxis = new JSpinner();
		LBLYAxis.setLabelFor(YAxis);
		YAxis.setToolTipText("Y-SCALE");
		YAxis.setPreferredSize(new Dimension(96,26));
		ScaleInput.add(YAxis);
		
		//---ACCEPT
		VButton BTNAccept = new VButton("ACCEPT");
		BTNAccept.addMouseListener(new MouseAdapter() {
			@Override			
			public void mouseClicked(MouseEvent E) {
				Blade.setVisible(false);
				Interface.Board.DRAWBoard((int) XAxis.getValue(), (int) YAxis.getValue());
				Toolbar.Enable();
			}
		});
		BTNAccept.setIcon(new ImageIcon(Main.class.getResource("/Resources/Accept.png")));
		BTNAccept.setPreferredSize(new Dimension(48, 48));
		Blade.add(BTNAccept);
		
		//---CANCEL
		VButton BTNCancel = new VButton("CANCEL");
		BTNCancel.addMouseListener(new MouseAdapter() {
			@Override			
			public void mouseClicked(MouseEvent E) {
				Blade.setVisible(false);
				Toolbar.Enable();
			}
		});
		BTNCancel.setIcon(new ImageIcon(Main.class.getResource("/Resources/Cancel.png")));
		BTNCancel.setPreferredSize(new Dimension(48, 48));
		Blade.add(BTNCancel);
		
		//---RESET SCREEN
		VButton BTNReset = new VButton("RESET");
		BTNReset.addMouseListener(new MouseAdapter() {
			@Override			
			public void mouseClicked(MouseEvent E) {
				Interface.Board.RESETBoard();
				Blade.setVisible(false);
				Toolbar.Enable();
			}
		});
		BTNReset.setText("RESET");
		BTNReset.setPreferredSize(new Dimension(72, 48));
		Blade.add(BTNReset);
		
		Blade.setVisible(false);
		return Blade;
	}
}