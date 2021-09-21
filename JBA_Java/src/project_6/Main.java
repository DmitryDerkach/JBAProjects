package project_6;

import java.util.*;
public class Main {
	private static ArrayList<String> container = null;
	private static String secretCode = "";
	private static int secretCodeLength = 0;
	private static ArrayList<String> tempSecretCode = null;
	private static Random random = new Random();
	private static Scanner scanner = new Scanner(System.in);

	static class MyExeption extends Throwable {

	}

	public static void main(String[] args) {
		System.out.println("Input the length of the secret code:");
		String possibleLength = scanner.nextLine();
		try {
			secretCodeLength = Integer.parseInt(possibleLength);
		} catch (NumberFormatException e) {
			System.out.println("Error: \"" + possibleLength + "\" isn't a valid number.");
			return;
		}
		System.out.println("Input the number of possible symbols in the code:");
		String possibleUniqueSymbols = scanner.nextLine();
		int uniqueSymbols = 0;
		try {
			uniqueSymbols = Integer.parseInt(possibleUniqueSymbols);
		} catch (NumberFormatException e) {
			System.out.println("Error: \"" + possibleUniqueSymbols + "\" isn't a valid number.");
			return;
		}
		if (uniqueSymbols < secretCodeLength) {
			System.out.println("Error: it's not possible to generate a code with a length of 6 with 5 unique symbols.");
			return;
		}
		if (uniqueSymbols > 36) {
			System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
			return;
		}
		String temp = "";
		try {
			temp = getPassword(secretCodeLength, uniqueSymbols);
		} catch (MyExeption e) {
			return;
		}
		System.out.print("The secret password is prepared: ");
		do {
			System.out.print("*");
			secretCodeLength--;
		} while (secretCodeLength != 0);
		if (uniqueSymbols <= 10 ) {
			System.out.println(" (0-9).");
		} else {
			if (uniqueSymbols == 11) {
				System.out.println(" (0-9), a");
			} else {
				System.out.println(" (0-9), (" + container.get(10) + "-" + container.get(uniqueSymbols-1) + ").");
			}
		}
		System.out.print("Okay, let's start a game!");
		int turnCounter = 0;
		do {
			int bullCounter = 0;
			int cowCounter = 0;
			turnCounter++;
			System.out.println();
			System.out.println("Turn " + turnCounter);
			String probabpleCode = scanner.next();
			for (int i = 0; i < secretCode.length(); i++) {
				if (secretCode.charAt(i) == probabpleCode.charAt(i)) {
					bullCounter++;
				}
			}
			for (int i = 0; i < secretCode.length(); i++) {
				if ((secretCode.contains(probabpleCode.charAt(i) + "")) && (secretCode.charAt(i) != probabpleCode.charAt(i))) {
					cowCounter++;
				}
			}
			if (cowCounter == 0 && bullCounter != 0) {
				if (bullCounter == probabpleCode.length()) {
					if (bullCounter == 1) {
						System.out.printf("Grade: 1 bull.");
						System.out.println();
						System.out.println("Congratulations! You guessed the secret code.");
						return;
					} else {
						System.out.printf("Grade: %d bulls.", bullCounter);
						System.out.println();
						System.out.println("Congratulations! You guessed the secret code.");
						return;
					}
				}
				switch (bullCounter) {
					case (1) : {
						System.out.printf("Grade: %d bull.", bullCounter);
						continue;
					}
					default : {
						System.out.printf("Grade: %d bulls.", bullCounter);
						continue;
					}
				}
			}
			if (cowCounter != 0 && bullCounter == 0) {
				if (bullCounter > 1) {
					System.out.printf("Grade: %d cows.", cowCounter);
					continue;
				} else {
					System.out.printf("Grade: %d cow.", cowCounter);
					continue;
				}
			}
			if (cowCounter == 0 && bullCounter == 0) {
				System.out.printf("Grade: None.");
				continue;
			}
			if (cowCounter != 0 && bullCounter != 0) {
				if (cowCounter == 1 && bullCounter != 1) {
					System.out.printf("Grade: %d bulls and %d cow.", bullCounter, cowCounter);
					continue;
				}
				if (cowCounter != 1 && bullCounter == 1) {
					System.out.printf("Grade: %d bull and %d cows.", bullCounter, cowCounter);
					continue;
				}
				if (cowCounter == 1 && bullCounter == 1) {
					System.out.printf("Grade: %d bull and %d cow.", bullCounter, cowCounter);
					continue;
				}
				if (cowCounter != 1 && bullCounter != 1) {
					System.out.printf("Grade: %d bulls and %d cows.", bullCounter, cowCounter);
					continue;
				}
			}
		} while (true);

	} //Main

	public static String getPassword(int secretCodeLength, int uniqueSymbols) throws MyExeption {
		container = new ArrayList<String>(Arrays.asList("0123456789abcdefghigklmnopqrstuvwxyz".split("")));
		if (secretCodeLength == 0) {
			System.out.println("Error: Password cannot be empty.");
			throw new MyExeption();
		}
		tempSecretCode = new ArrayList<>();
		int counter = 0;
		do {
			tempSecretCode.add(container.get(counter));
			/*Логика на случай, когда кол-во уникальных случаев недотягивает до длины пароля*/
//			if (tempSecretCode.size() == uniqueSymbols) {
//				while (tempSecretCode.size() < secretCodeLength) {
//					tempSecretCode.add(tempSecretCode.get(random.nextInt(tempSecretCode.size() - 1)));
//				}
//			}
			if (tempSecretCode.size() ==  secretCodeLength) {
				break;
			}
			counter++;
		} while (true);
		Collections.shuffle(tempSecretCode);
		for (int i = 0; i < tempSecretCode.size(); i++) {
			secretCode += tempSecretCode.get(i);
		}
		return secretCode;
	}
}