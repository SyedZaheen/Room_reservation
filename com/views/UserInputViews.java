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
    
    /** 
     * Prints all of the choices provided to the user, and returns an integer related to that choice
     * @param choices
     * @return int
     */
    public static int getUserChoice(String[] choices) {
        @SuppressWarnings("resource")
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

    
    /** 
     *  Prints all of the choices provided to the user, and returns an integer related to that choice. However, a user can enter a negative number to exit
     * @param choices
     * @param exit
     * @return int
     */
    public static int getUserChoice(String[] choices, boolean exit) {
        @SuppressWarnings("resource") 
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

    
    /** 
     * Allows the user to enter a field with error checking. First prints a prompt, then if the user input returns false through the validator, the error prompt is called
     * @param prompt
     * @param errorPrompt
     * @param validator
     * @param type
     * @return T
     */

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

    
    /** 
     * Returns true if user confirms the printed details
     * @param details
     * @return boolean
     */
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
