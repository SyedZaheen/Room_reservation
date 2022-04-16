package com.db;
import java.util.List;


public interface DB<T> {
    public static final String FILE_PATH = "./com/db/";
    public abstract boolean createEntry(T entry);
    public abstract List<T> findAllEntries();
}
