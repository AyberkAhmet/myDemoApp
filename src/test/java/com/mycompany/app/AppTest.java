package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    public void testBugTeam2Won() {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(4, 3, 2, 1));
        assertEquals("Bug Team 2 Won geçti", "Bug Team 2 Kazandı!!!", App.BugFight(array, array2, 1, 3));
    }

    public void testBugTeam1Won() {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(4, 3, 2, 1));
        assertEquals("Bug Team 1 Won geçti", "Bug Team 1 Kazandı!!!" , App.BugFight(array, array2, 2 , 1));
    }

    public void testBerabere() {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(4, 3, 2, 1));
        assertEquals("Berabere geçti", "Berabere", App.BugFight(array, array2, 4, 1));
    }

    public void testNull() {
        ArrayList<Integer> array = null;
        ArrayList<Integer> array2 = null;
        assertEquals("Null geçti", "Bug Team 1 formu ya da Bug Team 2 formu null", App.BugFight(array, array2, 4, 1));
    }

    public void testIsEmpty() {
        ArrayList<Integer> array = new ArrayList<>();
        ArrayList<Integer> array2 = new ArrayList<>();
        assertEquals("IsEmpty geçti", "Bug Team 1 formu ya da Bug Team 2 formu bos", 
                App.BugFight(array, array2, 4, 1));
    }

}
