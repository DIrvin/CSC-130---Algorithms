// Programmed By: Derek Irvin
// Assignment 4
// May 10th, 2014
// Dijkstra's Algorithm Excercise for finding the shortest path to each destination from the starting
// point of Sacramento. Simply Comparing the weights of the edges for the smallest weight and updating the path displaying cost of the weight and path taken.

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class RailPath implements Comparable<RailPath>
{
    public final String name;								// Node Name
    public Edge[] adjacencies;								// Adjacent Paths
    public double minWeight = Double.POSITIVE_INFINITY;	// All Other nodes are initally set to positive infinity since we assume
    														// That all weights are positive
    public RailPath optimalPath;									// Previous node when comparing weights of nodes to find lowest weight
    public RailPath(String argName) { name = argName; }
    public String toString() { return name; }				// Convert City Name To String
    public int compareTo(RailPath other)
    {
        return Double.compare(minWeight, other.minWeight);
    }
}

class Edge
{
    public final RailPath target;	// Location of the Node
    public final double weight;	// Weight of the edge
    public Edge(RailPath argTarget, double argWeight)
    { target = argTarget; weight = argWeight; }	// Name; Weight
}

public class Assignment4
{
    public static void computePaths(RailPath source)
    {
        source.minWeight = 0.;	// Initial Distance(weight) Set to 0 for our initial node
        PriorityQueue<RailPath> vertexQueue = new PriorityQueue<RailPath>();	// Creation of Heap with RailPath(Nodes we travel)
      	vertexQueue.add(source);

    // While we have Nodes to Travel
	while (!vertexQueue.isEmpty()) {
	    RailPath u = vertexQueue.poll();

            // Visit each edge of the node checking the loop condition of weight and 
	    	// removing possible edges that have higher weights 
            for (Edge e : u.adjacencies)	// While Edges Avaliable at the node Cycle through the nodes testing one weight to the others to find the cheapest node
            {
                RailPath v = e.target;	// Current Node
                double weight = e.weight;	// Current Weight of Node
                double grandWeight = u.minWeight + weight;	// Minimal Weight + Current Weight 
		// Comparison of the total weight of the path and the weight of the current node
        if (grandWeight < v.minWeight) {
		    vertexQueue.remove(v);	// remove target node
		    v.minWeight = grandWeight ;	// UPdate Distance
		    v.optimalPath = u;		// The Optimal Path
		    vertexQueue.add(v);	// Add The Optimal Path
		}
            }
        }
    }

    // Get The Shortest Path 
    // Logic. While RailPath is not Empty and set RailPath to previous node. Add the Node with each call where Previous is the Optimal Path when checking the Adjacent Nodes
    public static List<RailPath> getShortestPathTo(RailPath target)
    {
        List<RailPath> path = new ArrayList<RailPath>();	// New Array For The Smallest Path
        for (RailPath RailPath = target; RailPath != null; RailPath = RailPath.optimalPath)	// While Path Avaliable, Path Is Equal to the optimal Path
            path.add(RailPath);	// Add to the Path the Optimal Node Position
        Collections.reverse(path);	// Reverse Path
        return path;	// Return and Display Shortest Path
    }

