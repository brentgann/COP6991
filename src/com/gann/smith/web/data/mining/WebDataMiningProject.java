package com.gann.smith.web.data.mining;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.HashMap;

/**
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
        while (flagReadFile) {
            Console console = System.console();
            String input;
            input = console.readLine("\nPlease enter the main Transaction filename.");
            if (!dataMiningProject.readFile(input)) {
                System.err.println("\nError: The File " + input + " cannot be found.\n");
                flagReadFile = true;
            }
            flagReadFile = false;
        }

        //prompt menu loop
        while (flagMenu) {
            flagMenu = dataMiningProject.menu();
        }

        System.exit(0);
    }


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
                    items.add(lineSplit[i]);
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

    private boolean menu() {
        Console console = System.console();
        String input;
        int menuChoice;

        System.out.println("\n      Welcome to SLCC\n Support Lift and Confidence" +
                           "\n===========================\nPlease select a menu item:");
        System.out.println("\n1 - Calculate Support");
        System.out.println("\n2 - Calculate Confidence");
        System.out.println("\n3 - Calculate lift");
        System.out.println("\n4 - Exit");
        input = console.readLine("Enter menu Choice:");
        menuChoice = Integer.parseInt(input);

        Set<String> set1 = new TreeSet<String>();
        Set<String> set2 = new TreeSet<String>();

        switch(menuChoice) {
            case 1:
                Set<String> set = buildSet("To calculate support enter an item set."
                        + "\n=======================================\n");
                Double support =  Calculate.support(set, transactions);
                System.out.println("\nThe support for the set entered is: " + support + "\n");
                break;
            case 2:
                set1 = buildSet("To calculate confidence enter item set X."
                        + "\n=========================================\n");
                set2 = buildSet("To calculate confidence enter item set Y."
                        + "\n=========================================\n");
                Double confidence = Calculate.confidence(set1, set2, transactions);
                System.out.println("\nThe confidence for the two sets entered is: " + confidence + "\n");
                break;
            case 3:
                set1 = buildSet("To calculate lift enter item set X."
                        + "\n===================================\n");
                set2 = buildSet("To calculate lift enter item set Y."
                        + "\n===================================\n");
                Double lift = Calculate.lift(set1, set2, transactions);
                System.out.println("\nThe lift for the two sets entered is: " + lift + "\n");
                break;
            case 4:
                System.out.println("Thank you for using SLCC");
                return false;
            default:
                System.out.println("\n" + input + " is not a menu option.  Please try again.\n");
                break;
        }
        return true;
    }

    private Set<String> buildSet(String setName) {
        String input = "";
        Set<String> set = new TreeSet<String>();

        System.out.println(setName);
        while (input != "#") {
            Console console = System.console();
            input = console.readLine("\nEnter an item to add to the set(\'#\' to signal a complete set):");
            if (input == "#") {
                break;
            }
            set.add(input);
        }
        return set;
    }

}