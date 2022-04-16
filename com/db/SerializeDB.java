package com.db;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import java.util.ArrayList;

// Note : When structure of the Object type (the class file) in the list changed
// the Serialized file may fail.

/**
 * The class that provides the lowest-level interface for de-/serialising objects 
 * and writing/reading to/from the serialised files
 * @author DSF 1 Group 1
 */
public class SerializeDB {
	

	
	/** Reads the object from a serialised file
	 * @param filename
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> readSerializedObject(String filename) {
		List<T> objectFromFile = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			objectFromFile = (ArrayList<T>) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (ClassCastException ex) {
			ex.printStackTrace();
		} 
		return objectFromFile;
	}

	
	/** 
	 * Takes in an object, serialises it and stores it in a file.
	 * The object used in this app is strictly an arraylist of models 
	 * @param filename
	 * @param list
	 * @return boolean
	 */
	public static <T> boolean writeSerializedObject(String filename, List<T> list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}

}