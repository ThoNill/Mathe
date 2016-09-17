package mathe.strukturen;

import java.io.Serializable;

public interface Körper<K  extends Serializable> extends Ring<K> {

    @Override
    default boolean hatInverse(K value) {
        return !istGleich(getNull(), value);
    }

}
