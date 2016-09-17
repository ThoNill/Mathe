package mathe.grothendieck;

import java.io.Serializable;
import java.util.Iterator;

import mathe.strukturen.Gruppe;
import mathe.strukturen.Halbgruppe;
import mathe.strukturen.K�rper;
import mathe.strukturen.Ring;

public class Quotientenk�rper<R  extends Serializable, GP extends GrothendieckPaar<R>> implements
        K�rper<GP>, GrothendieckPaarMenge<R, GP> {
    Ring<R> ring;
    GrothendieckGruppe<R, GP> multStruktur = null;
    Quotientenk�rperAdditiveStruktur<R, GP> additiveStruktur;

    public Quotientenk�rper(Ring<R> ring) {
        super();
        this.ring = ring;
        multStruktur = new GrothendieckGruppe<>(ring.multStruktur(), this);
        additiveStruktur = new Quotientenk�rperAdditiveStruktur<>(ring, this);
    }

    @Override
    public Halbgruppe<GP> multStruktur() {
        return multStruktur;
    }

    @Override
    public Gruppe<GP> addStruktur() {
        return additiveStruktur;
    }

    @Override
    public GP getElement(String beschreibung) {
        return neuesPaar(ring.getElement(beschreibung), ring.getEins());
    }

    @Override
    public boolean hatInverse(GP value) {
        return !ring.istGleich(ring.getNull(), value.li);
    }

    @Override
    public boolean istElement(GP e) {
        return ring.istElement(e.li) && ring.istElement(e.re);
    }

    @Override
    public boolean istGleich(GP a, GP b) {
        return multStruktur.istGleich(a, b);
    }

    @Override
    public Iterator<GP> iterator() {
        return null;
    }

    @Override
    public GP neuesPaar(R li, R re) {
        return (GP) new GrothendieckPaar<R>(li, re);
    }
}