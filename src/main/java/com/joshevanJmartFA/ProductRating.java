package com.joshevanJmartFA;

/**
 * This class store product rating
 * @author Joshevan
 *
 */
public class ProductRating
{
	/**
	 * Total rating
	 */
    private long total;
    /**
     * Number of rating
     */
    private long count;
    /**
     * This is the default constructor
     */
    public ProductRating (){
        total = 0;
        count = 0;
    }
    /**
     * This method add the input rating to total rating
     * @param rating input rating
     */
    public void insert(int rating){
        total = total+rating;
        count ++;
    }
    /**
     * This method will count average rating
     * @return average rating
     */
    public double getAverage(){
        if (count == 0){
            return 0;
        }
        else {
            return total/count;
        }
    }
    /**
     * This is the getter method for the number of rating
     * @return number of rating
     */
    public long getCount(){
        return count;
    }
    /**
     * This is the getter method for the total rating
     * @return total rating
     */
    public long getTotal(){
        return total;
    }
}
