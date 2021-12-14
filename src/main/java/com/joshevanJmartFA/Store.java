package com.joshevanJmartFA;
import java.util.regex.Matcher;
/**
 * This class contains store information such as store name, address, phone number,
 * balance
 */
import java.util.regex.Pattern;
public class Store
{
	/**
	 * Store name 
	 */
    public String name;
    /**
	 * Store address 
	 */
    public String address;
    /**
	 * Store phone number 
	 */
    public String phoneNumber;
    /**
	 * Store balance 
	 */
    public double balance;
    /**
	 * Regex phone number constant for checking 
	 */
    public static final String REGEX_PHONE = "[0-9]{9,12}";
    /**
	 * Regex name constant for checking 
	 */
    public static final String REGEX_NAME = "^[A-Z].{4,20}!\\s{2}";
    /**
     * This is the default constructor
     * @param name store name
     * @param address store address
     * @param phoneNumber store phone number
     * @param balance store balance
     */
    public Store (String name, String address, String phoneNumber, double balance){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
    /**
     * This method override toString method
     */
    public String toString(){
        return "name: "+this.name+"address: "+this.address+"phoneNumber: "+this.phoneNumber;
    }
    /**
     * This method checks input phone number and name with regex so that the input
     * will follow standard phone number and name
     * @return true if match with regex, false if do not match
     */
    public boolean validate(){
        Pattern patternNumber = Pattern.compile (REGEX_PHONE);
        Pattern patternName = Pattern.compile (REGEX_NAME);
        Matcher matcherNumber = patternNumber.matcher (this.phoneNumber);
        Matcher matcherName   = patternName.matcher (this.name);
        return (matcherName.find() && matcherNumber.find());
    }
}
