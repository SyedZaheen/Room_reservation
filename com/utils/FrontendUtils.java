package com.utils;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class FrontendUtils <T> {

    public static int getUserChoice(String[] choices) {
        @SuppressWarnings("resource") /*Java does some dumb shit when I close the scanner on the first 
        function run. So I won't close the scanner, just suppress the warning.*/
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("");
            for (int i = 0; i < choices.length; i++) {
                System.out.printf("(%d) %s \n", i + 1, choices[i]);
            }
            System.out.println("Enter the number of your choice: ");

            try {
                choice = sc.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("Please enter a NUMBER only!!");
                sc.nextLine();
                choice = choices.length+1;
                continue;
            }

            if (choice > choices.length) System.out.printf("Please enter a number between 1 and %d only\n", choices.length);

        } while (choice > choices.length);

        return choice;
    }

    public static void getEachFieldFromUser()
    {

    }
}
