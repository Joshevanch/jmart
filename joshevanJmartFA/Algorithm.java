package joshevanJmartFA;
import java.util.*;

public class Algorithm {
	private Algorithm () {
		
	}
	public static <T> int count (T[] array, T value) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return count (iterator, pred);
	}
	public static <T> int count (Iterable <T> iterable, T value) {
		Iterator <T> iterator = iterable.iterator();
		Predicate<T> pred = value::equals;
		return count (iterator, pred);
	}
	public static <T> int count (Iterator <T> iterator, T value) {
		Predicate<T> pred = value::equals;
		return count (iterator, pred);
	}
	public static <T> int count (T[] array, Predicate<T> pred) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return count (iterator, pred);
	}
	public static <T> int count (Iterable <T> iterable,  Predicate<T> pred) {
		Iterator <T> iterator = iterable.iterator();
		return count(iterator, pred);
	}
	public static <T> int count (Iterator <T> iterator , Predicate<T> pred) {
		int counter = 0;
		while (iterator.hasNext()) {
			if (pred.predicate(iterator.next())) {
				counter++;
			}
		}
		return counter;
	}
	public static <T> T find (T[] array , T value) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return find (iterator, pred);
	}
	public static <T> T find (Iterable <T> iterable , T value) {
		Iterator<T> iterator = iterable.iterator();
		Predicate<T> pred = value::equals;
		return find (iterator, pred);
	}
	public static <T> T find (Iterator <T> iterator , T value) {
		Predicate<T> pred = value::equals;
		return find (iterator, pred);
	}
	public static <T> T find (T[] array, Predicate <T> pred) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return find (iterator, pred);
	}
	public static <T> T find (Iterable <T> iterable, Predicate <T> pred) {
		Iterator<T> iterator = iterable.iterator();
		return find (iterator, pred);
	}
	public static <T> T find (Iterator <T> iterator , Predicate<T> pred) {
		while (iterator.hasNext()) {
			T a = iterator.next();
			if (pred.predicate(a)) {
				return a;
			}
		}
		return null;
	}
	public static <T> boolean exists (T[] array, T value) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return exists (iterator, pred);
	}
	public static <T> boolean exists (Iterable <T> iterable, T value) {
		Iterator<T> iterator = iterable.iterator();
		Predicate<T> pred = value::equals;
		return exists (iterator, pred);
	}
	public static <T> boolean exists (Iterator <T> iterator, T value) {
		Predicate<T> pred = value::equals;
		return exists (iterator, pred);
	}
	public static <T> boolean exists (T[] array, Predicate <T> pred) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return exists (iterator, pred);
	}
	public static <T> boolean exists (Iterable <T> iterable, Predicate <T> pred) {
		Iterator<T> iterator = iterable.iterator();
		return exists (iterator, pred);
	}
	public static <T> boolean exists (Iterator <T> iterator , Predicate<T> pred) {
		while (iterator.hasNext()) {
			T a = iterator.next();
			if (pred == a) {
				return true;
			}
		}
		return false;
	}
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
	public static <T extends Comparable<? super T>> T max (T[] array) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return max (iterator);
		}
	public static <T extends Comparable<? super T>> T max (Iterable <T> iterable) {
		Iterator <T> iterator = iterable.iterator();
		return max (iterator);
		}
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
	public static <T extends Comparable<? super T>> T max (T[] array, Comparator<? super T> comparator) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return max (iterator,comparator);
		}
	public static <T extends Comparable<? super T>> T max (Iterable <T> iterable, Comparator<? super T> comparator) {
		Iterator <T> iterator = iterable.iterator();
		return max (iterator,comparator);
		}
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
	public static <T extends Comparable<? super T>> T min (T[] array) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return min (iterator);
		}
	public static <T extends Comparable<? super T>> T min (Iterable <T> iterable) {
		Iterator <T> iterator = iterable.iterator();
		return min (iterator);
		}
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
	public static <T extends Comparable<? super T>> T min (T[] array, Comparator<? super T> comparator) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return min (iterator,comparator);
		}
	public static <T extends Comparable<? super T>> T min (Iterable <T> iterable, Comparator<? super T> comparator) {
		Iterator <T> iterator = iterable.iterator();
		return min (iterator,comparator);
		}
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
	public static <T> List<T> collect (T[] array, T value){
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> pred = value::equals;
		return collect (iterator, pred);
		}
	public static <T> List<T> collect (Iterable <T> iterable, T value){
		Iterator<T> iterator = iterable.iterator();
		Predicate<T> pred = value::equals;
		return collect (iterator, pred);
		}
	public static <T> List<T> collect (Iterator <T> iterator, T value){
		Predicate<T> pred = value::equals;
		return collect (iterator, pred);
		}
	public static <T> List<T> collect (T[] array, Predicate <T> pred){
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return collect (iterator,pred);
		}
	public static <T> List<T> collect (Iterable <T> iterable, Predicate <T> pred){
		Iterator <T> iterator = iterable.iterator();
		return collect (iterator,pred);
		}
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
}