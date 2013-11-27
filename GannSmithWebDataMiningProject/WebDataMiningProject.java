import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.HashMap;
import java.util.ServiceConfigurationError;

/**
 * Project that calculates Support, Lift, and Confidence on given sets(user input) over a given transaction database(csv file)
 *
 * Created with IntelliJ IDEA.
 * User: bgann, csmith
 * Date: 11/25/13
 * Time: 9:13 PM
 */
public class WebDataMiningProject {

    private HashMap<String, List<String>> transactions = new HashMap<String, List<String>>();
    private Set<String> items = new TreeSet<String>();

    public static void main(String[] args) {

        WebDataMiningProject dataMiningProject = new WebDataMiningProject();

        boolean flagMenu = true;
        boolean flagReadFile = true;


        //Read file into data structure
        do {
            Console console = System.console();
            String input;
            input = console.readLine("\nPlease enter the main Transaction filename: ");
            if (!dataMiningProject.readFile(input)) {
                System.err.println("\nError: The File " + input + " cannot be found.\n");
                continue;
            }
            else {
                flagReadFile = false;
            }
        }while (flagReadFile);



        //prompt menu loop
        while (flagMenu) {
            flagMenu = dataMiningProject.menu();
        }

        System.exit(0);
    }

    /**
     *  Reads file in and builds the apropriate data structures for the program
     *
     * @param filename String name of the file that contains transaction data
     * @return Boolean flag: true if file read, false if file failed to read
     */
    private boolean readFile(String filename) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = null;

            while ((line = reader.readLine()) != null) {
                String transactionName;
                String lineSplit[] = line.split(",");

                transactionName = lineSplit[0].trim();
                transactions.put(transactionName, new ArrayList<String>());
                for (int i = 1; i < lineSplit.length; ++i) {
                    transactions.get(transactionName).add(lineSplit[i].trim()); // the next string read in
                    items.add(lineSplit[i].trim());
                }
            }
        } catch (FileNotFoundException e) {
            return false;
        } catch (Exception e) {
            System.err.println("\nError: An unhandled exception has occurred.\n Exiting program.\n");
            System.exit(1);
        }
        return true;
    }


    /**
     * Display menu and call appropriate functions based on chosen menu item
     * @return true until the user chooses the exit menu item
     */
    private boolean menu() {
        Console console = System.console();
        String input;
        int menuChoice;

        System.out.println("\n\n           Welcome to SLCC" +
                           "\n Support, Lift and Confidence Calculator" +
                           "\n========================================="+
                           "\n      Please select a menu item:" +
                           "\n-----------------------------------------");
        System.out.println("\n1 - Calculate Support");
        System.out.println("\n2 - Calculate Confidence");
        System.out.println("\n3 - Calculate Lift");
        System.out.println("\n4 - Print Out Unique Items Set");
        System.out.println("\n5 - Exit" +
                           "\n-----------------------------------------");
        input = console.readLine("Enter menu Choice: ");
        menuChoice = Integer.parseInt(input);

        Set<String> set1 = new TreeSet<String>();
        Set<String> set2 = new TreeSet<String>();

        switch(menuChoice) {
            case 1:
                Set<String> set = buildSet("\nTo calculate support enter an item set."
                                       + "\n\n=========================================\n");
                Double support =  Calculate.support(set, transactions);
                System.out.println("\n\n=========================================" +
                                   "\n+++++++++++++++++++++++++++++++++++++++++"   +
                                   "\nThe support for the set entered is: " + support +
                                   "\n+++++++++++++++++++++++++++++++++++++++++"  +
                                   "\n=========================================\n");
                break;
            case 2:
                set1 = buildSet("\nTo calculate confidence for rule X->Y enter item set X."
                              + "\n=======================================================\n");
                set2 = buildSet("\nTo calculate confidence for rule X->Y enter item set Y."
                        +       "\n=======================================================\n");
                Double confidence = Calculate.confidence(set1, set2, transactions);
                System.out.println("\n\n==================================================" +
                                   "\n++++++++++++++++++++++++++++++++++++++++++++++++++"   +
                                   "\nThe confidence for the two sets entered is: " + confidence +
                                   "\n++++++++++++++++++++++++++++++++++++++++++++++++++"  +
                                   "\n==================================================\n");
                break;
            case 3:
                set1 = buildSet("\nTo calculate lift for rule X->Y enter item set X."
                              + "\n=================================================\n");
                set2 = buildSet("\nTo calculate lift enter for rule X->Y item set Y."
                              + "\n=================================================\n");
                Double lift = Calculate.lift(set1, set2, transactions);
                System.out.println("\n\n=============================================" +
                                   "\n+++++++++++++++++++++++++++++++++++++++++++++"   +
                                   "\nThe lift for the two sets entered is: " + lift +
                                   "\n+++++++++++++++++++++++++++++++++++++++++++++"  +
                                   "\n=============================================\n");
                break;
            case 4:
                printSet();
                break;
            case 5:
                System.out.println("\nThank you for using SLCC\n");
                return false;
            default:
                System.out.println("\n" + input + " is not a menu option.  Please try again.\n");
                break;
        }
        return true;
    }

    /**
     * A set creation method that prompts the user to input their set 1 item at a time until a "#" is entered
     * @param setName A string value that prints out the prompt to the user
     * @return the TreeSet created from the individual items provided by the user
     */
    private Set<String> buildSet(String setName) {
        String input = "";
        Set<String> set = new TreeSet<String>();
        String escape = "#";

        System.out.println(setName);
        while (!input.trim().equals(escape)) {
            Console console = System.console();
            input = console.readLine("Enter an item to add to the set(\'#\' to signal a complete set): ");
            if (input.trim().equals(escape)) {
                break;
            }
            set.add(input);
        }
        return set;
    }

    /**
     * A method for printing out all of the unique elements in the Transaction Database
     */
    private void printSet() {
        System.out.println("\n\n=================================" +
                             "\nUnique Items In Data Transactions" +
                             "\n=================================");
        for(String s : items) {
            System.out.println(s);
        }
        System.out.println("=================================\n");
    }
}