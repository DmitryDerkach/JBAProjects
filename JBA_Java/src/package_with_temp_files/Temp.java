package package_with_temp_files;

import java.util.Random;

public class Temp {
/*Алгоритм Луа*/
	public static void main(String[] args) {
		
	}
	
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
	
}
