package Default;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Application {
	
	private static JFrame frame = new JFrame();

	public static void main (String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					initDemo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void initDemo() {
		frame.setVisible(true);
		frame.setTitle("MY TEXT FIELD DEMO");
		frame.setSize(new Dimension(500, 200));
		frame.setLayout(new GridBagLayout());
		
		JTextField JField = new JTextField("NORMAL TEXT FIELD", 25);
		JField.setPreferredSize(new Dimension(0,40));
		
		KhaleesiOfTextFields TField = new KhaleesiOfTextFields("WINTER IS TAKING TOO LONG TO COME", 25);
		TField.setPreferredSize(new Dimension(0,40));
		
		GridBagConstraints C = new GridBagConstraints();
		
		C.gridx=0;
		frame.add(JField, C);
		
		C.gridy=1;
		frame.add(TField, C);
	}
}
