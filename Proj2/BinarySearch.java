// package Proj2;

import java.util.*;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class BinarySearch {

    public static int search(int[] nums, int find){
        int lowidx = 0;
        int highidx = nums.length -1;

        while(lowidx <= highidx){
            int mididx = (highidx + lowidx)/2;
            // System.out.println(mididx + " | " + nums[mididx]);

            if(nums[mididx] == find){
                // System.out.println(mididx);
                return mididx;
            }

            if(nums[mididx] < find){
                lowidx = mididx+1;
            }else{
                highidx = mididx-1;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) throws Exception{
        String filename = args[0];
        
        int[] nums = new int[1000];

        int val;

        try{
            val = Integer.parseInt(args[1]);
        }catch(Exception e){
            val = (int)Math.random()*1000;
        }

        Scanner scan = new Scanner(new File(filename));

        for(int i = 0; scan.hasNextInt(); i++){
            nums[i] = scan.nextInt();
        }
        scan.close();

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long start = threadMXBean.getCurrentThreadCpuTime();
        
        int index = search(nums, val);

        long end = threadMXBean.getCurrentThreadCpuTime();

        long cpuTime = end-start;

        System.out.println("\nBinary Search: CPU Time: " + cpuTime / 1000000000.0 + " seconds - " + filename);
        System.out.println("# " + val + " found at line " + (index+1));
    }
}
