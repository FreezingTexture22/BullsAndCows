package BullsAndCows;

import java.util.Scanner;

public class Stage7 {

    static String secretCode; // generating secret code
    static int length = 0;
    static boolean answerIsRight = false; // mark to stop the game if answer is right
    static int NumberOfPossibleSymbols = 0;
    static boolean lengthIsOk = false;

    public static void main(String[] args) {

        userInput();



    }

    static void userInput() {
        // System.out.println("secretCode: " + secretCode);
        System.out.println("Input the length of the secret code:");
        Scanner in = new Scanner(System.in);
        String input = "";

        String tmpInput = in.nextLine();

        if (!tmpInput.matches("\\d+")) {
            System.out.println("Error: NOT NUMBERS");
            return;
        } else {
            length = Integer.parseInt(tmpInput);
        }

        if (length == 0) {
            System.out.println("Error: length can not be 0");
            return;
        }

        // get number of possible symbols in code

        System.out.println("Input the number of possible symbols in the code:");
        String tmpInput2 = in.nextLine();

        if (!tmpInput2.matches("\\d+")) {
            System.out.println("Error: NOT NUMBERS");
            return;

        } else {
            NumberOfPossibleSymbols = Integer.parseInt(tmpInput2);
        }

        if (NumberOfPossibleSymbols < length) {
            System.out.println("Error: it's not possible to generate a code with a length less than unique symbols.");
            return;
        }


        if (NumberOfPossibleSymbols > 0 && NumberOfPossibleSymbols < 37) {
            lengthIsOk = true;
        } else {
            System.out.println("Error: Wrong number of possible symbols in the code. Must be in range from 1 to 36 inclusive!");
            return;
        }

        //// START
        // If number of possible symbols in code is 10 or less - then we will generate
        //// only digits
        // else
        // we will generate digits AND some chars - depend on length
        if (NumberOfPossibleSymbols <= 10) {

            System.out.print("The secret is prepared: ");
            for (int i = 0; i < length; i++) {
                System.out.print("*");
            }
            System.out.println(" (0-" + (NumberOfPossibleSymbols - 1) + ").");

            secretCode = generateOnlyDigits(); // generate only digits

        } else {
            System.out.print("The secret is prepared: ");
            for (int i = 0; i < length; i++) {
                System.out.print("*");
            }
            System.out.println(" (0-9, a-" + ((char) (96 + NumberOfPossibleSymbols - 10)) + ").");

            secretCode = generateDigitsAndChars();
        }
        //// END

        //// START game
        int turn = 1;
        System.out.println("Okay, let's start a game!");

        //// START turn 1 of the game, with turn counter printed
        //// play till you win!
        do {
            System.out.println("Turn " + turn + ":");
            game();
            turn++;

        } while (!answerIsRight);

        // print gongrats message
        System.out.println("Congratulations! You guessed the secret code.");
    }

    static void game() {
        Scanner in = new Scanner(System.in);


        // declaring bulls and cows
        int bulls = 0;
        int cows = 0;

        // user INPUT
        String input = in.nextLine();

        // check INPUT for bulls
        if (secretCode.equals(input)) {
            bulls = length;
            answerIsRight = true;
        } else {
            for (int i = 0; i < length; i++) {
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

    static String generateOnlyDigits() {
        char r; // declare variable for random number
        String result = "";
        StringBuilder sb = new StringBuilder();

        // loop till length of sb == length of sequence
        do {

            result = sb.toString();
            r = generateDigit(); // generate random number 0 - 9

            String temporaryRandomNumber = String.valueOf(r); // convert random int r to String

            // checking that we have a unique number (0-9), if it is not in 'result' - add it
            if (!result.contains(temporaryRandomNumber)) {
                sb = sb.append(temporaryRandomNumber);
            }

        } while (sb.length() != length);

        return sb.toString();

    }

    static String generateDigitsAndChars() {
        char r; // declare variable for random number
        String result = "";
        StringBuilder sb = new StringBuilder();

        // loop till length of sb == length of sequence
        do {

            result = sb.toString();
            r = generateChars(); // generate chars

            String temporaryRandomNumber = String.valueOf(r); // convert to String

            // checking that we have a unique number (0-9), if it is not in 'result' - add it
            if (!result.contains(temporaryRandomNumber)) {
                sb = sb.append(temporaryRandomNumber);
            }

        } while (sb.length() != length);

        return sb.toString();

    }

    static char generateChars() {

        boolean randomCharOk = false;
        int randomChar = 0;
        char c = 0;

        // digits are from 48 to 57 inclusive
        // small chars are from 97 to 122 inclusive

        do {
            randomChar = (int) Math.floor(48 + (Math.random() * 74));

            if ((randomChar >= 48 && randomChar <= 57) || (randomChar >= 97 && randomChar <= 96 + (length - 10))) {
                randomCharOk = true;
                c = (char) randomChar;

            }

        } while (!randomCharOk);

        return c;
    }

    static char generateDigit() {
        // digits are from 48 to 57 inclusive
        int randomDigit = (int) Math.floor(48 + (Math.random() * 10));
        char d = (char) randomDigit;
        return d;
    }

    static char generateSmallChar() {
        // small chars are from 97 to 122 inclusive
        int randomChar = (int) Math.floor(97 + (Math.random() * 26));
        char c = (char) randomChar;
        return c;
    }


}