package joshevanJmartFA;


public class Shipment implements FileParser
{
    public String address;
    public int shipmentCost;
    public String receipt;
    public Duration duration;
    
    public Shipment (String address, int shipmentCost, Duration duration, String receipt){
        this.address = address;
        this.shipmentCost = shipmentCost;
        this.duration = duration;
        this.receipt = receipt;
    }
    public static class Duration{
    public static final Duration INSTANT  = new Duration ((byte)0);
    public static final Duration SAME_DAY = new Duration ((byte)2);
    public static final Duration NEXT_DAY = new Duration ((byte)4);
    public static final Duration REGULER  = new Duration ((byte)8);
    public static final Duration KARGO    = new Duration ((byte)16);
    private byte bit;
    private Duration (byte bit){
        this.bit = bit;
    }
    }
    public class MultiDuration {
        public byte bit;
        public MultiDuration (Duration... args){
        for (Duration a:args){  
           this.bit = (byte) (this.bit|a.bit);  
        }   
       
    }
    
    public boolean isDuration (Duration reference){
        if ((reference.bit & bit) == reference.bit){
            return true;
        }
        else {
            return false;
        }
    }
    }
    
    public boolean read (String content){
        return false;
    }
}
