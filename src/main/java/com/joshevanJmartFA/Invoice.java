package com.joshevanJmartFA;
import java.util.Date;

import com.joshevanJmartFA.dbjson.Serializable;

/**
 * This class stores user invoice information, such as date, buyer id, product id,complaint id,
 * rating and extends serializable so that the complaint will have id that is serialized
 * @author Joshevan
 * @version 1.0
 * 
 */
public abstract class Invoice extends Serializable
{
	/**
	 * Invoice date
	 */
    public final Date date;
    /**
	 * Buyer id
	 */
    public int buyerId;
    /**
	 * Product id
	 */
    public int productId;
    /**
	 * Product complaint
	 */
    public int complaintId;
    /**
	 * Product rating
	 */
    public Rating rating;
    /**
     * Default constructor of invoice, date will be constructed with now date,
     * rating will be constructed with none
     * @param buyerId Buyer id
     * @param productId Product id
     */
    protected Invoice (int buyerId, int productId){
        this.buyerId = buyerId;
        this.productId = productId;
        date = new Date();
        this.rating = Rating.NONE;
        complaintId = -1;
    }
    public abstract double getTotalPay(Product product);
    /**
     * Enumeration of product rating
     * @author Joshevan
     *
     */
    public static enum Rating{
    NONE,
    BAD,
    NEUTRAL,
    GOOD;
    }
    /**
     * Enumeration of invoice status
     * @author Joshevan
     *
     */
    public static enum Status{
    WAITING_CONFIRMATION,
    CANCELLED,
    ON_PROGRESS,
    ON_DELIVERY,
    DELIVERED,
    COMPLAINT,
    FINISHED,
    FAILED;
    }
}
