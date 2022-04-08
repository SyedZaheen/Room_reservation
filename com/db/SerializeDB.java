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

public class SerializeDB {
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
		// print out the size
		// System.out.println(" Details Size: " + objectFromFile.size());
		// System.out.println();
		return objectFromFile;
	}

	public static <T> boolean writeSerializedObject(String filename, List<T> list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
			return true;
			// System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// public static void main(String[] args) {
	// List list;
	// try {
	// // read from serialized file the list of professors
	// list = (ArrayList)SerializeDB.readSerializedObject("professor.dat");
	// for (int i = 0 ; i < list.size() ; i++) {
	// Professor p = (Professor)list.get(i);
	// System.out.println("name is " + p.getName() );
	// System.out.println("contact is " + p.getContact() );
	// }

	// // write to serialized file - update/insert/delete
	// // example - add one more professor
	// Professor p = new Professor("Joseph","jos@ntu.edu.sg",67909999);
	// // add to list
	// list.add(p);
	// // list.remove(p); // remove if p equals object in the list

	// SerializeDB.writeSerializedObject("professor.dat", list);

	// } catch ( Exception e ) {
	// System.out.println( "Exception >> " + e.getMessage() );
	// }
	// }
}