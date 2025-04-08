import java.io.*;
import java.util.*;

public class Driver {

  public static void main(String[] args) {

    String fileNames[][] = {{
      "./TestCase1/Department-ComputerScience.txt",
      "./TestCase1/Department-Mathematics.txt",
      "./TestCase1/Department-Chemistry.txt",
      "./TestCase1/Department-PhysicsAndAstronomy.txt"},
      {
      "./TestCase1/Department-ComputerScience.txt",
      "./TestCase1/Department-Mathematics.txt",
      "./TestCase1/Department-Chemistry.txt",
      "./TestCase1/Department-PhysicsAndAstronomy.txt"},
      {
      "./TestCase2/Department-DeptA.txt",
      "./TestCase2/Department-DeptB.txt",
      "./TestCase2/Department-DeptC.txt",
      "./TestCase2/Department-DeptD.txt",
      "./TestCase2/Department-DeptE.txt",
      "./TestCase2/Department-DeptF.txt",
      "./TestCase2/Department-DeptG.txt"}
      };
                                                

    System.out.println(  ); 
    ResourceManagement.printName( );
    System.out.println(  );

    //Call student solution for the test files:
    double budgets[] = {41850.00, 0.01, 27961.10};
    for( int i=0; i<3; i++ )
      testFile( fileNames[i], budgets[i] );
    
    System.out.println(  ); 
    ResourceManagement.printName( );
    System.out.println(  );
    
  }

  public static void testFile( String fileNames[], double budget ) {  
    String budgetString = String.format("$%.2f", budget );
    System.out.println("----------------------------TESTING with budget = " + budgetString + "----------------------------");
    System.out.println("");

    ResourceManagement rm = new ResourceManagement(fileNames, budget);
    rm.printSummary();  
  }
}