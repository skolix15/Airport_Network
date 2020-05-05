import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindAirportFrame extends JFrame{
	
	private JButton findButton;
	private JPanel panel;
	private JTextField cityNameTextField;
	private Airport anAirport;
	private JButton visualizeNetworkButton;

	/*Creating a constructor.*/
	
	public FindAirportFrame(){
		
		
		findButton  = new JButton("Find");
		
		cityNameTextField = new JTextField(10);
		
		visualizeNetworkButton = new JButton("Visualize Network");
		
		panel = new JPanel();
		
		
		/*Adding buttons and text fields in to the panel*/
		
		panel.add(cityNameTextField);
		panel.add(findButton);
		panel.add(visualizeNetworkButton);

		this.setContentPane(panel);
		
		/*Creating button listener for the buttons*/
		
		ButtonListener listener = new ButtonListener();
		findButton.addActionListener(listener);
		visualizeNetworkButton.addActionListener(listener);
		
		this.setSize(300, 200);
		this.setTitle("Find Airport");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	
	/*Class for button listener*/
	
class ButtonListener implements ActionListener{

	
	public void actionPerformed(ActionEvent arg0) {
		
		/*Closing this frame and opening another one with the details of a specific airport
		 *that the user has written in a text field of this frame.*/
		
		if(arg0.getActionCommand().equals("Find")) {
		
			String name = cityNameTextField.getText();
		
			anAirport = CentralRegistry.getAirport(name);
		
			if(anAirport != null) {
			
				dispose();
				new AirportPageFrame(anAirport);
			
			}
			
			/*Closing this frame and opening another one with a message.*/
			
			else {
				dispose();
				new MessageFrame(name);
				new FindAirportFrame();
			}
			
		}
		
		/*Closing this frame and opening another one with a graph.*/
		
		else if(arg0.getActionCommand().equals("Visualize Network")) {
			dispose();
			new GraphFrame();
		}
		
			
	  }

	}

}