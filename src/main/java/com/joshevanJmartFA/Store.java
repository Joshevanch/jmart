package com.joshevanJmartFA;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Store
{
    public String name;
    public String address;
    public String phoneNumber;
    public double balance;
    public static final String REGEX_PHONE = "[0-9]{9,12}";
    public static final String REGEX_NAME = "^[A-Z].{4,20}!\\s{2}";
    public Store (String name, String address, String phoneNumber, double balance){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
    public String toString(){
        return "name: "+this.name+"address: "+this.address+"phoneNumber: "+this.phoneNumber;
    }
    public boolean validate(){
        Pattern patternNumber = Pattern.compile (REGEX_PHONE);
        Pattern patternName = Pattern.compile (REGEX_NAME);
        Matcher matcherNumber = patternNumber.matcher (this.phoneNumber);
        Matcher matcherName   = patternName.matcher (this.name);
        return (matcherName.find() && matcherNumber.find());
    }
}
