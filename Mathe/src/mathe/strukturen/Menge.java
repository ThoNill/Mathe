package mathe.strukturen;

import java.util.Iterator;

public interface Menge<T> extends Iterable<T> {
	default Iterator<T> elemente() {
		return iterator();
	}

	T getElement(String beschreibung);

	boolean istElement(T element);

	boolean istGleich(T a, T b);
	
	default String toString(T element) {
		return element.toString();
	}

}
