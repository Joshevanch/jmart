package joshevanJmartFA;
import java.util.ArrayList;
public class Filter
{
    public static ArrayList <PriceTag> filterPriceTag (PriceTag[] list, int value, boolean less){
        ArrayList <PriceTag> priceTags = new ArrayList <PriceTag>();
        if (less == true){
            for (PriceTag num : list){
                if (num.getAdjustedPrice() < value){
                    priceTags.add(num);
                }
            }
        }
        if ( less == false){
            for (PriceTag num : list){
                if (num.getAdjustedPrice() >= value){
                     priceTags.add(num);
                }
            }
    }
    return  priceTags;
}
    public static void filterProductRating (ArrayList <ProductRating> list,double value, boolean less){
         ArrayList <ProductRating> productRating = new ArrayList <ProductRating>();
        if (less == true ){
            for (ProductRating num : list){
                if (num.getAverage() >= value){
                    productRating.add(num);
                }
            }
        }
        if (less == false ){
            for (ProductRating num : list){
                if (num.getAverage() < value){
                    productRating.add(num);
                }
            }
        }
    }
}
