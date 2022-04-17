package com.models;
import java.io.Serializable;

/**
 * A model interface that implements the Serializable interface so that we can serialize the model in the database and overrides toString method.
 * @author DSF 1 Group 1
 *
 * @param <T> Generic type
 */

public interface Model<T> extends Serializable {
    public String toString();
}
