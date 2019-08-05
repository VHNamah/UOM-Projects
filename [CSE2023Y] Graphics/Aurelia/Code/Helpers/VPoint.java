package Helpers;

import java.awt.Point;

public class VPoint{
	public double X;
	public double Y;
	
	public VPoint(Point Pt) {
		this.X = Pt.x;
		this.Y = Pt.y;
	}
	
	public VPoint(double X, double Y) {
		this.X = X;
		this.Y = Y;
	}
	
	@Override
	public String toString() {
		return "(" + this.X + "," + this.Y + ")";
	}
}