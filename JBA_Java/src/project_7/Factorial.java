package project_7;

import java.util.Scanner;

public class Factorial {
	public static void main(String[] args) {
		System.out.println(getFactorial(30));	
	}
	
	public static long getFactorial (long value) {
	    if (value <= 1)
	    {
	        return 1;
	    }
	    return value * getFactorial(value - 1);

		}
}
