package joshevanJmartFA;
import java.util.*;

public class Algorithm {
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
	public static <T extends Comparable<T>> T max (T first, T second) {
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
}