package computernetworks;
import java.util.*;
/* 	Implementation of dijkstra's algorithm
 *	Contains : 	Method to find shortest path 
 *				Method to find connection table for a given node*/	
public class dijkistra 
{
	public int[][] matrix;
	private int[] sdist; // except start initialize rest to int.max 
	private int[] prevnd; // list of previous nodes from which shortest dist was possible 
	private int start, end;//intializing source and destination node

	public void findshortest(int startnd, int endnd) 
	{
		matrix = InputParser.a;
		int len = matrix.length;			// total number of routers
		sdist=  new int[len];
		prevnd=  new int[len];
		start = startnd-1;
		end=endnd-1;

		// Queue to hold the router children list of current router
		Set<Integer> unvisited = new HashSet<>();

		// Intitalizing all node dist to infinity 
		for(int i=0;i<len;i++)
		{
			unvisited.add(i);
			if(i != start)
				sdist[i] = Integer.MAX_VALUE;
		}

		prevnd[start] = start;//setting previous node of source to point to itself

		while(!unvisited.isEmpty())//check for unvisited nodes
		{
			int currnode = getNext(sdist,unvisited);//method call to get the next node 
			if(currnode==-1)
				break;
			unvisited.remove(currnode);//remove current node from unvisited
			for(int i=0; i<len; i++)
			{
				if(unvisited.contains(i) && matrix[currnode][i] > 0)
				{
					int dist = sdist[currnode]+matrix[currnode][i];

					if(dist < sdist[i])
					{
						sdist[i] = dist;
						prevnd[i] = currnode;
					}
				}
			}
		}
	}
	
	public void PrintShortestPath() //method call to print shortest path
	{
		int startnd = start+1, endnd=end+1;
		System.out.println("\nShortest distance from Router " + startnd +" to Router " +endnd + " is : " + sdist[end]);
		int x=end;
		String path=Integer.toString(endnd);
		while(true)
		{
			if(prevnd[x]==x)
				break;

			path +=" >- "+(prevnd[x]+1);
			x = prevnd[x];
		}


		for(int i=path.length()-1;i>=0;i--)
		{
			System.out.print(path.charAt(i));
		}
	}

	private int getNext(int[] sdist, Set<Integer> node_order) {

		int min = Integer.MAX_VALUE;
		int nd = -1;
		for(int x: node_order)
		{
			if(sdist[x] < min)
			{
				nd = x;
				min = sdist[x];
			}
		}
		return nd;
	}

	public void connectiontable(int startnd)
	{
		matrix = InputParser.a;
		System.out.println("\n \t\t\t ------- Printing Connection Table for Router " + startnd + " ------- ");
		int max = Integer.MAX_VALUE;
		if(matrix.length != startnd)
			findshortest(startnd, matrix.length);
		else
			findshortest(startnd, matrix.length-1);
		
		for(int i=0;i<matrix.length;i++)
		{
			if(i == startnd-1)
			{
				System.out.println(startnd + "\t --");
			}
			else
			{
				int prev = i;
				while(true)
				{
					if(prevnd[prev] == prev || prevnd[prev] == startnd-1)
						break;
					else
						prev = prevnd[prev];
				}
				if(sdist[i] != max)
					System.out.println((i+1)+"\t "+(prev+1));
				else
					System.out.println((i+1)+"\t --");
			}
		}
	}

}

