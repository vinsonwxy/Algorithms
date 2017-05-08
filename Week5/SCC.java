import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Scanner;

public class SCC {
	int n = 875714;
	Map<Integer,List<Integer>> adjList;
	Map<Integer,List<Integer>> adjListRev;
	int[] ft;
	int t;
	int s;
	boolean[] marked;
	int[] leaders;

	public SCC() {
		init();
		t = 0;
		s = 0;
		marked = new boolean[n + 1];
		leaders = new int[n + 1];
	}
	
	void init() {
		adjList = new HashMap<Integer,List<Integer>>();
		adjListRev = new HashMap<Integer,List<Integer>>();
		ft = new int[n + 1];
		List<Integer> adj;
		try {
			Scanner scanner = new Scanner (new InputStreamReader(this.getClass().
		    		getClassLoader().getResourceAsStream("SCC.txt")));
		    while(scanner.hasNextLine()) {
			   	String s = scanner.nextLine().trim();
			   	String[] num = s.split(" ");
			   	if (!adjList.containsKey(Integer.parseInt(num[0]))) {
					adjList.put(Integer.parseInt(num[0]), new ArrayList<Integer>());
				}
				adj = adjList.get(Integer.parseInt(num[0]));
				adj.add(Integer.parseInt(num[1]));
				adjList.put(Integer.parseInt(num[0]), adj);

			   	if (!adjListRev.containsKey(Integer.parseInt(num[1]))) {
					adjListRev.put(Integer.parseInt(num[1]), new ArrayList<Integer>());
				}
				adj = adjListRev.get(Integer.parseInt(num[1]));
				adj.add(Integer.parseInt(num[0]));
				adjListRev.put(Integer.parseInt(num[1]), adj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void DFS_Loop() {

		for (int i = 1; i < n + 1; i++) {
			marked[i] = false;
		}
		for (int i = n; i > 0; i--) {
			if (!marked[i]) {
				revDFS(i);
			}
		}
		for (int i = 1; i < n + 1; i++) {
			marked[i] = false;
			leaders[i] = 0;
		}
		for (int i = n; i > 0; i--) {
			if (!marked[ft[i]]) {
				s = ft[i];
				DFS(ft[i]);
			}
		}
	}

	public void revDFS(int i) {
		marked[i] = true;
		List<Integer> edges = adjListRev.get(i);
		if (edges != null) {
			for (int j: edges) {
				if (!marked[j]) {
					revDFS(j);
				}
			}
		}
		t += 1;
		ft[t] = i;
	}
	
	public void DFS(int i) {
		marked[i] = true;
		leaders[s] += 1;
		List<Integer> edges = adjList.get(i);
		if (edges != null) {
			for (int j: edges) {
				if (!marked[j]) {
					DFS(j);
				}
			}
		}
	}

	public static void main(String[] args) {
		SCC scc = new SCC();
		scc.DFS_Loop();
		Arrays.sort(scc.leaders);
		for (int i = scc.n; i < scc.n - 5; i--) {
			System.out.println(scc.leaders[i]);
		}
	}
}