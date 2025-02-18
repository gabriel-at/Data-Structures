package randNumGen;

//import java.io.FileWriter;
import java.util.Random;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class RandomNumbers {
    public static void main(String[] args) {
        int[] randomNumbers = new int[1000];          // array elements are 0-999
//        Random a = new Random();                      // random number generator object
//        Set<Integer> b = new HashSet<>();

//        for (int i = 0; i < randomNumbers.length; i++) {       
//            int number;
//
//            do{
//                number = a.nextInt(1000);       // will keep generating a random number between 0-999, until
//            }                                         // it lands on one not present in the hash set.            
//            while (b.contains(number));               
//            b.add(number);                            // adds number to hash set, so it can't be used in array again.
//        
//            randomNumbers[i] = number;
//        }
        
        randomNumbers = randomArray(randomNumbers);

//        try (FileWriter c = new FileWriter("RandNumb.txt")) {
//            for (int number : randomNumbers) {                    // for-each block specifying iteration for every 
//                c.write(number + "\n");                           // element of the randomNumbers array
//            }
//        } catch (IOException e) {                                 // if IOExceptions are not handled the program
//            e.printStackTrace();                                  // will not compile
//        }
//        String fileName = "RandNumb.txt";
//        printArrayToFile(randomNumbers, fileName);

    }
    
    // Method to return sorted array, useful as a module
    public static int[] randomArray(int[] randomNumbers) {
    	Random a = new Random();                      // random number generator object
        Set<Integer> b = new HashSet<>();

        for (int i = 0; i < randomNumbers.length; i++) {       
            int number;

            do{
                number = a.nextInt(1000);       // will keep generating a random number between 0-999, until
            }                                         // it lands on one not present in the hash set.            
            while (b.contains(number));               
            b.add(number);                            // adds number to hash set, so it can't be used in array again.
        
            randomNumbers[i] = number;
        }
    	
    	return randomNumbers;
    }
    
//    public static void printArrayToFile(int[] randomNumbers, String fileName) {
//        try (FileWriter c = new FileWriter(fileName)) {			  // fileName is "RandNumb.txt"
//            for (int number : randomNumbers) {                    // for-each block specifying iteration for every 
//                c.write(number + "\n");                           // element of the randomNumbers array
//            }
//        } catch (IOException e) {                                 // if IOExceptions are not handled the program
//            e.printStackTrace();                                  // will not compile
//        }
//    	
//    }
}
