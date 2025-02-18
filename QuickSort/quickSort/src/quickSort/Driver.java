package quickSort;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.management.*;


public class Driver {

	public static void main(String[] args) {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		long start = getRunTime(threadMXBean);
		
		int[] data = new int[1000]; // Empty integer array of length 1000
		String fileNameInput = "RandNumb.txt"; // Name of file for random array of integers
		String fileNameOutput = "sortednumb.txt"; // Name of file for sorted array of integers
		
		data = readArrayFromFile(data, fileNameInput); // Initializes given array with integers read from "RandNumb.txt"
		
//		printArray(data);
//		printArrayToFile(data, fileNameInput); // Prints random array of integers to file "RandNumb.txt"
		
		QuickSort qs = new QuickSort(); // Creates QuickSort object, qs, from QuickSort class
		qs.sort(data, 0, data.length-1); // Calls quick sort algorithm to sort data
		
//		printArray(data);
		printArrayToFile(data, fileNameOutput); // Prints sorts array of integers to file "sortednumb.txt
		
		long end = getRunTime(threadMXBean);
		long cpuTime = end - start;
		System.out.println("CPU Time Taken: " + cpuTime / 1000000000.0 + " seconds");
		
	}
	
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	
	// General use printToFile method depending on fileName parameter
    public static void printArrayToFile(int[] data, String fileName) {
        try (FileWriter c = new FileWriter(fileName)) {			  // fileName is "RandNumb.txt"
            for (int number : data) {                    // for-each block specifying iteration for every 
                c.write(number + "\n");                           // element of the randomNumbers array
            }
        } catch (IOException e) {                                 // if IOExceptions are not handled the program
            e.printStackTrace();                                  // will not compile
        }
    	
    }
    
    public static int[] readArrayFromFile(int[] data, String fileName) {
    	try (BufferedReader r = new BufferedReader(new FileReader(fileName))) {
    		for (int i = 0; i < data.length; i++) {
    			data[i] = Integer.parseInt(r.readLine());
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return data;
    }
    
    public static long getRunTime(ThreadMXBean threadMXBean) {
    	return threadMXBean.getCurrentThreadCpuTime();
    }
    
    

}
