import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class CentralRegistry{
	
	/*Attributes of the class. 
	 *Nothing more from two ArrayLists. The first one is for all the airports of the program
	 *and the second one is for all the flights of the program.
	 *Both ArrayLists are static.*/
	
	private static ArrayList<Airport> airports = new ArrayList<Airport>();
	private static ArrayList<Flight> flights = new ArrayList<Flight>();
	
	/*Adding an Airport Object in to the "airports" ArrayList*/
	
	public static void addAirport(Airport anAirport) {
		
		airports.add(anAirport); 		
	}
	
	/*Adding a Flight Object in to the "flights" ArrayList*/
	
	public static void addFlight(Flight aFlight) {
		
		flights.add(aFlight);			
	}
	
	/*This function is checking all the airports and the direct connections of these airports with others and 
	 *it returns the Airport Object with the most direct connections.*/
	
	public static Airport getLargestHub() {								
																	
		int max = 0;												
		int index = 0;												
		
		for(int i=0;i<airports.size();i++) 
			if(airports.get(i).getConnectedAirports().size()>max) {
				max = airports.get(i).getConnectedAirports().size();
				index = i;
			}
		return (airports.get(index));
			
	}
	
	/*This function is checking all the flights and their duration and it returns
	 *the flight with the longest duration. */
	
	public static Flight getLongestFlight() {								
																	
		int max = 0;												
		int index = 0;												
		
		for(int i=0;i<flights.size();i++) {
			if(flights.get(i).getDuration()>max) {
				max = flights.get(i).getDuration();
				index = i;
			}			
		}
		
		return (flights.get(index));
			
	}
	
	public static Airport getAirport(String cityName) {
		
		for(Airport i: airports) 
			if(i.getCity().equals(cityName))
				return i;
			
		return null;
	}
	
	/*This function is getting the details of direct flights between two airports 
	 *and it returns a string with these details. */
	
	public static String getDirectlyFlightsDetails(Airport a, Airport b) {
		 
	
		 
		String text = "";
		int count = 0;
		
		HashSet<Flight> set = new HashSet<>(getCommonFlightsFromTheGivenAirports(a,b));
		
		text += "DIRECTLY FLIGHTS DETAILS\n";
		
		Iterator<Flight> iter = set.iterator();
		
		while(iter.hasNext()) {
			text+= ("[" + (count+1) + "] " + iter.next().toString()  + "\n");
			count+=1;
		}
		
		return text;
		
	}
	
	/*This function is getting all the common flights of two given airports and it returns an ArrayList
	 *with these flights.*/
	
	private static ArrayList<Flight> getCommonFlightsFromTheGivenAirports(Airport a, Airport b) {
		
		ArrayList<Flight> list = new ArrayList<>();
		
		for(Flight i: flights) {
			if(i.getAirportA().getName().equals(a.getName()) && i.getAirportB().getName().equals(b.getName()))
				list.add(i);
		}
		
		return list;
	}
	
	
	/*This function is getting the details of indirect flights between two airports 
	 *and it returns a string with these details. */
	
	
	public static String getInDirectlyFlightsDetails(Airport a, Airport b) {
		
		ArrayList<Airport> list = new ArrayList<>();
		int count = 0;
		
		
		list = a.getCommonConnections(b);
		
		HashSet<Airport> set = new HashSet<>(list);
		
		String text = "";
		
		text += "INDIRECT FLIGHTS through...\n";
	
		for(Airport airport: set) {
			text += ("[" + (count+1) + "] " + airport.getCity() + ", " + airport.getCodeName() + "\n");
			count+=1;
		}
		
		
		return text;
		
	}

	public static ArrayList<Flight> getFlights() {
		return flights;
	}
	
	
	
}