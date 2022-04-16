package com.views;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.utils.AnonymousFunction;
import com.utils.MiscUtils;
/**
 * The class that provides the API for the presentation layer to get appropriate user input.
 * @author DSF1 Group 1
 *
 */
public abstract class UserInputViews {

    public abstract void process();

    public static int getUserChoice(String[] choices) {
        @SuppressWarnings("resource") /*
                                       * Java does some dumb shit when I close the scanner on the first
                                       * function run. So I won't close the scanner, just suppress the warning.
                                       */
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
                continue;
            }

            if (choice > choices.length || choice <= 0)
                System.out.printf("Please enter a number between 1 and %d only\n", choices.length);

        } while (choice > choices.length || choice <= 0);
        MiscUtils.printLightTransition();
        return choice;
    }

    public static int getUserChoice(String[] choices, boolean exit) {
        @SuppressWarnings("resource") /*
                                       * Java does some dumb shit when I close the scanner on the first
                                       * function run. So I won't close the scanner, just suppress the warning.
                                       */
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
                continue;
            }

            if (choice > choices.length)
                System.out.printf("Please enter a number between 1 and %d only\n", choices.length);

        } while (choice > choices.length);
        MiscUtils.printLightTransition();
        return choice;
    }

    // This function here is a little complicated. I'll go through step-by-step

    // The generic type here refers to what type of field we want. Name will be
    // string, phone no will be integer, etc

    @SuppressWarnings("unchecked") // Java does not like that I am casting generic Object choice to T at the end
    public static <T> T getEachFieldFromUser(
            // This is the prompt that users will see
            String prompt,
            String errorPrompt,
            // This is the function that will return true if data entered is valid, false
            // otherwise
            AnonymousFunction<Boolean, T> validator,
            String type) {

        try {
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);
            boolean valid = false;
            Object choice = new Object();

            do {
                System.out.println();
                System.out.println(prompt);

                try {
                    switch (type) {
                        case "Integer":
                            choice = sc.nextInt();
                            valid = validator.execute((T) choice);
                            break;

                        case "String":
                            choice = sc.nextLine();
                            valid = validator.execute((T) choice);
                            break;

                        case "Long":
                            choice = sc.nextLong();
                            valid = validator.execute((T) choice);
                            break;

                        case "Double":
                            choice = sc.nextDouble();
                            valid = validator.execute((T) choice);

                        default:
                            break;
                    }

                } catch (InputMismatchException ime) {
                    System.out.println(errorPrompt);
                    sc.nextLine();
                    continue;
                }

                if (!valid)
                    System.out.println(errorPrompt);
            } while (!valid);

            return (T) choice;
        } catch (ClassCastException cce) {

            System.out.println("There was a ClassCastException. Contact the administrators");
        }
        return null;

    }

    public static <T> boolean userDoubleConfirmDetails(T details) {
        MiscUtils.printLightTransition();

        System.out.println(
                "\nPlease confirm that the following data is correct: ");
        System.out.println(details);

        int confirmchoice = getUserChoice(new String[] {
                "Enter 1 if the above data are all correct",
                "Enter 2 if the data needs to be entered again"
        });

        // todo: Fun fact the user can continuously click 2 and get a stack overflow
        return confirmchoice == 1 ? true : false;
    }
}
