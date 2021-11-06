package joshevanJmartFA;



public class Coupon extends Serializable
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
    public boolean canApply (double price, double discount){
        if (Treasury.getAdjustedPrice(price,discount)>minimum && used == false){
            return true;
        }
        else{
            return false;
        }
    }
    public double apply (double price, double discount){
        used = true;
        if (this.type == Type.DISCOUNT){
            return Treasury.getAdjustedPrice(price,discount) - (cut/100 * Treasury.getAdjustedPrice(price,discount));
        }
        else {
            return Treasury.getAdjustedPrice(price,discount) - 2000;
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
