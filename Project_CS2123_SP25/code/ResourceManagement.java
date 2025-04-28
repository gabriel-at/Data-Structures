import java.io.*;
import java.util.*;

/* ResourceManagement
 *
 * Stores the information needed to manage a university's department purchases
 * based on a limited overall budget. 
 * Prioritizes departments by amount already spent, purchases items if possible,
 * awards scholarships if no affordable items remain.
 */
public class ResourceManagement {
    private List<Department> departments; // List of all departments
    private Double remainingBudget;       // Budget still available
    private Double budget;                // Total original budget

    // Print the names of the team members
    public static void printName() {
        System.out.println("This solution was completed by:");
        System.out.println("Erick");
        System.out.println("Gabriel");
        System.out.println("Jackson");
        System.out.println("Joseph");
        System.out.println("Odaba");
    }
        /* Constructor: initializes departments and processes all purchases */
        public ResourceManagement(String[] fileNames, Double budget) {
          this.budget = budget;
          this.remainingBudget = budget;
          this.departments = new ArrayList<>();
  
          // Load each department from its file
          for (String fileName : fileNames) {
              try {
                  Department dept = new Department(fileName);
                  departments.add(dept);
              } catch (Exception e) {
                  System.err.println(e);
              }
          }
  
          // PriorityQueue orders departments based on total spent and name
          PriorityQueue<Department> purchasingQueue = new PriorityQueue<>(departments);
  
          System.out.println("ITEMS PURCHASED");
          System.out.println("----------------------------");
  
          // Process purchases until no departments are left
          while (!purchasingQueue.isEmpty()) {
              Department department = purchasingQueue.poll();
  
              // Skip departments with no desired items
              if (department.itemsDesired.isEmpty()) {
                  continue;
              }
  
              Item item = department.itemsDesired.peek(); // Peek at next desired item
  
              if (item.price <= remainingBudget) {
                  // If item affordable, purchase it
                  department.itemsDesired.poll();        // Remove item from desired
                  department.itemsReceived.add(item);    // Add to received items
                  remainingBudget -= item.price;         // Subtract cost
                  department.priority += item.price;     // Update department's spending
  
                  // Print purchase details
                  String price = String.format("$%.2f", item.price);
                  System.out.printf("Department of %-30s- %-30s- %30s\n", department.name, item.name, price);
  
                  // If department still has more items, reinsert into queue
                  if (!department.itemsDesired.isEmpty()) {
                      purchasingQueue.add(department);
                  }
              } else {
                  // If item too expensive, move it to removed list
                  department.itemsDesired.poll();
                  department.itemsRemoved.add(item);
  
                  // Check if any affordable items are left
                  boolean hasAffordableItem = false;
                  for (Item nextItem : department.itemsDesired) {
                      if (nextItem.price <= remainingBudget) {
                          hasAffordableItem = true;
                          break;
                      }
                  }
  
                  if (hasAffordableItem) {
                      // If department still has affordable items, reinsert
                      purchasingQueue.add(department);
                  }
                  // Otherwise, department is finished (not reinserted)
              }
          }
      }
    /* printSummary
     * Prints the detailed summary of each department's purchases and missed items
     */
    public void printSummary() {
      System.out.println("\nSUMMARY");
      System.out.println("--------------------------------------------------");

      // Sort departments alphabetically by name
      departments.sort(Comparator.comparing(d -> d.name));

      for (Department dept : departments) {
          String spent = String.format("$%.2f", dept.priority);
          double percent = (budget == 0) ? 0.0 : (dept.priority / budget) * 100;

          System.out.println(dept.name);
          System.out.println("Total Spent       = " + spent);
          System.out.printf("Percent of Budget = %.2f%%\n", percent);
          System.out.println("----------------------------");

          System.out.println("ITEMS RECEIVED");
          if (dept.itemsReceived.isEmpty()) {
              System.out.println("None");
          } else {
              for (Item item : dept.itemsReceived) {
                  String price = String.format("$%.2f", item.price);
                  System.out.printf("%-30s - %30s\n", item.name, price);
              }
          }

          System.out.println();

          System.out.println("ITEMS NOT RECEIVED");
          if (dept.itemsRemoved.isEmpty()) {
              System.out.println("None");
          } else {
              for (Item item : dept.itemsRemoved) {
                  String price = String.format("$%.2f", item.price);
                  System.out.printf("%-30s - %30s\n", item.name, price);
              }
          }

          System.out.println();
      }
  }
}
/* Department
 *
 * Stores all information about a department's requested items and purchases
 */
class Department implements Comparable<Department> {
  String name;                   // Department name
  Double priority;               // Total money spent
  Queue<Item> itemsDesired;      // Items the department wants
  Queue<Item> itemsReceived;     // Items purchased
  Queue<Item> itemsRemoved;      // Items skipped or unaffordable

  /* Build a Department object from input file */
  public Department(String fileName) {
      itemsDesired = new LinkedList<>();
      itemsReceived = new LinkedList<>();
      itemsRemoved = new LinkedList<>();
      priority = 0.0;

      try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
          name = br.readLine(); // First line: department name
          String line;
          while ((line = br.readLine()) != null) {
              line = line.trim();
              if (line.isEmpty()) continue;

              String itemName = line;
              if ((line = br.readLine()) != null) {
                  double itemPrice = Double.parseDouble(line.trim());
                  itemsDesired.add(new Item(itemName, itemPrice));
              }
          }
      } catch (IOException e) {
          System.err.println("Error reading file: " + fileName);
          e.printStackTrace();
      }
  }

  /* Comparison based on priority spent; ties broken by department name */
  @Override
  public int compareTo(Department dept) {
      int spentComparison = this.priority.compareTo(dept.priority);
      if (spentComparison != 0) {
          return spentComparison;
      } else {
          return this.name.compareTo(dept.name);
      }
  }
}
/* Item
 *
 * Represents a single item requested by a department
 */
class Item {
  String name;    // Name of the item
  Double price;   // Price of the item

  /* Constructor for an Item */
  public Item(String name, Double price) {
      this.name = name;
      this.price = price;
  }
}

