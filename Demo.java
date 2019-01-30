/*
 This script is to test the ParseEq parser.
 */

import java.io.*;
import java.util.*;

public class Demo
{
	public static void main(String[] args)
		throws FileNotFoundException {
            String equation = "(1+(5*(8/10)))";
            String solution="";
            ParseEq solveFor = new ParseEq();//Define your ParseEq class
            solution = solveFor.ParseEq(equation);//Pass equation to ParseEq class
            System.out.println(solution);
        }
}
