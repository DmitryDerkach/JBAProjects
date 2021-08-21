import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	private static class MyExeption extends Throwable{
		
	}
	public enum Properties{
		BUZZ, 
		DUCK, 
		PALINDROMIC, 
		GAPFUL,
		SPY, 
		EVEN, 
		ODD;
		
	}
	private static int counter = 0; //for buzz check
	private static long value = 0;
	private static long itterator = 0;
	private static String property = "";
	public static void main(String[] args) {
		System.out.println("Welcome to Amazing Numbers!");
		System.out.println();
		System.out.println("Supported requests:\n"
				+ "- enter a natural number to know its properties;\n"
				+ "- enter two natural numbers to obtain the properties of the list:\n"
				+ "\t* the first parameter represents a starting number;\n"
				+ "\t* the second parameters show how many consecutive numbers are to be processed;\n"
				+ "- two natural numbers and a property to search for;\n"
				+ "- separate the parameters with one space;\n"
				+ "- enter 0 to exit.\n");
		do {
		point:{	
			try {
				System.out.print("Enter a request: > ");
		        Test.getValue();
			} catch (MyExeption exeption) {
				value = 1;
				break point;
			}

		if(value == 0) {
			return;
		}
		/*if input contains 2 numbers and property*/
		if(property != "") {
			/*Buzz*/
			if(property.equalsIgnoreCase(Properties.BUZZ.toString())){
			do {
				checkDivision(value);
				checkEnding(value);
				if (counter == 1 || counter == 2) {
					twoNumbersOutput();
					value++;
					itterator--;
				} else {
					value++;
				}
			} while (itterator != 0);
		
			}
			if (itterator == 0) {
				break point;
			}
			/*Even*/
			if(property.equalsIgnoreCase(Properties.EVEN.toString())){
				do {
					if(value % 2 == 0) {
						twoNumbersOutput();
						value++;
						itterator--;
					} else {
						value++;
					}
				} while (itterator != 0);
			}
			if (itterator == 0) {
				break point;
			}
			/*Odd*/
			if(property.equalsIgnoreCase(Properties.ODD.toString())){
				do {
					if(value % 2 != 0) {
						twoNumbersOutput();
						value++;
						itterator--;
					} else {
						value++;
					}
				} while (itterator != 0);
			}
			if (itterator == 0) {
				break point;
			}
			/*Duck*/
			if(property.equalsIgnoreCase(Properties.DUCK.toString())){
				do {
					String s = value + "";
					if (s.contains("0") && s.lastIndexOf("0") != 0 || s.contains("0") != false) {
						twoNumbersOutput();
						value++;
						itterator--;
					} else {
						value++;
					}
				} while (itterator != 0);
			}
			if (itterator == 0) {
				break point;
			}
			/*Palindromic*/
			if(property.equalsIgnoreCase(Properties.PALINDROMIC.toString())){
				do {
					String s = value + "";
					if (checkPalindromic(value)) {
						twoNumbersOutput();
						value++;
						itterator--;
					} else {
						value++;
					}
				} while (itterator != 0);
			}
			if (itterator == 0) {
				break point;
			}
			/*Gapful*/
			if(property.equalsIgnoreCase(Properties.GAPFUL.toString())){
				do {
					if (String.valueOf(value).length()>=3) {
						if (checkGapful(value)) {
							twoNumbersOutput();
							value++;
							itterator--;
						} else {
							value++;
						}
				} else {
					value++;
				}
				} while (itterator != 0);
			}
			if (itterator == 0) {
				break point;
			}
			/*Spy*/
			if(property.equalsIgnoreCase(Properties.SPY.toString())){
				do {
					if (checkSpy(value)) {
						twoNumbersOutput();
						value++;
						itterator--;
					} else {
						value++;
					}
				} while (itterator != 0);
			}
			if (itterator == 0) {
				break point;
			}
		}
		/*if input contains 2 numbers*/
		if (itterator != 0) {
			while (itterator!=0) {
				twoNumbersOutput();
				value++;
				itterator--;
				if (itterator == 0) {
					break point;
				}
			}
		}
		/*If input contains 1 number*/
		System.out.println("Properties of " + value);
		/*Even_Odd Checker*/
		String evenCkecker = (value % 2 == 0) ? "even: true" : "even: false";
		String oddCkecker = (value % 2 == 0) ? "odd: false" : "odd: true";
		System.out.println(evenCkecker);
		System.out.println(oddCkecker);
		/*Buzz number check*/
		checkDivision(value);
		checkEnding(value);
		switch (counter) {
			case(0):{
				System.out.println("buzz: false");
				/*System.out.println("Explanation: \n" + value + " is neither divisible by 7 
				nor does it end with 7.");*/
				break;
			}
			case(1):{
				System.out.println("buzz: true");
				/*System.out.println("Explanation: \n" + value + " is divisible by 7.");*/
				break;
			}
			case(2):{
				System.out.println("buzz: true");
				/*System.out.println("Explanation: \n" + value + " is divisible by 7 and ends with 7.");*/
				break;
			}
		}
		counter = 0;
		/*Duck number check*/
		String s = value + "";
		if (s.contains("0") && s.lastIndexOf("0") == 0 || s.contains("0") == false) {
			System.out.println("duck: false");
		} else {
			System.out.println("duck: true");
		}
		/*Palindromic check*/
		if (checkPalindromic(value)) {
			System.out.println("palindromic: true");
		} else {
			System.out.println("palindromic: false");
		}
		/*Gapful check*/
		if (String.valueOf(value).length()>=3) {
			if (checkGapful(value)) {
				System.out.println("gapful: true");
			} else {
				System.out.println("gapful: false");
			}
		} else {
			System.out.println("gapful: false");
		}
		/*Spy check*/
		if (checkSpy(value)) {
			System.out.println("spy: true");
		} else {
			System.out.println("spy: false");
		}
		System.out.println();
		value--;
		}//Скобка breakpoint-a
		} while(value != 0);//Скобка while цикла
		
	}//Скобка Main-метода		
		
	public static String getValue() throws Test.MyExeption {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		String[] tempstorage = input.split(" ");
		switch (tempstorage.length) {
			case(1): {
				try {
					value = Long.parseLong(tempstorage[0]);
				} catch (NumberFormatException exeptionV1) {
					System.out.println("The first parameter should be a natural number or zero.");
					System.out.println();
					throw new Test.MyExeption();
				}
				if(value < 0) {
					System.out.println("The first parameter should be a natural number or zero.");
					System.out.println();
					throw new Test.MyExeption();
				}
				break;
			}
			case(2): {
				try {
					value = Long.parseLong(tempstorage[0]);
				} catch (NumberFormatException exeptionV1) {
					System.out.println("The first parameter should be a natural number or zero.");
					System.out.println();
					throw new Test.MyExeption();
				}
				if(value < 0) {
					System.out.println("The first parameter should be a natural number or zero.");
					System.out.println();
					throw new Test.MyExeption();
				}
				
				try {
					itterator = Long.parseLong(tempstorage[1]);
				} catch (NumberFormatException exeptionV1) {
					System.out.println("The second parameter should be a natural number or zero.");
					System.out.println();
					throw new Test.MyExeption();
				}
				if(itterator < 0) {
					System.out.println("The second parameter should be a natural number or zero.");
					System.out.println();
					throw new Test.MyExeption();
				}
				break;
			}
			case(3): {
				try {
					value = Long.parseLong(tempstorage[0]);
				} catch (NumberFormatException exeptionV1) {
					System.out.println("The first parameter should be a natural number or zero.");
					System.out.println();
					throw new Test.MyExeption();
				}
				if(value < 0) {
					System.out.println("The first parameter should be a natural number or zero.");
					System.out.println();
					throw new Test.MyExeption();
				}
				
				try {
					itterator = Long.parseLong(tempstorage[1]);
				} catch (NumberFormatException exeptionV1) {
					System.out.println("The second parameter should be a natural number or zero.");
					System.out.println();
					throw new Test.MyExeption();
				}
				if(value < 0) {
					System.out.println("The second parameter should be a natural number or zero.");
					System.out.println();
					throw new Test.MyExeption();
				}
				
				property = tempstorage[2];
				boolean checker = false;
				for (Properties itterator : Test.Properties.values()) {
					if (property.equalsIgnoreCase(itterator.toString())) {
						checker = true;
					} else {
						continue;
					}
				}
				if(!checker) {
					System.out.println("The property [" + property + "] is wrong.");
					System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]");
					System.out.println();
					throw new Test.MyExeption();
				}
				break;
			}
		}
		return value + " " + itterator + " " + property;
	}

	public static void checkDivision(long value) {
		if (value % 7 == 0) {
			counter +=1;
			}
		}

	public static void checkEnding(long value) {
		long supvalueV1 = value % 10;
		if (supvalueV1 == 7) {
			counter++;
		}
	}
	
	public static boolean checkPalindromic(long value) {
		boolean trigger = true;
		String supstring = String.valueOf(value);
		ArrayList<Character> container = new ArrayList<Character>();
		for (int i = 0; i < supstring.length(); i++) {
			container.add(supstring.charAt(i));
		}
		for (int i = 0; i < container.size(); i++) {
			if (container.get(i) != container.get(container.size() - 1 -i)) {
				trigger = false;
			}
		}
		return trigger;
	}
	
	public static boolean checkGapful(long value) {
		String input = String.valueOf(value);
		String firstdigit = input.charAt(0) + "";
		String lastdigit = input.charAt(input.length() - 1) + "";
		String concat = firstdigit + lastdigit;
		Integer sum = Integer.parseInt(concat);
		if (value % (int) sum == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean checkSpy(long value) {
		String[] tempStringContainer = String.valueOf(value).split("");
		Integer[] tempIntegerContainer = new Integer[tempStringContainer.length];
		long sum = 0;
		long produce = 1;
		for (int i = 0; i < tempIntegerContainer.length; i++) {
			tempIntegerContainer[i] = Integer.parseInt(tempStringContainer[i]);
			sum+=tempIntegerContainer[i];
			produce*=tempIntegerContainer[i];
		}
		if (sum == produce) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void twoNumbersOutput() {
			/*Even_Odd Checker*/
			String evenOddChecker = value % 2 == 0 ? "even; " : "odd; ";
			System.out.print(value + " is " + evenOddChecker);
			/*Buzz Checker*/
			checkDivision(value);
			checkEnding(value);
			switch (counter) {
				case(1):{
					System.out.print("buzz; ");
					break;
				}
				case(2):{
					System.out.print("buzz; ");
					break;
				}
			}
			counter = 0;
			/*Duck Checker*/
			String s = value + "";
			if (s.contains("0") && s.lastIndexOf("0") != 0 || s.contains("0") != false) {
				System.out.print("duck; ");
			} 
			/*Palindromic Checker*/
			if (checkPalindromic(value)) {
				System.out.print("palindromic; ");
			}
			/*Gapful Checker*/
			if (String.valueOf(value).length()>=3) {
				if (checkGapful(value)) {
					System.out.print("gapful; ");
				}
			}
			/*Spy Checker*/
			if (checkSpy(value)) {
				System.out.print("spy; ");
			}
			System.out.println();
	}
	
} //Скобка класса




