import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class LinearSearch {
    public static void main(String[] args) {
        String rngFile = "RandNumb.txt";
        int target = 500;                               // number being searched for
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long start = threadMXBean.getCurrentThreadCpuTime();

        try (BufferedReader br = new BufferedReader(new FileReader(rngFile))) {
            String line;
            int index = 0;

            while ((line = br.readLine()) != null) {
                int number = Integer.parseInt(line);
                if (number == target) {
                    System.out.println("# " + target + " found at line " + (index + 1));
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
        System.out.println("\nCPU Time: " + cpuTime / 1000000000.0 + " seconds");
    }
}


