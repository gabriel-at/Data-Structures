// package bubble;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.lang.management.ThreadMXBean;
import java.lang.management.ManagementFactory;

public class BubbleSortRandomNumbers {
    // Optimized Bubble Sort algorithm
    public static void bubbleSort(int[] arr) {
        for (int i = 0, n = arr.length; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap adjacent elements if they are in the wrong order
                    int temp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // If no swaps occurred, the list is already sorted
        }
    }

    // Reads numbers from a file and returns them as an integer array
    public static int[] readNumbersFromFile(String filename) throws IOException {
        List<Integer> numberList = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            for (String line; (line = reader.readLine()) != null;)
                for (String numStr : line.split("\\s+")) // Split by whitespace
                    try { numberList.add(Integer.parseInt(numStr.trim())); }
                    catch (NumberFormatException e) { System.err.println("Invalid number format: " + numStr); }
        }
        if (numberList.isEmpty()) throw new IOException("File is empty or invalid.");
        return numberList.stream().mapToInt(i -> i).toArray();
    }

    // Writes sorted numbers to a file
    public static void writeNumbersToFile(String filename, int[] numbers) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            // writer.write(Arrays.toString(numbers).replaceAll("[\\[\\],]", ""));
            // writer.write(Arrays.toString(numbers));
            for(int i : numbers){
                writer.write(Integer.toString(i));
                writer.write('\n');
            }
        }
    }

    public static void main(String[] args) {
        // File paths for input and output
        // String inputFilename = "RandNumb.txt", 

        String inputFilename = "RandNumb.txt";
        String outputFilename = "sortedNumbBubble.txt";
        
        // int[] nums = new int[1000];

        int target;

        try{
            target = Integer.parseInt(args[1]);
        }catch(Exception e){
            target = (int)Math.random()*1000; // random number between 0-999
        }

        try {
            // Read numbers from input file
            int[] numbers = readNumbersFromFile(inputFilename);
            // System.out.println("Original: " + Arrays.toString(numbers));
            
            // Sort the numbers and measure execution time
            // long startTime = System.currentTimeMillis();
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		    long start = threadMXBean.getCurrentThreadCpuTime();
            bubbleSort(numbers);
            long end = threadMXBean.getCurrentThreadCpuTime();
		    long cpuTime = end - start;
            System.out.println("\nBubble Sort: CPU Time: " + cpuTime / 100000000.0 + " seconds");

            // System.out.println("Sorted in " + (System.currentTimeMillis() - startTime) + " ms");
            
            // Write sorted numbers to output file
            writeNumbersToFile(outputFilename, numbers);
            // System.out.println("Saved to " + outputFilename);
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
    }
}

