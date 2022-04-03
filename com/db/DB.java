package com.db;
import java.util.List;

public interface DB<T> {
    public abstract boolean createEntry();
    public abstract List<T> readEntries();
    public abstract boolean updateEntry();
    public abstract T deleteEntry();
}
