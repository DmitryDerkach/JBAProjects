package project_2;
import java.util.ArrayList;
import java.util.Scanner;

public class AmazingNumbers {
	private static class MyExeption extends Throwable{
		
	}
	public enum Properties{
		BUZZ, DUCK, PALINDROMIC, GAPFUL,SPY, EVEN, ODD, SQUARE, SUNNY, JUMPING, HAPPY, SAD;
	}
	private static long value = 0;// Перве число
	private static long itterator = 0;//Второе число
	private static String[] inputDataContainer = null;//Массив данных из потока ввода
	public static void main(String[] args) {
		System.out.println("Welcome to Amazing Numbers!");
		System.out.println();
		System.out.println("Supported requests:\n"
				+ "- enter a natural number to know its properties;\n"
				+ "- enter two natural numbers to obtain the properties of the list:\n"
				+ "\t* the first parameter represents a starting number;\n"
				+ "\t* the second parameters show how many consecutive numbers are to be processed;\n"
				+ "- two natural numbers and a properties to search for;\n"
				+ "- a property preceded by minus must not be present in numbers;"
				+ "- separate the parameters with one space;\n"
				+ "- enter 0 to exit.\n");
		do {
		point:{	
			try {
				value--;
				System.out.print("Enter a request: > ");
				inputDataContainer = AmazingNumbers.getValue();
			} catch (MyExeption exeption) {
				value = 1;
				break point;
			}
		if(value == 0) {
			return;
		}
		/*If input contains only 1 number (specific output for this particular case)*/
		if (inputDataContainer.length == 1) {
			oneNumberOutput();
		}
		/*All other cases*/
		while (itterator != 0) {
			ArrayList<String> containerWithPlusProperties = new ArrayList<String>();
			ArrayList<String> containerWithMinusProperties = new ArrayList<String>();
			if (inputDataContainer.length > 2) {
				for (int i = 2; i < inputDataContainer.length; i++) {
					if (inputDataContainer[i].startsWith("-")) {
						containerWithMinusProperties.add(inputDataContainer[i]);
					} else {
						containerWithPlusProperties.add(inputDataContainer[i]);
					}
				}
				boolean flag = false;
				repeat:{
				for (int i = 0; i < containerWithPlusProperties.size(); i++) {
					flag = triggerSpecificChecker(containerWithPlusProperties.get(i));
					if (!flag) {
						value++;
						break repeat;
					}
				}
				}
				if (containerWithPlusProperties.size() == 0) {
					flag = true;
				}
				if (flag) {
					repeatV2:{
					boolean remover = false;
					for (int i = 0; i < containerWithMinusProperties.size(); i++) {
						remover = triggerSpecificChecker(containerWithMinusProperties.get(i).substring(1));
						if (remover == true) {
							break;
						}
					}	
					if (!remover) {
						output();
						value++;
						itterator--;
					} else {
						value++;
						break repeatV2;
					}
					}
				} 
				if (itterator == 0) {
					break point;
				}
				} else {
					output();
					value++;
					itterator--;
				if (itterator == 0) {
					break point;
				}
			}
		}
		/*Ending*/
		}//Скобка breakpoint-a
		} while(value != 0);//Скобка while цикла
	}//Скобка Main-метода		
	
