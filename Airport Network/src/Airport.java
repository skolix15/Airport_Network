import java.util.ArrayList;

public class Airport {
	
	/* Attributes of the class (airportName, codeName, city, country).
	 * Extra two ArrayLists. The first ArrayList is 
	 * for the companies that the Airport Object has and the second one is for the 
	 * other airports that this Airport Object is connected to. */
	
	private String airportName;
	private String codeName;
	private String city;
	private String country;
	private ArrayList<String> companies = new ArrayList<String>();
	private ArrayList<Airport> connectedAirports = new ArrayList<Airport>();
	
	/* Constructor for Airport Class
	 *  Putting values to variables "aiportName", "codeName", "city", "country". */
	
	public Airport(String anAirport_name,String aCode_name,String aCity,String aCountry){
		airportName = anAirport_name;
		codeName = aCode_name;
		city = aCity;
		country = aCountry;
	}
	
	/* Adding a company into "companies" ArrayList with all the companies of the program */
	
	public void addCompany(String company) {
		companies.add(company);
	}
	
	/* Connecting an Airport object with another Airport object.
	 *  The connections of every Airport object exist in the "connectedAirports" ArrayList */
	
	public void connectWithAnAirport(Airport anAirport) {				
		connectedAirports.add(anAirport);				 	 			
	}

	public String getName() {
		return airportName;
	}

	public String getCodeName() {
		return codeName;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}
	
	/* Printing all the companies of the Airport Object */

	public void printCompanies() {
		
		for(int i=0;i<companies.size();i++)								
			System.out.println(companies.get(i));						
	}
	
	/* Searching if this Airport Object is directly connected to another Airport.
	 * Specifically, this function is searching in the "connectedAirports" ArrayList, 
	 * to find another Airport if it exists. 
	 * This function returns a boolean value.
	 * The comparison it is done by the names of the airports.*/
	
	public boolean isDirectlyConnectedTo(Airport anAirport) {
		
		String airportName1;											
		String airportName2;											
		airportName2 = anAirport.getName();								
		
		
		for(int i=0;i<connectedAirports.size();i++) {					
			airportName1 = connectedAirports.get(i).getName();		
			if(airportName1.equals(airportName2))
				return true;
		}
		return false;
		
	}
	
	/* Searching if this Airport Object is indirectly connected to another Airport.
	 * Specifically, this function is searching in the "connectedAirports" ArrayLists of two Airport Objects, 
	 * to find if they have common Airport's names.
	 * This function returns a boolean value. 
	 * The comparison it is done by the names of the airports.*/
	
	
	public boolean isInDirectlyConnectedTo(Airport anAirport) {
		
		String airportName1;											
		String airportName2;											
																		
		for(int i=0;i<connectedAirports.size();i++) {				   			
			airportName1 = connectedAirports.get(i).getName();		  
			for(int j=0;j<anAirport.connectedAirports.size();j++) {		
				airportName2 =  anAirport.connectedAirports.get(j).getName();	
				if(airportName1.equals(airportName2))	
					return true;
				}	
			}
					
		return false;
	}
	
	/*Searching if this Airport Object has common connections with another Airport Object.
	 *Specifically, this function is searching in the "connectedAiports" ArrayLists of the two Airport Objects
	 *to find the common Airport's names. All the common connections of these two Airport Objects are being added
	 *in a new ArrayList named "sameAirporsts".
	 *This function it returns the "sameAirports" ArrayList. 
	 *The comparison it is done by the names of the airports.*/
	
	public ArrayList<Airport> getCommonConnections(Airport anAirport){
		
		ArrayList<Airport> sameAirports = new ArrayList<Airport>();		
		String airportName1;											
		String airportName2;											
																		
		for(int i=0;i<connectedAirports.size();i++) {						
			airportName1 = connectedAirports.get(i).getName();
			for(int j=0;j<anAirport.connectedAirports.size();j++) {
				airportName2 =  anAirport.connectedAirports.get(j).getName();
				if(airportName1.equals(airportName2))
					sameAirports.add(connectedAirports.get(i));
				
			}
		}
		
		return sameAirports;
	
	}

	public ArrayList<Airport> getConnectedAirports() {
		return connectedAirports;
	}

	public ArrayList<String> getCompanies() {
		return companies;
	}
	
}