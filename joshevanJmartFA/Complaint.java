package joshevanJmartFA;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Complaint extends Recognizable implements FileParser
{
  public Date date;
  public String desc;
  public Complaint (int id, String desc){
      super (id);
      this.desc = desc;
      date = new Date();
  }
  public boolean read (String content){
      return false;
  }
  public String toString(){
      Calendar cal = Calendar.getInstance();
      cal.setTime (date);
      SimpleDateFormat SDFormat = new SimpleDateFormat("dd/MM/yyyy");
      String formatDate = SDFormat.format (cal.getTime());
      return "Complaint{date= " +formatDate+ ",desc='" +this.desc+ "'}";
  }
}
