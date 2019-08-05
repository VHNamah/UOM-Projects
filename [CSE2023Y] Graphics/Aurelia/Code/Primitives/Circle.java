package Primitives;

import java.awt.Color;
import java.awt.Point;

import Executable.Interface;
import Helpers.VBoard;
import Helpers.VConverter;

public class Circle {

	Point Centre;
	int Radius;
	Color SColor;
	
	public Circle(Point O, int R, Color C) {
		this.Centre=O;
		this.Radius=R;
		this.SColor=C;
	}
	
	public static void CHECKScale(Point Centre, int R) {
		int EndY = Centre.y+R;
		
		if (EndY>VBoard.YLimit)
			VBoard.YIncrement=(int) Math.ceil((double) EndY/VBoard.YDivision);
		
		VBoard.XIncrement=VBoard.YIncrement;
		
		Interface.Board.DRAWBoard(VBoard.XIncrement, VBoard.YIncrement);
	}
	
	public void Draw() {
		Point C = VConverter.Map(Centre);
		int R = (int) (Radius*((double) 50/VBoard.YIncrement));
		
		VBoard.GDraw.setColor(SColor);
		
		int X=0;
		int Y=R;
		
		double P = (5/4)-R;
		
		while(X<=Y) {
			VBoard.GDraw.drawLine(C.x+X, C.y+Y, C.x+X, C.y+Y);
			VBoard.GDraw.drawLine(C.x-X, C.y+Y, C.x-X, C.y+Y);
			VBoard.GDraw.drawLine(C.x+X, C.y-Y, C.x+X, C.y-Y);
			VBoard.GDraw.drawLine(C.x-X, C.y-Y, C.x-X, C.y-Y);
			
			VBoard.GDraw.drawLine(C.x+Y, C.y+X, C.x+Y, C.y+X);
			VBoard.GDraw.drawLine(C.x-Y, C.y+X, C.x-Y, C.y+X);
			VBoard.GDraw.drawLine(C.x+Y, C.y-X, C.x+Y, C.y-X);
			VBoard.GDraw.drawLine(C.x-Y, C.y-X, C.x-Y, C.y-X);
					
			if (P<0) {
				P+=2*X+1;
				X++;
			}
			
			else {
				P+=2*(X-Y)+1;
				X++;
				Y--;
			}
		}
	}
}
