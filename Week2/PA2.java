import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.lang.Exception;

public class PA2 {

	public static long invCount(int[] arr) {
		if (arr.length < 2) {
        	return 0;
		}

	    int m = (arr.length + 1) / 2;
	    int left[] = Arrays.copyOfRange(arr, 0, m);
	    int right[] = Arrays.copyOfRange(arr, m, arr.length);

    	return invCount(left) + invCount(right) + merge(arr, left, right);
		
	}

	public static long merge(int[] arr, int[] left, int[] right) {
	    int i = 0, j = 0, count = 0;
	    while (i < left.length || j < right.length) {
	        if (i == left.length) {
	            arr[i+j] = right[j];
	            j++;
	        } else if (j == right.length) {
	            arr[i+j] = left[i];
	            i++;
	        } else if (left[i] <= right[j]) {
	            arr[i+j] = left[i];
	            i++;                
	        } else {
	            arr[i+j] = right[j];
	            count += left.length-i;
	            j++;
	        }
	    }
	    return count;
	}
	
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(new File("IntegerArray.txt"));
			int[] intArray = new int [100000];
			for (int i = 0; i < 100000; i++) {
			   intArray[i] = scanner.nextInt();
			}
			System.out.println(invCount(intArray));
		} catch(Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

	}
}