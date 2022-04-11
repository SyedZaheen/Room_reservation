package com.utils;

import java.util.Random;


public abstract class MiscUtils {
    public static boolean isValidSingaporeNumber(int j) {
        return j > 6 * 1e7 && j < 1e8;
    }

    public static boolean stringWithinLength(String str, int min, int max) {
        return str.length() >= min && str.length() <= max;
    }

    public static boolean isValidInteger(int i) {
        return i > 0;
    }

    public static boolean isValidIntegerFromStartToEnd(int start, int end, int i) {
        if (end < start) {
            return false;
        }
        return i > start && i < end;
    }

    public static boolean isValidYear(int i) {
        return i >= 1900 && i <= 2022;
    }

    public static boolean isValidMonth(int i) {
        return i >= 1 && i <= 12;
    }

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

    public static String dateConvertor(Integer year, Integer month, Integer day) {
        String date = "";

        date = date.concat(
                year.toString() + "-" + month.toString() + "-" + day.toString());

        return date;
    }

    public static boolean roomNumberExists(Integer i) {
        if (i < 100 || i > 999)
            return false;

        i %= 100;
        if (i < 1 || i > 12)
            return false;

        return true;
    }

    public static boolean isValidID(int id) {
        return id >= 1e6 && id < 1e7;
    }

    public static void printTransition() {
        System.out.println("=========================================================");

    }

    public static int generateID() {
        return new Random().nextInt(1000000) + (new Random().nextInt(9) + 1) * 1000000;
    }

    public static void printLightTransition() {
        System.out.println("_________________________________________________________\n");
    }
}
