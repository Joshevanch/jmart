package joshevanJmartFA;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

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
    public final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat ("E, MMMM dd yyyy");
    public static final Duration INSTANT  = new Duration ((byte)0);
    public static final Duration SAME_DAY = new Duration ((byte)2);
    public static final Duration NEXT_DAY = new Duration ((byte)4);
    public static final Duration REGULER  = new Duration ((byte)8);
    public static final Duration KARGO    = new Duration ((byte)16);
    private byte bit;
    public String getEstimatedArrival (Date reference){
        Calendar cal = Calendar.getInstance();
        cal.setTime (reference);
        if (bit == 0 || bit == 10){
            cal.add(Calendar.DATE,0);
        }
        else if (bit == 100){
            cal.add(Calendar.DATE,1);
        }
        else if (bit == 1000){
            cal.add(Calendar.DATE,2);
        }
        else if (bit == 10000){
            cal.add(Calendar.DATE,3);
        }
        return (ESTIMATION_FORMAT.format(cal.getTime()));
    }
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
