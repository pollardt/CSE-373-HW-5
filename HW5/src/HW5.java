import java.util.*;

public class HW5 {
	public static void main(String[] args)
	{
		Vertex source = new Vertex("test1");
		Vertex dest = new Vertex("test2");
		LinkedList<Edge> e = new LinkedList<Edge>();
		e.add(new Edge(source, dest, 2));
		
		LinkedList<Vertex> v = new LinkedList<Vertex>();
		v.add(source);
		v.add(dest);
		Graph test = new MyGraph(v,e);
		
		Collection<Vertex> vSet = test.vertices();
		System.out.println(vSet.toString());
		
		Collection<Edge> eSet = test.edges();
		System.out.println(eSet.toString());
		
		Collection<Vertex> adjS = test.adjacentVertices(source);
		System.out.println(adjS.toString());
		
		Collection<Vertex> adjD = test.adjacentVertices(dest);
		System.out.println(adjD.toString());
		
		int C = test.edgeCost(source, dest);
		System.out.println(C);
	}
}
