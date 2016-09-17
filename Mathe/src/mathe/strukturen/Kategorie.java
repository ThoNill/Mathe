package mathe.strukturen;

import java.io.Serializable;

public interface Kategorie<OBJ  extends Serializable, MOR  extends Serializable> {
    Menge<OBJ> objecte();

    Menge<MOR> morphismen(OBJ from, OBJ to);

}
