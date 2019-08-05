package Blades;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import Executable.Interface;
import Executable.Main;
import Executable.Toolbar;
import Helpers.*;
import Primitives.*;

public class Shapes extends VPanel{
	
	private static final long serialVersionUID = -3864313076683844197L;
	
	private static VButton BTNDraw = new VButton("PENCIL");
	private static VButton BTNLine = new VButton("LINE");
	private static VButton BTNCircle = new VButton("CIRCLE");
	private static VButton BTNEllipse = new VButton("ELLIPSE");
	private static VButton BTNRPolygon = new VButton("POLYGON [REGULAR]");
	private static VButton BTNIPolygon = new VButton("POLYGON [IRREGULAR]");
	
	public static void Disable() {
		BTNDraw.setEnabled(false);
		BTNLine.setEnabled(false);
		BTNCircle.setEnabled(false);
		BTNEllipse.setEnabled(false);
		BTNRPolygon.setEnabled(false);
		BTNIPolygon.setEnabled(false);
	}
	
	public static void Enable() {
		BTNDraw.setEnabled(true);
		BTNLine.setEnabled(true);
		BTNCircle.setEnabled(true);
		BTNEllipse.setEnabled(true);
		BTNRPolygon.setEnabled(true);
		BTNIPolygon.setEnabled(true);
	}
	
