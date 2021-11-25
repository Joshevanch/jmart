package com.joshevanJmartFA;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.joshevanJmartFA.dbjson.Serializable;

public class Account extends Serializable
{
   public static final String REGEX_EMAIL = "^(?!.*^[.])(?!.*[.]{2})[a-zA-Z0-9&._*~]+@(?![.-])[a-zA-Z0-9.-]+(?!.*$[.])$";
   public static final String REGEX_PASSWORD = "^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?!.*[\\s]{2}).*$";
   public double balance;
   public String name;
   public String email;
   public String password;
   public Store store; 
   public Account (String name, String email, String password, double balance){
       this.name = name;
       this.email = email;
       this.password = password;
       this.balance = balance;
   }
   public boolean validate(){
       Pattern patternEmail = Pattern.compile (REGEX_EMAIL);
       Matcher matcherEmail = patternEmail.matcher(this.email);
       Pattern patternPass = Pattern.compile (REGEX_PASSWORD);
       Matcher matcherPass = patternPass.matcher (this.password);
       return (matcherEmail.find() && matcherPass.find());
   }
   public String toString(){
       return "name: " +name+ "\nemail: " +email+ "\npassword: "+password;
   }
}