package joshevanJmartFA;



public abstract class Invoice extends Recognizable implements FileParser
{
    public String date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public Status status;
    protected Invoice (int id, int buyerId, int productId){
        super (id);
        this.buyerId = buyerId;
        this.productId = productId;
        date = "September";
        this.rating = Rating.NONE;
        this.status = Status.WAITING_CONFIRMATION;
        complaintId = -1;
    }
    public abstract double getTotalPay();
    public boolean read (String content){
        return false;
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
