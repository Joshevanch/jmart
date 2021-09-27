package joshevanJmartFA;



public class Product extends Recognizable implements FileParser
{
    public int storeId;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag priceTag;
    public ProductCategory category;
    public ProductRating rating;
    public Shipment.MultiDuration multiDuration;
    public Product (int id, int storeId, String name ,int weight ,boolean conditionUsed,PriceTag priceTag,ProductCategory category, Shipment.MultiDuration multiDuration){
        super(id);
        this.storeId = storeId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        this.multiDuration = multiDuration;
        rating = new ProductRating();
    }
    public boolean read (String content){
        return false;
    }
    public String toString(){
        name = "Harry Potter";
        weight = 1;
        conditionUsed = false;
        priceTag = new PriceTag(21000);
        category = ProductCategory.BOOK;
        rating = new ProductRating();
        rating.insert (0);
        storeId = 1;
        return name;
    }
}
