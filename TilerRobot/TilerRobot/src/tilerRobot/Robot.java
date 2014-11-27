package tilerRobot;

import java.io.IOException;
import java.lang.System;
import java.util.Scanner;

public class Robot {
	static int NOT_INTEGRAL_VALUE_ERROR = -1;
	static int IO_ERROR = -2;

	private static int getIntegralValue(Scanner myInputScanner) {
		int inputValue = 0;
		boolean isIntegral = false;
		char proceedUserAnswer = 'Q';

		while (!isIntegral) {
			try {
				if (myInputScanner.hasNextInt()) {
					inputValue = myInputScanner.nextInt();
					myInputScanner.nextLine();
					isIntegral = true;
				} else {
					System.out
							.print("Not an integral value, try [a]gain or [q]uit? [q]: ");
					try {
						proceedUserAnswer = (char) System.in.read();
					} catch (IOException e) {
						proceedUserAnswer = 'Q';
					}
					if ((proceedUserAnswer == 'A')
							|| (proceedUserAnswer == 'a')) {
						myInputScanner.next();
						System.out.println("Enter an itegral value: ");
						isIntegral = false;
					} else {
						System.out.println("Quitting...");
						System.exit(NOT_INTEGRAL_VALUE_ERROR);
					}
				}
			}

			catch (IllegalStateException e) {
				System.out.println("I/O error.\n");
				System.exit(IO_ERROR);
			}
		}

		return inputValue;
	}

	public static void main(String[] args) {
		int floorWidth, floorLength;
		int[] columnTileCount = null, rowTileCount = null;
		int choise;
		Floor currentFloor = null;

		Scanner myInputScanner = new Scanner(System.in);

		System.out.println("Welcome to the tiling robot");

		System.out.println("Please enter floor M size below:");
		floorWidth = getIntegralValue(myInputScanner);
		System.out.println("Please enter floor N size below:");
		floorLength = getIntegralValue(myInputScanner);
		currentFloor = Floor.getFloorInstance(floorWidth, floorLength);
		while(true){
			System.out.println("Please choose an action from the list below:");
			System.out.println("1. Color tile");
			System.out.println("2. Color rectangular");
			System.out.println("3. Count black tiles in rows");
			System.out.println("4. Count black tiles in columns");
			System.out.println("5. Count all black tiles");
			System.out.println("6. Check if a rectangular fits");
			System.out.println("7. Check if floor meets the tiling rules");
	
			choise = getIntegralValue(myInputScanner);
			if (choise > 0 && choise < 9) {
				switch (choise) {
					case 1:
						System.out.println("Please enter tile coordiantes below:");
						int x = getIntegralValue(myInputScanner);
						int y = getIntegralValue(myInputScanner);
						System.out.println("Please enter 1 for black and 0 for white:");
						int color = myInputScanner.nextInt();
						if (color == 1) {
							currentFloor.colorTile(x, y, currentFloor.black);
						} else {
							currentFloor.colorTile(x, y, currentFloor.white);
						}
						break;
					case 2:
						System.out.println("Please enter tile coordiantes below:");
						int x1 = getIntegralValue(myInputScanner);
						int y1 = getIntegralValue(myInputScanner);
						System.out.println("Please enter dimensions:");
						int x2 = getIntegralValue(myInputScanner);
						int y2 = getIntegralValue(myInputScanner);
						currentFloor.colorTileRect(x1, y1, x2, y2);
						break;
					case 3:
						rowTileCount = currentFloor.getRowTileCount();
						for (int i = 0; i < floorWidth; i++) {
							System.out.println("black tiles in row " + i + " = "
									+ rowTileCount[i] + " ");
						}
						break;
					case 4:
						columnTileCount = currentFloor.getColumnTileCount();
						for (int i = 0; i < floorLength; i++) {
							System.out.println("black tiles in column " + i + " = "
									+ columnTileCount[i] + " ");
						}
						break;
					case 5:
						System.out.println("total number of black tiles: "
								+ currentFloor.getTileCount());
						break;
					case 6:
						System.out.println("Please enter rectangle coordiantes below:");
						int x3 = getIntegralValue(myInputScanner);
						int y3 = getIntegralValue(myInputScanner);
						System.out.println("Please enter dimensions:");
						int x4 = getIntegralValue(myInputScanner);
						int y4 = getIntegralValue(myInputScanner);
						System.out.println("tried fitting" + x4 + "x" + y4 + "@" + x3
								+ "," + y3 + " coords: "
								+ currentFloor.checkRectClear(x3, y3, x4, y4));
						break;
					case 7:
						System.out.println("rectangle conditions are met: "
								+ currentFloor.checkRectanglesCorrect());
						break;
				}
			}
		}
	}

}
