package com.joshevanJmartFA;

/**
 * This class contains pair object
 * @author Joshevan
 *
 * @param <T> Can be used with all types of objects 
 * @param <U> Can be used with all types of objects 
 */
public class Pair<T,U> {
	/**
	 * First object
	 */
	public T first;
	/**
	 * Second object
	 */
	public U second;
	/**
	 * This is constructor without parameter, fill the first and second object with null
	 */
	public Pair () {
		this.first= null;
		this.second = null;
	}
	/**
	 * This is the default constructor
	 * @param first First object
	 * @param second Second object
	 */
	public Pair (T first, U second) {
		this.first = first;
		this.second = second;
	}
}
