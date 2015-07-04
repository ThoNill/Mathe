package mathe.strukturen;

public interface MagmaMitEins<K> extends Magma<K> {
	K getEins();

	default boolean hatLInverse(K e) {
		return hatLQuotient(getEins(), e);
	}

	default boolean hatRInverse(K e) {
		return hatRQuotient(getEins(), e);
	}

	default K getLInverse(K e) {
		return getLQuotient(getEins(), e);
	}

	default K getRInverse(K e) {
		return getRQuotient(getEins(), e);
	}

}
