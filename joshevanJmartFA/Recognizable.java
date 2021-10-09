package joshevanJmartFA;



abstract class Recognizable implements Comparable<Recognizable>
{
    public final int id;
    protected Recognizable(int id){
        this.id = id;
    }
    public static <T extends Recognizable>int setClosingId(T a, int id) {
    	return 0;
    }
    public static <T extends Recognizable>int getClosingId(T a) {
    	return 0;
    }
    public boolean equals (Object a){
    return (a instanceof Recognizable && this.id == ((Recognizable) a).id);
    }
    
    public boolean equals (Recognizable a){
        return (this.id == a.id);
    }
    public int compareTo(Recognizable a) {
    return Integer.compare(this.id, a.id);
    }
}
