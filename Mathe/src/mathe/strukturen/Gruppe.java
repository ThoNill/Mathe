package mathe.strukturen;

import java.io.Serializable;

public interface Gruppe<K extends Serializable> extends Halbgruppe<K> {

    K getInverse(K value);

    @Override
    default boolean hatLInverse(K element) {
        return true;
    }

    @Override
    default boolean hatRInverse(K element) {
        return true;
    }

    @Override
    default boolean hatLQuotient(K r, K re) {
        return true;
    }

    @Override
    default boolean hatRQuotient(K r, K re) {
        return true;
    }

    @Override
    default K getLQuotient(K r, K re) {
        return op(r, getInverse(re));
    }

    @Override
    default K getRQuotient(K r, K li) {
        return op(getInverse(li), r);
    }
}
