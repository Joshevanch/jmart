package com.joshevanJmartFA;

import com.joshevanJmartFA.dbjson.Serializable;

/**
 * This class contains product information, such as account id, product category, condition used
 * discount, name, price, shipment plans, weight and extends serializable so that the product 
 * will have id that is serialized
 * @author Joshevan
 *
 */
public class Product extends Serializable
{
	/**
	 * Account id
	 */
    public int accountId;
    /**
     * Product category
     */
    public ProductCategory category;
    /**
     * Product condition used
     */
    public boolean conditionUsed;
    /**
     * Product discount
     */
    public double discount;
    /**
     * Product name
     */
    public String name;
    /**
     * Product price
     */
    public double price;
    /**
     * Product shipment plan
     */
    public byte shipmentPlans;
    /**
     * Product weight
     */
    public int weight;
    /**
     * This is the default constuctor
     * @param accountId account id
     * @param name product name
     * @param weight product weight
     * @param conditionUsed product condition used
     * @param price product price
     * @param discount product discount
     * @param category product category
     * @param shipmentPlans product shipment plans
     */
    public Product (int accountId, String name ,int weight ,boolean conditionUsed, double price, double discount, ProductCategory category, byte shipmentPlans){
        this.accountId = accountId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.shipmentPlans = shipmentPlans;
    }
    /**
     * This method override toString
     */
    public String toString(){
        return "Account Id: "+this.accountId+"Name: " +this.name+ "Weight: " +this.weight+ "Condition used: "+this.conditionUsed+"Price: "+this.price+"Discount: "+this.discount+"Category: "+this.category;
    }
}
