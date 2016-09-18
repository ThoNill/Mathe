package mathe.grothendieck;

import java.io.Serializable;
import java.util.Iterator;

import mathe.strukturen.Gruppe;
import mathe.strukturen.Halbgruppe;

public class GrothendieckGruppe<H extends Serializable, GP extends GrothendieckPaar<H>>
        implements Gruppe<GP>, GrothendieckPaarMenge<H, GP> {
   
    private static final long serialVersionUID = 5306610866508554478L;
    Halbgruppe<H> halbgruppe;
    GrothendieckPaarMenge<H, GP> paarMenge;

    public GrothendieckGruppe(Halbgruppe<H> halbgruppe,
            GrothendieckPaarMenge<H, GP> paarMenge) {
        super();
        this.halbgruppe = halbgruppe;
        this.paarMenge = paarMenge;
    }

    @Override
    public GP neuesPaar(H li, H re) {
        return paarMenge.neuesPaar(li, re);
    }

    @Override
    public GP getEins() {
        return neuesPaar(halbgruppe.getEins(), halbgruppe.getEins());
    }

    @Override
    public GP op(GP l, GP r) {
        return neuesPaar(halbgruppe.op(l.li, r.li), halbgruppe.op(l.re, r.re));
    }

    @Override
    public GP getElement(String beschreibung) {
        return neuesPaar(halbgruppe.getElement(beschreibung),
                halbgruppe.getEins());
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
        return halbgruppe.istElement(e.li) && halbgruppe.istElement(e.re);
    }

    @Override
    public boolean istGleich(GP a, GP b) {
        return halbgruppe.istGleich(halbgruppe.op(a.li, b.re),
                halbgruppe.op(b.li, a.re));
    }

    @Override
    public Iterator<GP> iterator() {
        return null;
    }

    @Override
    public GP getInverse(GP value) {
        return neuesPaar(value.re, value.li);
    }

}
