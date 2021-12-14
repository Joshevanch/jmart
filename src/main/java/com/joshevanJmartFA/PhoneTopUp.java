package com.joshevanJmartFA;

/**
 * This class can change phone number and extends invoice
 * @author Joshevan
 *
 */
public class PhoneTopUp extends Invoice {
	/**
	 * Phone number
	 */
	String phoneNumber;
	/**
	 * Status
	 */
	Status status;
	/**
	 * This is the default constructor, send buyer id and product id to parent class
	 * @param buyerId buyer id 
	 * @param productId product id 
	 * @param phoneNumber phone number
	 */
	public PhoneTopUp (int buyerId, int productId, String phoneNumber) {
		super (buyerId, productId);
		this.phoneNumber = phoneNumber;
	}
	/**
	 * This method return total pay for product
	 */
	public double getTotalPay (Product product) {
		return product.price - product.discount;
	}
}
