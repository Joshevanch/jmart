package joshevanJmartFA;
import java.util.HashMap;

public class Serializable implements Comparable<Serializable>
{
    public final int id;
    private static HashMap <Class<?>, Integer> mapCounter = new HashMap();
    protected Serializable(){
        Integer counter = mapCounter.get(getClass());
        if (mapCounter.get(getClass()) == null){
        	counter = 0;
        	mapCounter.put(getClass(), 0);
        	id = 0;
        }
        else {
        	counter ++;
            this.id = counter;
            mapCounter.put(getClass(), counter);
        }
        
    }
    public static <T extends Serializable>int setClosingId(Class<T> clazz, int id) {
    	return mapCounter.put(clazz, id);
    }
    public static <T extends Serializable>int getClosingId(Class <T> clazz) {
    	return mapCounter.get(clazz);
    }
    public boolean equals (Object other){
    return (other instanceof Serializable && this.id == ((Serializable)other).id);
    }
    
    public boolean equals (Serializable other){
        return (this.id == other.id);
    }
    public int compareTo(Serializable other) {
    return Integer.compare(this.id, other.id);
    }
}
