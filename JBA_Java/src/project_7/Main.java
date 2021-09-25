package project_7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final ArrayList<long[][]> dataStorage = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        do {
            System.out.println("1. Create an account\n2. Log into account\n0. Exit");
            int userСhoice = scanner.nextInt();
            switch (userСhoice) {
                case (1) : {
                	System.out.println();
                    createAccount();
                    continue;
                }
                case (2) : {
                    long [] tempStorage = longInto();
                    if (tempStorage[0] == 0 && tempStorage[1] == 0) {
                        System.out.println("Wrong card number or PIN!");
                        continue;
                    } else {
                        System.out.println("You have successfully logged in!");
                        cardOperations(tempStorage);
                        continue;
                    }
                }
                case (0) : {
                	System.out.println("Bye!");
                    System.exit(0);
                }
                default : {
                    System.out.println("Wrong input");
                }
            }
        } while (true);
    }// Main

    public static void createAccount() {
        Random random = new Random();
        System.out.println("Your card has been created\nYour card number:");
        String tempCardnumber = ""; //16 nums Total
        tempCardnumber = "400000" + random.nextInt(1000000000) + "5";// First and last (6 + 1 = 7) is predifined
        long cardNumber = Long.parseLong(tempCardnumber);
        System.out.println(cardNumber);
        System.out.println("Your card PIN:");
        int pin = random.nextInt(9000) + 1000;
        System.out.println(pin);
        long [][] tempStorage = {{cardNumber, pin},{0}}; //cardNum + cardPin || cardBalance 
        dataStorage.add(tempStorage);
    }

    public static long[] longInto() {
        System.out.println("Enter your card number:");
        String cardNum = scanner.next();
        System.out.println("Enter your PIN:");
        int cardPassword = scanner.nextInt();
        long [] tempStorage = {Long.parseLong(cardNum), cardPassword};
        for (long[][] fromStorage : dataStorage) {
            if (Arrays.equals(tempStorage, fromStorage[0])) {
                return tempStorage;
            }
        }
        return new long[]{0,0};
    }

    public static void cardOperations(long[] cardData){
        final class actionsWithCard {
            private void possibleActions() {
                do {
                    System.out.println("1. Balance\n2. Log out\n0. Exit");
                    int userChoice = scanner.nextInt();
                    switch (userChoice) {
                        case (1) : {
                            getBalance(cardData);
                            continue;
                        }
                        case (2) : {
                            System.out.println("You have successfully logged out!");
                            return;
                        }
                        case (0) : {
                            System.exit(0);
                        }
                        default : {
                            System.out.println("Wrong input!");
                        }
                    }
                } while (true);
            }

            private void getBalance(long[] cardData) {
                for (long[][] tempStorage : dataStorage) {
                    if (Arrays.equals(tempStorage[0], cardData)) {
                        System.out.println("Balance: " + tempStorage[1][0]);
                    }
                }
            }
        }; // End of <<actionsWithCard>> class
        actionsWithCard action = new actionsWithCard();
        action.possibleActions();

    }// End of cardOperations() method

}//End of the class
