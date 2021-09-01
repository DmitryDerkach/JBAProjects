import java.util.Scanner;

public class CoffeeMachine {
	private static Scanner sc = new Scanner(System.in);
	private static int[] Stock = {400, 540, 120, 9, 550};
	
	static class MyExeption extends Throwable {
		//just for making simple cycle
	}
	
	public static void main(String[] args) {
		do {
		breakpoint : {
			try {
				System.out.println("Write action (buy, fill, take, remaining, exit): ");
				String desiredAction = sc.next();
				switch (desiredAction) {
					case ("buy") : {
						buyCoffee();
						break;	
					}
					case ("fill") : {
						fillMachine();
						break;
					}
					case ("take") : {
						takeMoney();
						break;
					}
					case ("remaning") : {
						output();
						break;
					}
					case ("exit") : {
						return;
					}
				}
			} catch (MyExeption e) {
				break breakpoint;
			}
		}//Breakpoint	
		} while(true);
	}// Main
	public static void output() throws MyExeption{
		System.out.printf("%d ml of water\n%d ml of milk\n%d g of coffee beans\n%d disposable cups\n$%d of money",
				          Stock[0], Stock[1], Stock[2], Stock[3], Stock[4]);
		System.out.println();
		throw new MyExeption();
	}
	public static void buyCoffee() throws MyExeption {
		System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
		int desiredCoffee = sc.nextInt();
		switch (desiredCoffee) {
			case (1) : {
				int[] oneEspressoCup = {250, 16, 4};
				if(Stock[0] >= oneEspressoCup[0] && Stock[2] >= oneEspressoCup[1] && Stock[3] >= 1) {
					System.out.println("I have enough resources, making you a coffee!");
					Stock[0] = Stock[0] - oneEspressoCup[0]; //-Water
					Stock[1] = Stock[1]; //No milk
					Stock[2] = Stock[2] - oneEspressoCup[1]; //-Beans
					Stock[3] = Stock[3] - 1; //-Cup
					Stock[4] = Stock[4] + 4; //+4$ 
					System.out.println();
					throw new MyExeption();
				} else { 
					if (Stock[0] < oneEspressoCup[0]) {
						System.out.println("Sorry, not enough water!");
						System.out.println();
						throw new MyExeption();
					}
					if (Stock[2] < oneEspressoCup[1]) {
						System.out.println("Sorry, not enough coffee beans!");
						System.out.println();
						throw new MyExeption();
					}
					if (Stock[3] < 1) {
						System.out.println("Sorry, not enough disposable cups!");
						System.out.println();
						throw new MyExeption();
					}
				}
			}// Case 1
			case (2) : {
				int[] oneLatteCup = {350, 75, 20, 7};
				if (Stock[0] >= oneLatteCup[0] && Stock[1] >= oneLatteCup[1] && Stock[2] >= oneLatteCup[2] && Stock[3] >= 1) {
					System.out.println("I have enough resources, making you a coffee!");
					Stock[0] = Stock[0] - oneLatteCup[0]; //-Water
					Stock[1] = Stock[1] - oneLatteCup[1]; //-Milk
					Stock[2] = Stock[2] - oneLatteCup[2]; //-Beans
					Stock[3] = Stock[3] - 1; //-Cup
					Stock[4] = Stock[4] + 7; //+7$ 
					System.out.println();
					throw new MyExeption();
				} else {
					if (Stock[0] < oneLatteCup[0]) {
						System.out.println("Sorry, not enough water!");
						System.out.println();
						throw new MyExeption();
					}
					if (Stock[1] < oneLatteCup[1]) {
						System.out.println("Sorry, not enough milk!");
						System.out.println();
						throw new MyExeption();
					}
					if (Stock[2] < oneLatteCup[2]) {
						System.out.println("Sorry, not enough coffee beans!");
						System.out.println();
						throw new MyExeption();
					}
					if (Stock[3] < 1) {
						System.out.println("Sorry, not enough disposable cups!");
						System.out.println();
						throw new MyExeption();
					}
				}
			}// Case 2
			case (3) : {
				int[] oneCappuccinoCup = {200, 100, 12, 6};	
				if(Stock[0] >= oneCappuccinoCup[0] && Stock[1] >= oneCappuccinoCup[1] && Stock[2] >= oneCappuccinoCup[2] && Stock[3] >= 1) {
					System.out.println("I have enough resources, making you a coffee!");
					Stock[0] = Stock[0] - oneCappuccinoCup[0]; //-Water
					Stock[1] = Stock[1] - oneCappuccinoCup[1]; //-Milk
					Stock[2] = Stock[2] - oneCappuccinoCup[2]; //-Beans
					Stock[3] = Stock[3] - 1; //-Cup
					Stock[4] = Stock[4] +	6; //+6$
					System.out.println();
					throw new MyExeption();
				} else {
					if (Stock[0] < oneCappuccinoCup[0]) {
						System.out.println("Sorry, not enough water!");
						System.out.println();
						throw new MyExeption();
					}
					if (Stock[1] < oneCappuccinoCup[1]) {
						System.out.println("Sorry, not enough milk!");
						System.out.println();
						throw new MyExeption();
					}
					if (Stock[2] < oneCappuccinoCup[2]) {
						System.out.println("Sorry, not enough coffee beans!");
						System.out.println();
						throw new MyExeption();
					}
					if (Stock[3] < 1) {
						System.out.println("Sorry, not enough disposable cups!");
						System.out.println();
						throw new MyExeption();
					}
				}
			}// Case 3
			default : {
				System.out.println();
				throw new MyExeption();
			}
		}// Switch
	}
	public static void fillMachine() throws MyExeption {
		System.out.println("Write how many ml of water you want to add: ");
		Stock[0] = Stock[0] + sc.nextInt();
		System.out.println("Write how many ml of milk you want to add:  ");
		Stock[1] = Stock[1] + sc.nextInt();
		System.out.println("Write how many grams of coffee beans you want to add:  ");
		Stock[2] = Stock[2] + sc.nextInt();
		System.out.println("Write how many disposable cups of coffee you want to add:  ");
		Stock[3] = Stock[3] + sc.nextInt();
		System.out.println();
		throw new MyExeption();
	}
	public static void takeMoney() throws MyExeption {
		System.out.printf("I gave you $%d\n", Stock[4]);
		Stock[4] = 0;
		System.out.println();
		throw new MyExeption();
	}	
} // CM Class
