package mathe.strukturen;

import java.util.Iterator;

public interface Ring<K> extends Menge<K> {
	Halbgruppe<K> multStruktur();

	Gruppe<K> addStruktur();

	default K add(K a, K b) {
		return addStruktur().op(a, b);
	}

	default K mult(K a, K b) {
		return multStruktur().op(a, b);
	}
	
	default K getNull() {
		return addStruktur().getEins();
	}

	default K getEins() {
		return multStruktur().getEins();
	}

	default boolean hatInverse(K value) {
		return multStruktur().hatLInverse(value);
	}

	default K getInverse(K value) {
		return multStruktur().getLInverse(value);
	}

	default K getNegativ(K value) {
		return addStruktur().getLInverse(value);
	}

	@Override
	default Iterator<K> elemente() {
		return addStruktur().elemente();
	};

	@Override
	default K getElement(String beschreibung) {
		return addStruktur().getElement(beschreibung);
	}

	@Override
	default boolean istElement(K element) {
		return addStruktur().istElement(element);
	}

	@Override
	default boolean istGleich(K a, K b) {
		return addStruktur().istGleich(a, b);
	}
	
	
}
