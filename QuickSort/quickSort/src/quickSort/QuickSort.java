package quickSort;
import java.util.*;


public class QuickSort {
	
	public static void swap(int[] data, int a, int b)
    {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }
	
	private int partition(int data[], int low, int high) {
		
		// Pivot at value of starting index 
		int pivot = data[low];
		int start = low; // start is start of array
		int i = high; 
		int end = high; // end is end of array
		
		for (int j = high; j > low; j--) {
			if (data[j] > pivot) { // If current value is lesser or equal to pivot
				// Swap data[i--] and data[j]
				swap(data, j, i--);
			}
		}
		
		// swap data[low] and data[i] (should be pivot)
		swap(data, low, i);
		
//		printData(data);
		
		return i;
		
	}
	
	public void sort(int data[], int low, int high) {
		if (low < high) {
			// Index where partition groups occurs
			int partIndex = partition(data, low, high);

			sort(data, low, partIndex-1);
			sort(data, partIndex+1, high);
		}
		


	}
	
//	public static void main(String artgs[]) {
//		int data[] = {-4, 1, 25, 50, 8, 10, 2};
//		int N = data.length;		
//
//		QuickSort qs = new QuickSort();
//		qs.sort(data, 0, N-1);
//		
//		qs.printData(data);
//	}
//	
//	private void printData(int[] data) {
//		for (int i = 0; i < data.length; i++) {
//			System.out.print(data[i] + " ");
//		}
//		System.out.println();
//	}
	
}
