package com.joshevanJmartFA;
import java.util.*;
/**
 * This class contains several algorithm that can be used to implements several function
 * that used repeatedly
 * @author Joshevan
 * @version 1.0
 * 
 * 
 */
public class Algorithm {
	private Algorithm () {
		
	}
	/**This method counts objects in the array that matches the value.
	 * This method will convert array to iterator, value to predicate and call the default method
	 * @param <T> Can be used with all types of objects
	 * @param array Array of object that will be counted
	 * @param value value Value that will be matched
	 * @return Number of objects that matches the predicate
	 */
	public static <T> int count (T[] array, T value) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return count (iterator, pred);
	}
	/**This method counts objects in the iterable that matches the value.
	 * This method will convert iterable to iterator, value to predicate and call the default method
	 * @param <T> Can be used with all types of objects
	 * @param iterable Iterable object that will be counted
	 * @param value Value that will be matched
	 * @return Number of objects that matches the predicate
	 */
	public static <T> int count (Iterable <T> iterable, T value) {
		Iterator <T> iterator = iterable.iterator();
		Predicate<T> pred = value::equals;
		return count (iterator, pred);
	}
	/**
	 * This method counts objects in the iterator that matches the value.
	 * This method will convert value to predicate and call the default method
	 * @param <T> Can be used with all types of objects
	 * @param iterator Iterator of object that will be counted
	 * @param value Value that will be matched
	 * @return Number of objects that matches the predicate
	 */
	public static <T> int count (Iterator <T> iterator, T value) {
		Predicate<T> pred = value::equals;
		return count (iterator, pred);
	}
	/**
	 * This method counts objects in the array that matches the predicate.
	 * This method will convert array to iterator and call the default method
	 * @param <T> Can be used with all types of objects
	 * @param array Array of object that will be counted
	 * @param pred Predicate that will be matched
	 * @return Number of objects that matches the predicate
	 */
	public static <T> int count (T[] array, Predicate<T> pred) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return count (iterator, pred);
	}
	/**
	 * This method counts objects in the iterable that matches the predicate.
	 * This method will convert iterable to iterator and call the default method
	 * @param <T> Can be used with all types of objects
	 * @param iterable Iterable object that will be counted
	 * @param pred Predicate that will be matched
	 * @return Number of objects that matches the predicate
	 */
	public static <T> int count (Iterable <T> iterable,  Predicate<T> pred) {
		Iterator <T> iterator = iterable.iterator();
		return count(iterator, pred);
	}
	/**
	 * This method counts objects in the iterator that matches the predicate.
	 * This method will be the default method 
	 * @param <T> Can be used with all types of objects
	 * @param iterator Iterator of object that will be counted
	 * @param pred Predicate that will be matched
	 * @return Number of objects that matches the predicate
	 */
	public static <T> int count (Iterator <T> iterator , Predicate<T> pred) {
		int counter = 0;
		while (iterator.hasNext()) {
			if (pred.predicate(iterator.next())) {
				counter++;
			}
		}
		return counter;
	}
	/**
	 * This method will find objects in the array that matches the value  
	 * This method will convert array to iterator, value to predicate, and call the default method
	 * @param <T> Can be used with all types of objects 
	 * @param array Array of object that will be checked
	 * @param value Value that will be matched
	 * @return object in the array that matches the value, return null if no object matches the value
	 */
	public static <T> T find (T[] array , T value) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return find (iterator, pred);
	}
	/**
	 * This method will find objects in the iterable that matches the value  
	 * This method will convert iterable to iterator, value to predicate, and call the default method
	 * @param <T> Can be used with all types of objects 
	 * @param iterable Iterable object that will be checked
	 * @param value Value that will be matched
	 * @return object in the iterable that matches the value, return null if no object matches the value
	 */
	public static <T> T find (Iterable <T> iterable , T value) {
		Iterator<T> iterator = iterable.iterator();
		Predicate<T> pred = value::equals;
		return find (iterator, pred);
	}
	/**
	 * This method will find objects in the iterator that matches the value  
	 * This method will convert value to predicate, and call the default method
	 * @param <T> Can be used with all types of objects 
	 * @param iterator Iterator of object that will be checked
	 * @param value Value that will be matched
	 * @return object in the iterator that matches the value, return null if no object matches the value
	 */
	public static <T> T find (Iterator <T> iterator , T value) {
		Predicate<T> pred = value::equals;
		return find (iterator, pred);
	}
	/**
	 * This method will find objects in the array that matches the predicate  
	 * This method will convert array to iterator and call the default method
	 * @param <T> Can be used with all types of objects 
	 * @param array Array of object that will be checked
	 * @param pred The predicate that will be matched
	 * @return object in the array that matches the predicate, return null if no object matches the predicate
	 */
	public static <T> T find (T[] array, Predicate <T> pred) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return find (iterator, pred);
	}
	/**
	 * This method will find objects in the iterable that matches the predicate  
	 * This method will convert iterable to iterator and call the default method
	 * @param <T> Can be used with all types of objects 
	 * @param iterable Iterable object that will be checked
	 * @param pred The predicate that will be matched
	 * @return object in the iterable that matches the predicate, return null if no object matches the predicate
	 */
	public static <T> T find (Iterable <T> iterable, Predicate <T> pred) {
		Iterator<T> iterator = iterable.iterator();
		return find (iterator, pred);
	}
	/**
	 * This method will find objects in the iterator that matches the predicate  
	 * This method will be the default method 
	 * @param <T> Can be used with all types of objects 
	 * @param iterator Iterator of object that will be checked
	 * @param pred The predicate that will be matched
	 * @return object in the iterator that matches the predicate, return null if no object matches the predicate
	 */
	public static <T> T find (Iterator <T> iterator , Predicate<T> pred) {
		while (iterator.hasNext()) {
			T a = iterator.next();
			if (pred.predicate(a)) {
				return a;
			}
		}
		return null;
	}
	/**
	 * This method will check if there is an object in the array that matches the value  
	 * This method will convert array to iterator, value to predicate and call the default method
	 * @param <T> Can be used with all types of objects
	 * @param array  Array of object that will be checked
	 * @param value Value that will be matched
	 * @return if there is an object in the array that matches the value,
	 * return false if no object matches the value
	 */
	public static <T> boolean exists (T[] array, T value) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return exists (iterator, pred);
	}
	/**
	 * This method will check if there is an object in the iterable that matches the predicate  
	 * This method will convert iterable to iterator, value to predicate and call the default method
	 * @param <T> Can be used with all types of objects
	 * @param iterable Iterable object that will be checked
	 * @param value Value that will be matched
	 * @return if there is an object in the iterable that matches the value,
	 * return false if no object matches the value
	 */
	public static <T> boolean exists (Iterable <T> iterable, T value) {
		Iterator<T> iterator = iterable.iterator();
		Predicate<T> pred = value::equals;
		return exists (iterator, pred);
	}
	/**
	 * This method will check if there is an object in the iterator that matches the value  
	 * This method will convert value to predicate and call the default method
	 * @param <T> Can be used with all types of objects
	 * @param iterator Iterator of object that will be checked
	 * @param value Value that will be matched
	 * @return if there is an object in the iterator that matches the value,
	 * return false if no object matches the value
	 */
	public static <T> boolean exists (Iterator <T> iterator, T value) {
		Predicate<T> pred = value::equals;
		return exists (iterator, pred);
	}
	/**
	 * This method will check if there is an object in the array that matches the predicate  
	 * This method will convert array to iterator and call the default method
	 * @param <T> Can be used with all types of objects
	 * @param array  Array of object that will be checked
	 * @param pred The predicate that will be matched
	 * @return true if there is an object in the array that matches the predicate,
	 * return false if no object matches the predicate
	 */
	public static <T> boolean exists (T[] array, Predicate <T> pred) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return exists (iterator, pred);
	}
	/**
	 * This method will check if there is an object in the iterable that matches the predicate  
	 * This method will convert iterable to iterator and call the default method
	 * @param <T> Can be used with all types of objects 
	 * @param iterable Iterable object that will be checked
	 * @param pred The predicate that will be matched
	 * @return true if there is an object in the iterable that matches the predicate,
	 * return false if no object matches the predicate
	 */
	public static <T> boolean exists (Iterable <T> iterable, Predicate <T> pred) {
		Iterator<T> iterator = iterable.iterator();
		return exists (iterator, pred);
	}	
	/**
	 * This method will check if there is an object in the iterator that matches the predicate  
	 * This method will be the default method 
	 * @param <T> Can be used with all types of objects 
	 * @param iterator Iterator of object that will be checked
	 * @param pred The predicate that will be matched
	 * @return true if there is an object in the iterator that matches the predicate,
	 * return false if no object matches the predicate
	 */
	public static <T> boolean exists (Iterator <T> iterator , Predicate<T> pred) {
		while (iterator.hasNext()) {
			T a = iterator.next();
			if (pred == a) {
				return true;
			}
		}
		return false;
	}
	/**
	 * This method will find max object from two objects
	 * @param <T> Can be used with all types of objects 
	 * @param first First object that will be checked
	 * @param second Second object that will be checked
	 * @return max object
	 */
	public static <T extends Comparable<? super T>> T max (T first, T second) {
		int hasil = first.compareTo(second);
		if (hasil<0) {
			return second;
		}
		if (hasil>0) {
			return first;
		}
		else {
			return second;
		}
		}
	/**
	 * This method will find max object from array
	 * @param <T> Can be used with all types of objects
	 * @param array Array of objects that will be checked
	 * @return max object in the array
	 */
	public static <T extends Comparable<? super T>> T max (T[] array) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return max (iterator);
		}
	/**
	 * This method will find max object from Iterable objects 
	 * @param <T> Can be used with all types of objects
	 * @param iterable Iterable objects that will be checked 
	 * @return max object in the iterable objects 
	 */
	public static <T extends Comparable<? super T>> T max (Iterable <T> iterable) {
		Iterator <T> iterator = iterable.iterator();
		return max (iterator);
		}
	/**
	 * This method will find max object from iterator objects 
	 * @param <T> Can be used with all types of objects
	 * @param iterator Iterator of object that will be checked
	 * @return max object in the iterator objects 
	 */
	public static <T extends Comparable<? super T>> T max (Iterator <T> iterator) {
		T max = null;
		while (iterator.hasNext()) {
			T a = iterator.next();
			if (a.compareTo(max)>0 ) {
				max = a;
			}
		}
		return max;
		}
	/**
	 * This method will find max object from two objects with a specific comparator
	 * @param <T> Can be used with all types of objects
	 * @param first First object that will be checked
	 * @param second Second object that will be checked
	 * @param comparator Comparator that will be used 
	 * @return max object
	 */
	public static <T extends Comparator<? super T>> T max (T first, T second, Comparator<? super T> comparator) {
		int hasil = comparator.compare(first, second);
		if (hasil<0) {
			return second;
		}
		if (hasil>0) {
			return first;
		}
		else {
			return second;
		}
		}
	/**
	 * This method will find max object from array with a specific comparator
	 * @param <T> Can be used with all types of objects
	 * @param array Array of object that will be checked 
	 * @param comparator Comparator that will be used 
	 * @return max object in the array 
	 */
	public static <T extends Comparable<? super T>> T max (T[] array, Comparator<? super T> comparator) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return max (iterator,comparator);
		}
	/**
	 * This method will find max object from iterable objects with a specific comparator
	 * @param <T> Can be used with all types of objects
	 * @param iterable Iterable objects that will be checked 
	 * @param comparator Comparator that will be used 
	 * @return max object in the iterable objects 
	 */
	public static <T extends Comparable<? super T>> T max (Iterable <T> iterable, Comparator<? super T> comparator) {
		Iterator <T> iterator = iterable.iterator();
		return max (iterator,comparator);
		}
	/**
	 * This method will find max object from iterator with a specific comparator
	 * @param <T> Can be used with all types of objects
	 * @param iterator Iterator of object that will be checked
	 * @param comparator Comparator that will be used 
	 * @return max object in the iterator objects
	 */
	public static <T extends Comparable<? super T>> T max (Iterator <T> iterator, Comparator<? super T> comparator) {
		T max = null;
		while (iterator.hasNext()) {
			T a = iterator.next();
			if (comparator.compare(a, max) >0 ) {
				max = a;
			}
		}
		return max;
		}
	/**
	 * This method will find min object from two objects
	 * @param <T> Can be used with all types of objects 
	 * @param first First object that will be checked
	 * @param second Second object that will be checked
	 * @return min object
	 */
	public static <T extends Comparable<T>> T min (T first, T second) {
		int hasil = first.compareTo(second);
		if (hasil<0) {
			return first;
		}
		if (hasil>0) {
			return second;
		}
		else {
			return first;
		}
		}
	/**
	 * This method will find min object from array
	 * @param <T> Can be used with all types of objects
	 * @param array Array of objects that will be checked
	 * @return min object in the array
	 */
	public static <T extends Comparable<? super T>> T min (T[] array) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return min (iterator);
		}
	/**
	 * This method will find min object from Iterable objects 
	 * @param <T> Can be used with all types of objects
	 * @param iterable Iterable objects that will be checked 
	 * @return min object in the iterable objects 
	 */
	public static <T extends Comparable<? super T>> T min (Iterable <T> iterable) {
		Iterator <T> iterator = iterable.iterator();
		return min (iterator);
		}
	/**
	 * This method will find min object from iterator objects 
	 * @param <T> Can be used with all types of objects
	 * @param iterator Iterator of object that will be checked
	 * @return min object in the iterator objects 
	 */
	public static <T extends Comparable<? super T>> T min (Iterator <T> iterator) {
		T min = null;
		while (iterator.hasNext()) {
			T a = iterator.next();
			if (a.compareTo(min)<0 ) {
				min = a;
			}
		}
		return min;
		}
	/**
	 * This method will find min object from two objects with a specific comparator
	 * @param <T> Can be used with all types of objects
	 * @param first First object that will be checked
	 * @param second Second object that will be checked
	 * @param comparator Comparator that will be used 
	 * @return min object
	 */
	public static <T extends Comparator<? super T>> T min (T first, T second, Comparator<? super T> comparator) {
		int hasil = comparator.compare(first, second);
		if (hasil>0) {
			return second;
		}
		if (hasil<0) {
			return first;
		}
		else {
			return second;
		}
		}
	/**
	 * This method will find min object from array with a specific comparator
	 * @param <T> Can be used with all types of objects
	 * @param array Array of object that will be checked 
	 * @param comparator Comparator that will be used 
	 * @return min object in the array 
	 */
	public static <T extends Comparable<? super T>> T min (T[] array, Comparator<? super T> comparator) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return min (iterator,comparator);
		}
	/**
	 * This method will find min object from iterable objects with a specific comparator
	 * @param <T> Can be used with all types of objects
	 * @param iterable Iterable objects that will be checked 
	 * @param comparator Comparator that will be used 
	 * @return min object in the iterable objects 
	 */
	public static <T extends Comparable<? super T>> T min (Iterable <T> iterable, Comparator<? super T> comparator) {
		Iterator <T> iterator = iterable.iterator();
		return min (iterator,comparator);
		}
	/**
	 * This method will find min object from iterator with a specific comparator
	 * @param <T> Can be used with all types of objects
	 * @param iterator Iterator of object that will be checked
	 * @param comparator Comparator that will be used 
	 * @return min object in the iterator objects
	 */
	public static <T extends Comparable<? super T>> T min (Iterator <T> iterator, Comparator<? super T> comparator) {
		T min = null;
		while (iterator.hasNext()) {
			T a = iterator.next();
			if (comparator.compare(a, min) <0 ) {
				min = a;
			}
		}
		return min;
		}
	/**
	 * This method will collect objects in the array that matches the value  
	 * This method will convert array to iterator, value to predicate and call the default method
	 * @param <T> Can be used with all types of objects 
	 * @param array Array of object that will be collected 
	 * @param value Value that will be matched
	 * @return List of object that mathces the value
	 */
	public static <T> List<T> collect (T[] array, T value){
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return collect (iterator, pred);
		}
	/**
	 * This method will collect objects in the iterable that matches the value  
	 * This method will convert iterable to iterator, value to predicate and call the default method
	 * @param <T> Can be used with all types of objects 
	 * @param iterable Iterable objects that will be collected
	 * @param value Value that will be matched
	 * @return List of object that mathces the value
	 */
	public static <T> List<T> collect (Iterable <T> iterable, T value){
		Iterator<T> iterator = iterable.iterator();
		Predicate<T> pred = value::equals;
		return collect (iterator, pred);
		}
	/**
	 * This method will collect objects in the iterator that matches the value  
	 * This method will convert value to predicate and call the default method
	 * @param <T> Can be used with all types of objects 
	 * @param iterator Iterator of object that will be collected
	 * @param value Value that will be matched
	 * @return List of object that mathces the value
	 */
	public static <T> List<T> collect (Iterator <T> iterator, T value){
		Predicate<T> pred = value::equals;
		return collect (iterator, pred);
		}
	/**
	 * This method will collect objects in the array that matches the predicate  
	 * This method will convert array to iterator and call the default method
	 * @param <T> Can be used with all types of objects 
	 * @param array Array of object that will be collected
	 * @param pred Predicate that will be matched
	 * @return List of object that mathces the predicate
	 */
	public static <T> List<T> collect (T[] array, Predicate <T> pred){
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return collect (iterator,pred);
		}
	/**
	 * This method will collect objects in the iterable that matches the predicate  
	 * This method will convert iterable to iterator and call the default method
	 * @param <T> Can be used with all types of objects 
	 * @param iterable Iterable objects that will be collected 
	 * @param pred Predicate that will be matched
	 * @return List of object that mathces the predicate
	 */
	public static <T> List<T> collect (Iterable <T> iterable, Predicate <T> pred){
		Iterator <T> iterator = iterable.iterator();
		return collect (iterator,pred);
		}
	/**
	 * This method will collect objects in the iterator that matches the predicate  
	 * This method will be the default method 
	 * @param <T> Can be used with all types of objects 
	 * @param iterator Iterator of object that will be collected
	 * @param pred Predicate that will be matched
	 * @return List of object that mathces the predicate
	 */
	public static <T> List<T> collect (Iterator<T> iterator, Predicate<T> pred){
		List <T> list = new ArrayList<T>();
		while (iterator.hasNext()) {
			T a = iterator.next();
			if (pred.predicate(a)) {
				list.add(a);
			}
		}
		return list;
	}
	/**
	 * This method will paginate object in the iterator that matches the predicate  
	 * This method will be the default method 
	 * @param <T> Can be used with all types of objects 
	 * @param array Array of object that will be collected 
	 * @param page  The page number
	 * @param pageSize The number of objects that will be collected 
	 * @param pred The predicate that will be matched
	 * @return List of object that matches the predicate
	 */
	public static <T> List<T> paginate (T[] array, int page, int pageSize, Predicate <T> pred){
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return paginate (iterator, page, pageSize, pred);
	}
	/**
	 * This method will paginate object in the iterator that matches the predicate  
	 * This method will be the default method 
	 * @param <T> Can be used with all types of objects 
	 * @param iterable Iterable objects that will be collected 
	 * @param page  The page number
	 * @param pageSize The number of objects that will be collected 
	 * @param pred The predicate that will be matched
	 * @return List of object that matches the predicate
	 */
	public static <T> List<T> paginate (Iterable <T> iterable, int page, int pageSize, Predicate <T> pred){
		Iterator<T> iterator = iterable.iterator();
		return paginate (iterator, page, pageSize, pred);
	}
	/**
	 * This method will paginate object in the iterator that matches the predicate  
	 * This method will be the default method 
	 * @param <T> Can be used with all types of objects 
	 * @param iterator Iterator of object that will be checked
	 * @param page  The page number
	 * @param pageSize The number of objects that will be collected 
	 * @param pred The predicate that will be matched
	 * @return List of object that matches the predicate
	 */
	public static <T> List<T> paginate (Iterator<T> iterator, int page, int pageSize, Predicate<T>pred){
    	List<T> list = new ArrayList<T>();
    	List<T> list2 = new ArrayList<T>();
    	int i = 0;
    	while (iterator.hasNext()) {
    		T a = iterator.next();
    			if (pred.predicate(a)) {
    				list.add(a);
    			}
    	
    }
    	for (T b : list) {
			if (i>= ((page)*pageSize) && i<((page+1)*pageSize)) {
				list2.add(b);
			}
			i ++;
}
    	return list2;
    }
}