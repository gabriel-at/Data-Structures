// package quickSort;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;


public class QuickSort {

	// public static void writeNumbersToFile(String filename, int[] numbers) throws IOException {
    //     try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
    //         // writer.write(Arrays.toString(numbers).replaceAll("[\\[\\],]", ""));
    //         // writer.write(Arrays.toString(numbers));
    //         for(int i : numbers){
    //             writer.write(Integer.toString(i));
    //             writer.write('\n');
    //         }
    //     }
    // }
	private static void writeNumbersToFile(String filename, int[] arr) throws IOException {
		try (PrintWriter writer = new PrintWriter(new File(filename))) {
			for (int num : arr) {
				writer.println(num);
			}
		}
	}
	
	public static void swap(int[] data, int a, int b)
    {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }
	
	private static int partition(int data[], int low, int high) {
		
		// Pivot at value of starting index 
		int pivot = data[low];
		int i = high; 

		
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
	
	public static void sort(int data[], int low, int high) {
		if (low < high) {
			// Index where partition groups occurs
			int partIndex = partition(data, low, high);

			sort(data, low, partIndex-1);
			sort(data, partIndex+1, high);
		}
		


	}

	public static void main(String[] args) throws Exception{
        String filename = "RandNumb.txt";
        String fileNameOutput = "sortedNumbQuick.txt";

        int[] nums = new int[1000];


        Scanner scan = new Scanner(new File(filename));

        for(int i = 0; scan.hasNextInt(); i++){
            nums[i] = scan.nextInt();
        }
        scan.close();

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long start = threadMXBean.getCurrentThreadCpuTime();
        
        sort(nums, 0, 999);

        long end = threadMXBean.getCurrentThreadCpuTime();
        long cpuTime = end-start;

		System.out.println("\nQuick Sort: CPU Time: " + cpuTime / 100000000.0 + " seconds");

		writeNumbersToFile(fileNameOutput, nums);
	}
}
