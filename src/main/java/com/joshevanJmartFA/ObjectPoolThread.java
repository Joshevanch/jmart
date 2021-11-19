package com.joshevanJmartFA;
import java.util.Vector;
import java.util.function.Function;

public class ObjectPoolThread<T> extends Thread{
	private boolean exitSignal = false;
	private Vector <T> objectPool = new Vector <T> ();
	private Function<T, Boolean> routine;
	public ObjectPoolThread (String name, Function<T,Boolean> routine) {
		super (name);
		this.routine = routine;
	}
	public ObjectPoolThread (Function <T,Boolean> routine) {
		this.routine = routine;
	}
	public synchronized void add (T object) {
		this.objectPool.add (object);
	}
	public synchronized void exit () {
		this.exitSignal = true;
	}
	public void run() {
		while (this.exitSignal == false) {
			for (T a: this.objectPool) {
				System.out.println(this.routine.apply(a));
				while (this.routine.apply(a) != true) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (routine.apply(a) == true) {
					objectPool.remove(a);
				}
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
	public int size() {
		return objectPool.size();
	}
}
