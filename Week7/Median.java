import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class Median {
	private PriorityQueue<Integer> low;
	private PriorityQueue<Integer> high;
	
	public Median() {
		low = new PriorityQueue<Integer>(Collections.reverseOrder());
		high = new PriorityQueue<Integer>();
	}

	public void add(int n) {
		if (low.size() == 0 || n < low.peek()) {
			low.add(n);
		} else {
			high.add(n);
		}
		if (low.size() > high.size() + 1) {
			high.add(low.poll());
		} else if (low.size() < high.size()) {
			low.add(high.poll());
		}
	}

	public int compute() {
		return low.peek();
	}

	public List<Integer> input() {
		List<Integer> nums = new ArrayList<Integer>();

		try {
			Scanner scanner = new Scanner (new InputStreamReader(this.getClass().
		    		getClassLoader().getResourceAsStream("Median.txt")));
		    while(scanner.hasNextLine()) {
			   	String s = scanner.nextLine();
			   	int num = Integer.parseInt(s);
			   	nums.add(num);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nums;
	}

	public static void main(String[] args) {
		Median m = new Median();
		List<Integer> nums = m.input();
		int sum = 0;
		while (!nums.isEmpty()) {
			m.add(nums.remove(0));
			sum += m.compute();
		}
		System.out.print(sum);
	}
}