    public static void main(String[] args)
    {
    // Nodes Sacramento Being Starting Point
    RailPath Sacramento = new RailPath("Sacramento");
	RailPath SanFran = new RailPath("SFO");	// Proposed Smallest Path: Sacramento -> SFO
	RailPath Atlanta = new RailPath("Atlanta"); // Proposed Smallest Path: Sacramento -> Atlanta
	RailPath Detroit = new RailPath("Detroit");	// Proposed Smallest Path: Sacramento -> Atlanta -> Detroit
	RailPath Chicago = new RailPath("Chicago");	// Proposed Smallest Path: Sacramento -> Atlanta -> Destroit -> Chicago
	RailPath LA = new RailPath("LA"); // Proposed Smallest Path: Sacramento -> Atlanta -> Destroit -> Chicago -> Vegas -> LA
	RailPath NewJersery = new RailPath("NJ");	//Proposed Smallest Path: Sacramento -> Atlanta -> NJ
	RailPath Vegas = new RailPath("Vegas");		// Proposed Smallest Path: Sacramento -> Atlanta -> Detroid -> Chicago -> Vegas
	RailPath Miami = new RailPath("Miami"); 	// Proposed Smallest Path: Sacramento -> Atlanta -> Nj -> Miami
	RailPath Boston = new RailPath("Boston");	// Proposed Smallest Path: Sacramento -> Atlanta -> NJ -> Boston
	RailPath DC = new RailPath("DC");			// Proposed Smallest Path: Error. Should be revised (Revised Edges of New York)
	RailPath Dallas = new RailPath("Dallas");	
	RailPath NewYork = new RailPath("New York");

	// Edges
	
	// Error Revision: Origionally Had All Edges Listed So Program was Not correctly getting to LA, New York, Or Sin City. Since Removed. 
	
	// Sacramento. 2 Edges SFO ($50.00) and Atlanta($35)
	Sacramento.adjacencies = new Edge[]{ new Edge(SanFran, 50),
	                             new Edge(Atlanta, 35)};
	
	// SFO 3 Edges Detroit(70), LA(60), Chicago(35). Sacramento 50 
	SanFran.adjacencies = new Edge[]{ new Edge(Detroit, 70),
	                             new Edge(LA, 60),
	                             new Edge(Chicago, 35)}; 
								 //new Edge(Sacramento, 50)};
	
	// Atlanta 3 Edges Detroit 20, Chicago 60, NJ 30. Sacramento 35
	Atlanta.adjacencies = new Edge[]{ new Edge(Detroit, 20),
                               new Edge(Chicago, 60), 
								new Edge(NewJersery, 30)};
								//new Edge(Sacramento, 35)};
	
	// Detroit 1 Edge Chicago 10. SFO 70, Atlanta 20
	Detroit.adjacencies = new Edge[]{ new Edge(Chicago, 10)};
								  //new Edge(SanFran, 70),
								  //new Edge(Atlanta, 20)};
	
	// Gotham City 2 Edges Vegas 5, Miami 40. Detroit 10, SFO 35, Atlanta 60
	Chicago.adjacencies = new Edge[]{ new Edge(Vegas, 5),
                               new Edge(Miami, 40)};
                               //new Edge(Detroit, 10),
                               //new Edge(SanFran, 35),
                               //new Edge(Atlanta, 60)};
	
	// LA  1 Edge Vegas 30. SFO 60
	LA.adjacencies = new Edge[]{ new Edge(Vegas, 30)};
								 //new Edge(SanFran, 60)};

	// NJ 2 Edges Miami 30, Boston 25. Atlanta 45
	NewJersery.adjacencies = new Edge[]{ new Edge(Miami, 30),
                               new Edge(Boston, 25)};
                               //new Edge(Atlanta, 45)};
	
	// Sin City 1 Edge Dallas 20. LA 30, Chicago 5
	Vegas.adjacencies = new Edge[]{ new Edge(Dallas, 20)};
								 // new Edge(LA, 30),
								 // new Edge(Chicago, 5)};
	
	// Miami 1 Edge 50. Chicago 40, NJ 30
	Miami.adjacencies = new Edge[]{ new Edge(DC, 50)};
								 //new Edge(Chicago, 40),
								 //new Edge(NewJersery, 30)};
	
	// Boston 1 Edge NY 20. NJ 25
	Boston.adjacencies = new Edge[]{ new Edge(NewYork, 20)};
                               //new Edge(NewJersery, 25) };
	
	// DC one Edge NY 15. Miami 50
	DC.adjacencies = new Edge[]{ //new Edge(Miami, 50),
                               new Edge(NewYork, 15) };
	
	// Dallas One Edge NY 10. Vegas 20
	Dallas.adjacencies = new Edge[]{ new Edge(NewYork, 10)};
                               //new Edge(Vegas, 20) };

	// Empire State of Mind. Dallas 10, DC 15, Boston 20
	NewYork.adjacencies = new Edge[]{ };

	// Initialize Rail Path
	RailPath[] vertices = { Sacramento, SanFran, Atlanta, Detroit, Chicago, LA, NewJersery, Vegas, Miami, Boston, DC, Dallas, NewYork };
        // Magic. 
		computePaths(Sacramento);
        // While Not Null
        // Basic Logic: So We Call minWeight to get the cost and path is initialized to getShortestPath which will record the previous destinations in a seperate array
        // and reversed using collection utilities to display the path
        
        for (RailPath v : vertices)
	{
	    System.out.println("Cost to " + v + ": $" + v.minWeight);	// Call Minimum Distance for Cost of Shortest Path
	    List<RailPath> path = getShortestPathTo(v);	// Path Set to shortest path from function
	    System.out.println("Path: " + path);	// Display Path 
	}
    }
}