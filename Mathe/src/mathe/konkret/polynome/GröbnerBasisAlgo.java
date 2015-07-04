package mathe.konkret.polynome;

import java.util.List;
import java.util.Vector;

public class GröbnerBasisAlgo<C> {
	Polynomring<C> ring;

	public GröbnerBasisAlgo(Polynomring<C> ring) {
		super();
		this.ring = ring;
	}

	private SMonome calculateSMonom(Monom p, Monom q) {
		Monom max = Monom.max(p, q);
		// System.out.println("max = " + ring.toString(max));

		Monom a = Monom.div(max, p);
		Monom b = Monom.div(max, q);
		// System.out.println("ma= " + ring.toString(a));
		// System.out.println("mb= " + ring.toString(b));
		return new SMonome(a, b);
	}

	private SCMonome<C> calculateSCMonom(CMonom<C> p, CMonom<C> q) {
		SMonome sMonome = calculateSMonom(p.monom, q.monom);
		/*
		 * C ggt = ring.koeffRing.ggt(q.a,p.a); C qa =
		 * ring.koeffRing.multStruktur().getLQuotient(q.a,ggt); C pa =
		 * ring.koeffRing.multStruktur().getLQuotient(p.a,ggt);
		 */
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

		// System.out.println("ma = " + ring.toString(sMonom.a));
		// System.out.println("mb = " + ring.toString(sMonom.b));

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
			throw new RuntimeException("Falsch!");
		}

		sPolynom = normieren(ring.reduzieren(sPolynom));
		if (sPolynom.hatMonom(mb.monom)) {
			throw new RuntimeException("hat Monom!");
		}

		return sPolynom;
	}

	private Polynom<C> normieren(Polynom<C> p) {
		if (p.size() == 0)
			return p;
		ring.sortieren(p);

		// System.out.println("vor Normierung = " + ring.toString(p));

		Polynom<C> np = new Polynom<>();
		C mFirst = ring.koeffRing.getInverse(p.get(0).a);
		for (CMonom<C> m : p.monome) {
			np.addElement(new CMonom<C>(ring.koeffRing.mult(mFirst, m.a),
					m.monom));
		}
		// System.out.println("nach Normierung = " + ring.toString(np));
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

	public List<Polynom<C>> reduziereDieBasis(List<Polynom<C>> gröbnerBasis) {
		List<Polynom<C>> reduzierteBasis = new Vector<>();
		for (Polynom<C> teiler : gröbnerBasis) {
			Polynom<C> rest = restBestimmen(teiler, gröbnerBasis, teiler);
			if (!ring.isNullPolynom(rest)) {
				reduzierteBasis.add(rest);
			}
		}
		return reduzierteBasis;
	}

	public List<Polynom<C>> gröbner(List<Polynom<C>> basis) {
		List<Polynom<C>> gröbnerBasis = new Vector<>();
		gröbnerBasis.addAll(basis);

		List<Polynom<C>> neueBasen = new Vector<>();
		List<Polynom<C>> ergänzteBasen = basis;

		// System.out.println("neu = " + ring.toString(neueBasen));
		// System.out.println("ergänzt =\n" + ring.toString(ergänzteBasen));

		do {
			neueBasen = new Vector<Polynom<C>>();
			ring.sortieren(gröbnerBasis);

			for (Polynom<C> p1 : gröbnerBasis) {

				for (Polynom<C> p2 : ergänzteBasen) {
					// System.out.println("p1=" + ring.toString(p1));
					// System.out.println("p2=" + ring.toString(p2));

					if (!p1.equals(p2)) {
						
						Polynom<C> sPolynom = calculateSPolynom(p1, p2);
						
						
						List<Polynom<C>> gBasis = new Vector<>();
						gBasis.addAll(gröbnerBasis);
						gBasis.addAll(neueBasen);
						ring.sortieren(gBasis);
						
					//	Polynom<C> rest = restBestimmen(sPolynom, gBasis);
						sPolynom = restBestimmen(sPolynom,gBasis);
						sPolynom = normieren(sPolynom);
						
						if (!ring.isNullPolynom(sPolynom)) {
							if (!(gröbnerBasis.contains(sPolynom) || neueBasen
									.contains(sPolynom))) {
								neueBasen.add(sPolynom);
								ring.sortieren(neueBasen);
							}
						}
					}
				}

			}

			gröbnerBasis.addAll(neueBasen);
			ergänzteBasen = neueBasen;
		} while (neueBasen.size() > 0);

		ring.sortieren(gröbnerBasis);
		gröbnerBasis = reduziereDieBasis(gröbnerBasis);
		System.out.println("reduzierte Anzahl: " + gröbnerBasis.size());
		return gröbnerBasis;
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

class SCMonome<C> {
	CMonom<C> a;
	CMonom<C> b;

	public SCMonome(CMonom<C> a, CMonom<C> b) {
		super();
		this.a = a;
		this.b = b;
	}
}
