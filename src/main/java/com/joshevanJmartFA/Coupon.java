package com.joshevanJmartFA;

import com.joshevanJmartFA.dbjson.Serializable;

/**
 * This class stores user coupon information, such as name, code, discount,type, minimum value,
 * used coupon and extends serializable so that the coupon will have id that is serialized
 * @author Joshevan
 * @version 1.0
 * 
 */
public class Coupon extends Serializable
{
	/**
	 * Coupon name
	 */
    public final String name;
    /**
	 * Coupon code
	 */
    public final int code;
    /**
	 * Coupon cut
	 */
    public final double cut;
    /**
	 * Coupon type
	 */
    public final Type type;
    /**
	 * Coupon minimum
	 */
    public final double minimum;
    /**
	 * Coupon used condition
	 */
    private boolean used;
    /**
     * This is the default constructor
     * @param name Coupon name
     * @param code Coupon code
     * @param type Coupon type
     * @param cut Coupon cut
     * @param minimum Coupon minimum value
     */
    public Coupon (String name, int code, Type type, double cut, double minimum){
        this.name = name;
        this.code = code;
        this.cut = cut;
        this.type = type;
        this.minimum = minimum;
    }
    /**
     * This method is the getter method for the coupon used condition
     * @return coupon used condition
     */
    public boolean isUsed(){
        return used;
    }
    /**
     * This method will check whether coupon can be applied or not
     * @param price Product's price
     * @param discount Product's discount
     * @return true if coupon can be applied, false if cannot be pplied
     */
    public boolean canApply (double price, double discount){
        if (Treasury.getAdjustedPrice(price,discount)>minimum && used == false){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * This method will apply the coupon to the product
     * @param price Product's price
     * @param discount Product's discount
     * @return price of the product after discounted
     */
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
    /**
     * enumeration type of coupon
     * @author Joshevan
     *
     */
    enum Type{
        DISCOUNT,
        REBATE;
    }
}
