package mathe.strukturen;

public interface K�rper<K> extends Ring<K> {

	@Override
	default boolean hatInverse(K value) {
		return !istGleich(getNull(), value);
	}

}
