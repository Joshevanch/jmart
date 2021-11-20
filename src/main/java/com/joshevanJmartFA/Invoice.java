package com.joshevanJmartFA;
import java.util.Date;

import com.joshevanJmartFA.dbjson.Serializable;


public abstract class Invoice extends Serializable
{
    public final Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    protected Invoice (int buyerId, int productId){
        this.buyerId = buyerId;
        this.productId = productId;
        date = new Date();
        this.rating = Rating.NONE;
        complaintId = -1;
    }
    public abstract double getTotalPay(Product product);
    
    enum Rating{
    NONE,
    BAD,
    NEUTRAL,
    GOOD;
    }
    enum Status{
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
