package Primitives;

import java.awt.Color;
import java.awt.Point;

import Executable.Interface;
import Helpers.VBoard;
import Helpers.VConverter;

public class Ellipse {
	
	Point Centre;
	int RadiusX;
	int RadiusY;
	Color SColor;
	
	public Ellipse(Point Centre, int RadiusX, int RadiusY, Color SColor) {
		this.Centre=Centre;
		this.RadiusX=RadiusX;
		this.RadiusY=RadiusY;
		this.SColor=SColor;
	}
	
	
	public static void CHECKScale(Point C, int RadX, int RadY) {
		int EndX = C.x+RadX;
		int EndY = C.y+RadY;
		
		if (EndX>VBoard.XLimit)
			VBoard.XIncrement=(int) Math.ceil((double) EndX/VBoard.XDivision);
		
		if (EndY>VBoard.YLimit)
			VBoard.YIncrement=(int) Math.ceil((double) EndY/VBoard.YDivision);
		
		Interface.Board.DRAWBoard(VBoard.XIncrement, VBoard.YIncrement);
	}

	public void Pixel(int CX, int CY, int X, int Y) {
		VBoard.GDraw.drawLine(CX+X, CY+Y, CX+X, CY+Y);
		VBoard.GDraw.drawLine(CX-X, CY+Y, CX-X, CY+Y);
		VBoard.GDraw.drawLine(CX+X, CY-Y, CX+X, CY-Y);
		VBoard.GDraw.drawLine(CX-X, CY-Y, CX-X, CY-Y);
	}
	
	public void Draw() {
		Point C = VConverter.Map(Centre);
		double RX = (RadiusX*((double) 50/VBoard.XIncrement));
		double RY = (RadiusY*((double) 50/VBoard.YIncrement));
		
		double RXSq = RX*RX;
		double RYSq = RY*RY;
		
		double X = 0;
		double Y = RY;
		
		double P;
		double PX = 0;
		double PY = 2*RXSq*Y;
		
		Pixel(C.x, C.x, (int) X, (int) Y);
		
		//REGION 1
		P = (RYSq-(RXSq*RY)+(0.25+RXSq));
		
		while (PX<PY) {
			X++;
			PX=PX+2*RYSq;
			
			if (P<0)
				P=P+RYSq+PX;
			
			else {
				Y--;
				PY=PY-2*RXSq;
				P=P+RYSq+PX-PY;
			}
			
			Pixel(C.x, C.y, (int) X, (int) Y);
		}
		
		//REGION 2
		P = (int) (RYSq*(X+0.5)*(X+0.5)+RXSq*(Y-1)*(Y-1)-RXSq*RYSq);
		
		while (Y>0) {
			Y--;
			PY=PY-2*RXSq;
			
			if (P>0)
				P=P+RXSq-PY;
			
			else {
				X++;
				PX=PX+2*RYSq;
				P=P+RXSq-PY+PX;
			}
			
			Pixel(C.x, C.y, (int) X, (int) Y);
		}
	}
}
