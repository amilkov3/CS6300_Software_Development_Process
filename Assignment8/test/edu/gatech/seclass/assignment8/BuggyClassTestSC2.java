package edu.gatech.seclass.assignment8;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ammilkov on 7/2/15.
 */
public class BuggyClassTestSC2 {

    @Test
    public void testMethod2() throws Exception {
        BuggyClass buggyClass = new BuggyClass();
        buggyClass.method2(true, true);
        buggyClass.method2(false, false);
        buggyClass.method2(true, false);
    }
}