import java.util.Scanner;
import java.io.File;
import java.lang.Exception;

public class PA3 {
	
	public static int QuickSort1(int[] arr, int l, int r) {
		if (r - l < 1) {
			return 0;
		}
		int pivot = arr[l];
		int i = l + 1;
		for (int j = l + 1; j <= r; j++) {
			if (arr[j] < pivot) {
				int temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
				i += 1;
			}
		}
		arr[l] = arr[i - 1];
		arr[i - 1] = pivot;
		int left = QuickSort1(arr, l, i - 2);
		int right = QuickSort1(arr, i, r);
		return r - l + left + right;
	}

	public static int QuickSort2(int[] arr, int l, int r) {
		if (r - l < 1) {
			return 0;
		}
		int pivot = arr[r];
		arr[r] = arr[l];
		arr[l] = pivot;
		int i = l + 1;
		for (int j = l + 1; j <= r; j++) {
			if (arr[j] < pivot) {
				int temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
				i += 1;
			}
		}
		arr[l] = arr[i - 1];
		arr[i - 1] = pivot;
		int left = QuickSort2(arr, l, i - 2);
		int right = QuickSort2(arr, i, r);
		return r - l + left + right;
	}

	private static int median(int[] arr, int l, int r) {
		int mid = (l + r) / 2;
		if (arr[l] < arr[mid]) {
			if (arr[mid] < arr[r]) {
				return mid;
			} else if (arr[l] < arr[r]) {
				return r;
			} else {
				return l;
			}
		} else {
			if (arr[mid] > arr[r]) {
				return mid;
			} else if (arr[l] > arr[r]) {
				return r;
			} else {
				return l;
			}
		}
	}

	public static int QuickSort3(int[] arr, int l, int r) {
		if (r - l < 1) {
			return 0;
		}
		int pivotIndex;
		if (r - l == 1) {
			pivotIndex = l;
		} else {
			pivotIndex = median(arr, l ,r);
		}
		int pivot = arr[pivotIndex];
		arr[pivotIndex] = arr[l];
		arr[l] = pivot;
		int i = l + 1;
		for (int j = l + 1; j <= r; j++) {
			if (arr[j] < pivot) {
				int temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
				i += 1;
			}
		}
		arr[l] = arr[i - 1];
		arr[i - 1] = pivot;
		int left = QuickSort3(arr, l, i - 2);
		int right = QuickSort3(arr, i, r);
		return r - l + left + right;
	}

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(new File("QuickSort.txt"));
			int[] intArray = new int [10000];
			for (int i = 0; i < 10000; i++) {
			   intArray[i] = scanner.nextInt();
			}
			//System.out.println(QuickSort1(intArray, 0 , 9999));
			//System.out.println(QuickSort2(intArray, 0 , 9999));
			System.out.println(QuickSort3(intArray, 0 , 9999));
		} catch(Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}