package mathe.konkret.polynome;

import java.io.Serializable;

import mathe.strukturen.Gruppe;
import mathe.strukturen.Ring;

public class PolynomeAdditiveGruppe<C  extends Serializable> extends Polynome<C> implements
        Gruppe<Polynom<C>> {

    public PolynomeAdditiveGruppe(Ring<C> koeffRing,
            MonomHalbgruppe monomBereich) {
        super(koeffRing, monomBereich);
    }

    @Override
    public Polynom<C> getEins() {
        return new Polynom<>();
    }

    @Override
    public Polynom<C> op(Polynom<C> l, Polynom<C> r) {
        Polynom<C> summe = new Polynom<>();
        erweitern(l, summe);
        erweitern(r, summe);
        return reduzieren(summe);
    }

    @Override
    protected void erweitern(Polynom<C> l, Polynom<C> summe) {
        int anz = l.size();
        for (int i = 0; i < anz; i++) {
            summe.addElement(l.get(i));
        }
    }

    @Override
    public Polynom<C> getInverse(Polynom<C> value) {
        Polynom<C> neg = new Polynom<>();
        int anz = value.size();
        for (int i = 0; i < anz; i++) {
            CMonom<C> m = value.get(i);
            C a = koeffRing.getNegativ(m.a);
            CMonom<C> negCMonom = new CMonom<C>(a, m.monom);
            neg.addElement(negCMonom);

        }
        return neg;
    }

}
