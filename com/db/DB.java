package com.db;
import java.util.ArrayList;

public interface DB<T> {
    public abstract boolean createEntry();
    public abstract ArrayList<T> readEntries();
    public abstract boolean updateEntry();
    public abstract T deleteEntry();
}
