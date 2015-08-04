package edu.gatech.seclass.assignment8;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ammilkov on 7/2/15.
 */
public class BuggyClassTestSC3 {

    @Test
    public void testMethod3() throws Exception {
        BuggyClass buggyClass = new BuggyClass();
        buggyClass.method3(true, true, 2);
        buggyClass.method3(true, true, 3);
        buggyClass.method3(false, false, 3);
        buggyClass.method3(false, true, 3);
    }
}