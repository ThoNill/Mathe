package mathe.grothendieck;

import java.io.Serializable;

import mathe.strukturen.Menge;

public interface GrothendieckPaarMenge<R  extends Serializable, GP extends GrothendieckPaar<R>>
        extends Menge<GP> {

    public GP neuesPaar(R li, R re);

}
