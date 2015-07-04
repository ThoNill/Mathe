package mathe.strukturen;

public interface Magma<K> extends Menge<K> {

	K op(K l, K r);

	boolean hatLQuotient(K r, K re);

	boolean hatRQuotient(K r, K le);

	K getLQuotient(K r, K re);

	K getRQuotient(K r, K li);

}
