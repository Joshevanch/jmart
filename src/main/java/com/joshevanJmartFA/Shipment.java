package com.joshevanJmartFA;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * This class stores shipment information, such as address, shipment cost, shipment receipt
 * shipment plan, and estimated arrival date
 * @author Joshevan
 *
 */
public class Shipment
{
	public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat ("E, MMMM dd yyyy");
	/**
	 * Instant plan
	 */
	public static final Plan INSTANT  = new Plan ((byte)0);
	/**
	 * Same day plan
	 */
    public static final Plan SAME_DAY = new Plan ((byte)2);
    /**
	 * Next day plan
	 */
    public static final Plan NEXT_DAY = new Plan ((byte)4);
    /**
	 * Reguler plan
	 */
    public static final Plan REGULER  = new Plan ((byte)8);
    /**
	 * Kargo plan
	 */
    public static final Plan KARGO    = new Plan ((byte)16);
    /**
     * Shipment address
     */
    public String address;
    /**
     * Shipment cost
     */
    public int cost;
    /**
     * Shipment receipt
     */
    public String receipt;
    /**
     * Shipment plan
     */
    public byte plan;
    /**
     * Inner class of Plan
     * @author Joshevan
     *
     */
    public static class Plan{
        public final byte bit;
        /**
         * This is the default constructor
         * @param bit byte of the shipment plan
         */
        private Plan (byte bit) {
        	this.bit = bit;
        }
        }
    /**
     * This is the default constructor
     * @param address shipment address
     * @param cost shipment cost
     * @param plan shipment plan
     * @param receipt shipment receipt
     */
    public Shipment (String address, int cost, byte plan, String receipt){
        this.address  = address;
        this.cost 	  = cost;
        this.plan 	  = plan;
        this.receipt  = receipt;
    }
    /**
     * This method counts the estimated arrival date
     * @param reference the counted date
     * @return estimated arrival date
     */
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
    /**
     * This method checks the reference plan with the shipment plan
     * @param reference plan
     * @return true if reference plan same with the shipment plan, false if reference plan
     * different with the shipment plan
     */
    public boolean isDuration (Plan reference){
        if ((reference.bit & plan) == reference.bit){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * This method checks the reference plan with the object plan
     * @param object object plan 
     * @param reference reference plan
     * @return true if reference plan same with the object plan, false if reference plan
     * different with the object plan
     */
	public boolean isDuration (byte object, Plan reference) {
		if ((reference.bit & plan) == object) {
			return true;
	}
		else {
			return false;
		}
}
}
