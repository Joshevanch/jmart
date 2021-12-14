package com.joshevanJmartFA.dbjson;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Vector;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

/**
 * This class will be the parent class of JSON Table
 * @author Joshevan
 *
 * @param <T> Can be used with all types of objects 
 */
public class JsonTable<T> extends Vector<T> {
	/**
	 * File path in local directory
	 */
	public final String filepath;
	/**
	 * Gson used to convert object to gson
	 */
	private static final Gson gson = new Gson();
	/**
	 * This is the default constructor, import and convert json file to json table
	 * @param clazz object class
	 * @param filepath file path
	 * @throws IOException input output exception
	 */
	public JsonTable (Class <T> clazz, String filepath) throws IOException{
		this.filepath = filepath;
		try {
			Class <T[]> array = (Class <T[]>) Array.newInstance(clazz, 0).getClass();
			T[] result = readJson(array, filepath);
			if (result != null) {
				Collections.addAll(this, result);
			}
		}
		catch (FileNotFoundException e){
			
		}
	}
	/**
	 * This method read json from file and convert to object class
	 * @param <T> Can be used with all types of objects 
	 * @param clazz object class
	 * @param filepath file path
	 * @return converted object
	 * @throws FileNotFoundException file not found exception
	 */
	public static <T> T readJson (Class <T> clazz, String filepath) throws FileNotFoundException{
		final JsonReader json = new JsonReader(new FileReader (filepath));
		T t = gson.fromJson(json, clazz);
		return t;
	}
	/**
	 * This method write object to file
	 * @throws IOException input output exception
	 */
	public void writeJson () throws IOException{
		writeJson (this, this.filepath);
	}
	/**
	 * This method write object to file
	 * @param object object 
	 * @param filepath file path
	 * @throws IOException input output exception
	 */
	public static void writeJson (Object object, String filepath) throws IOException{
		File file = new File(filepath);
        if (!file.exists())
        {
            File parent = file.getParentFile();
            if (parent != null)
                parent.mkdirs();
            file.createNewFile();
        }
        final FileWriter writer = new FileWriter(filepath);
        writer.write(gson.toJson(object));
        writer.close();
	}
}
