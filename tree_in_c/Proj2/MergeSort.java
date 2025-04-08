// package Proj2;

import java.io.*;
import java.util.*;
// import java.lang.*;
import java.lang.management.ThreadMXBean;
import java.lang.management.ManagementFactory;

public class MergeSort {
	public static void main(String[] args) {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		long start = 0;
		long end = 0;
		String inputFile = "RandNumb.txt"; // Input File with rand nums
		String outputFile = "sortedNumbMerge.txt"; // output file with sorted nums

		try {
			int[] arr = readArrFromFile(inputFile);
			start = threadMXBean.getCurrentThreadCpuTime();
			mergeSort(arr, 0, arr.length - 1);
			end = threadMXBean.getCurrentThreadCpuTime();
			writeArrToFile(arr, outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		long cpuTime = end - start;
		System.out.println("\nMerge Sort: CPU Time: " + cpuTime / 100000000.0 + " seconds");
	}

    // Reads numbers from a file and returns them as an integer array
    public static int[] readArrFromFile(String filename) throws IOException {
        // List<Integer> numberList = new ArrayList<>();
        // try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
        //     for (String line; (line = reader.readLine()) != null;)
        //         for (String numStr : line.split("\\s+")) // Split by whitespace
        //             try { numberList.add(Integer.parseInt(numStr.trim())); }
        //             catch (NumberFormatException e) { System.err.println("Invalid number format: " + numStr); }
        // }
        // if (numberList.isEmpty()) throw new IOException("File is empty or invalid.");
        // // return numberList.stream().mapToInt(i -> i).toArray();
        // return numberList.st1

        int[] nums = new int[1000];

        Scanner scan = new Scanner(new File(filename));

        for(int i = 0; scan.hasNextInt(); i++){
            nums[i] = scan.nextInt();
        }
        scan.close();

        return nums;
    }

	private static void mergeSort(int[] arr, int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merge(arr, left, mid, right);
		}
	}

	private static void merge(int[] arr, int left, int mid, int right) {
		int n1 = mid - left + 1, n2 = right - mid;
		int[] leftArr = new int[n1], rightArr = new int[n2];

		for (int i = 0; i < n1; i++) {
			leftArr[i] = arr[left + i];
		}
		for (int j = 0; j < n2; j++) {
			rightArr[j] = arr[mid + 1 + j];
		}

		int i = 0;
		int j = 0;
		int k = left;

		while (i < n1 && j < n2) {
			if (leftArr[i] <= rightArr[j]) {
				arr[k] = leftArr[i];
				i++;
			} else {
				arr[k] = rightArr[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			arr[k] = leftArr[i];
			i++;
			k++;
		}

		while (j < n2) {
			arr[k] = rightArr[j];
			j++;
			k++;
		}
	}

	private static void writeArrToFile(int[] arr, String filename) throws IOException {
		try (PrintWriter writer = new PrintWriter(new File(filename))) {
			for (int num : arr) {
				writer.println(num);
			}
		}
	}
}
