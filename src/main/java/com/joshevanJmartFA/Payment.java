package com.joshevanJmartFA;

import java.util.Date;

import com.joshevanJmartFA.controller.ProductController;

import java.util.*;

/**
 * This class contains payment information, such as product count, shipment, 
 * record history and extends invoice 
 * @author Joshevan
 *
 */
public class Payment extends Invoice 
{
	/**
	 * Number of products paid
	 */
    public int productCount;
    /**
     * Payment shipment
     */
    public Shipment shipment;
    /**
     * List of record history
     */
    public ArrayList<Record>history= new ArrayList<Record>();
    /**
     * This is the default constructor, send the buyer id and product id to parent class
     * @param buyerId Buyer id 
     * @param productId Product id
     * @param productCount Number of product
     * @param shipment Payment shipment
     */
    public Payment (int buyerId, int productId, int productCount, Shipment shipment){
       super (buyerId, productId);
       this.shipment = shipment;
       this.productCount = productCount;
    }
    /**
     * Total price that must be paid
     */
    public double getTotalPay(Product product){
        return product.price - product.discount;
    }
    /**
     * Inner class contains record information, such as status of the invoice,
     * record date, and record message
     * @author Joshevan
     *
     */
    public static class Record{
        public Status status;
        public Date date;
        public String message;
        /**
         * This is the default constructor for record
         * @param status record status
         * @param message record message
         */
        public Record (Status status, String message) {
        	this.date = new Date();
        	this.status = status;
        	this.message = message;
        }
    }
   
}
