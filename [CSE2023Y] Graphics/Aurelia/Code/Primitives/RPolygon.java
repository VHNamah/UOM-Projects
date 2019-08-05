package Primitives;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

import Executable.Interface;
import Helpers.VBoard;
import Helpers.VConverter;

public class RPolygon extends Polygon {
	
	private static final long serialVersionUID = -3640848819043078663L;
	
	Color SColor;
	
	public RPolygon(Point Centre, int R, int N, Color SColor) {
		Centre = VConverter.Map(Centre);
		R = (int) (R*((double) 50/VBoard.YIncrement));
		
		double Angle = (2*Math.PI)/N;
		double X=0, Y=0;
		
		int XPoints[] = new int[N];
		int YPoints[] = new int[N];
		
		for (int I=0; I<N; I++) {
			X = Centre.x + R * Math.sin(I * Angle);
		    Y = Centre.y + R * Math.cos(I * Angle);
		    
		    XPoints[I]=(int) X;
		    YPoints[I]=(int) Y;
		}
		
		this.xpoints=XPoints;
		this.ypoints=YPoints;
		this.npoints=N;
		this.SColor=SColor;
	}
	
	public static void CHECKScale(Point Centre, int R) {
		int EndY = Centre.y+R;
		
		if (EndY>VBoard.YLimit)
			VBoard.YIncrement=(int) Math.ceil((double) EndY/VBoard.YDivision);
		
		VBoard.XIncrement=VBoard.YIncrement;
		
		Interface.Board.DRAWBoard(VBoard.XIncrement, VBoard.YIncrement);
	}	
	
	public void Draw() {
		VBoard.GDraw.setColor(SColor);
		VBoard.GDraw.drawPolygon(this);
	}
}
