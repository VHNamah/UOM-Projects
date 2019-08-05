package Default;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class KhaleesiOfTextFields extends JTextField implements FocusListener {

	private static final long serialVersionUID = 3518017498859285366L;
	
	private String phValue = null;
	private boolean isModified = false;
	
	//SETS PLACEHOLDER
	public void initPlaceholder() {
		//Set the placeholder in a light shade of grey
		this.setForeground(new Color(211,211,211));
		this.setText(this.phValue);
		
		//Reset the flag
		this.isModified = false;
	}
	
	//DEFINED CONSTRUCTORS
	public KhaleesiOfTextFields(String S) {
		super();
		
		this.phValue=S;
		this.initPlaceholder();
		
		//Initialize the class to use FocusListeners
		addFocusListener(this);
	}
	
	public KhaleesiOfTextFields(String S, int C) {
		super(C);
		
		this.phValue=S;
		this.initPlaceholder();
		
		//Initialize the class to use FocusListeners
		addFocusListener(this);
	}
	
	//ON GAIN FOCUS
	@Override
	public void focusGained(FocusEvent E) {
		if (!isModified) {
		//Prepare the empty field for input
			this.isModified=true;
			this.setText("");
			this.setForeground(Color.BLACK);
		}
	}

	//ON LOST FOCUS
	@Override
	public void focusLost(FocusEvent E) {
		if (this.getText().length()==0) {
		//Empty field
			this.isModified=false;
			this.initPlaceholder();
		}
	}	
}