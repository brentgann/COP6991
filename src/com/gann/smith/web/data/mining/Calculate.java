package com.gann.smith.web.data.mining;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: bgann
 * Date: 11/26/13
 * Time: 10:32 PM
 */
public abstract class Calculate {


    /**
     *
     * Calculates the confidence of a rule X -> Y where X and Y are itemsets
     *
     * @param leftSideItemSet - the itemset X
     * @param rightSideItemSet - the itemset Y
     * @param transactions - the transaction hashmap
     * @return confidence - the confidence of rule {leftSideItemSet} -> {rightSideItemSet}
     *
     **/
    public static double confidence(Set<String> leftSideItemSet, Set<String> rightSideItemSet, HashMap<String, List<String>> transactions) {

        Set<String> fullItemSet = new TreeSet<String>();
        fullItemSet.addAll(leftSideItemSet);
        fullItemSet.addAll(rightSideItemSet);

        double leftHandSupport = support(leftSideItemSet, transactions);
        double fullSupport = support(fullItemSet, transactions);

        if(leftHandSupport == 0)
            return 0;


        return fullSupport/leftHandSupport;
    }

    /**
     *
     * Calculates the support of an itemset X
     *
     * @param itemSet - the full set of items being checked (X)
     * @param transactions - the transaction hashmap
     * @return support - the support of the itemset
     *
     **/
    public static double support(Set<String> itemSet, HashMap<String, List<String>> transactions) {

        if(transactions.size() == 0)
            return 0;

        Set<String> transactionsSupported = new TreeSet<String>();
        Set<Map.Entry<String, List<String>>> entrySet = transactions.entrySet();

        for(Map.Entry<String, List<String>> entry : entrySet){

            boolean transactionSupported = true;


            for( String item : itemSet )
                if(!entry.getValue().contains(item))
                    transactionSupported = false;

            if( transactionSupported )
                transactionsSupported.add(entry.getKey());
        }

        return (double)transactionsSupported.size()/(double)transactions.size();
    }

    /**
     *
     * Calculates the lift (improvement) of a rule X -> Y where X and Y are itemsets
     *
     * @param leftSideItemSet - the itemset X
     * @param rightSideItemSet - the itemset Y
     * @param transactions - the transaction hashmap
     * @return confidence - the confidence of rule {leftSideItemSet} -> {rightSideItemSet}
     *
     **/
    public static double lift(Set<String> leftSideItemSet, Set<String> rightSideItemSet, HashMap<String, List<String>> transactions) {

        double rightHandSupport = support(rightSideItemSet, transactions);
        double fullConfidence = confidence(leftSideItemSet, rightSideItemSet, transactions);

        return fullConfidence/rightHandSupport;

    }


}
