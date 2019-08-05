package Helpers;

import java.awt.Point;

public class VConverter {
	
	public static Point Map(Point A) {
		int Ax = (int) (A.x*((double) 50/VBoard.XIncrement)+VBoard.Origin.x);
		int Ay = (int) (-A.y*((double) 50/VBoard.YIncrement)+VBoard.Origin.y);
		
		return (new Point(Ax,Ay));
	}
	
	public static VPoint Map(VPoint A) {
		double Ax = A.X*((double) 50/VBoard.XIncrement)+VBoard.Origin.x;
		double Ay = -A.Y*((double) 50/VBoard.YIncrement)+VBoard.Origin.y;

		return (new VPoint(Ax,Ay));
	}

}
