# Travel Agent Trip Planner
This project implements a travel agent's trip planner using graphs to efficiently plan round trips between cities with flight connections. The planner allows clients to specify a starting city and destination city, and then generates a round trip itinerary including transit cities where direct connections are unavailable. By leveraging graph theory, the solution optimizes pathfinding algorithms to calculate the most cost-effective routes and ensure seamless travel planning for users. This project can be tested and run using tools like **Visual Studio Code** or **IntelliJ IDEA**.

![Graph image](Image/Graph.jpeg)


## Features
- **Round Trip Planning:**
  - Clients specify a starting city and destination city.
  - The planner calculates the cheapest path for the forward trip from the starting city to the destination city, considering transit cities if direct connections are unavailable.
  - It then calculates the cheapest path for the return trip from the destination city back to the starting city, ensuring that transit cities used in the forward trip are not reused.
  - If the starting and destination cities are directly connected, the return trip avoids the same path.

- **Cost Estimation:**
  - Provides cost estimates for the forward trip, return trip, and total round trip for the client.
### Files Provided

#### RoundTripPlanner.java

- Contains class members, constructor, and methods.
- Constructor initializes class variables and invokes `generateRoundTrip()` method.
- `generateRoundTrip()` method performs necessary actions to compute paths and update variables.
- `printRoundTrip()` method prints the forward trip, return trip, and associated costs.

#### Supporting Classes

- `Edge.java`, `Graph.java`, `UnweightedGraph.java`, `WeightedEdge.java`, `WeightedGraph.java`: Contains necessary methods and structures for graph representation and operations.

### Testing

#### TripTester.java

- Declares cities and connections with flight costs.
- `userPrompt()` method obtains user input for start and destination cities.
- Creates `RoundTripPlanner` instance and invokes `printRoundTrip()` method to display travel itinerary.
- `verifyRoundTrip()` method validates that the generated round trip meets the travel agent's planning criteria.
- 

### Implementation Notes

- Utilizes Dijkstra's algorithm for pathfinding in weighted graphs.
- Manipulates edge weights to ensure the return trip excludes edges used in the forward trip.
- Ensures efficient path calculation and cost estimation for round trips.
