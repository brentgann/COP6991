import com.gann.smith.web.data.mining.Calculate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: bgann
 * Date: 11/26/13
 * Time: 10:43 PM
 */
public class TestCalculate {

    HashMap<String, List<String>> transactions = new HashMap<String, List<String>>();

    @Before
    public void setUp(){

        transactions.put("1001", new ArrayList<String>());
        transactions.get("1001").add("A");
        transactions.get("1001").add("B");
        transactions.get("1001").add("C");

        transactions.put("1002", new ArrayList<String>());
        transactions.get("1002").add("A");
        transactions.get("1002").add("C");

        transactions.put("1003", new ArrayList<String>());
        transactions.get("1003").add("A");
        transactions.get("1003").add("D");

        transactions.put("1004", new ArrayList<String>());
        transactions.get("1004").add("B");
        transactions.get("1004").add("E");
        transactions.get("1004").add("F");

        transactions.put("1005", new ArrayList<String>());
        transactions.get("1005").add("A");
        transactions.get("1005").add("D");
        transactions.get("1005").add("F");

    }

    @Test
    public void testSupportCalculationOfAC(){
        Set<String> supportTest = new TreeSet<String>();

        supportTest.add("A");
        supportTest.add("C");

        double answer = Calculate.support(supportTest, transactions);

        assertEquals(.4, answer, 0);
    }

    @Test
    public void testSupportCalculationOfT(){
        Set<String> supportTest = new TreeSet<String>();

        supportTest.add("T");

        double answer = Calculate.support(supportTest, transactions);

        assertEquals(0, answer, 0);
    }

    @Test
    public void testConfidenceCalculationOfAtoC(){
        Set<String> leftHandSide = new TreeSet<String>();
        Set<String> rightHandSide = new TreeSet<String>();

        leftHandSide.add("A");
        rightHandSide.add("C");

        double answer = Calculate.confidence(leftHandSide, rightHandSide, transactions);

        assertEquals(.5, answer, 0);
    }

    @Test
    public void testConfidenceCalculationOfTtoC(){
        Set<String> leftHandSide = new TreeSet<String>();
        Set<String> rightHandSide = new TreeSet<String>();

        leftHandSide.add("T");
        rightHandSide.add("C");

        double answer = Calculate.confidence(leftHandSide, rightHandSide, transactions);

        assertEquals(0, answer, 0);
    }

    @Test
    public void testConfidenceCalculationOfCtoA(){
        Set<String> leftHandSide = new TreeSet<String>();
        Set<String> rightHandSide = new TreeSet<String>();

        leftHandSide.add("C");
        rightHandSide.add("A");

        double answer = Calculate.confidence(leftHandSide, rightHandSide, transactions);

        assertEquals(1, answer, 0);
    }

    @Test
    public void testLiftCalculationOfCtoA(){
        Set<String> leftHandSide = new TreeSet<String>();
        Set<String> rightHandSide = new TreeSet<String>();

        leftHandSide.add("C");
        rightHandSide.add("A");

        double answer = Calculate.lift(leftHandSide, rightHandSide, transactions);

        assertEquals(1.25, answer, 0);
    }


}