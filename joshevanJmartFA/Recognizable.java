package joshevanJmartFA;



abstract class Recognizable
{
    public final int id;
    protected Recognizable(int id){
        this.id = id;
    }
    
    public boolean equals (Object a){
    return (a instanceof Recognizable && this.id == ((Recognizable) a).id);
    }
    
    public boolean equals (Recognizable a){
        return (this.id == a.id);
    }
}
