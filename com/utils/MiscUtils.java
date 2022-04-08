package com.utils;

import com.models.MenuItem;

public abstract class MiscUtils {
    public static boolean isValidSingaporeNumber(int j)
    {
        return j > 6* 1e7 && j < 1e8;
    }

    public static boolean stringWithinLength(String str, int min, int max)
    {
        return str.length() >= min && str.length() <= max;
    }
}

