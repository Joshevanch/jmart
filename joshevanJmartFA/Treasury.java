package joshevanJmartFA;


public class Treasury
{
   public static final double COMMISION_MULTIPLIER = 0.05;
   public static final double BOTTOM_PRICE = 20000;
   public static final double BOTTOM_FEE = 1000;
   public static double getAdjustedPrice(double price, double discount){
       return price-discount;
   }
   public static double getAdminFee(double price, double discount){
       if (price<BOTTOM_PRICE){
           return BOTTOM_FEE;
       }
       else {
           return (price-discount)* COMMISION_MULTIPLIER ;
       }
   }
   public static double getDiscountedPrice (double price, double discount){
       if (discount>100){
           discount = 100;
       }
       return price - (discount/100 * price);
   }
}
