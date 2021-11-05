package joshevanJmartFA;



public class Coupon extends Recognizable
{
    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private boolean used;
    public Coupon (String name, int code, Type type, double cut, double minimum){
        this.name = name;
        this.code = code;
        this.cut = cut;
        this.type = type;
        this.minimum = minimum;
    }
    public boolean isUsed(){
        return used;
    }
    public boolean canApply (Treasury treasury){
        if (treasury.getAdjustedPrice(100000,2000)>minimum && used == false){
            return true;
        }
        else{
            return false;
        }
    }
    public double apply (Treasury treasury){
        used = true;
        if (this.type == Type.DISCOUNT){
            return treasury.getAdjustedPrice(100000,2000) - (cut/100 * treasury.getAdjustedPrice(100000,2000));
        }
        else {
            return treasury.getAdjustedPrice(100000,2000) - 2000;
        }
    }
    public boolean read (String content){
        return false;
    }
    enum Type{
        DISCOUNT,
        REBATE;
    }
}
