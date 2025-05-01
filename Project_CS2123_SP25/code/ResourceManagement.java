import java.io.*;
import java.util.*;

/* ResourceManagement
 *
 * Stores the information needed to decide which items to purchase for the given budget and departments
 */
public class ResourceManagement {
    private List<Department> departments; // List of all departments
    private Double remainingBudget; // Budget left after purchases
    private Double budget; // Original total budget

    // Print the names of team members
    public static void printName() {
        System.out.println("This solution was completed by:");
        System.out.println("Erick");
        System.out.println("Gabriel");
        System.out.println("Jackson");
        System.out.println("Joseph");
        System.out.println("Obada");
    }

    /*
     * Constructor for a ResourceManagement object
     * Simulates the algorithm to determine what items are purchased
     * for the given budget and department item lists.
     */
    public ResourceManagement(String[] fileNames, Double budget) {
        this.budget = budget;
        this.remainingBudget = budget;
        this.departments = new ArrayList<>();

        // Load departments from files
        for (String fileName : fileNames) {
            try {
                Department dept = new Department(fileName);
                departments.add(dept);
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        PriorityQueue<Department> queue = new PriorityQueue<>(departments);
        System.out.println("ITEMS PURCHASED");
        System.out.println("----------------------------");

        while (!queue.isEmpty() && remainingBudget > 0) {
            Department dept = queue.poll();

            // Remove unaffordable items from the front
            if (!dept.itemsDesired.isEmpty() && dept.itemsDesired.peek().price > remainingBudget) {
                dept.itemsRemoved.add(dept.itemsDesired.poll());
                queue.add(dept); // give department another chance later
                continue;         // skip to next department
            }

            if (dept.itemsDesired.isEmpty()) {
                // Grant scholarship if nothing is affordable
                double scholarship = Math.min(remainingBudget, 1000.00);
                dept.itemsReceived.add(new Item("Scholarship", scholarship));
                dept.priority += scholarship;
                remainingBudget -= scholarship;

                String price = String.format("$%.2f", scholarship);
                System.out.printf("Department of %-30s- %-30s- %30s\n", dept.name, "Scholarship", price);
            } else {
                // Purchase next item
                Item item = dept.itemsDesired.poll();
                dept.itemsReceived.add(item);
                dept.priority += item.price;
                remainingBudget -= item.price;

                String price = String.format("$%.2f", item.price);
                System.out.printf("Department of %-30s- %-30s- %30s\n", dept.name, item.name, price);
            }

            if (!dept.itemsDesired.isEmpty() || remainingBudget > 0) {
                queue.add(dept);
            }
        }
     // Move any remaining items in itemsDesired to itemsRemoved
        for (Department dept : departments) {
            while (!dept.itemsDesired.isEmpty()) {
                dept.itemsRemoved.add(dept.itemsDesired.poll());
            }
        }

    }

    /*
     * printSummary
     * Print a summary of what each department received and did not receive.
     * Also prints remaining budget.
     */
    public void printSummary() {
        System.out.println("\nSUMMARY");
        System.out.println("--------------------------------------------------");

        // departments.sort(Comparator.comparing(d -> d.name));
        Collections.sort(departments, new Comparator<Department>() {
            @Override
            public int compare(Department d1, Department d2) {
                return d1.name.compareTo(d2.name);
            }
        });

        for (Department dept : departments) {
            String spent = String.format("$%.2f", dept.priority);
            double percent = (budget == 0) ? 0.0 : (dept.priority / budget) * 100;

            System.out.println("Department of " + dept.name);
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

class Department implements Comparable<Department> {
    String name;
    Double priority;
    Queue<Item> itemsDesired;
    Queue<Item> itemsReceived;
    Queue<Item> itemsRemoved;

    public Department(String fileName) {
        itemsDesired = new LinkedList<>();
        itemsReceived = new LinkedList<>();
        itemsRemoved = new LinkedList<>();
        priority = 0.0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            name = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue;
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

    @Override
    public int compareTo(Department dept) {
        int cmp = this.priority.compareTo(dept.priority);
        return (cmp != 0) ? cmp : this.name.compareTo(dept.name);
    }
}

class Item {
    String name;
    Double price;

    public Item(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
