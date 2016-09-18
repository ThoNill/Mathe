package mathe.konkret.polynome;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Gr�bnerBasisAlgo<C extends Serializable> {
    private static final Logger LOG = Logger.getLogger(Gr�bnerBasisAlgo.class
            .getName());

    Polynomring<C> ring;

    public Gr�bnerBasisAlgo(Polynomring<C> ring) {
        super();
        this.ring = ring;
    }

    private SMonome calculateSMonom(Monom p, Monom q) {
        Monom max = Monom.max(p, q);
        LOG.finest("max = " + ring.toString(max));

        Monom a = Monom.div(max, p);
        Monom b = Monom.div(max, q);
        LOG.finest("ma= " + ring.toString(a));
        LOG.finest("mb= " + ring.toString(b));
        return new SMonome(a, b);
    }

    private SCMonome<C> calculateSCMonom(CMonom<C> p, CMonom<C> q) {
        SMonome sMonome = calculateSMonom(p.monom, q.monom);
        C pa = ring.koeffRing.getInverse(q.a);
        C qa = ring.koeffRing.getInverse(p.a);
        CMonom<C> ca = new CMonom<C>(qa, sMonome.a);
        CMonom<C> cb = new CMonom<C>(ring.koeffRing.getNegativ(pa), sMonome.b);
        return new SCMonome<C>(ca, cb);
    }

    private Polynom<C> calculateSPolynom(Polynom<C> a, Polynom<C> b) {
        ring.sortieren(a);
        ring.sortieren(b);
        SCMonome<C> sMonom = calculateSCMonom(a.get(0), b.get(0));

        LOG.finest("ma = " + ring.toString(sMonom.a));
        LOG.finest("mb = " + ring.toString(sMonom.b));

        Polynom<C> polyZuA = new Polynom<>();
        polyZuA.addElement(sMonom.a);
        Polynom<C> polyZuB = new Polynom<>();
        polyZuB.addElement(sMonom.b);
        Polynom<C> sPolynomA = ring.mult(polyZuA, a);
        Polynom<C> sPolynomB = ring.mult(polyZuB, b);
        Polynom<C> sPolynom = ring.add(sPolynomA, sPolynomB);

        CMonom<C> ma = sPolynomA.get(0);
        CMonom<C> mb = sPolynomB.get(0);

        if (!ma.monom.equals(mb.monom)) {
            throw new ArithmeticException("Koeffizienten m�ssen gleich sein");
        }

        sPolynom = normieren(ring.reduzieren(sPolynom));
        if (sPolynom.hatMonom(mb.monom)) {
            throw new ArithmeticException("dieses Monom darf nicht vorkommen");
        }

        return sPolynom;
    }

    private Polynom<C> normieren(Polynom<C> p) {
        if (p.isEmpty())
            return p;
        ring.sortieren(p);

        LOG.finest("vor Normierung = " + ring.toString(p));

        Polynom<C> np = new Polynom<>();
        C mFirst = ring.koeffRing.getInverse(p.get(0).a);
        for (CMonom<C> m : p.monome) {
            np.addElement(new CMonom<C>(ring.koeffRing.mult(mFirst, m.a),
                    m.monom));
        }
        LOG.finest("nach Normierung = " + ring.toString(np));
        return np;
    }

    public Polynom<C> restBestimmen(Polynom<C> p, List<Polynom<C>> liste) {
        Polynom<C> rest = p;
        for (Polynom<C> teiler : liste) {
            DivMitRest<C> divErgebnis = ring.teile(rest, teiler);
            rest = divErgebnis.rest;
        }
        return rest;
    }

    public Polynom<C> restBestimmen(Polynom<C> p, List<Polynom<C>> liste,
            Polynom<C> ausnahme) {
        Polynom<C> rest = p;
        for (Polynom<C> teiler : liste) {
            if (!teiler.equals(ausnahme)) {
                DivMitRest<C> divErgebnis = ring.teile(rest, teiler);
                rest = divErgebnis.rest;
            }
        }
        return rest;
    }

    public List<Polynom<C>> reduziereDieBasis(List<Polynom<C>> gr�bnerBasis) {
        List<Polynom<C>> reduzierteBasis = new ArrayList<>();
        for (Polynom<C> teiler : gr�bnerBasis) {
            Polynom<C> rest = restBestimmen(teiler, gr�bnerBasis, teiler);
            if (!ring.isNullPolynom(rest)) {
                reduzierteBasis.add(rest);
            }
        }
        return reduzierteBasis;
    }

    public List<Polynom<C>> gr�bner(List<Polynom<C>> basis) {
        List<Polynom<C>> gr�bnerBasis = new ArrayList<>();
        gr�bnerBasis.addAll(basis);

        List<Polynom<C>> neueBasen = new ArrayList<>();
        List<Polynom<C>> erg�nzteBasen = basis;

        LOG.finest("neu = " + ring.toString(neueBasen));
        LOG.finest("erg�nzt =\n" + ring.toString(erg�nzteBasen));

        do {
            neueBasen = new ArrayList<Polynom<C>>();
            ring.sortieren(gr�bnerBasis);

            for (Polynom<C> p1 : gr�bnerBasis) {

                for (Polynom<C> p2 : erg�nzteBasen) {
                    LOG.finest("p1=" + ring.toString(p1));
                    LOG.finest("p2=" + ring.toString(p2));

                    if (!p1.equals(p2)) {

                        Polynom<C> sPolynom = calculateSPolynom(p1, p2);

                        List<Polynom<C>> gBasis = new ArrayList<>();
                        gBasis.addAll(gr�bnerBasis);
                        gBasis.addAll(neueBasen);
                        ring.sortieren(gBasis);

                        sPolynom = restBestimmen(sPolynom, gBasis);
                        sPolynom = normieren(sPolynom);

                        if (!ring.isNullPolynom(sPolynom)
                                && !(gr�bnerBasis.contains(sPolynom) || neueBasen
                                        .contains(sPolynom))) {
                            neueBasen.add(sPolynom);
                            ring.sortieren(neueBasen);

                        }
                    }
                }

            }

            gr�bnerBasis.addAll(neueBasen);
            erg�nzteBasen = neueBasen;
        } while (!neueBasen.isEmpty());

        ring.sortieren(gr�bnerBasis);
        gr�bnerBasis = reduziereDieBasis(gr�bnerBasis);
        return gr�bnerBasis;
    }
}

class SMonome {
    Monom a;
    Monom b;

    public SMonome(Monom a, Monom b) {
        super();
        this.a = a;
        this.b = b;
    }
}

class SCMonome<C extends Serializable> {
    CMonom<C> a;
    CMonom<C> b;

    public SCMonome(CMonom<C> a, CMonom<C> b) {
        super();
        this.a = a;
        this.b = b;
    }
}
