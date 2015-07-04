package mathe.strukturen;

public interface Körper<K> extends Ring<K> {

	@Override
	default boolean hatInverse(K value) {
		return !istGleich(getNull(), value);
	}

}
