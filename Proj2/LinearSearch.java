// package Proj2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class LinearSearch {
    public static void main(String[] args) {
        // String rngFile = "RandNumb.txt";
        // int target = 500;                               // number being searched for

        String rngFile = args[0];
        
        // int[] nums = new int[1000];

        int target;

        try{
            target = Integer.parseInt(args[1]);
        }catch(Exception e){
            target = (int)Math.random()*1000; // random number between 0-999
        }

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long start = threadMXBean.getCurrentThreadCpuTime();

        int index = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(rngFile))) {
            String line;

            // int index = 0;

            while ((line = br.readLine()) != null) {
                int number = Integer.parseInt(line);
                if (number == target) {
                    // System.out.println("# " + target + " found at line " + (index + 1));
                    break;
                }
                index++;
            }
        } 

        catch (IOException e) {
            e.printStackTrace();
        }

        long end = threadMXBean.getCurrentThreadCpuTime();
        long cpuTime = end - start;
        System.out.println("\nLinear Search: CPU Time: " + cpuTime / 1000000000.0 + " seconds - " + rngFile);
        System.out.println("# " + target + " found at line " + (index + 1));
    }
}


