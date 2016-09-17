package mathe.konkret.polynome;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import mathe.strukturen.Menge;
import mathe.strukturen.Ring;

public class Polynome<C  extends Serializable> implements Menge<Polynom<C>> {
    protected Ring<C> koeffRing;
    protected MonomHalbgruppe monomBereich;
    protected MonomOrdnung ordnung = new LexOrdnung();

    public Polynome(Ring<C> koeffRing, MonomHalbgruppe monomBereich) {
        super();
        this.koeffRing = koeffRing;
        this.monomBereich = monomBereich;
    }

    public void sortieren(Polynom<C> p) {
        p.sortieren(ordnung);
    }

    protected void sortieren(List<Polynom<C>> liste) {
        liste.sort(new PolynomOrdnung<>(ordnung));
    }

    @Override
    public Polynom<C> getElement(String beschreibung) {
        String[] koeffizienten = beschreibung.trim().split("[ ,]+");
        Polynom<C> p = new Polynom<>();
        for (int i = 0; i < koeffizienten.length; i++) {
            C koeffizient = koeffRing.getElement(koeffizienten[i]);
            if (!koeffRing.istGleich(koeffRing.getNull(), koeffizient)) {
                CMonom cMonom = getMonom(koeffizient, i);
                p.addElement(cMonom);
            }
        }
        return p;
    }

    public Polynom<C> getElement(CMonom<C>... monome) {
        Polynom<C> p = new Polynom<>();
        for (CMonom<C> m : monome) {
            p.addElement(m);
        }
        return p;
    }

    public CMonom<C> getMonom(C a, int... exp) {
        return new CMonom<C>(a, monomBereich.getSize(), exp);
    }

    public String toString(CMonom<C> m) {
        String koeffizient = koeffRing.toString(m.a);
        String monom = monomBereich.toString(m.monom);

        if (!"".equals(monom)) {
            if ("1".equals(koeffizient)) {
                return monom;
            }
            if ("-1".equals(koeffizient)) {
                return "-" + monom;
            }
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append(koeffizient);
        buffer.append(" ");
        buffer.append(monom);
        return buffer.toString();

    }

    @Override
    public String toString(Polynom<C> p) {
        sortieren(p);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < p.size(); i++) {
            buffer.append(toString(p.get(i), i));
        }
        String rohErgebnis = buffer.toString();
        return rohErgebnis.replaceAll("\\+ *\\-", " - ");
    }

    private String toString(CMonom<C> cMonom, int i) {
        return ((i > 0) ? "+ " : "") + toString(cMonom);
    }

    public String toString(List<Polynom<C>> l) {
        StringBuffer buffer = new StringBuffer();
        int i = 0;
        for (Polynom<C> m : l) {
            buffer.append("p[");
            buffer.append(i);
            buffer.append("]=");
            buffer.append(toString(m));
            buffer.append("\n");
            i++;
        }
        return buffer.toString();
    }

    @Override
    public boolean istElement(Polynom<C> element) {
        return true;
    }

    @Override
    public boolean istGleich(Polynom<C> a, Polynom<C> b) {
        sortieren(a);
        sortieren(b);
        if (a.size() != b.size()) {
            return false;
        }
        int anz = a.size();
        for (int i = 0; i < anz; i++) {
            CMonom<C> ma = a.get(i);
            CMonom<C> mb = b.get(i);
            if (!istGleich(ma, mb)) {
                return false;
            }
        }
        return true;
    }

    private boolean istGleich(CMonom<C> ma, CMonom<C> mb) {
        return monomBereich.istGleich(ma.monom, mb.monom)
                && koeffRing.istGleich(ma.a, mb.a);
    }

    @Override
    public Iterator<Polynom<C>> iterator() {
        return null;
    }

    protected Polynom<C> reduzieren(Polynom<C> p) {
        sortieren(p);
        Polynom<C> reduziert = new Polynom<>();
        int anz = p.size();
        if (anz > 0) {
            CMonom<C> m1 = p.get(0);
            for (int i = 1; i < anz; i++) {
                CMonom<C> m2 = p.get(i);
                if (monomBereich.istGleich(m1.monom, m2.monom)) {
                    C a = koeffRing.add(m1.a, m2.a);
                    CMonom<C> summe = new CMonom<C>(a, m1.monom);
                    m1 = summe;
                } else {
                    addWennNichtNull(reduziert, m1);
                    m1 = m2;
                }
            }
            addWennNichtNull(reduziert, m1);
        }
        return reduziert;
    }

    protected void addWennNichtNull(Polynom<C> reduziert, CMonom<C> m) {
        if (!koeffRing.istGleich(koeffRing.getNull(), m.a)) {
            reduziert.addElement(m);
        }
        ;
    }

    protected void erweitern(Polynom<C> l, Polynom<C> summe) {
        int anz = l.size();
        for (int i = 0; i < anz; i++) {
            summe.addElement(l.get(i));
        }
    }

    public boolean isNullPolynom(Polynom<C> p) {
        if (p.size() == 1) {
            return koeffRing.istGleich(koeffRing.getNull(), p.get(0).a);
        }
        if (p.size() == 0) {
            return true;
        }
        return false;
    }
}
