package quickSort;
import java.util.*;


public class QuickSort {
	
	// Generates random pivot value
	private void randPivot(int data[], int low, int high) {
		Random rand = new Random();
		int pivot = rand.nextInt(high-low) + low;
		
		int temp = data[pivot];
		data[pivot] = data[high];
		data[high] = temp;
	}
	
	private int partition(int data[], int low, int high) {
		
		// Chooses pivot randomly
		randPivot(data, low, high);
		int pivot = data[high];
		int i = low-1; // Index of smaller value
		
		for (int j = low; j < high; j++) {
			if (data[j] <= pivot) { // If current value is lesser or equal to pivot
				i++;
				
				// Swap data[i] and data[j]
				int temp = data[i];
				data[i] = data[j];
				data[j] = temp;
			}
		}
		
		// swap data[i+1] and data[high] (which is pivot)
		int temp = data[i+1];
		data[i+1] = data[high];
		data[high] = temp;
//		printData(data);
		
		return i+1;
		
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
