import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	private static class MyExeption extends Throwable {
		
	}
	private static String[][] inputDataContainer = null;
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.print("Enter cells: ");
		inputDataContainer = new String[3][3];
		inputDataContainer = Main.InnerClass.fillingTheGrid();
		Main.InnerClass.gridOutput();
		do {
			point : {
				System.out.print("Enter the coordinates: ");
				try {
					inputDataContainer = Main.InnerClass.gridChange(sc.nextInt(), sc.nextInt());
				} catch (MyExeption e) {
					break point;
				} catch (InputMismatchException e) {
					System.out.println("You should enter numbers!");
					sc.nextLine();
					break point;
				}
				Main.InnerClass.gridOutput();
				System.out.println(Main.InnerClass.currentGameState());
				return;
			}
		} while (true);
	}

	private static class InnerClass {
		private static StringBuilder tempStorage = new StringBuilder();
		private static final String XSEQUENCE = "XXX";
		private static final String OSEQUENCE = "OOO";
		public static boolean xSequence = false;
		public static boolean oSequence = false;
		
		public static String[][] gridChange(int a, int b) throws MyExeption {
			if ((a > 3 || b > 3) || (a < 1 || b < 1)) {
				System.out.println("Coordinates should be from 1 to 3!");
				throw new MyExeption();
			}
			if (inputDataContainer[a-1][b-1].equals("X") || inputDataContainer[a-1][b-1].equals("O")) {
				System.out.println("This cell is occupied! Choose another one!");
				throw new MyExeption();
			}
			inputDataContainer[a-1][b-1] = "X";
			return inputDataContainer;
		}
		public static String[][] fillingTheGrid() {
			String inputLine = sc.nextLine();
			char[] inputLineCharContainer = inputLine.toCharArray();
			int arrayColumnItterator = 0;
			int arrayLineItterator = 0;
			int inputLineContainerItterator = 0;
			do {
				do {
					inputDataContainer[arrayLineItterator][arrayColumnItterator] = inputLineCharContainer[inputLineContainerItterator] + "";
					arrayColumnItterator++;
					inputLineContainerItterator++;
				} while (arrayColumnItterator != 3);
				arrayColumnItterator = 0;
				arrayLineItterator++;
			} while (arrayLineItterator < 3);
			return inputDataContainer;
		}
		public static void gridOutput() {
			System.out.println("---------");
			System.out.print("| ");
			for (int i = 0; i < inputDataContainer.length; i++) {
				for (int j = 0; j < inputDataContainer[i].length; j++) {
					System.out.print(inputDataContainer[i][j] + " ");
				}
				System.out.print("|");
				if (i != inputDataContainer.length - 1) {
					System.out.println();
					System.out.print("| ");
				} else {
					break;
				}
			}
			System.out.println();
			System.out.println("---------");
		}
		public static String currentGameState() {
			StringBuilder result = new StringBuilder();
			do {
				if (impossibleCheck()) {
					result.append("Impossible");
					break;
				}
				if (xWinsCheck()) {
					result.append("X wins");
					break;
				}
				if (oWinsCheck()) {
					result.append("O wins");
					break;
				}
				if (drawCheck()) {
					result.append("Draw");
					break;
				}
				if (gameNotFinishedCheck()) {
					result.append("Game not finished");
					break;
				}
			} while (true);
			return result.toString();
		}
		public static boolean sequenceChecker() {
			boolean checkState = false;
			switch(tempStorage.toString()) {
				case (XSEQUENCE): {
					xSequence = true;
					checkState = true;
					break;
				}
				case (OSEQUENCE): {
					oSequence = true;
					checkState = true;
					break;
				}
			}
			tempStorage.delete(0, 3);
			return checkState;
		}
		public static boolean rowsElementsSequenceCheck() {
			boolean checkState = false;
			for (int i = 0; i < inputDataContainer.length; i++) {
				for (int j = 0; j < inputDataContainer[i].length; j++) {
					tempStorage.append(inputDataContainer[i][j]);
				}
				if(sequenceChecker()){
					checkState = true;
				}
			}
			return checkState;
		}
		public static boolean columnsElementsSequenceCheck() {
			boolean checkState = false;
			for (int i = 0; i < inputDataContainer.length; i++) {
				for (int j = 0; j < inputDataContainer[i].length; j++) {
					tempStorage.append(inputDataContainer[j][i]);
				}
				if(sequenceChecker()){
					checkState = true;
				}
			}
			return checkState;
		}
		public static boolean mainDiagElementSequenceCheck() {
			boolean checkState = false;
			int tempItterator = 0;
			do {
				tempStorage.append(inputDataContainer[tempItterator][tempItterator]);
				tempItterator++;
			} while (tempItterator < 3);
			checkState = sequenceChecker();
			return checkState;
		}
		public static boolean supDiagElementSequenceCheck() {
			boolean checkState = false;
			for (int i = 0; i < inputDataContainer.length; i++) {
				tempStorage.append(inputDataContainer[i][inputDataContainer.length - 1 - i]);
			}
			checkState = sequenceChecker();
			return checkState;
		}
		public static boolean emptyCellsCheck() {
			boolean checkState = false;
			String tempContainer = "";
			point : {
				for (int i = 0; i < inputDataContainer.length; i++) {
					for (int j = 0; j < inputDataContainer[i].length; j++) {
						tempContainer = inputDataContainer[i][j];
						if (tempContainer.equals("_")) {
							checkState = true;
							break point;
						}
					}
				}
			}
			return checkState;
		}
		public static boolean checkingTheSequenceOfThree() {
			boolean checkState = false;
			int magicalNum = 0;
			do {
				checkState = rowsElementsSequenceCheck() ? true : false;
				if (checkState) {
					break;
				}
				checkState = columnsElementsSequenceCheck() ? true : false;
				if (checkState) {
					break;
				}
				checkState = mainDiagElementSequenceCheck() ? true : false;
				if (checkState) {
					break;
				}
				checkState = supDiagElementSequenceCheck() ? true : false;
				if (checkState) {
					break;
				}
			} while (magicalNum != 0);
			return checkState;
		}
		/*Game States Checks*/
		public static boolean gameNotFinishedCheck() {
			boolean checkState = false;
			if (!checkingTheSequenceOfThree()) {
				checkState = emptyCellsCheck();
			}
			return checkState;	
		}
		public static boolean drawCheck() {
			boolean checkState = false;
			StringBuilder tempStorage = new StringBuilder();
			if (!checkingTheSequenceOfThree() && !emptyCellsCheck()) {
				checkState = true;	
			}
			return checkState;
		}
		public static boolean xWinsCheck() {
			boolean checkState = false;
			if(checkingTheSequenceOfThree() && xSequence) {
				checkState = true;
			}
			return checkState;
		}
		public static boolean oWinsCheck() {
			boolean checkState = false;
			if(checkingTheSequenceOfThree() && oSequence) {
				checkState = true;
			}
			return checkState;
		}
		public static boolean impossibleCheck() {
			int xCounter = 0;
			int oCounter = 0;
			boolean checkState = false;
			if (xWinsCheck() && oWinsCheck()) {
				checkState = true;
			}
			for (int i = 0 ; i < inputDataContainer.length; i++) {
				for (int j = 0; j < inputDataContainer[i].length; j++) {
					if (inputDataContainer[i][j].equals("X")) {
						xCounter++;
					}
					if (inputDataContainer[i][j].equals("O")) {
						oCounter++;
					}
				}
			}
			if (Math.abs(xCounter - oCounter) >= Math.abs(2)) {
				checkState = true;
			}
			return checkState;
		}
	}// Inner Class

}// Main Class
