import java.io.FileWriter;
import java.util.Random;
import java.io.IOException;


public class RandomNumbers {
    public static void main(String[] args) {
        int[] randomNumbers = new int[1000];                      //array elements are 0-999
        Random a = new Random();                                  //random number generator object

        for (int i = 0; i < randomNumbers.length; i++) {          //argument for a.nextInt is the bound,
            randomNumbers[i] = a.nextInt(1000);             //ensuring generated numbers are between 0-999
        }                                                         

        try (FileWriter b = new FileWriter("RandNumb.txt")) {
            for (int number : randomNumbers) {                    // for-each block specifying iteration for every 
                b.write(number + "\n");                           // element of the randomNumbers array
            }
        } catch (IOException e) {                                 // if IOExceptions are not handled the program
            e.printStackTrace();                                  // will not compile
        }
    }
}
