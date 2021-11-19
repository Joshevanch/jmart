package com.joshevanJmartFA;

import java.util.Date;
import java.util.*;

public class Payment extends Invoice 
{
    public int productCount;
    public Shipment shipment;
    public ArrayList<Record>history= new ArrayList<Record>();
    public Payment (int buyerId, int productId, int productCount, Shipment shipment){
       super (buyerId, productId);
       this.shipment = shipment;
       this.productCount = productCount;
    }
    public double getTotalPay(Product product){
        return product.price - product.discount;
    }
    public static class Record{
        public Status status;
        public Date date;
        public String message;
        public Record (Status status, String message) {
        	this.date = new Date();
        	this.status = status;
        	this.message = message;
        }
    }
   
}
