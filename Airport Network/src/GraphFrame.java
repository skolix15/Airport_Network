import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

/*This class is visualizing a graph that contains all the flights that exist in the central registry.
 *For the graph it is used an external library ("jung2-2_0_1") */

public class GraphFrame extends JFrame {
	
	private String firstCityName = null;
	private String secondCityName = null;
	
	/*An ArrayList where will be added inside the same flights that have been used.*/
	private ArrayList<String> same = new ArrayList<>();
	
	/*An ArrayList for all the flights that exist.*/
	private ArrayList<Flight> flightsList = new ArrayList<>();
	
	/*A graph "panel".*/
	private SparseMultigraph<String,String> graph;
	
	/*Creating a constructor*/
	
	public GraphFrame() {
		
		/*Creating a graph "panel".*/
		
		graph = new SparseMultigraph<String, String>();
		
		/*Adding all the flights that exist in Central Registry in the ArrayListn "flightsList".*/
		
		flightsList = CentralRegistry.getFlights();
		
		int count = 0;
		
		for(Flight flight: flightsList) {
			
			/*Every flight it is represented with a row where in each side of the row there is one vertex-node.
			 *The two vertexes-nodes represent the two airports of the flight.
			 *So this part of the code, is giving in each vertex-node a name of an airport.*/
			
			count++;
			String edge = "Edge" + count;
			firstCityName = flight.getAirportA().getCity();
			secondCityName = flight.getAirportB().getCity();
			
			/*Adding vertexes in the graph "panel" with their names*/
			
			graph.addVertex(firstCityName);
			graph.addVertex(secondCityName);
			
			/*Checking if a flight has been added before in the graph "panel". For example it is possible to exist a flight from Airport A to airport B and 
			 *and the same time to exist another flight from airport B to airport A.
			 *So in order to avoid duplicates it exists an ArrayList "same" that in there are exist the flights that have already
			 *been added in the graph "panel".*/
			
			if(!same.contains(firstCityName + "-" + secondCityName))
				graph.addEdge(edge, firstCityName,secondCityName);
			
			/*When a flight has been added in the graph "panel" then it is added also to the ArrayList "same".*/
			
			same.add(firstCityName + "-" + secondCityName);
					
		}
		
		/*Visualize the graph and adding it in the main pane.*/
		
		VisualizationImageServer<String, String> vs =
		        new VisualizationImageServer<String, String>(new CircleLayout<String, String>(graph), new Dimension(300, 200));
		
		vs.getRenderContext().setVertexLabelTransformer(
		        new ToStringLabeller<String>());
		
		getContentPane().add(vs);
		
		
		this.setSize(350,400);
		this.setTitle("City Airport Connections Network");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

}
