package com.models;
import java.util.HashMap;

public interface Entity<T> {
    public HashMap<String, String> toHashMap();
}
