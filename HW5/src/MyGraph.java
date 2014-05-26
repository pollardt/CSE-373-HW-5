import java.util.*;

//import sun.security.provider.certpath.Vertex;

/**
 * A representation of a graph. Assumes that we do not have negative cost edges
 * in the graph.
 */
public class MyGraph implements Graph 
{
	private ArrayList<ArrayList<Edge>> outList;
	private HashMap<Vertex, Integer> vMap;
	private Set<Edge> edgeSet;
	
	/**
	 * Creates a MyGraph object with the given collection of vertices and the
	 * given collection of edges.
	 * 
	 * @param v
	 *            a collection of the vertices in this graph
	 * @param e
	 *            a collection of the edges in this graph
	 */
	public MyGraph(Collection<Vertex> v, Collection<Edge> e) 
	{
		vMap = new HashMap<Vertex, Integer>();
		
		Iterator<Vertex> vIt = v.iterator();
		int i = 0;
		Vertex nV;
		while (vIt.hasNext())
		{
			nV = vIt.next();
			if(!vMap.containsKey(nV)) //filters duplicate edges.
			{
				vMap.put(nV, i);
				i++;
			}
		}
		
		outList = new ArrayList<ArrayList<Edge>>();
		outList.ensureCapacity(i);
		for(int j = 0; j < i; j++) //Initialize the internal ArrayLists
		{
			outList.add(new ArrayList<Edge>());
		}
		
		edgeSet = new HashSet<Edge>();
		
		Iterator<Edge> eIt = e.iterator();
		int index;
		Edge next;
		while(eIt.hasNext()) //Iterate through the inputted edges.
		{
			next = eIt.next();
			if (next.getWeight() < 0) //Negative Weights are Not allowed.
			{
				throw new NegativeEdgeWeightException();
			}
			if (!vMap.containsKey(next.getDestination()) || !vMap.containsKey(next.getSource())) //Edges must have existing vertexs.
			{
				throw new NoSuchVertexException();
			}
			
			int nextWeight = edgeCost(next.getSource(), next.getDestination());
			
			if (nextWeight == -1) //Edge not already present.
			{
				edgeSet.add(next);
				index = vMap.get(next.getSource());
				outList.get(index).add(next);
			} else 
			{
				if (nextWeight != next.getWeight())
				{
					throw new ConflictingDirectedEdgeException();
				}
			}
		}
	}
	


	/**
	 * Return the collection of vertices of this graph
	 * 
	 * @return the vertices as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Vertex> vertices() {
		return vMap.keySet();
	}

	/**
	 * Return the collection of edges of this graph
	 * 
	 * @return the edges as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Edge> edges() 
	{
		return edgeSet;
	}

	/**
	 * Return a collection of vertices adjacent to a given vertex v. i.e., the
	 * set of all vertices w where edges v -> w exist in the graph. Return an
	 * empty collection if there are no adjacent vertices.
	 * 
	 * @param v
	 *            one of the vertices in the graph
	 * @return an iterable collection of vertices adjacent to v in the graph
	 * @throws IllegalArgumentException
	 *             if v does not exist.
	 */
	@Override
	public Collection<Vertex> adjacentVertices(Vertex v) {
		int index = vMap.get(v);
		
		ArrayList<Edge> outEdges = outList.get(index);
		Set<Vertex> adjSet = new HashSet<Vertex>();
		
		for (Edge e: outEdges)
		{
			adjSet.add(e.getDestination());
		}
		
		return adjSet;
	}

	/**
	 * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed
	 * graph. Assumes that we do not have negative cost edges in the graph.
	 * 
	 * @param a
	 *            one vertex
	 * @param b
	 *            another vertex
	 * @return cost of edge if there is a directed edge from a to b in the
	 *         graph, return -1 otherwise.
	 * @throws IllegalArgumentException
	 *             if a or b do not exist.
	 */
	@Override
	public int edgeCost(Vertex a, Vertex b) {
		if(!vMap.containsKey(a) || !vMap.containsKey(b))
		{
			throw new IllegalArgumentException();
		}
		int weight = -1;
		int vIndex = vMap.get(a);
		ArrayList<Edge> outEdges = outList.get(vIndex);
		for(Edge e : outEdges)
		{
			if (e.getDestination().equals(b))
			{
				weight = e.getWeight();
			}
		}
		return weight;
	}

	/**
	 * Returns the shortest path from a to b in the graph, or null if there is
	 * no such path. Assumes all edge weights are nonnegative. Uses Dijkstra's
	 * algorithm.
	 * 
	 * @param a
	 *            the starting vertex
	 * @param b
	 *            the destination vertex
	 * @return a Path where the vertices indicate the path from a to b in order
	 *         and contains a (first) and b (last) and the cost is the cost of
	 *         the path. Returns null if b is not reachable from a.
	 * @throws IllegalArgumentException
	 *             if a or b does not exist.
	 */

	
	public Path shortestPath(Vertex a, Vertex b) {
		return null;
		// YOUR CODE HERE (you might comment this out this method while doing
		// Part 1)

	}

}
