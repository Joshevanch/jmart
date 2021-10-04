package joshevanJmartFA;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Recognizable implements FileParser
{
   public static final String REGEX_EMAIL = "^(?!.*^[.])(?!.*[.]{2})[\\w&._*~]+@(?![.-])[\\w.-]+(?!.*$[.])$";
   public static final String REGEX_PASSWORD = "^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?!.*[\\s]{2}).*$";
   public String name;
   public String email;
   public String password;
   public Account (int id, String name, String email, String password){
       super (id);
       this.name = name;
       this.email = password;
       this. password = password;
   }
   public boolean read (String content){
       return false;
   }
   public String toString(){
       return "name: "+this.name+"email: "+this.email+"password: "+this.password;
   }
   public boolean validate(){
       Pattern patternEmail = Pattern.compile (REGEX_EMAIL);
       Pattern patternPass = Pattern.compile (REGEX_PASSWORD);
       Matcher matcherEmail = patternEmail.matcher(this.email);
       Matcher matcherPass = patternPass.matcher (this.password);
       return (matcherEmail.find() && matcherPass.find());
   }
}
