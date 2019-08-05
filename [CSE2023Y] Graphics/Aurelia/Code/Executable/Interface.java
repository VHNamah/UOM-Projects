package Executable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import Helpers.VBoard;

public class Interface extends JFrame {

	public static VBoard Board = new VBoard();
	public static Toolbar Tools = new Toolbar();
	
	//STATIC HARDCODED VALUES THAT I HATE
	int ToolbarHeight = 156;
	
	private static final long serialVersionUID = 979206327272139791L;
	
	public Interface() {
		System.out.println("PROGRAM START.\n");
		
		Dimension Screen = Toolkit.getDefaultToolkit().getScreenSize();
		Insets Taskbar = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		
		//Initializing FRAME
		setTitle("Aurelia 4.0");
		setBounds(0, 0, Screen.width, Screen.height - Taskbar.bottom);
		setMinimumSize(new Dimension(1074, Screen.height - Taskbar.bottom));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//TOOLBAR
		Tools.setBorder(new LineBorder(Color.WHITE));
		this.getContentPane().add(Tools, BorderLayout.NORTH);
		
		//DRAWBOARD
		Board.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()-ToolbarHeight));
		this.getContentPane().add(Board, BorderLayout.SOUTH);
	}
	
}
