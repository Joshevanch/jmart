package joshevanJmartFA;
import java.util.Date;
import java.util.ArrayList;


public abstract class Invoice extends Recognizable
{
    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public Status status;
    public ArrayList <Record> history = new ArrayList <Record>();
    protected Invoice (int buyerId, int productId){
        this.buyerId = buyerId;
        this.productId = productId;
        date = new Date();
        this.rating = Rating.NONE;
        this.status = Status.WAITING_CONFIRMATION;
        complaintId = -1;
    }
    public abstract double getTotalPay();
    public class Record{
        public Status status;
        public Date date;
        public String message;
    }
    enum Rating{
    NONE,
    BAD,
    NEUTRAL,
    GOOD;
    }
    enum Status{
    WAITING_CONFIRMATION,
    CANCELLED,
    ON_PROGRESS,
    ON_DELIVERY,
    COMPLAINT,
    FINISHED,
    FAILED;
    }
}
