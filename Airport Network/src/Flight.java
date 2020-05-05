
public class Flight {
	
	private Airport firstAirport;
	private Airport secondAirport;
	private int duration;
	private String company;
	
	/*Creating a constructor.*/
	
	public Flight(Airport airport1,Airport airport2,int aDuration,String aCompany){
		
		firstAirport = airport1;
		secondAirport = airport2;
		duration = aDuration;
		company = aCompany;
		
		/*Line 21: Connecting the first airport with the second one.
		 *Line 22: Connecting the second airport with the first one.*/
		
		airport1.connectWithAnAirport(airport2);		
		airport2.connectWithAnAirport(airport1);		
		
		/*Adding the company to both two airports.*/
		
		airport1.addCompany(aCompany);					
		airport2.addCompany(aCompany);					
		
	}
	
	public Airport getAirportA() {
		return firstAirport;
	}
	public Airport getAirportB() {
		return secondAirport;
	}

	public int getDuration() {
		return duration;
	}

	public String getCompany() {
		return company;
	}
	
	/*Rewriting the function "toString" for this class*/
	
	public String toString() {
		return ("Flight operated by " + this.getCompany() + ", duration "  + this.getDuration()  + " minutes\n");
	}

}