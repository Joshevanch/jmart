package com.joshevanJmartFA;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Shipment
{
	public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat ("E, MMMM dd yyyy");
	public static final Plan INSTANT  = new Plan ((byte)0);
    public static final Plan SAME_DAY = new Plan ((byte)2);
    public static final Plan NEXT_DAY = new Plan ((byte)4);
    public static final Plan REGULER  = new Plan ((byte)8);
    public static final Plan KARGO    = new Plan ((byte)16);
    public String address;
    public int cost;
    public String receipt;
    public byte plan;
    public static class Plan{
        public final byte bit;
        private Plan (byte bit) {
        	this.bit = bit;
        }
        }
    public Shipment (String address, int cost, byte plan, String receipt){
        this.address  = address;
        this.cost 	  = cost;
        this.plan 	  = plan;
        this.receipt  = receipt;
    }
    public String getEstimatedArrival (Date reference){
        Calendar cal = Calendar.getInstance();
        cal.setTime (reference);
        if (plan == 0 || plan == 10){
            cal.add(Calendar.DATE,0);
        }
        else if (plan == 100){
            cal.add(Calendar.DATE,1);
        }
        else if (plan == 1000){
            cal.add(Calendar.DATE,2);
        }
        else if (plan == 10000){
            cal.add(Calendar.DATE,3);
        }
        return (ESTIMATION_FORMAT.format(cal.getTime()));
    }
    public boolean isDuration (Plan reference){
        if ((reference.bit & plan) == reference.bit){
            return true;
        }
        else {
            return false;
        }
    }
	public boolean isDuration (byte object, Plan reference) {
		if ((reference.bit & plan) == object) {
			return true;
	}
		else {
			return false;
		}
}
}
