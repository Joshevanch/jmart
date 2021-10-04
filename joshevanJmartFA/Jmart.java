package joshevanJmartFA;



public class Jmart
{
    public static void main (String[] args){
        Account joshevan = new Account(1001, "Joshevan", "Joshevan@ui.ac.id", "Joshevan123");
        joshevan.validate();
     }
    // public static Product createProduct(){
        // PriceTag hargabaju = new PriceTag (100000, 10);
        // Product baju = new Product ("kemeja", 5, false, hargabaju, ProductCategory.FASHION);
        // return baju; 
    // }
    // public static Coupon createCoupon(){
        // Coupon kupon = new Coupon ("kupon1", 1, Coupon.Type.DISCOUNT , 5, 1000);
        // return kupon;
    // }
    // public static ShipmentDuration createShipmentDuration(){
        // ShipmentDuration durasi = new ShipmentDuration (ShipmentDuration.INSTANT, ShipmentDuration.SAME_DAY,
// ShipmentDuration.NEXT_DAY);
        // return durasi;
    // }
}
