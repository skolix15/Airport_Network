import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AirportPageFrame extends JFrame {
	
	private JButton findFlightsButton;
	private JButton backToSearchScreenButton;
	private JPanel panel;
	
	private JTextField nameField,codeNameField,cityField,countryField;
	
	private JTextField secondAirportNameField;
	
	private Airport firstAirport;
	private Airport secondAirport;
	
	private JTextArea directlyFlightsDetailsField;
	private JTextArea inDirectlyFlightsDetailsField;
	
	private JPanel addPanel;
	
	private ArrayList<String> companies;
	private DefaultListModel<String> listModel;
	private JList<String> airportsJList;
	private JScrollPane scrollPane;
	
	/*Creating a constructor with a parameter, an Airport Object*/

	public AirportPageFrame(Airport anAirport){
		
		companies = new ArrayList<String>();
		
		firstAirport = anAirport;
		
		/*Getting all the companies that have access to this airport.*/
		
		for(int i = 0; i < firstAirport.getCompanies().size(); i++) {
			
			String company = firstAirport.getCompanies().get(i);
			
			if(!companies.contains(company))
				companies.add(company);
		}
		
		/*Sort the list of the companies.*/
		
		Collections.sort(companies);
		
		/*Creating a default list model.*/
		
		listModel = new DefaultListModel<String>();
		
		/*Adding the elements of the previous list with the companies in the default list model.*/
		
		for(int i=0;i<companies.size();i++) 
			listModel.addElement(companies.get(i));
			
		findFlightsButton  = new JButton("Find Flights");
		backToSearchScreenButton = new JButton("Back to Search Screen");
		
		panel = new JPanel();
		
		nameField = new JTextField(anAirport.getName() + "		");
		codeNameField = new JTextField(anAirport.getCodeName() + "		");
		cityField = new JTextField(anAirport.getCity() + "		");
		countryField = new JTextField(anAirport.getCountry() + "		");
		
		secondAirportNameField= new JTextField(15);
		
		/*Creating a JList with the companies.*/
		
		airportsJList = new JList<String>(listModel);
		
		/*Adding scroll pane do the JList.*/
		
		scrollPane = new JScrollPane(airportsJList);
		
		
		/*Adding all the fields and all the buttons in to the panel*/
		
		panel.add(nameField);
		panel.add(codeNameField);
		panel.add(cityField);
		panel.add(countryField);
		panel.add(scrollPane);

		panel.add(secondAirportNameField);
		panel.add(findFlightsButton);
		panel.add(backToSearchScreenButton);
		
				
		this.setContentPane(panel);
		
		/*Creating button listeners*/
					
		ButtonsListener findFlightsListener = new ButtonsListener();
		ButtonsListener changeScreenListener = new ButtonsListener();
		findFlightsButton.addActionListener(findFlightsListener);
		backToSearchScreenButton.addActionListener(changeScreenListener);
		
		this.setSize(900,400);
		this.setTitle("Airport Page");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	/*Class for button listeners*/
	
class ButtonsListener implements ActionListener{

	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getActionCommand().equals("Find Flights")) {
			String firstText = "";
			String secondText= "";
			String secondAirportName = "";
			
			/*Creating a new panel. The new one.*/
			
			addPanel = new JPanel();
			
			/*Taking the text of the field. The name of the airport that the user entered.*/
			
			secondAirportName = secondAirportNameField.getText();;
			
			for(Airport airport: firstAirport.getConnectedAirports() ) {
				if(airport.getCity().equals(secondAirportName))
					secondAirport = airport;	
			}
			
			/*Checking if the field was empty.*/
			
			if (secondAirportName.equals("")) 
				new MessageFrame();
			
			/*Checking if the arrival city is the same with the departure city.*/
			
			else if(secondAirportName.equals(firstAirport.getCity())) {
				dispose();
				new MessageFrame(firstAirport);
			}
			
			/*Checking if the airport exists*/
			
			else if(secondAirport == null)
				new MessageFrame(secondAirportName);
			
			else { 
				
				/*Taking the details of the direct and indirect flights between the two airports that the user want.*/
				
				firstText = CentralRegistry.getDirectlyFlightsDetails(firstAirport,secondAirport);
				secondText = CentralRegistry.getInDirectlyFlightsDetails(firstAirport, secondAirport);
				
				/*Adding the details into the text areas*/
				
				directlyFlightsDetailsField = new JTextArea(25,30);
				directlyFlightsDetailsField.setText(firstText);
				inDirectlyFlightsDetailsField = new JTextArea(25,25);
				inDirectlyFlightsDetailsField.setText(secondText);
				
				/*Adding the text areas with the details into the new panel*/
				
				addPanel.add(directlyFlightsDetailsField);
				addPanel.add(inDirectlyFlightsDetailsField);
				
				/*Making the changes in order the new panel to be printed.*/
				
				panel.add(addPanel);
				panel.revalidate(); 
		        panel.repaint();
		        
		        new CreateTextFile(firstAirport,secondAirport);
				
			}
				
		}
		
		/*Closing this frame and opening another one, the previous frame.*/
		
		else if(arg0.getActionCommand().equals("Back to Search Screen")) {
			dispose();
			new FindAirportFrame();
		}
		
		
	}
	
}


}
