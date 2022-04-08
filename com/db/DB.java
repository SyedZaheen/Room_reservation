package com.db;
import java.util.List;

public interface DB<T> {
    public boolean createEntry(T entry);
    public List<T> findAllEntries();
}
