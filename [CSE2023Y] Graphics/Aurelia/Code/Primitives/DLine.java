package Primitives;

import java.awt.Color;
import java.awt.Point;

import Executable.Interface;
import Helpers.*;

public class DLine {
	
	VPoint Start;
	VPoint End;
	Color SColor;
	
	public DLine(Point Start, Point End, Color SColor) {
		this.Start = new VPoint(Start);
		this.End = new VPoint(End);
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
		VPoint StartPt = VConverter.Map(Start);
		VPoint EndPt = VConverter.Map(End);
		
		VBoard.GDraw.setColor(SColor);
		
		double DX=EndPt.X-StartPt.X;
		double DY=EndPt.Y-StartPt.Y;
		double Steps = (DY>DX) ? DY : DX;
		
		double XGain = DX/Steps;
		double YGain = DY/Steps;
		
		double NextX = StartPt.X;
		double NextY = StartPt.Y;
		
		while(NextX<=EndPt.X) {
			VBoard.GDraw.drawLine((int) NextX, (int) NextY, (int) NextX, (int) NextY);
			NextX+=XGain;
			NextY+=YGain;
		}
	}
	
	@Override
	public String toString() {
		return (Start + " TO " + End);
	}
}