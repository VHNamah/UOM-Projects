package Blades;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Executable.Interface;
import Executable.Main;
import Executable.Toolbar;
import Helpers.*;

public class Style extends VPanel{
	
	private static final long serialVersionUID = -3864313076683844197L;
	
	private static VButton BTNColor = new VButton("COLOR");
	private static VButton BTNWeight = new VButton("THICKNESS");
	private static VButton BTNStyle = new VButton("STYLE");

	public static void Disable() {
		BTNColor.setEnabled(false);
		BTNWeight.setEnabled(false);
		BTNStyle.setEnabled(false);
	}
	
	public static void Enable() {
		BTNColor.setEnabled(true);
		BTNWeight.setEnabled(true);
		BTNStyle.setEnabled(true);
	}
	
	public Style() {
		super();
		
		//SETTING AESTHETICS
		BTNColor.setIcon(new ImageIcon(Main.class.getResource("/Resources/Color.png")));
		BTNWeight.setIcon(new ImageIcon(Main.class.getResource("/Resources/Weight.png")));
		BTNStyle.setIcon(new ImageIcon(Main.class.getResource("/Resources/Style.png")));
		
		//ADDING COMPONENTS
		this.add(BTNColor);
		this.add(BTNWeight);
		this.add(BTNStyle);
		
		//INITIALIZING BLADES
		JPanel PANColor = ColorBlade();
		JPanel PANWeight = WeightBlade();
		JPanel PANStyle = StyleBlade();
		
		//SETTING LISTENERS
		BTNColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent E) {
				Interface.Tools.add(PANColor);
				PANColor.setVisible(true);
				Toolbar.Disable();
			}
		});
		
		BTNWeight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent E) {
				Interface.Tools.add(PANWeight);
				PANWeight.setVisible(true);
				Toolbar.Disable();
			}
		});
		
		BTNStyle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent E) {
				Interface.Tools.add(PANStyle);
				PANStyle.setVisible(true);
				Toolbar.Disable();
			}
		});
	}
	
	public static JPanel StyleBlade() {
		JPanel Blade = new JPanel();

		//---ACCEPT
		VButton BTNAccept = new VButton("ACCEPT");
		BTNAccept.addMouseListener(new MouseAdapter() {
			@Override			
			public void mouseClicked(MouseEvent E) {
				Blade.setVisible(false);
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
		
		Blade.setVisible(false);
		
		return Blade;
	}
	
	public static JPanel WeightBlade() {
		JPanel Blade = new JPanel();

		//---ACCEPT
		VButton BTNAccept = new VButton("ACCEPT");
		BTNAccept.addMouseListener(new MouseAdapter() {
			@Override			
			public void mouseClicked(MouseEvent E) {
				Blade.setVisible(false);
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
		
		Blade.setVisible(false);
		
		return Blade;
	}
	
	public static JPanel ColorBlade() {
		JPanel Blade = new JPanel();
		
		JSpinner R = new JSpinner(new SpinnerNumberModel(VBoard.PColor.getRed(), 0, 255, 1));
		JSpinner G = new JSpinner(new SpinnerNumberModel(VBoard.PColor.getGreen(), 0, 255, 1));
		JSpinner B = new JSpinner(new SpinnerNumberModel(VBoard.PColor.getBlue(), 0, 255, 1));
		
		JPanel Preview = new JPanel();
		Preview.setBackground(new Color(0,0,0));
		
		FlowLayout Layout = (FlowLayout) Blade.getLayout();
		Layout.setAlignment(FlowLayout.LEFT);
		Blade.setPreferredSize(new Dimension(400, 106));
		Blade.setVisible(false);
		
		//---RGB INPUT
		JPanel RGBPanel = new JPanel();
		RGBPanel.setPreferredSize(new Dimension(116, 106));
		Blade.add(RGBPanel);
		
		//----RED
		JLabel LBLRed = new JLabel("R");
		RGBPanel.add(LBLRed);
		
		R.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent E){
				int Red = (int) R.getValue();
				int Green = (int) G.getValue();
				int Blue = (int) B.getValue();
				Preview.setBackground(new Color(Red, Green, Blue));
			}
		});
		LBLRed.setLabelFor(R);
		R.setToolTipText("Red");
		R.setPreferredSize(new Dimension(96,26));
		RGBPanel.add(R);
	
		//----GREEN
		JLabel LBLGreen = new JLabel("G");
		RGBPanel.add(LBLGreen);
			
		G.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent E){
				int Red = (int) R.getValue();
				int Green = (int) G.getValue();
				int Blue = (int) B.getValue();
				Preview.setBackground(new Color(Red, Green, Blue));
			}
		});
		LBLGreen.setLabelFor(G);
		G.setToolTipText("Green");
		G.setPreferredSize(new Dimension(96,26));
		RGBPanel.add(G);
		
		//----BLUE
		JLabel lblBlue = new JLabel("B");
		RGBPanel.add(lblBlue);
		
		B.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent E){
				int Red = (int) R.getValue();
				int Green = (int) G.getValue();
				int Blue = (int) B.getValue();
				Preview.setBackground(new Color(Red, Green, Blue));
			}
		});
		lblBlue.setLabelFor(B);
		B.setToolTipText("Blue");
		B.setPreferredSize(new Dimension(96,26));
		RGBPanel.add(B);
		
		//---COLOR DISPLAY
		Preview.setPreferredSize(new Dimension(24, 24));
		Blade.add(Preview);
		
		//---ACCEPT
		VButton BTNAccept = new VButton("ACCEPT");
		BTNAccept.addMouseListener(new MouseAdapter() {
			@Override			
			public void mouseClicked(MouseEvent E) {
				Blade.setVisible(false);
				
				int Red = (int) R.getValue();
				int Green = (int) G.getValue();
				int Blue = (int) B.getValue();
				
				VBoard.SETColor(Red, Green, Blue);
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
		
		Blade.setVisible(false);
		
		return Blade;
	}
}
