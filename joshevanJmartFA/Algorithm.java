package joshevanJmartFA;
import java.util.*;

public class Algorithm {
	public static <T> int count (T[] array, T a) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> predicate = a::equals;
		return count (iterator, predicate);
	}
	public static <T> int count (Iterable <T> iterable, T a) {
		Iterator <T> iterator = iterable.iterator();
		Predicate<T> predicate = a::equals;
		return count (iterator, predicate);
	}
	public static <T> int count (Iterator <T> iterator, T a) {
		Predicate<T> predicate = a::equals;
		return count (iterator, predicate);
	}
	public static <T> int count (T[] array, Predicate<T> predicate) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return count (iterator, predicate);
	}
	public static <T> int count (Iterable <T> iterable,  Predicate<T> predicate) {
		Iterator <T> iterator = iterable.iterator();
		return count(iterator, predicate);
	}
	public static <T> int count (Iterator <T> iterator , Predicate<T> predicate) {
		int counter = 0;
		while (iterator.hasNext()) {
			if (predicate.predicate(iterator.next())) {
				counter++;
			}
		}
		return counter;
	}
	public static <T> T find (T[] array , T a) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> predicate = a::equals;
		return find (iterator, predicate);
	}
	public static <T> T find (Iterable <T> iterable , T a) {
		Iterator<T> iterator = iterable.iterator();
		Predicate<T> predicate = a::equals;
		return find (iterator, predicate);
	}
	public static <T> T find (Iterator <T> iterator , T a) {
		Predicate<T> predicate = a::equals;
		return find (iterator, predicate);
	}
	public static <T> T find (T[] array, Predicate <T> predicate) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return find (iterator, predicate);
	}
	public static <T> T find (Iterable <T> iterable, Predicate <T> predicate) {
		Iterator<T> iterator = iterable.iterator();
		return find (iterator, predicate);
	}
	public static <T> T find (Iterator <T> iterator , Predicate<T> predicate) {
		while (iterator.hasNext()) {
			T a = iterator.next();
			if (predicate.predicate(a)) {
				return a;
			}
		}
		return null;
	}
	public static <T> boolean exists (T[] array, T a) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> predicate = a::equals;
		return exists (iterator, predicate);
	}
	public static <T> boolean exists (Iterable <T> iterable, T a) {
		Iterator<T> iterator = iterable.iterator();
		Predicate<T> predicate = a::equals;
		return exists (iterator, predicate);
	}
	public static <T> boolean exists (Iterator <T> iterator, T a) {
		Predicate<T> predicate = a::equals;
		return exists (iterator, predicate);
	}
	public static <T> boolean exists (T[] array, Predicate <T> predicate) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return exists (iterator, predicate);
	}
	public static <T> boolean exists (Iterable <T> iterable, Predicate <T> predicate) {
		Iterator<T> iterator = iterable.iterator();
		return exists (iterator, predicate);
	}
	public static <T> boolean exists (Iterator <T> iterator , Predicate<T> predicate) {
		while (iterator.hasNext()) {
			T a = iterator.next();
			if (predicate == a) {
				return true;
			}
		}
		return false;
	}
	public static <T extends Comparable<T>> T max (T a, T b) {
		int hasil = a.compareTo(b);
		if (hasil<0) {
			return b;
		}
		if (hasil>0) {
			return a;
		}
		else {
			return a;
		}
		}
	public static <T extends Comparable<T>> T min (T a, T b) {
		int hasil = a.compareTo(b);
		if (hasil<0) {
			return a;
		}
		if (hasil>0) {
			return b;
		}
		else {
			return a;
		}
		}
}