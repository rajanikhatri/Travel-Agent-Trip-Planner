
import java.util.Arrays;
import java.util.List;

public class RoundTripPlanner {
	// user inputs for the source and destination
	private int startCityIndex;
	private int endCityIndex;


	// Graph created using the following vertices and edges
	private WeightedGraph<String> flightNetwork;

	// array of vertices
	private String[] cities;
	// array of weighted edges [source][dest][weight]
	private int[][] connections;

	// forward and return route cities lists and cost of trip
	private List<String> forwardRoute;
	private double forwardRouteCost;
	private List<String> returnRoute;
	private double returnRouteCost;

	/*
	 * Constructor:
	 * - Assigns class variables
	 * - Invokes generateRoundTrip() method
	 */
	public RoundTripPlanner(String[] cities, int[][] connections, int startCityIndex, int endCityIndex) {
		// TO DO
		 // Here we assign the parameters to our class variables.
		this.cities= cities;
		this.connections=connections;
		this.startCityIndex=startCityIndex;
		this.endCityIndex=endCityIndex;
		generateRoundTrip();  // After setting things up, we immediately start generating the round trip.
	}


	/*
	 * Round trip generator:
	 * - Creates flight network graph
	 * - Updates forward trip path variable and forward trip cost
	 * - Performs necessary actions for return trip planning
	 * - Updates return trip path variable and return trip cost
	 */
	public void generateRoundTrip() {
		// TO DO
		// First, we create a new graph for our flight network using the cities and their connections.
		flightNetwork= new WeightedGraph<>(cities, connections);

		// We then find the shortest path from the start city to the end city.
		WeightedGraph.ShortestPathTree shortestPath1 = flightNetwork.getShortestPath(startCityIndex);
		forwardRoute= shortestPath1.getPath(endCityIndex);
		forwardRouteCost= shortestPath1.getCost(endCityIndex);


		// To prepare for the return trip, we make all the edges used in the forward trip unusable by setting their weight to the maximum integer value.
		int currentCityInReturnPath =endCityIndex;
		int parentCityInReturnPath ;
		while( currentCityInReturnPath != startCityIndex) {
			parentCityInReturnPath= shortestPath1.getParent(currentCityInReturnPath); //making parentCity parent of currentCity
			for (Edge ed : flightNetwork.neighbors.get(currentCityInReturnPath)) {
				if(ed.v == parentCityInReturnPath) {
					((WeightedEdge) ed).weight = Integer.MAX_VALUE;// setting edge between currentCity and parentcity to maximum
				}
			}
			for (Edge ed : flightNetwork.neighbors.get(parentCityInReturnPath)) { //edges exiting parentcity to max
				((WeightedEdge) ed).weight = Integer.MAX_VALUE;
			}
			currentCityInReturnPath= parentCityInReturnPath;//making currentcity to parentcity
		}

		// We now find the shortest path back to the starting city from the destination.
		WeightedGraph.ShortestPathTree shortestPath2 = flightNetwork.getShortestPath(endCityIndex);
		returnRoute= shortestPath2.getPath(startCityIndex);
		returnRouteCost= shortestPath2.getCost(startCityIndex);
	}


	/*
	 * Trip viewer:
	 * - prints forward trip in the format:
	 * "Forward trip from A to B: A �> P �> Q �> R �> B"
	 * - prints return trip in the same format:
	 * "Return trip from B to A: B �> S �> T �> U �> A"
	 * - prints the costs for the forward trip, return trip, and total trip in the format:
	 *  "Forward route cost: 200.0"
	 *  "Return route cost: 300.0"
	 *  "Total trip cost: 500.0"
	 */
	public void printRoundTrip() {
		// TO DO
		// We build a string that represents the path of our forward trip.
		StringBuilder ForwardRouteBuilder = new StringBuilder(forwardRoute.get(0));
		for(int i=1; i<forwardRoute.size(); i++) {
			ForwardRouteBuilder.append(" > ").append(forwardRoute.get(i));
		}
		System.out.println("Forward trip from " + cities[startCityIndex] + " to " + cities[endCityIndex] + ": " + ForwardRouteBuilder);

		 // And we do the same for the return trip.
		StringBuilder parentCityInReturnPath= new StringBuilder(returnRoute.get(0));
		for(int i=1; i<returnRoute.size(); i++) {
			parentCityInReturnPath.append(" > ").append(returnRoute.get(i));
		}
		System.out.println("Return trip from       " + cities[endCityIndex] + " to " + cities[startCityIndex] + ": " + parentCityInReturnPath);
		System.out.println("Forward route cost:    " + forwardRouteCost);
		System.out.println("Return route cost:     " + returnRouteCost);
		System.out.println("Total trip cost:       " + (forwardRouteCost+returnRouteCost));
	}

	// Returns the forwardRoute class variable
	public List<String> getForwardRoute() {
		return forwardRoute;
	}

	// Returns the returnRoute class variable
	public List<String> getReturnRoute() {
		return returnRoute;
	}

	// Returns the forwardRouteCost class variable
	public double getForwardRouteCost() {
		return forwardRouteCost;
	}

	// Returns the returnRouteCost class variable
	public double getReturnRouteCost() {
		return returnRouteCost;
	}



}
