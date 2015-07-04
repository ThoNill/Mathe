package mathe.strukturen;

public interface KommutativesMagma<K> extends Magma<K> {

	boolean hatQuotient(K r, K re);

	K getQuotient(K r, K re);

	@Override
	default K getLQuotient(K r, K re) {
		return getQuotient(r, re);
	}

	@Override
	default K getRQuotient(K r, K li) {
		return getQuotient(r, li);
	}

	@Override
	default boolean hatLQuotient(K r, K re) {
		return hatQuotient(r, re);
	}

	@Override
	default boolean hatRQuotient(K r, K li) {
		return hatQuotient(r, li);
	}

}
