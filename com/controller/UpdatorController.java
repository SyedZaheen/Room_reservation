package com.controller;

/**
 * UpdateController.java
 * Interface class that has the following methods.
 * 
 * @author DSF1 Group 1
 */

public interface UpdatorController<T> {

    /**
     * manageUpdateEntry
     * Abstract method that will be implemented in concrete classes that realises this interface.
     */
    public abstract T manageUpdateEntry();
    
}
