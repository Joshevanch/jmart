package joshevanJmartFA;



public class Coupon
{
    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private boolean used = false;
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
    public boolean canApply (PriceTag priceTag){
        if (priceTag.getAdjustedPrice()>minimum && used == false){
            return true;
        }
        else{
            return false;
        }
    }
    public double apply (PriceTag priceTag){
        used = true;
        if (this.type == Type.DISCOUNT){
            return priceTag.getAdjustedPrice() - (cut/100 * priceTag.getAdjustedPrice());
        }
        else {
            return priceTag.getAdjustedPrice() - priceTag.price;
        }
    }
    enum Type{
        DISCOUNT,
        REBATE;
    }
}
