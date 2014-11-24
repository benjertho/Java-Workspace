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
		int[] columnTileCount, rowTileCount;
		Floor currentFloor;
		
		Scanner myInputScanner = new Scanner(System.in);
		System.out.println("Welcome to the tiling robot");
		System.out.println("Please enter floor M size below:");
		floorWidth = getIntegralValue(myInputScanner);
		System.out.println("Please enter floor N size below:");
		floorLength = getIntegralValue(myInputScanner);
		
		currentFloor = new Floor(floorWidth, floorLength);
		
		currentFloor.colorTileRect(1, 1, 2, 2);
		
		currentFloor.colorTile(5, 5, currentFloor.black);
		
		columnTileCount = currentFloor.getColumnTileCount();
		rowTileCount = currentFloor.getRowTileCount();
		
		for(int i = 0; i < floorWidth; i++) {
			System.out.println("black tiles in row " + i + " = " + rowTileCount[i] + " ");
		}
		
		for(int i = 0; i < floorLength; i++) {
			System.out.println("black tiles in column " + i + " = " + columnTileCount[i] + " ");
		}
		
		System.out.println("total number of black tiles: " + currentFloor.getTileCount());
		
		System.out.println("tried fitting 2x2 @ 10,10 coords: " + currentFloor.checkRectClear(8, 8, 2, 4));
		
		System.out.println("rectangle conditions are met: " + currentFloor.checkRectangleViolation());
		
		myInputScanner.close();
	}

}
