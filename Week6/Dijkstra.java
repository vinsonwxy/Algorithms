import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dijkstra {
	private List<Integer> X;
	private int[] A;
	private HashMap<Integer, List<EdgeTo>> adjList;
	private int n;
	 
	public Dijkstra() {
		init();
		n = adjList.size();
		X = new ArrayList<Integer>();
		A = new int[n + 1];
		X.add(1);
		A[1] = 0;
		for (int i = 2; i < n + 1; i++) {
			A[i] = 1000000;
		}
	}

	private void init() {
		adjList = new HashMap<Integer,List<EdgeTo>>();
		try {
			Scanner scanner = new Scanner (new InputStreamReader(this.getClass().
		    		getClassLoader().getResourceAsStream("dijkstraData.txt")));
		    while(scanner.hasNextLine()) {
			   	String s = scanner.nextLine();
			   	String[] num = s.split("\\t");
			   	List<EdgeTo> adj = new ArrayList<>();
			   	for (int i = 1; i < num.length; i++) {
			   		String[] e = num[i].split(",");
			   		EdgeTo et = new EdgeTo(Integer.parseInt(e[0]), Integer.parseInt(e[1]));
				 	adj.add(et);
			   	}
			   	adjList.put(Integer.parseInt(num[0]), adj);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class EdgeTo {
		public int node;
		public int dist;

		public EdgeTo(int n, int d) {
			node = n;
			dist = d;
		}
	}

	public void mainLoop() {
		int minScore;
		int vStar;
		int wStar;
		boolean findNew;
		while (X.size() < n) {
			minScore = 1000000;
			vStar = 0;
			wStar = 0;
			findNew = false;
			for (int v: X) {
				List<EdgeTo> adj = adjList.get(v);
				for (EdgeTo edge: adj) {
					if (!X.contains(edge.node) && edge.dist + A[v] < minScore) {
						minScore = edge.dist + A[v];
						vStar = v;
						wStar = edge.node;
						findNew = true;
					}
				}
			}
			if (!findNew) {
				return;
			}
			X.add(wStar);
			A[wStar] = minScore;
		}
	}

	public static void main(String[] args) {
		Dijkstra d = new Dijkstra();
		d.mainLoop();
		System.out.println(d.A[7]);
		System.out.println(d.A[37]);
		System.out.println(d.A[59]);
		System.out.println(d.A[82]);
		System.out.println(d.A[99]);
		System.out.println(d.A[115]);
		System.out.println(d.A[133]);
		System.out.println(d.A[165]);
		System.out.println(d.A[188]);
		System.out.println(d.A[197]);
	}
}