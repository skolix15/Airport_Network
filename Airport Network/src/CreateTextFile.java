import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateTextFile {
	
	private String text;
	private BufferedWriter output = null;
	private String textFileName;
	
	/*Creating a constructor with two parameters, two Airport Objects.*/
	
	public CreateTextFile(Airport firstAirport, Airport secondAirport) {
	
		textFileName = firstAirport.getCity() + "To" + secondAirport.getCity() + ".txt";
		
		/*Creating a txt file where the information of the direct and indirect flights between the two airport objects will be written.*/		
		
		try {
					
			File file = new File(textFileName);
			FileWriter writer = new FileWriter(file);
			text = writeText(firstAirport,secondAirport);
			
			/*Writing the information in to the txt file*/
			
			writer.write(text);
					
			writer.close();
		} catch (IOException e) {
					
			e.printStackTrace();
		}
				
				
				
			
			
		
	}
	
	/*Function that returns a string with the information of the direct and 
	 *indirect flights between the two airport objects.*/
	
	public String writeText(Airport firstAirport, Airport secondAirport) {
		
		String lines;
		
		lines = "CITY: " + firstAirport.getCity() + ", " + firstAirport.getCountry() + "\n";
		lines += "Airport: " + firstAirport.getName() + "( " + firstAirport.getCodeName()  + " )\n";
		
		lines += "\nDESTINATION: " + secondAirport.getCity() + "\n";
		
		lines += "\n" + CentralRegistry.getDirectlyFlightsDetails(firstAirport, secondAirport) + "\n";
	
		lines += "\n\n" + CentralRegistry.getInDirectlyFlightsDetails(firstAirport, secondAirport) + "\n";
		
		return lines;
	}

}
