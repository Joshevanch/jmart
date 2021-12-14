package com.joshevanJmartFA;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.joshevanJmartFA.dbjson.Serializable;

/**
 * This class stores user account information, such as email, password, name, balance
 * and extends serializable so that the account will have id that is serialized
 * @author Joshevan
 * @version 1.0
 * 
 * 
 */
public class Account extends Serializable
{
	/**
	 * Regex email constant for checking
	 */
   public static final String REGEX_EMAIL = "^(?!.*^[.])(?!.*[.]{2})[a-zA-Z0-9&._*~]+@(?![.-])[a-zA-Z0-9.-]+(?!.*$[.])$";
   /**
    * Regex password constant for checking
    */
   public static final String REGEX_PASSWORD = "^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?!.*[\\s]{2}).*$";
   /**
    * Account balance
    */
   public double balance;
   /**
    * Account name
    */
   public String name;
   /**
    * Account email
    */
   public String email;
   /**
    * Account password
    */
   public String password;
   /**
    * Account store
    */
   public Store store; 
   /**
    * Account constructor
    * @param name account's name
    * @param email account's email
    * @param password account's password
    * @param balance account's initial balance will be 0
    */
   public Account (String name, String email, String password, double balance){
       this.name = name;
       this.email = email;
       this.password = password;
       this.balance = 0;
   }
   /**
    * This method checks input email and password with regex so that the input
    * will follow standard email and password
    * @return true if match with regex, false if do not match
    */
   public boolean validate(){
       Pattern patternEmail = Pattern.compile (REGEX_EMAIL);
       Matcher matcherEmail = patternEmail.matcher(this.email);
       Pattern patternPass = Pattern.compile (REGEX_PASSWORD);
       Matcher matcherPass = patternPass.matcher (this.password);
       return (matcherEmail.find() && matcherPass.find());
   }
   /**
    * Override to string method
    */
   public String toString(){
       return "name: " +name+ "\nemail: " +email+ "\npassword: "+password;
   }
}