	public static String[] getValue() throws AmazingNumbers.MyExeption {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		inputDataContainer = input.split(" ");
		final class Checker {
			Properties[] propertiesDataContainer = Properties.values();
			void inputFirst() throws AmazingNumbers.MyExeption{
				try {
					value = Long.parseLong(inputDataContainer[0]);
				} catch (NumberFormatException exeptionV1) {
					System.out.println("The first parameter should be a natural number or zero.");
					System.out.println();
					throw new AmazingNumbers.MyExeption();
				}
				if(value < 0) {
					System.out.println("The first parameter should be a natural number or zero.");
					System.out.println();
					throw new AmazingNumbers.MyExeption();
				}
			}
			void inputSecond() throws AmazingNumbers.MyExeption{
				try {
					itterator = Long.parseLong(inputDataContainer[1]);
				} catch (NumberFormatException exeptionV1) {
					System.out.println("The second parameter should be a natural number or zero.");
					System.out.println();
					throw new AmazingNumbers.MyExeption();
				}
				if(itterator < 0) {
					System.out.println("The second parameter should be a natural number or zero.");
					System.out.println();
					throw new AmazingNumbers.MyExeption();
				}
			}
			void inputRest() throws AmazingNumbers.MyExeption {
				ArrayList<String> tempcontainer = new ArrayList<String>();
				for (int i = 2; i < inputDataContainer.length; i++) {
					boolean trigger = false;
					for (int j = 0; j < propertiesDataContainer.length; j++) {
						if (inputDataContainer[i].equalsIgnoreCase(propertiesDataContainer[j].toString()) ||
							inputDataContainer[i].equalsIgnoreCase("-" + propertiesDataContainer[j].toString())){
							trigger = true;		
						}
					}
					if(!trigger) {
						tempcontainer.add(inputDataContainer[i]);
					}
				}
				if (tempcontainer.size() == 1) {
					System.out.println("The property [" + tempcontainer.get(0) + "] is wrong.");
					System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
					System.out.println();
					throw new AmazingNumbers.MyExeption();
				} 
				if (tempcontainer.size() > 1) {
					System.out.print("The properties [ ");
					for (int i = 0; i < tempcontainer.size(); i++) {
						System.out.print(tempcontainer.get(i) + " ");
					}
					System.out.println("] are wrong.");
					System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
					System.out.println();
					throw new AmazingNumbers.MyExeption();
				}
				for(int i = 2; i < inputDataContainer.length; i++) {
					for (int j = 0; j < inputDataContainer.length; j++) {
						if(inputDataContainer[i].equalsIgnoreCase("EVEN") && inputDataContainer[j].equalsIgnoreCase("ODD") ||
						   inputDataContainer[i].equalsIgnoreCase("-EVEN") && inputDataContainer[j].equalsIgnoreCase("-ODD")) {
							System.out.println("The request contains mutually exclusive properties: [" + inputDataContainer[i] + ", "
												+ inputDataContainer[j] + "]");
							System.out.println("There are no numbers with these properties.");
							System.out.println();
							throw new AmazingNumbers.MyExeption();
						}
						if(inputDataContainer[i].equalsIgnoreCase("DUCK") && inputDataContainer[j].equalsIgnoreCase("SPY") ||
						   inputDataContainer[i].equalsIgnoreCase("-DUCK") && inputDataContainer[j].equalsIgnoreCase("-SPY")) {
							System.out.println("The request contains mutually exclusive properties: [" + inputDataContainer[i] + ", "
												+ inputDataContainer[j] + "]");
							System.out.println("There are no numbers with these properties.");
							System.out.println();
							throw new AmazingNumbers.MyExeption();
						}
						if(inputDataContainer[i].equalsIgnoreCase("SUNNY") && inputDataContainer[j].equalsIgnoreCase("SQUARE")) {
							System.out.println("The request contains mutually exclusive properties: [" + inputDataContainer[i] + ", "
												+ inputDataContainer[j] + "]");
							System.out.println("There are no numbers with these properties.");
							System.out.println();
							throw new AmazingNumbers.MyExeption();
						}
						if(inputDataContainer[i].equalsIgnoreCase("HAPPY") && inputDataContainer[j].equalsIgnoreCase("SAD") ||
						   inputDataContainer[i].equalsIgnoreCase("-HAPPY") && inputDataContainer[j].equalsIgnoreCase("-SAD")) {
							System.out.println("The request contains mutually exclusive properties: [" + inputDataContainer[i] + ", "
												+ inputDataContainer[j] + "]");
							System.out.println("There are no numbers with these properties.");
							System.out.println();
							throw new AmazingNumbers.MyExeption();
						}
						if(inputDataContainer[i].equalsIgnoreCase("-" + inputDataContainer[j])) {
							System.out.println("The request contains mutually exclusive properties: [" + inputDataContainer[i] + ", "
												+ inputDataContainer[j] + "]");
							System.out.println("There are no numbers with these properties.");
							System.out.println();
							throw new AmazingNumbers.MyExeption();
						}
						
					}
				}	
			}//inputRest	
		}//Checker
		Checker check = new Checker();
		switch (inputDataContainer.length) {
			case(1): {
				check.inputFirst();
				break;
			}
			case(2): {
				check.inputFirst();
				check.inputSecond();
				break;
		}
		}
		if (inputDataContainer.length > 2) {
			check.inputFirst();
			check.inputSecond();
			check.inputRest();
		}
		return inputDataContainer;
	}//Скобка getValue
	/*Check Methods*/
	public static boolean checkHappy(long value) {	
		ArrayList<String> container = new ArrayList<String>();
		ArrayList<Integer> storage = new ArrayList<Integer>();
		int currentSum;
		do {
			currentSum = 0;
			container.removeAll(container);
			storage.removeAll(storage);
			int itterator = 0;
			do {
				container.add(String.valueOf(value).charAt(itterator) + "");
				itterator++;
			} while (String.valueOf(value).length() != itterator);
			for (int i = 0; i < container.size(); i++) {
				storage.add(Integer.parseInt(container.get(i)));
			}
			for (int i = 0; i < storage.size(); i++) {
				currentSum += storage.get(i)*storage.get(i);
			}
			if (currentSum / 10 != 0) {
				value = currentSum;
				continue;
			} 
		} while (currentSum / 10 != 0);
		if (currentSum == 1) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean checkSad(long value) {	
		ArrayList<String> container = new ArrayList<String>();
		ArrayList<Integer> storage = new ArrayList<Integer>();
		int currentSum;
		do {
			currentSum = 0;
			container.removeAll(container);
			storage.removeAll(storage);
			int itterator = 0;
			do {
				container.add(String.valueOf(value).charAt(itterator) + "");
				itterator++;
			} while (String.valueOf(value).length() != itterator);
			for (int i = 0; i < container.size(); i++) {
				storage.add(Integer.parseInt(container.get(i)));
			}
			for (int i = 0; i < storage.size(); i++) {
				currentSum += storage.get(i)*storage.get(i);
			}
			if (currentSum / 10 != 0) {
				value = currentSum;
				continue;
			} 
		} while (currentSum / 10 != 0);
		if (currentSum == 1) {
			return false;
		} else {
			return true;
		}
	}
	public static boolean checkJumping(long value) {
		boolean flag = true;
		String tempcontainer = String.valueOf(value);
		if (tempcontainer.length() >= 3 ||tempcontainer.length() == 1) {
			for (int i = 1; i < tempcontainer.length() - 1; i++) {
				if ((Math.abs((long)tempcontainer.charAt(i) - (long)tempcontainer.charAt(i+1)) != 1) || 
					(Math.abs((long)tempcontainer.charAt(i) - (long)tempcontainer.charAt(i-1)) != 1)) {	
					flag = false;
				}	
			}
		}
		if (tempcontainer.length() == 2) {
			for (int i = 0; i < tempcontainer.length() - 1; i++) {
				if ((Math.abs((long)tempcontainer.charAt(i) - (long)tempcontainer.charAt(i+1)) != 1)) {	
					flag = false;
					}	
			}
		}
		return flag;
	}
	public static boolean checkEven(long value) {
		return value % 2 == 0 ? true : false; 
	}
	public static boolean checkOdd(long value) {
		return value % 2 == 0 ? false : true; 
	}
	public static boolean checkSunny(long value) {
		if(checkSquare(value + 1)) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean checkSquare(long value) {
		return value == ((int)Math.sqrt(value)*(int)Math.sqrt(value)) ? true : false;	
	}
	
	public static boolean checkBuzz(long value) {
		return (value % 7 == 0) ? true : (value % 10 == 7) ? true : false;
	}
	public static boolean checkDuck(long value) {
		boolean flag = false;
		String s = value + "";
		char[] container = new char[s.length()];
		container = s.toCharArray();
		for (int i = 0; i < container.length; i++) {
			if (container[i] == '0') {
				if(container[i] != s.charAt(0)) {
					flag = true;
				}
			}
		}
		return flag;
	}
	public static boolean checkPalindromic(long value) {
		boolean trigger = true;
		String tempString = String.valueOf(value);
		for (int i  = 0; i < tempString.length(); i++) {
			if (tempString.charAt(i) != tempString.charAt(tempString.length() - 1  - i)) {
				trigger = false;
			}
		}
		return trigger;
	}
	public static boolean checkGapful(long value) {
		if (value / 10 >= 10) {
			String tempString  = String.valueOf(value);
			StringBuilder  strb = new StringBuilder();
			strb.append(tempString.charAt(0)).append(tempString.charAt(tempString.length() - 1));
			Integer sum = Integer.parseInt(strb.toString());
			if (value % (int) sum == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public static boolean checkSpy(long value) {
		String[] tempStringContainer = String.valueOf(value).split("");
		Long[] tempIntegerContainer = new Long[tempStringContainer.length];
		long sum = 0;
		long produce = 1;
		for (int i = 0; i < tempIntegerContainer.length; i++) {
			tempIntegerContainer[i] = Long.parseLong(tempStringContainer[i]);
			sum+=tempIntegerContainer[i];
			produce*=tempIntegerContainer[i];
		}
		if (sum == produce) {
			return true;
		} else {
			return false;
		}
	}
/*Output Methods*/	
	public static void oneNumberOutput() {
		StringBuilder output = new StringBuilder();
		System.out.println("Properties of " + value);
		output = checkEven(value) ? output.append("even: true \n") : output.append("even: false \n");
		output = checkOdd(value) ? output.append("odd: true \n") : output.append("odd: false \n");
		output = checkBuzz(value) ? output.append("buzz: true \n") : output.append("buzz: false \n");
		output = checkDuck(value) ? output.append("duck: true \n") : output.append("duck: false \n");
		output = checkPalindromic(value) ? output.append("palindromic: true \n") : output.append("palindromic: false \n");
		if (String.valueOf(value).length()>=3) {
			output = checkGapful(value) ? output.append("gapful: true \n") : output.append("gapful: false \n");
		} else {
			output.append("gapful: false \n");
		}
		output = checkSpy(value) ? output.append("spy: true \n") : output.append("spy: false \n");
		output = checkSquare(value) ? output.append("square: true \n") : output.append("square: false \n");
		output = checkSunny(value) ? output.append("sunny: true \n") : output.append("sunny: false \n");
		output = checkJumping(value) ? output.append("jumping: true \n") : output.append("jumping: false \n");
		output = checkHappy(value) ? output.append("happy: true \n") : output.append("happy: false \n");
		output = checkSad(value) ? output.append("sad: true \n") : output.append("sad: false \n");
		System.out.println(output);
	}
	public static void output() {
		System.out.print(value + " is ");
		/*Even Checker*/
		if (checkEven(value)) {
			System.out.print("even; ");
		}
		/*Odd Checker*/
		if (checkOdd(value)) {
			System.out.print("odd; ");
		}
		/*Buzz Checker*/
		if (checkBuzz(value)) {
			System.out.print("buzz; ");
		}
		/*Duck Checker*/
		if (checkDuck(value)) {
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
		/*Square Checker*/
		if (checkSquare(value)) {
			System.out.print("square; ");
		}
		/*Sunny Checker*/
		if (checkSunny(value)) {
			System.out.print("sunny; ");
		}
		/*Jumping Checker*/
		if (checkJumping(value)) {
			System.out.print("jumping; ");
		}
		/*Happy Checker*/
		if (checkHappy(value)) {
			System.out.print("happy; ");
		}
		/*Sad Checker*/
		if (checkSad(value)) {
			System.out.print("sad; ");
		}
		System.out.println();
	}
	public static boolean triggerSpecificChecker(String property) {
		boolean flag = false;
		if ("Even".equalsIgnoreCase(property)) {
			flag = checkEven(value);
		}
		if ("Odd".equalsIgnoreCase(property)) {
			flag = checkOdd(value);
		}
		if ("Buzz".equalsIgnoreCase(property)) {
			flag = checkBuzz(value);
		}
		if ("Duck".equalsIgnoreCase(property)) {
			flag = checkDuck(value);
		}
		if ("Palindromic".equalsIgnoreCase(property)) {
			flag = checkPalindromic(value);
		}
		if ("Gapful".equalsIgnoreCase(property)) {
			flag = checkGapful(value);
		}
		if ("Spy".equalsIgnoreCase(property)) {
			flag = checkSpy(value);
		}
		if ("Square".equalsIgnoreCase(property)) {
		    flag = checkSquare(value);
		}
		if ("Sunny".equalsIgnoreCase(property)) {
			flag = checkSunny(value);
		}
		if ("Jumping".equalsIgnoreCase(property)) {
			flag = checkJumping(value);
		}
		if ("Happy".equalsIgnoreCase(property)) {
			flag = checkHappy(value);
		}
		if ("Sad".equalsIgnoreCase(property)) {
			flag = checkSad(value);
		}
		return flag;
	}
} //Скобка класса




