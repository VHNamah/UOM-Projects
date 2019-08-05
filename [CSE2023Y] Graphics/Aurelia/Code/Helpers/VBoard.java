package Helpers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Primitives.*;

public class VBoard extends JPanel {
	
	private static final long serialVersionUID = 7662609298938318866L;
	
	//GRAPHICS TOOLS
	public static Graphics2D GDraw;
	public static Color PColor;
	public static int PWeight;
	
	//CANVAS CENTER
	public static Point Origin;
	
	//AXES VARIABLES
	public static int XDivision;
	public static int YDivision;
	
	public static int XIncrement;
	public static int YIncrement;
	
	public static int XLimit;
	public static int YLimit;
	
	//SHAPES DRAWN
	public static List<DLine> DDALines = new ArrayList<DLine>();
	public static List<BLine> BLines = new ArrayList<BLine>();
	public static List<Circle> Circles = new ArrayList<Circle>();
	public static List<Ellipse> Ellipses = new ArrayList<Ellipse>();
	public static List<RPolygon> RPolygons = new ArrayList<RPolygon>();
	
	public VBoard() {
		super();
		this.setBackground(Color.WHITE);
		RESETBoard();
	}
	
	public void DRAWBoard(int X, int Y) {
		XIncrement = X;
		YIncrement = Y;
		repaint();
	}
	
	public void RESETBoard() {
		DDALines.clear();
		BLines.clear();
		Circles.clear();
		Ellipses.clear();
		PColor = Color.black;
		PWeight = 1;
		DRAWBoard(10, 10);
	}
	
	public void DRAWScale() {
		int XScale = Origin.x;
		int YScale = Origin.y;
		
		int XDiv = 0;
		int YDiv = 0;
		
		XDivision = -1;
		YDivision = -1;
		
		GDraw.drawString("0", Origin.x+10, Origin.y+20);
		
		//DRAWING X-AXIS
		while (XScale<Origin.x*2) {
			XScale+=50;
			XDiv+=XIncrement;
			XDivision++;
			
			int Temp = Origin.x-(XScale-Origin.x);
			
			GDraw.setColor(new Color(230,230,230));
			GDraw.drawLine(XScale, 0, XScale, Origin.y*2);
			GDraw.drawLine(Temp, 0, Temp, Origin.y*2);
			
			GDraw.setColor(Color.gray);
			GDraw.drawLine(XScale, Origin.y-2, XScale, Origin.y+2);
			GDraw.drawLine(Temp, Origin.y-2, Temp, Origin.y+2);
			
			GDraw.drawString(String.valueOf(XDiv), XScale, Origin.y+20);
			GDraw.drawString(String.valueOf(-XDiv), Temp, Origin.y+20);
		}
		
		//DRAWING Y-AXIS
		while (YScale<Origin.y*2) {
			YScale+=50;
			YDiv+=YIncrement;
			YDivision++;
			
			int Temp = Origin.y-(YScale-Origin.y);
			
			GDraw.setColor(new Color(230,230,230));
			GDraw.drawLine(0, YScale, Origin.x*2, YScale);
			GDraw.drawLine(0, Temp, Origin.x*2, Temp);
			
			GDraw.setColor(Color.gray);
			GDraw.drawLine(Origin.x-2, YScale, Origin.x+2, YScale);
			GDraw.drawLine(Origin.x-2, Temp, Origin.x+2, Temp);
			
			GDraw.drawString(String.valueOf(-YDiv), Origin.x+10, YScale+5);
			GDraw.drawString(String.valueOf(YDiv), Origin.x+10, Temp+5);
		}
		
		XLimit = XDiv-XIncrement;
		YLimit = YDiv-YIncrement;	
	}
	
	public static void SETColor(int R, int G, int B) {
		PColor = new Color(R, G, B);
	}
	
	@Override
	public void paintComponent(Graphics G) {
		super.paintComponent(G);
		
		GDraw = (Graphics2D)G;
		GDraw.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		Dimension BoardSize = getSize();
		Origin = new Point(BoardSize.width/2, BoardSize.height/2);
		
		GDraw.setColor(Color.gray);
		GDraw.drawLine(Origin.x, 0, Origin.x, BoardSize.height);
		GDraw.drawLine(0, Origin.y, BoardSize.width, Origin.y);
		
		DRAWScale();
		
		//DRAWING LINES (DDA)
		for (DLine Line : DDALines) {
			System.out.println(Line);
			Line.Draw();
		}
		
		//DRAWING LINES (BRESENHAM)
		for (BLine Line : BLines)
			Line.Draw();
		
		//DRAWING CIRCLES
		for (Circle Circle : Circles)
			Circle.Draw();
			
		//DRAWING ELLIPSES
		for (Ellipse Ellipse : Ellipses)
			Ellipse.Draw();
		
		//DRAWING REGULAR POLYGONS
		for (RPolygon RPoly : RPolygons)
			RPoly.Draw();
	}
	
	public void ADDDLine(DLine L) {
		DDALines.add(L);
		repaint();
	}
	
	public void ADDBLine(BLine L) {
		BLines.add(L);
		repaint();
	}
	
	public void ADDCircle(Circle C) {
		Circles.add(C);
		repaint();
	}
	
	public void ADDEllipse(Ellipse E) {
		Ellipses.add(E);
		repaint();
	}
	
	public void ADDRPolygon(RPolygon R) {
		RPolygons.add(R);
		repaint();
	}
}
