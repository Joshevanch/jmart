package joshevanJmartFA;



public interface FileParser
{
     public default Object write (){
         return null;
     };
     public boolean read (String content);
     public static Object newInstance (String content){
         return null; 
     };
}
