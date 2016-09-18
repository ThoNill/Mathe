package mathe.strukturen;

import java.io.Serializable;

public interface K�rper<K extends Serializable> extends Ring<K> {

    @Override
    default boolean hatInverse(K value) {
        return !istGleich(getNull(), value);
    }

}
