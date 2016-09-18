package mathe.grothendieck;

import java.io.Serializable;
import java.util.Iterator;

public class DefaultPaarMenge<R extends Serializable> implements
        GrothendieckPaarMenge<R, GrothendieckPaar<R>> {

    @Override
    public GrothendieckPaar<R> neuesPaar(R li, R re) {
        return new GrothendieckPaar<R>(li, re);
    }

    @Override
    public GrothendieckPaar<R> getElement(String beschreibung) {
        return null;
    }

    @Override
    public boolean istElement(GrothendieckPaar<R> element) {
        return true;
    }

    @Override
    public boolean istGleich(GrothendieckPaar<R> a, GrothendieckPaar<R> b) {
        return a.equals(b);
    }

    @Override
    public Iterator<GrothendieckPaar<R>> iterator() {
        return null;
    }

}
