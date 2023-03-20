package BullsAndCows;

import java.util.Scanner;

public class Stage4 {

	static String secretCode; // generating secret code
	static int length = 0;
	static boolean answerIsRight = false; // mark to stop the game if answer is right

	public static void main(String[] args) {
		// System.out.println("secretCode: " + secretCode);
		System.out.println("Please, enter the secret code's length:");
		Scanner in = new Scanner(System.in);
		String input = "";

		length = in.nextInt();
		if (length > 9) {
			System.out.println("Error: can't generate a secret number with a length of " + length
					+ " because there aren't enough unique digits.");
			return;
		}

		secretCode = generateSecretCode(); // generating secret code

		// var for turn counter
		int turn = 1;
		System.out.println("Okay, let's start a game!");

		// start turn1 of the game, with turn counter printed
		do {
			System.out.println("Turn " + turn + ":");
			game();
			turn++;

		} while (!answerIsRight);

		System.out.println("Congratulations! You guessed the secret code.");

	}

	public static String generateSecretCode() {

		String result = "";
		long pseudoRandomNumber = 0;
		StringBuilder sb = new StringBuilder();

		do {
			result = sb.toString();
			pseudoRandomNumber = System.nanoTime() / (result.length() + 3);
			String a = String.valueOf(pseudoRandomNumber);
			String b = String.valueOf(a.charAt(a.length() / 2));

			if (!result.contains(b)) {
				sb = sb.append(b);
			}

			if (sb.charAt(0) == '0') {
				sb = sb.deleteCharAt(0);
			}

		} while (sb.length() != length);

		return sb.toString();

	}

	private static void game() {
		Scanner in = new Scanner(System.in);

		// get secret code char size
		int secretCodeLength = secretCode.length();

		// declaring bulls and cows
		int bulls = 0;
		int cows = 0;

		// user input
		String input = in.nextLine();

		// check for bulls
		if (secretCode.equals(input)) {
			bulls = secretCode.length();
			answerIsRight = true;
		} else {
			for (int i = 0; i < secretCodeLength; i++) {
				if (secretCode.charAt(i) == input.charAt(i)) {
					bulls++;
				} else if (secretCode.contains(String.valueOf(input.charAt(i)))) {
					cows++;
				}
			}
		}

		if (bulls > 0 && cows > 0) {
			System.out.printf("Grade: %d bull(s) and %d cow(s). \n", bulls, cows);
		} else if (bulls > 0) {
			System.out.printf("Grade: %d bull(s). \n", bulls);
		} else if (cows > 0) {
			System.out.printf("Grade: %d cows(s). \n", cows);
		} else {
			System.out.printf("Grade: None.  \n");
		}
	}

}