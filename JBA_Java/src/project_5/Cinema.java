package project_5;
import java.util.Scanner;

class Cinema {
	private static int numOfRows;
	private static int numofSeatsInARow;
	private static int rowNum;
	private static int seatNumber;
	private static int numOfPurchasedTickets = 0;
	private static int currentIncome = 0;
	private static String[][] cinemaRoom;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of rows:");
		numOfRows = scanner.nextInt();
		System.out.println("Enter the number of seats in each row:");
		numofSeatsInARow = scanner.nextInt();
		/*Filling the array*/;
		cinemaRoom = new String [numOfRows+1][numofSeatsInARow+1];
		for (int i = 0; i < cinemaRoom.length; i++) {
			for (int j = 0; j < cinemaRoom[i].length; j++) {
				if (i == 0) {
					cinemaRoom[i][j] = String.valueOf(j);
					continue;
				}
				if (j == 0) {
					cinemaRoom[i][j] = String.valueOf(i) ;
					continue;
				}
				cinemaRoom[i][j] = "S";
			}
		}
		cinemaRoom[0][0] = " ";
		do {
			System.out.println();
			System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
			int checkOption = scanner.nextInt();
			switch (checkOption) {
				case (1) : {
					System.out.println();
					printArray();
					System.out.println();
					continue;
				}
				case (2) : {
					do {
						try {
							System.out.println();
							System.out.println("Enter a row number:");
							rowNum = scanner.nextInt();
							System.out.println("Enter a seat number in that row:");
							seatNumber = scanner.nextInt();
							if (cinemaRoom[rowNum][seatNumber] != "B") {
								System.out.println("Ticket price: $" + checkPrice());
								cinemaRoom[rowNum][seatNumber] = "B";
								numOfPurchasedTickets++;
								break;
							} else {
								System.out.println("That ticket has already been purchased!");
								continue;
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("Wrong input!");
							continue;
						}
					} while (true);
					continue;
				}
				case (0) : {
					return;
				}
				case (3) : {
					showStatistic();
					continue;
				}
			}
		} while (true);

	} //Main

	public static int checkPrice() {
		int ticketPrice = 1;
		if (numOfRows * numofSeatsInARow <= 60) {
			ticketPrice = 10;
		} else {
			if  ((numOfRows / 2) >= rowNum) {
				ticketPrice = 10;
			} else {
				ticketPrice = 8;
			}
	    }
		currentIncome+=ticketPrice;
		return ticketPrice;	
	}
	
	public static void printArray() {
		System.out.println("Cinema:");
		for (int i = 0; i < cinemaRoom.length; i++) {
			for (int j = 0; j < cinemaRoom[i].length; j++) {
				System.out.print(cinemaRoom[i][j] + " ");
			}
			if (i != cinemaRoom.length-1) {
				System.out.println();
			}
		}
	}
	
	public static int totalIncome() {
		
		int priceForRow = 0;
		int totalIncome = 0;
		if (numOfRows * numofSeatsInARow <= 60) {
			priceForRow = 10;
			totalIncome = priceForRow * numOfRows * numofSeatsInARow;
		} else {
			int counter = 0;
			do {
				priceForRow = 10;
				totalIncome += priceForRow * numofSeatsInARow;
				counter++;
			} while (numOfRows / 2 != counter);
			do {
				priceForRow = 8;
				totalIncome += priceForRow * numofSeatsInARow;
				counter++;
			} while (numOfRows != counter);
		}
		return totalIncome;
	}
	
	public static void showStatistic() {
		System.out.println();
		System.out.println("Number of purchased tickets: " + numOfPurchasedTickets);
		double persenatge = (numOfPurchasedTickets/((double)numOfRows*numofSeatsInARow)) * 100;
		System.out.printf("Percentage: %3.2f%%%n", persenatge);
		System.out.println("Current income: $" + currentIncome);
		System.out.println("Total income: $" + totalIncome());
	}
} //Cinema

