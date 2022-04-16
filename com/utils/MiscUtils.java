package com.utils;

import java.time.LocalDate;
import java.util.Random;

/**
 * Basic abstract utility class with utility functions abstracted from the app. 
 * All functions are strictly static
 * @author DSF 1 Group 1
 */
public abstract class MiscUtils {
    
    /** Returns true if parameter is a valid Singapore number 
     * (8 digits starting with 6,8,9, without the area code +65)
     * @param SingaporeNumber
     * @return boolean
     */
    public static boolean isValidSingaporeNumber(int j) {
        return j > 6 * 1e7 && j < 1e8;
    }

    
    /** 
     * Returns true if the length of passed in string is within two bounds 
     * @param string
     * @param min
     * @param max
     * @return boolean
     */
    public static boolean stringWithinLength(String str, int min, int max) {
        return str.length() >= min && str.length() <= max;
    }

    
    /** Return true of i is a valid integer
     * @param i
     * @return boolean
     */
    public static boolean isValidInteger(int i) {
        return i > 0;
    }

    
    /** 
     * Returns true if i is within two bounds 
     * @param start
     * @param end
     * @param i
     * @return boolean
     */
    public static boolean isValidIntegerFromStartToEnd(int start, int end, int i) {
        if (end < start) {
            return false;
        }
        return i > start && i < end;
    }

    
    /** 
     * Returns true if i is a valid year between 1900 and 2022
     * @param i
     * @return boolean
     */
    public static boolean isValidYear(int i) {
        return i >= 1900 && i <= 2022;
    }

    
    /** 
     * Returns true if i is a valid month
     * @param i
     * @return boolean
     */
    public static boolean isValidMonth(int i) {
        return i >= 1 && i <= 12;
    }

    
    /** Returns true if the passed in date object is before now
     * @param date
     * @return boolean
     */
    public static boolean dateBeforeNow(LocalDate date)
    {
        return LocalDate.now().compareTo(date) > 0;
    }

    
    /** Returns true if the day exists in the month chosen. I.e., 30 does not exist for February, etc
     * @param day
     * @param month
     * @return boolean
     */
    public static boolean isValidDay(int i, int month) {

        boolean value;
        switch (month) {
            case 2:
                value = i >= 1 && i <= 29;
                break;

            case 4:
            case 6:
            case 9:
            case 11:
                value = i >= 1 && i <= 30;
                break;

            default:
                value = i >= 1 && i <= 31;
                break;
        }

        return value;
    }

     
    /** Returns true if i is a valid room number
     * @param i
     * @return boolean
     */
    public static boolean roomNumberExists(Integer i) {
        if (i < 100 || i > 499)
            return false;

        i %= 100;
        if (i < 1 || i > 12)
            return false;

        return true;
    }

    
    /** 
     * Returns true if the ID number is valid
     * @param id
     * @return boolean
     */
    public static boolean isValidID(int id) {
        return id >= 1e6 && id < 1e7;
    }

    /**
     * Prints a line break for the title page transitions
     */
    public static void printTransition() {
        System.out.println("=========================================================");

    }

    
    /** 
     * Returns a random valid integer 7-digit ID (not staring with 0) 
     * @return int
     */
    public static int generateID() {
        return new Random().nextInt(1000000) + (new Random().nextInt(9) + 1) * 1000000;
    }

    /** 
     * For printing light transitions
     * 
     */ 
    public static void printLightTransition() {
        System.out.println("_________________________________________________________\n");
    }
}
