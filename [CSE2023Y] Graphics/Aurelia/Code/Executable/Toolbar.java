package Executable;

import Blades.*;
import Helpers.VPanel;

public class Toolbar extends VPanel {
	
	private static final long serialVersionUID = -6092806417013713285L;

	public static General BAROptions = new General();
	public static Style BARStyle = new Style();
	public static Shapes BARShapes = new Shapes();
	
	public Toolbar() {
		super();
		this.add(BAROptions);
		this.add(BARStyle);
		this.add(BARShapes);
	}
	
	public static void Disable() {
		Shapes.Disable();
		General.Disable();
		Style.Disable();
	}
	
	public static void Enable() {
		Shapes.Enable();
		General.Enable();
		Style.Enable();
	}	
}
