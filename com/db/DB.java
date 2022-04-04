package com.db;
import java.util.List;

public interface DB<T> {
    public abstract boolean createEntry(T entry);
    public abstract List<T> findAllEntries();
}
