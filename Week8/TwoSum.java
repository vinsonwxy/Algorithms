import java.io.InputStreamReader;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Scanner;

public class TwoSum {
	private Set<Long> nums;

	public TwoSum() {
		nums = new HashSet<Long>();
		input();
	}

	private void input() {
		try {
			Scanner scanner = new Scanner (new InputStreamReader(this.getClass().
		    		getClassLoader().getResourceAsStream("2sum.txt")));
		    while(scanner.hasNextLine()) {
			   	String s = scanner.nextLine();
			   	long num = Long.parseLong(s);
			   	nums.add(num);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean containsSum(long target) {
		for (long num: nums) {
			if (nums.contains(target - num) && target - num != 2 * num) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		TwoSum ts = new TwoSum();
		int count = 0;
		for (long i = -10000; i < 10001; i++) {
			if (ts.containsSum(i)) {
				count += 1;
			}
		}
		System.out.println(count);
	}
}