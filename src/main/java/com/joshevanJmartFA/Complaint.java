package com.joshevanJmartFA;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Complaint extends Serializable
{
  public Date date;
  public String desc;
  public Complaint (String desc){
      this.desc = desc;
      date = new Date();
  }
  public String toString(){
      Calendar cal = Calendar.getInstance();
      cal.setTime (date);
      SimpleDateFormat SDFormat = new SimpleDateFormat("dd/MM/yyyy");
      String formatDate = SDFormat.format (cal.getTime());
      return "Complaint{date= " +formatDate+ ",desc='" +this.desc+ "'}";
  }
}
