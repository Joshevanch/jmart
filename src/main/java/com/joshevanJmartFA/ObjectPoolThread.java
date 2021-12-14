package com.joshevanJmartFA;
import java.util.Vector;
import java.util.function.Function;

/**
 * This class extends thread to apply method to object with multithreading
 * @author Joshevan
 *
 * @param <T> Can be used with all types of objects 
 */
public class ObjectPoolThread<T> extends Thread{
	/**
	 * Signal to stop thread
	 */
	private boolean exitSignal = false;
	/**
	 * Vector of object to apply method
	 */
	private Vector <T> objectPool = new Vector <T> ();
	/**
	 * Method that will be applide
	 */
	private Function<T, Boolean> routine;
	/**
	 * Default constructor
	 * @param name thread name
	 * @param routine method that will be applied
	 */
	public ObjectPoolThread (String name, Function<T,Boolean> routine) {
		super (name);
		this.routine = routine;
	}
	/**
	 * Constructor without the name of the thread
	 * @param routine method that will be applied
	 */
	public ObjectPoolThread (Function <T,Boolean> routine) {
		this.routine = routine;
	}
	/**
	 * This method will add object to apply method
	 * @param object Object to be added
	 */
	public synchronized void add (T object) {
		this.objectPool.add (object);
	}
	/**
	 * This method will exit the multi threading
	 */
	public synchronized void exit () {
		this.exitSignal = true;
	}
	/**
	 * This method will start the multi threading
	 */
	public void run() {
		while (this.exitSignal == false) {
			for (T a: this.objectPool) {
				while (this.routine.apply(a) != true);
				if (routine.apply(a) == true) {
					objectPool.remove(a);
				}
				synchronized (this) {
					while (this.size() == 0) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (this.size() != 0) {
						this.notify();
					}
				}
				
			}
			
		}
		}
	/**
	 * This is the getter method for the number of object
	 * @return number of object
	 */
	public int size() {
		return objectPool.size();
	}
}
