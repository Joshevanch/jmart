package joshevanJmartFA;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Vector;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class JsonTable<T> extends Vector<T> {
	public final String filepath;
	private static final Gson gson = new Gson();
	public JsonTable (Class <T> clazz, String filepath) throws IOException{
		this.filepath = filepath;
		try {
			Class <T[]> array = (Class <T[]>) Array.newInstance(clazz, 0).getClass();
			T[] result = JsonTable.readJson(array, filepath);
			if (result != null) {
				Collections.addAll(this, result);
			}
		}
		catch (FileNotFoundException e){
			File file = new File (filepath);
			File directory = file.getParentFile();
			directory.mkdirs();
			file.createNewFile();
		}
	}
	public static <T> T readJson (Class <T> clazz, String filepath) throws FileNotFoundException{
		JsonReader json = new JsonReader(new FileReader (filepath));
		T t = gson.fromJson(json, clazz);
		return t;
	}
	
	public void writeJson () throws IOException{
		writeJson (this, this.filepath);
	}
	public static void writeJson (Object object, String filepath) throws IOException{
		FileWriter file = new FileWriter(filepath);
		file.write (gson.toJson(object));
		file.close ();	
	}
}
