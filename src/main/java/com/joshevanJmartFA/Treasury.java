package com.joshevanJmartFA;

/**
 * This class counts the price for product
 * @author Joshevan
 *
 */
public class Treasury
{
	/**
	 * Product commision multiplier
	 */
   public static final double COMMISION_MULTIPLIER = 0.05;
   /**
    * Minimum price for admin fee
    */
   public static final double BOTTOM_PRICE = 20000;
   /**
    * Minimum admin fee
    */
   public static final double BOTTOM_FEE = 1000;
   /**
    * This method counts product price after discount
    * @param price product price
    * @param discount product discount
    * @return product price after discount
    */
   public static double getAdjustedPrice(double price, double discount){
       return price-discount;
   }
   /**
    * this method counts admin fee
    * @param price product price
    * @param discount product discount
    * @return product admin fee
    */
   public static double getAdminFee(double price, double discount){
       if (price<BOTTOM_PRICE){
           return BOTTOM_FEE;
       }
       else {
           return (price-discount)* COMMISION_MULTIPLIER ;
       }
   }
   /**
    * This method counts price after discount 
    * @param price product price
    * @param discount product discount percentage
    * @return product price after discount
    */
   public static double getDiscountedPrice (double price, double discount){
       if (discount>100){
           discount = 100;
       }
       return price - (discount/100 * price);
   }
}
