package joshevanJmartFA;



public class Jmart
{
    public static void main (String[] args){
        Product test = Jmart.create();
        System.out.println(test.id);
        System.out.println(test.name);
        System.out.println(test.weight);
        System.out.println(test.conditionUsed);
        System.out.println(test.priceTag.getAdjustedPrice());
        System.out.println(test.category);
    }
    public static Product create(){
        PriceTag hargabaju = new PriceTag (100000, 10);
        Product baju = new Product ("kemeja", 5, false, hargabaju, ProductCategory.FASHION);
        return baju; 
    }
}
