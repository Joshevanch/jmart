package joshevanJmartFA;


public class ShipmentDuration
{
    public static final ShipmentDuration INSTANT  = new ShipmentDuration (1);
    public static final ShipmentDuration SAME_DAY = new ShipmentDuration (2);
    public static final ShipmentDuration NEXT_DAY = new ShipmentDuration (4);
    public static final ShipmentDuration REGULER  = new ShipmentDuration (8);
    public static final ShipmentDuration KARGO    = new ShipmentDuration (16);
    private int bit;
    
    private ShipmentDuration (int bit){
        this.bit = bit;
    }
    public ShipmentDuration (ShipmentDuration... args){
        for (ShipmentDuration a:args){
           this.bit = this.bit | a.bit;  
        }   
           
    }
    public boolean isDuration (ShipmentDuration reference){
        if ((reference.bit & bit) == reference.bit){
            return true;
        }
        else {
            return false;
        }
    }
}
