package com.gmail.sungmin0511a.major;

import java.io.*;

public class ObjectInputOutput {

	public static Object[] loadObject(File file) {
		try {
			ObjectInputStream read = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(file)));
			Object[] result = new Object[read.readInt()];
			for (int i = 0; i < result.length; i++)
				result[i] = read.readObject();
			read.close();
			System.out.println("Load Complete!");
			return result;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void saveObject(File file, Object saveThing[]) {
		try {
			ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(file));
			write.writeInt(saveThing.length);
			for (int i = 0; i < saveThing.length; i++)
				write.writeObject(saveThing[i]);
			write.close();
			System.out.println("Save Complete!");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void saveObject(File file, Object saveThing) {
		Object result[] = { saveThing };
		saveObject(file, result);
	}
}
