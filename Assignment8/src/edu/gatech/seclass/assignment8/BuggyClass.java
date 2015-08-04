package edu.gatech.seclass.assignment8;

/**
 * Created by amilkov3 on 6/30/15.
 */
public class BuggyClass {

    public void method1(int x){
        String str = null;
        if (x == 2){
            str = "2";
        } else if (x == 3){
            str = "3";
        }
        str.length();
    }

    void method2(boolean x, boolean y){
        String str1 = null;
        String str2 = null;
        if (x){
            str1 = "x";
        } else {
            str2 = "not x";
        }
        if (y){
            str2 = "y";
        } else {
            str1 = "not y";
        }
        str1.length();
        str2.length();
    }

    void method3(boolean x, boolean y, int z){
        String str1 = null;
        String str2 = null;
        if (x){
            if (z == 2){
                str1 = "z is 2";
            } else if (z == 3){
                str1 = "z is 3";
            }
            str1 = "z is neiter";
        } else {
            str2 = "not x";
        }
        if (y){
            str2 = "y";
        } else {
            str1 = "not y";
        }
        str1.length();
        str2.length();
    }

}
