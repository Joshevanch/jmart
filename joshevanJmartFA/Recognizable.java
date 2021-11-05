package joshevanJmartFA;



abstract class Recognizable implements Comparable<Recognizable>
{
    public final int id;
    protected Recognizable(){
        this.id = 1;
    }
    public static <T extends Recognizable>int setClosingId(T clazz, int id) {
    	return 0;
    }
    public static <T extends Recognizable>int getClosingId(T clazz) {
    	return 0;
    }
    public boolean equals (Object other){
    return (other instanceof Recognizable && this.id == ((Recognizable)other).id);
    }
    
    public boolean equals (Recognizable other){
        return (this.id == other.id);
    }
    public int compareTo(Recognizable other) {
    return Integer.compare(this.id, other.id);
    }
}
