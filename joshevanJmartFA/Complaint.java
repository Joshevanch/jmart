package joshevanJmartFA;
import java.util.Date;


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
}
