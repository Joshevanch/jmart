package joshevanJmartFA;


public class PriceTag
{
   public final double COMMISION_MULTIPLIER = 0.05;
   public final double BOTTOM_PRICE = 20000;
   public final double BOTTOM_FEE = 1000;
   public double discount;
   public double price;
   public PriceTag(double price){
       this.price = price;
       this.discount = 0;
   }
   public PriceTag (double price, double discount){
       this.price = price;
       this.discount = discount;
   }
   public double getAdjustedPrice(){
       return getDiscountedPrice() + getAdminFee();
   }
   public double getAdminFee(){
       if (getDiscountedPrice()<BOTTOM_PRICE){
           return BOTTOM_FEE;
       }
       else {
           return getDiscountedPrice() * COMMISION_MULTIPLIER ;
       }
   }
   private double getDiscountedPrice(){
       if (discount>100){
           discount = 100;
       }
       return price - (discount/100 * price);
   }
}