	public Shapes() {
		super();
		
		//SETTING AESTHETICS
		BTNDraw.setIcon(new ImageIcon(Main.class.getResource("/Resources/Pen.png")));
		BTNLine.setIcon(new ImageIcon(Main.class.getResource("/Resources/Line.png")));
		BTNCircle.setIcon(new ImageIcon(Main.class.getResource("/Resources/Circle.png")));
		BTNEllipse.setIcon(new ImageIcon(Main.class.getResource("/Resources/Ellipse.png")));
		BTNRPolygon.setIcon(new ImageIcon(Main.class.getResource("/Resources/RPolygon.png")));
		BTNIPolygon.setIcon(new ImageIcon(Main.class.getResource("/Resources/IPolygon.png")));
		
		//ADDING COMPONENTS
		this.add(BTNDraw);
		this.add(BTNLine);
		this.add(BTNCircle);
		this.add(BTNEllipse);
		this.add(BTNRPolygon);
		this.add(BTNIPolygon);
		
		//INTIALIZING BLADES
		JPanel PANLine = LineBlade();
		JPanel PANCircle = CircleBlade();
		JPanel PANEllipse = EllipseBlade();
		JPanel PANRPolygon = RPolygonBlade();
		JPanel PANIPolygon = IPolygonBlade();
		
		//SETTING LISTENERS
		BTNLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent E) {
				Interface.Tools.add(PANLine);
				Toolbar.Disable();
				PANLine.setVisible(true);
			}
		});
		
		BTNCircle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent E) {
				Interface.Tools.add(PANCircle);
				Toolbar.Disable();
				PANCircle.setVisible(true);
			}
		});
		
		BTNEllipse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent E) {
				Interface.Tools.add(PANEllipse);
				Toolbar.Disable();
				PANEllipse.setVisible(true);
			}
		});
		
		BTNRPolygon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent E) {
				Interface.Tools.add(PANRPolygon);
				Toolbar.Disable();
				PANRPolygon.setVisible(true);
			}
		});
		
		BTNIPolygon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent E) {
				Interface.Tools.add(PANIPolygon);
				Toolbar.Disable();
				PANIPolygon.setVisible(true);
			}
		});
	}
	
	public static JPanel LineBlade() {
		JPanel Blade = new JPanel();
		
		//LINE PARAMETERS INPUT
		FlowLayout Layout = (FlowLayout) Blade.getLayout();
		Layout.setAlignment(FlowLayout.LEFT);
		Blade.setPreferredSize(new Dimension(400, 96));
		Blade.setVisible(false);
		
		//-START POINT INPUT
		JPanel InputStart = new JPanel();
		InputStart.setBorder(BorderFactory.createTitledBorder("START POINT"));
		InputStart.setPreferredSize(new Dimension(132, 92));
		Blade.add(InputStart);
		
		//-START POINT X
		JLabel LBLStartX = new JLabel("X");
		InputStart.add(LBLStartX);
		
		JSpinner StartX = new JSpinner();
		LBLStartX.setLabelFor(StartX);
		StartX.setPreferredSize(new Dimension(96,26));
		InputStart.add(StartX);
		
		//-START POINT Y
		JLabel LBLStartY = new JLabel("Y");
		InputStart.add(LBLStartY);
		
		JSpinner StartY = new JSpinner();
		LBLStartY.setLabelFor(StartY);
		StartY.setPreferredSize(new Dimension(96, 26));
		InputStart.add(StartY);
		
		//-END POINT INPUT
		JPanel InputEnd = new JPanel();
		Blade.add(InputEnd);
		InputEnd.setPreferredSize(new Dimension(132, 92));
		InputEnd.setBorder(BorderFactory.createTitledBorder("END POINT"));
		
		//-END POINT X
		JLabel LBLEndX = new JLabel("X");
		InputEnd.add(LBLEndX);
		
		JSpinner EndX = new JSpinner();
		EndX.setToolTipText("Start X-Coordinate");
		EndX.setPreferredSize(new Dimension(96, 26));
		InputEnd.add(EndX);
		
		//-END POINT Y
		JLabel LBLEndY = new JLabel("Y");
		InputEnd.add(LBLEndY);
		
		JSpinner EndY = new JSpinner();
		EndY.setToolTipText("Start Y-Coordinate");
		EndY.setPreferredSize(new Dimension(96, 26));
		InputEnd.add(EndY);
		
		//-DRAW OPTIONS
		JPanel Options = new JPanel();
		Options.setPreferredSize(new Dimension(106, 58));
		Blade.add(Options);
		
		//-DRAW
		VButton BTNDraw = new VButton("DRAW");
		BTNDraw.addMouseListener(new MouseAdapter() {
			@Override			
			public void mouseClicked(MouseEvent E) {
				Point Start = new Point((int) StartX.getValue(), (int) StartY.getValue());
				Point End = new Point((int) EndX.getValue(), (int) EndY.getValue());
				
				if (Start.x>End.x) {
					Point Buffer = Start;
					Start = End;
					End = Buffer;
				}
				
				Blade.setVisible(false);
				
				//Selecting Line Algorithm
				String Options[] = {"DDA", "BRESENHAM"};
				int LineChoice = JOptionPane.showOptionDialog(null,
					"Choose your Line Algorithm: ",
					"Draw Line",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					Options,
					Options[0]);
				
				System.out.print("OPTION: LINE - ");
				
				try {
					System.out.println(Options[LineChoice]);
				} catch (Exception Error) {
					System.out.println("CANCELLED\n");
				}
				
				if (LineChoice==0) {
					DLine.CHECKScale(End.x, End.y);
					Interface.Board.ADDDLine(new DLine(Start, End, VBoard.PColor));
				}
				
				else if (LineChoice==1) {
					BLine.CHECKScale(End.x, End.y);
					Interface.Board.ADDBLine(new BLine(Start, End, VBoard.PColor));
				}
				Toolbar.Enable();
			}
		});
		BTNDraw.setIcon(new ImageIcon(Main.class.getResource("/Resources/Accept.png")));
		BTNDraw.setPreferredSize(new Dimension(48, 48));
		Options.add(BTNDraw);
		
		//-CANCEL
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
		Options.add(BTNCancel);
		
		Blade.setVisible(false);
		return Blade;
	}
	
	public static JPanel CircleBlade() {
		JPanel Blade = new JPanel();
		
		//-CIRCLE PARAMETERS INPUT
		FlowLayout Layout = (FlowLayout) Blade.getLayout();
		Layout.setAlignment(FlowLayout.LEFT);
		Blade.setPreferredSize(new Dimension(400, 96));
		Blade.setVisible(false);
		
		//-START POINT INPUT
		JPanel InputCentre = new JPanel();
		InputCentre.setBorder(BorderFactory.createTitledBorder("CENTRE POINT"));
		InputCentre.setPreferredSize(new Dimension(132, 92));
		Blade.add(InputCentre);
		
		//-START POINT X
		JLabel LBLCentreX = new JLabel("X");
		InputCentre.add(LBLCentreX);
		
		JSpinner CentreX = new JSpinner();
		LBLCentreX.setLabelFor(CentreX);
		CentreX.setPreferredSize(new Dimension(96,26));
		InputCentre.add(CentreX);
		
		//-START POINT Y
		JLabel LBLCentreY = new JLabel("Y");
		InputCentre.add(LBLCentreY);
		
		JSpinner CentreY = new JSpinner();
		LBLCentreY.setLabelFor(CentreY);
		CentreY.setPreferredSize(new Dimension(96, 26));
		InputCentre.add(CentreY);
		
		//-RADIUS
		JLabel LBLRadius = new JLabel("Radius");
		Blade.add(LBLRadius);
		
		JSpinner Radius= new JSpinner();
		LBLRadius.setLabelFor(Radius);
		Radius.setPreferredSize(new Dimension(96,26));
		Blade.add(Radius);
		
		//-DRAW OPTIONS
		JPanel Options = new JPanel();
		Options.setPreferredSize(new Dimension(106, 58));
		Blade.add(Options);
		
		//-DRAW
		VButton BTNDraw = new VButton("ACCEPT");
		BTNDraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent E) {
				Point Centre = new Point((int) CentreX.getValue(), (int) CentreY.getValue());
				int R = (int) Radius.getValue();
				R *= (R<0) ? -1 : 1;
				
				Blade.setVisible(false);
				
				Circle.CHECKScale(Centre, R);
				
				Interface.Board.ADDCircle(new Circle(Centre, R, VBoard.PColor));
			
				System.out.println("OPTION: CIRCLE\n");

				Toolbar.Enable();
			}
		});
		BTNDraw.setIcon(new ImageIcon(Main.class.getResource("/Resources/Accept.png")));
		BTNDraw.setPreferredSize(new Dimension(48, 48));
		Options.add(BTNDraw);
		
		//-CANCEL
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
		Options.add(BTNCancel);
		
		Blade.setVisible(false);
		return Blade;
	}
	
	public static JPanel EllipseBlade() {
		JPanel Blade = new JPanel();

		//ELLIPSE PARAMETERS INPUT
		FlowLayout Layout = (FlowLayout) Blade.getLayout();
		Layout.setAlignment(FlowLayout.LEFT);
		Blade.setPreferredSize(new Dimension(400, 96));
		Blade.setVisible(false);
		
		//-CENTRE POINT INPUT
		JPanel InputEllipse = new JPanel();
		InputEllipse.setBorder(BorderFactory.createTitledBorder("CENTRE POINT"));
		InputEllipse.setPreferredSize(new Dimension(132, 92));
		Blade.add(InputEllipse);
		
		//-CENTRE POINT X
		JLabel LBLEllipseX = new JLabel("X");
		InputEllipse.add(LBLEllipseX);
		
		JSpinner EllipseX = new JSpinner();
		LBLEllipseX.setLabelFor(EllipseX);
		EllipseX.setPreferredSize(new Dimension(96,26));
		InputEllipse.add(EllipseX);
		
		//-CENTRE POINT Y
		JLabel LBLEllipseY = new JLabel("Y");
		InputEllipse.add(LBLEllipseY);
		
		JSpinner EllipseY = new JSpinner();
		LBLEllipseY.setLabelFor(EllipseY);
		EllipseY.setPreferredSize(new Dimension(96, 26));
		InputEllipse.add(EllipseY);

		//-RADII INPUT
		JPanel InputRadius = new JPanel();
		InputRadius.setBorder(BorderFactory.createTitledBorder("RADII"));
		InputRadius.setPreferredSize(new Dimension(132, 92));
		Blade.add(InputRadius);
		
		//-RADIUS X-AXIS
		JLabel LBLRadiusX = new JLabel("X");
		InputRadius.add(LBLRadiusX);
		
		JSpinner RadiusX = new JSpinner();
		LBLRadiusX.setLabelFor(RadiusX);
		RadiusX.setPreferredSize(new Dimension(96,26));
		InputRadius.add(RadiusX);
		
		//-RADIUS Y-AXIS
		JLabel LBLRadiusY = new JLabel("Y");
		InputRadius.add(LBLRadiusY);
		
		final JSpinner RadiusY = new JSpinner();
		LBLRadiusY.setLabelFor(RadiusY);
		RadiusY.setPreferredSize(new Dimension(96, 26));
		InputRadius.add(RadiusY);
		
		//-DRAW OPTIONS
		JPanel Options = new JPanel();
		Options.setPreferredSize(new Dimension(106, 58));
		Blade.add(Options);
		
		//-DRAW
		VButton BTNDraw = new VButton("DRAW");
		BTNDraw.addMouseListener(new MouseAdapter() {
			@Override			
			public void mouseClicked(MouseEvent E) {
				Point C = new Point((int) EllipseX.getValue(), (int) EllipseY.getValue());
				
				int RadX = (int) RadiusX.getValue();
				RadX *= (RadX<0) ? -1 : 1;
				
				int RadY = (int) RadiusY.getValue();
				RadY *= (RadY<0) ? -1 : 1;
				
				Blade.setVisible(false);
				
				Ellipse.CHECKScale(C, RadX, RadY);
				
				Interface.Board.ADDEllipse(new Ellipse(C, RadX, RadY, VBoard.PColor));
				
				System.out.println("OPTION: ELLIPSE\n");

				Toolbar.Enable();
			}
		});
		BTNDraw.setIcon(new ImageIcon(Main.class.getResource("/Resources/Accept.png")));
		BTNDraw.setPreferredSize(new Dimension(48, 48));
		Options.add(BTNDraw);
		
		//-CANCEL
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
		Options.add(BTNCancel);
		
		Blade.setVisible(false);
		return Blade;
	}
	
	public static JPanel RPolygonBlade() {
		JPanel Blade = new JPanel();
		
		//-REGULAR POLYGON PARAMETERS INPUT
		FlowLayout Layout = (FlowLayout) Blade.getLayout();
		Layout.setAlignment(FlowLayout.LEFT);
		Blade.setPreferredSize(new Dimension(400, 96));
		Blade.setVisible(false);
		
		//-CENTRE POINT INPUT
		JPanel InputCentre = new JPanel();
		InputCentre.setBorder(BorderFactory.createTitledBorder("CENTRE POINT"));
		InputCentre.setPreferredSize(new Dimension(132, 92));
		Blade.add(InputCentre);
		
		//-CENTRE POINT X
		JLabel LBLCentreX = new JLabel("X");
		InputCentre.add(LBLCentreX);
		
		JSpinner CentreX = new JSpinner();
		LBLCentreX.setLabelFor(CentreX);
		CentreX.setPreferredSize(new Dimension(96,26));
		InputCentre.add(CentreX);
		
		//-CENTRE POINT Y
		JLabel LBLCentreY = new JLabel("Y");
		InputCentre.add(LBLCentreY);
		
		JSpinner CentreY = new JSpinner();
		LBLCentreY.setLabelFor(CentreY);
		CentreY.setPreferredSize(new Dimension(96, 26));
		InputCentre.add(CentreY);
		
		//-ADDITIONAL INPUT
		JPanel InputOther = new JPanel();
		InputOther.setBorder(BorderFactory.createTitledBorder("RADIUS & SIDES"));
		InputOther.setPreferredSize(new Dimension(132, 92));
		Blade.add(InputOther);
		
		//-RADIUS
		JLabel LBLRadius = new JLabel("R");
		InputOther.add(LBLRadius);
		
		JSpinner Radius = new JSpinner();
		LBLRadius.setLabelFor(Radius);
		Radius.setPreferredSize(new Dimension(96, 26));
		InputOther.add(Radius);
		
		//-NUMBER OF SIDES
		JLabel LBLSides = new JLabel("S");
		InputOther.add(LBLSides);
		
		JSpinner Sides = new JSpinner();
		LBLSides.setLabelFor(Sides);
		Sides.setPreferredSize(new Dimension(96, 26));
		InputOther.add(Sides);		
		
		//-DRAW OPTIONS
		JPanel Options = new JPanel();
		Options.setPreferredSize(new Dimension(106, 58));
		Blade.add(Options);
		
		//-DRAW
		VButton BTNDraw = new VButton("ACCEPT");
		BTNDraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent E) {
				Point Centre = new Point((int) CentreX.getValue(), (int) CentreY.getValue());
				
				int R = (int) Radius.getValue();
				R *= (R<0) ? -1 : 1;
				
				int N = (int) Sides.getValue();
				
				Blade.setVisible(false);
				
				if (N>2) {
					Circle.CHECKScale(Centre, R);
					
					Interface.Board.ADDRPolygon(new RPolygon(Centre, R, N, VBoard.PColor));
				
					System.out.println("OPTION: REGULAR POLYGON\n");
				}

				Toolbar.Enable();
			}
		});
		BTNDraw.setIcon(new ImageIcon(Main.class.getResource("/Resources/Accept.png")));
		BTNDraw.setPreferredSize(new Dimension(48, 48));
		Options.add(BTNDraw);
		
		//-CANCEL
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
		Options.add(BTNCancel);
		
		Blade.setVisible(false);
		return Blade;
	}
	
	public JPanel IPolygonBlade() {
		JPanel Blade = new JPanel();
		
		FlowLayout Layout = (FlowLayout) Blade.getLayout();
		Layout.setAlignment(FlowLayout.LEFT);
		Blade.setPreferredSize(new Dimension(400, 106));
		Blade.setVisible(false);
		
		//-VERTEX INPUT
		JPanel VertexInput = new JPanel();
		VertexInput.setPreferredSize(new Dimension(132, 92));
		VertexInput.setBorder(BorderFactory.createTitledBorder("VERTEX"));
		Blade.add(VertexInput);
		
		//-X COORDINATE
		JLabel LBLPointX = new JLabel("X");
		VertexInput.add(LBLPointX);
		
		JSpinner PointX = new JSpinner();
		LBLPointX.setLabelFor(PointX);
		PointX.setPreferredSize(new Dimension(96,26));
		VertexInput.add(PointX);
	
		//-Y COORDINATE
		JLabel LBLPointY = new JLabel("Y");
		VertexInput.add(LBLPointY);
		
		JSpinner PointY = new JSpinner();
		LBLPointY.setLabelFor(PointY);
		PointY.setPreferredSize(new Dimension(96,26));
		VertexInput.add(PointY);
		
		//-ADD VERTEX
		VButton BTNAdd = new VButton("ADD VERTEX");
		BTNAdd.addMouseListener(new MouseAdapter() {
			@Override			
			public void mouseClicked(MouseEvent E) {

				Toolbar.Enable();
			}
		});
		BTNAdd.setIcon(new ImageIcon(Main.class.getResource("/Resources/Accept.png")));
		BTNAdd.setPreferredSize(new Dimension(48, 48));
		Blade.add(BTNAdd);
		
		//-DRAW
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
		
		Blade.setVisible(false);
		return Blade;
	}
}
