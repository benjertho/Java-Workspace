package tilerRobot;
import java.io.IOException;
import java.lang.System;
import java.util.Scanner;

public class Robot {
	static int NOT_INTEGRAL_VALUE_ERROR = -1;
	static int IO_ERROR = -2;
	
	private int getIntegralValue(Scanner myInputScanner) {
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
		// TODO Auto-generated method stub
		Scanner myInputScanner = new Scanner(System.in);
		System.out.println("Welcome to the tiling robot");
	}

}
