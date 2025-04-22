import java.io.*;
import java.util.*;

/* ResourceManagement
 *
 * Stores the information needed to decide which items to purchase for the given budget and departments
 */
public class ResourceManagement
{
  private PriorityQueue<Department> departmentPQ; /* priority queue of departments */
  private Double remainingBudget;                 /* the budget left after purchases are made (should be 0 after the constructor runs) */
  private Double budget;                          /* the total budget allocated */
  
  /* TO BE COMPLETED BY YOU
   * Fill in your name in the function below
   */  
  public static void printName( )
  {
    /* TODO : Fill in your name */
    System.out.println("This solution was completed by:");
    System.out.println("Erick");
    System.out.println("Gabriel");
    System.out.println("Jackson");
    System.out.println("Joseph");
    System.out.println("Odaba");
  }

  /* Constructor for a ResourceManagement object
   * TODO
   * Simulates the algorithm from the pdf to determine what items are purchased
   * for the given budget and department item lists.
   */
  public ResourceManagement( String fileNames[], Double budget ) {
    /* Create a department for each file listed in fileNames */
    this.budget = budget;
    this.remainingBudget = budget;
	  departmentPQ = new PriorityQueue<>();

	  for (int i = 0; i < fileNames.length; i++) {
		  File f = new File(fileNames[i]);
		  Scanner scan;

	    try {
		    scan = new Scanner(f);
        
		    departmentPQ.add(new Department(fileNames[i])); //4.09gt - pass file path to Department
        scan.close();
        
	    } catch(Exception e) {
		    System.err.println(e);
	    }
  }  

    while (!departmentPQ.isEmpty()) {
      Department department = departmentPQ.poll(); //4.09gt - get the department with highest priority

      while (!department.itemsDesired.isEmpty()) {
        Item item = department.itemsDesired.poll();
        
        if (item.price <= remainingBudget) {
          department.itemsReceived.add(item);
          remainingBudget -= item.price;
          department.priority += item.price;
          
          String price = String.format("$%.2f", item.price);
          System.out.printf("Department of %-30s- %-30s- %30s\n", department.name, item.name, price);
        } else { 
          //Skip the item
          department.itemsRemoved.add(item);
        }

      }
    }
    /* Simulate the algorithm for picking the items to purchase */
    /* Be sure to print the items out as you purchase them */
    /* Here's the part of the code I used for printing prices as items */
    //String price = String.format("$%.2f", /*Item's price*/ );
    //System.out.printf("Department of %-30s- %-30s- %30s\n", /*Department's name*/, /*Item's name*/, price );
    
    
  }  
  /* printSummary
   * TODO
   * Print a summary of what each department received and did not receive.
   * Be sure to also print remaining items in each itemsDesired Queue.
   */      
  public void printSummary(  ){
    
    /* Here's the part of the code I used for printing prices */
    //String price = String.format("$%.2f", /*Item's price*/ );
    //System.out.printf("%-30s - %30s\n", /*Item's name*/, price );
  }   
}

/* Department
 *
 * Stores the information associated with a Department at the university
 */
class Department implements Comparable<Department>
{
  String name;                /* name of this department */
  Double priority;            /* total money spent on this department */
  Queue<Item> itemsDesired;   /* list of items this department wants */
  Queue<Item> itemsReceived;  /* list of items this department received */
  Queue<Item> itemsRemoved;   /* list of items that were skipped because they exceeded the remaining budget */

  /* TODO
   * Constructor to build a Department from the information in the given fileName
   */
 public Department(String fileName) {
    /* Open the fileName, create items based on the contents, and add those items to itemsDesired */
    itemsDesired = new LinkedList<>();
    itemsReceived = new LinkedList<>();
    itemsRemoved = new LinkedList<>();
    priority = 0.0;

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        System.out.println("Reading file: " + fileName); // Debugging output
        name = br.readLine(); // Read the department name

        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim(); // Remove leading/trailing whitespace
            if (line.isEmpty()) {
                continue; // Skip blank lines
            }

            String itemName = line; // Read the item name
            if ((line = br.readLine()) != null) { // Read the next line for the price
                try {
                    double itemPrice = Double.parseDouble(line.trim());
                    itemsDesired.add(new Item(itemName, itemPrice)); // Add the item with name and price
                } catch (NumberFormatException e) {
                    System.err.println("Invalid price for item: " + itemName + " in file: " + fileName);
                }
            } else {
                System.err.println("Missing price for item: " + itemName + " in file: " + fileName);
            }
        }
    } catch (IOException e) {
        System.err.println("Error reading file: " + fileName);
        e.printStackTrace();
    }
}
  
  /*
   * Compares the data in the given Department to the data in this Department
   * Returns -1 if this Department comes first
   * Returns 0 if these Departments have equal priority
   * Returns 1 if the given Department comes first
   *
   * This function is to ensure the departments are sorted by the priority when put in the priority queue 
   */
  public int compareTo( Department dept ){
    return this.priority.compareTo( dept.priority );
  }

  public boolean equals( Department dept ){
    return this.name.compareTo( dept.name ) == 0;
  }

  @Override 
  @SuppressWarnings("unchecked") //Suppresses warning for cast
  public boolean equals(Object aThat) {
    if (this == aThat) //Shortcut the future comparisons if the locations in memory are the same
      return true;
    if (!(aThat instanceof Department))
      return false;
    Department that = (Department)aThat;
    return this.equals( that ); //Use above equals method
  }
  
  @Override
  public int hashCode() {
    return name.hashCode(); /* use the hashCode for data stored in this name */
  }

  /* Debugging tool
   * Converts this Department to a string
   */	
  @Override
  public String toString() {
    return "NAME: " + name + "\nPRIORITY: " + priority + "\nDESIRED: " + itemsDesired + "\nRECEIVED " + itemsReceived + "\nREMOVED " + itemsRemoved + "\n";
  }
}

/* Item
 *
 * Stores the information associated with an Item which is desired by a Department
 */
class Item
{
  String name;    /* name of this item */
  Double price;   /* price of this item */

  /*
   * Constructor to build a Item
   */
  public Item( String name, Double price ){
    this.name = name;
    this.price = price;
  }

  /* Debugging tool
   * Converts this Item to a string
   */		
  @Override
  public String toString() {
    return "{ " + name + ", " + price + " }";
  }
}
