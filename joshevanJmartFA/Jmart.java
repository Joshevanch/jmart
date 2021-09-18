package joshevanJmartFA;



public class Jmart
{
    public static void main (String[] args){
        
    }
    public static Product create(){
        PriceTag hargabaju = new PriceTag (100000, 10);
        Product baju = new Product ("kemeja", 5, false, hargabaju, ProductCategory.FASHION);
        return baju; 
    }
}
