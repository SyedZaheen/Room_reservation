package com.db;
import java.util.List;
/**
 * An interface that enforces that all DB APIs minimally allow for creating entries, and querying all entries from the DB
 * @author DSF1 Group 1
 */
public interface DB<T> {
    public static final String FILE_PATH = "./com/db/";
    public abstract boolean createEntry(T entry);
    public abstract List<T> findAllEntries();
    public abstract boolean isEmpty();
}
