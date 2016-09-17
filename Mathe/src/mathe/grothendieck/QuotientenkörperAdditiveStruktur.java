package mathe.grothendieck;

import java.io.Serializable;
import java.util.Iterator;

import mathe.strukturen.Gruppe;
import mathe.strukturen.Ring;

public class QuotientenkörperAdditiveStruktur<R  extends Serializable, GP extends GrothendieckPaar<R>>
        implements Gruppe<GP> {
    Ring<R> ring;
    GrothendieckPaarMenge<R, GP> paarMenge;

    public QuotientenkörperAdditiveStruktur(Ring<R> ring,
            GrothendieckPaarMenge<R, GP> paarMenge) {
        this.ring = ring;
        this.paarMenge = paarMenge;
    }

    public GP neuesPaar(R li, R re) {
        return paarMenge.neuesPaar(li, re);
    }

    @Override
    public GP getElement(String beschreibung) {
        return neuesPaar(ring.getElement(beschreibung), ring.getEins());
    }

    @Override
    public GP getEins() {
        return neuesPaar(ring.getNull(), ring.getEins());
    }

    @Override
    public GP op(GP l, GP r) {
        R nenner = ring.add(ring.mult(l.li, r.re), ring.mult(r.li, l.re));
        R zaehler = ring.mult(l.re, r.re);
        return neuesPaar(nenner, zaehler);
    }

    @Override
    public boolean hatLQuotient(GP r, GP re) {
        return true;
    }

    @Override
    public boolean hatRQuotient(GP r, GP le) {
        return true;
    }

    @Override
    public GP getLQuotient(GP r, GP re) {
        return op(r, getInverse(re));
    }

    @Override
    public GP getRQuotient(GP r, GP li) {
        return op(r, getInverse(li));
    }

    @Override
    public boolean istElement(GP e) {
        return ring.istElement(e.li) && ring.istElement(e.re);
    }

    @Override
    public Iterator<GP> iterator() {
        return null;
    }

    @Override
    public GP getInverse(GP value) {
        return neuesPaar(ring.getNegativ(value.li), value.re);
    }

    @Override
    public boolean istGleich(GP a, GP b) {
        return ring.istGleich(ring.mult(a.li, b.re), ring.mult(b.li, a.re));
    }

}