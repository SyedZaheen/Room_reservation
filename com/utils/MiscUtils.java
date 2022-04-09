package com.utils;

public abstract class MiscUtils {
    public static boolean isValidSingaporeNumber(int j)
    {
        return j > 6* 1e7 && j < 1e8;
    }

    public static boolean stringWithinLength(String str, int min, int max)
    {
        return str.length() >= min && str.length() <= max;
    }

    public static boolean isValidInteger(int i) {
        return i > 0;
    }

    public static boolean isValidYear(int i) {
        return i >= 1900 && i <= 2022;
    }

    public static boolean isValidMonth(int i) {
        return i >= 1 && i <= 12;
    }

    public static boolean isValidDay(int i, Integer month) {

        boolean value;
        switch(month) {
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
            year.toString() + "-" + month.toString() + "-" + day.toString()
        );

        return date;
    }
}
