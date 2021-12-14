package com.joshevanJmartFA;
import java.util.Date;

import com.joshevanJmartFA.dbjson.Serializable;

import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * This class stores user complaint information, such as date and description
 * and extends serializable so that the complaint will have id that is serialized
 * @author Joshevan
 * @version 1.0
 * 
 */
public class Complaint extends Serializable
{
  /**
  * Date of the complaint
  */
  public Date date;
  /**
  * Description of the complaint
  */
  public String desc;
  /**
   * This is the default constructor of the complaint, date will be constructed with current date
   * @param desc description of the complaint
   */
  public Complaint (String desc){
      this.desc = desc;
      date = new Date();
  }
  /**
   * Override the to string method, format the date with simple date format and
   * return complaint date and complaint description
   */
  public String toString(){
      Calendar cal = Calendar.getInstance();
      cal.setTime (date);
      SimpleDateFormat SDFormat = new SimpleDateFormat("dd/MM/yyyy");
      String formatDate = SDFormat.format (cal.getTime());
      return "Complaint{date= " +formatDate+ ",desc='" +this.desc+ "'}";
  }
}
