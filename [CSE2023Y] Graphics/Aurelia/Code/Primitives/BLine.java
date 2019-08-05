package Primitives;

import java.awt.Color;
import java.awt.Point;

import Executable.Interface;
import Helpers.VBoard;
import Helpers.VConverter;

public class BLine {
	
	Point Start;
	Point End;
	Color SColor;
	
	public BLine(Point Start, Point End, Color SColor) {
		this.Start = Start;
		this.End = End;
		this.SColor = SColor;
	}
	
	public static void CHECKScale(int EndX, int EndY) {
		if (EndX>VBoard.XLimit)
			VBoard.XIncrement=(int) Math.ceil((double) EndX/VBoard.XDivision);
		
		if (EndY>VBoard.YLimit)
			VBoard.YIncrement=(int) Math.ceil((double) EndY/VBoard.YDivision);
		
		Interface.Board.DRAWBoard(VBoard.XIncrement, VBoard.YIncrement);
	}
	
	public void Draw() {
		//TODO: Fix +X -Y Region not working
		VBoard.GDraw.setColor(SColor);
		
		Point StartPt = VConverter.Map(Start);
		Point EndPt = VConverter.Map(End);
		
		int DX = Math.abs(EndPt.x-StartPt.x);
		int DY = Math.abs(EndPt.y-StartPt.y);
		
		double M = (double) DY/DX;
		
		int NextX = StartPt.x;
		int NextY = StartPt.y;
		
		if (M<1.0) {
			int DDY = 2*DY;
			int DYX = DDY-(2*DX);
			int P = DDY-DX;
			
			while (NextX<EndPt.x) {
				VBoard.GDraw.drawLine(NextX, NextY, NextX, NextY);
				if (P<0) {
					NextX++;
					P+=DDY;
				}
				else {
					NextX++;
					NextY--;
					P+=DYX;
				}
			}
		}
		
		else {
			int DDX = 2*DX;
			int DXY = DDX-(2*DY);
			int P = DDX-DY;
			
			while (NextY>EndPt.y) {
				VBoard.GDraw.drawLine(NextX, NextY, NextX, NextY);
				if (P<0) {
					NextY--;
					P+=DDX;
				}
				else {
					NextX++;
					NextY--;
					P+=DXY;
				}
			}
		}	
	}
}
