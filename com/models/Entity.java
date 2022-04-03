package com.models;
import java.io.Serializable;
import java.util.HashMap;

public interface Entity<T> extends Serializable {
    public HashMap<String, String> toHashMap();
}
