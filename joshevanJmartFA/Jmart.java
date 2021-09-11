package joshevanJmartFA;



public class Jmart
{
    public static void main (String[] args){
        
    }
    
    public static int getPromo (){
        return 0;
    }
    
    public static String getCustomer(){
        return "oop";
    }
    
    public static float getDiscountPercentage (int before,int after){
        if (before <= after){
            return 0;
        }
        else {
            return ( (float)(before - after) / before*100);
        }
    }
    
    public static int getDiscountedPrice (int price, float discountPercentage){
        if (discountPercentage > 100){
            discountPercentage = 100;
        }
            price = price - (int)(price * discountPercentage/100);
        return price;
    }
    
    public static int getOriginalPrice (int discountedPrice, float discountPercentage){
        if (discountPercentage == 0){
            return 0;
        }
        else {
        discountedPrice = discountedPrice / (int)(100-discountPercentage) * 100;
        return discountedPrice;
    }
    }
    
    public static float getCommisionMultiplier (){
        return 0.05f;
    }
    
    public static int getAdjustedPrice  (int price){
        price = price + (int)(price * getCommisionMultiplier());
        return price;
    }
    
    public static int getAdminFee (int price){
        price = (int)(price * getCommisionMultiplier());
        return price;
    }
}
