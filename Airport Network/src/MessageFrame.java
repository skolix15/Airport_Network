import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageFrame extends JFrame {
	
	private int message;
	private String name;
	private Airport airport;
	
	/*Creating three constructors. The first two are taking one parameter but they are not taking the same type of parameter.
	 *The first constructor is taking an Airport Object as a parameter and the second constructor is taking a String as a parameter.
	 *The third constructor is not taking any parameter. 
	 *There are three different constructors for three different kind of messages*/
	
	
	public MessageFrame(String aName) {
		
		message = 1;
		
		name = aName;
		
		String text = name + " does not have an airport";
		
		int result = JOptionPane.showOptionDialog(null, text, "Message" , JOptionPane.DEFAULT_OPTION,
		        JOptionPane.INFORMATION_MESSAGE, null, null, null);
		
		if (result == JOptionPane.OK_OPTION || result == JOptionPane.CANCEL_OPTION
	              || result == JOptionPane.CLOSED_OPTION) {
			dispose();
		}

}

	public MessageFrame(Airport anAirport) {
		
		message = 2;
		
		airport = anAirport;
		
		String text ="Arrival and departure city cannot be the same!";
		
		int result = JOptionPane.showOptionDialog(null, text, "Message" , JOptionPane.DEFAULT_OPTION,
		        JOptionPane.INFORMATION_MESSAGE, null, null, null);
		
		if (result == JOptionPane.OK_OPTION || result == JOptionPane.CANCEL_OPTION
	              || result == JOptionPane.CLOSED_OPTION) {
			new AirportPageFrame(airport);
		}
		
}
	
	public MessageFrame() {
		
		message = 3;
		
		String text ="You did not enter anything";
		
		int result = JOptionPane.showOptionDialog(null, text, "Message" , JOptionPane.DEFAULT_OPTION,
		        JOptionPane.INFORMATION_MESSAGE, null, null, null);
		
		if (result == JOptionPane.OK_OPTION || result == JOptionPane.CANCEL_OPTION
	              || result == JOptionPane.CLOSED_OPTION) {
			dispose();
		}
		
		
	}

}