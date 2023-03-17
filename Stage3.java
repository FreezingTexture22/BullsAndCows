package BullsAndCows;

import java.util.Scanner;

public class Stage3 {


    public static void main(String[] args) {
        generateSecretCode();
    }

    public static void checkBullsAndCows() {
        Scanner in = new Scanner(System.in);

        // get secret code
        String secretCode = "9305";

        //get secret code char size
        int secretCodeLength = secretCode.length();

        //declaring bulls and cows
        int bulls = 0;
        int cows = 0;

        //user input
        String input = in.nextLine();

        //check for bulls
        if (secretCode.equals(input)) {
            bulls = 4;
        } else {
            for(int i = 0; i < secretCodeLength; i++) {
                if(secretCode.charAt(i) == input.charAt(i)) {
                    bulls++;
                } else if (secretCode.contains(String.valueOf(input.charAt(i)))) {
                    cows++;
                }
            }
        }

        if (bulls > 0 && cows > 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s). The secret code is %s", bulls, cows, secretCode);
        } else if (bulls > 0) {
            System.out.printf("Grade: %d bull(s). The secret code is %s", bulls, secretCode);
        } else if (cows > 0) {
            System.out.printf("Grade: %d cows(s). The secret code is %s", cows, secretCode);
        } else {
            System.out.printf("Grade: None. The secret code is %s", secretCode);
        }
    }

    public static String generateSecretCode() {
        Scanner in = new Scanner(System.in);
        String input = "";
        int length = -1;

        do {
            input = in.nextLine();
            //length = Integer.parseInt(input);


            if (input.matches("[1-9]")) {
                length = Integer.parseInt(input);
            } else if (input.matches("[0-9]+")) {
                System.out.println("Error: can't generate a secret number with a length of "+ input + " because there aren't enough unique digits.");
            }



        } while (length == -1);


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

        System.out.println("The random secret number is " + sb.toString());
        return sb.toString();


    }
}

    //