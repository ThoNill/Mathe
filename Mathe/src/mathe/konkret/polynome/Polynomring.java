package mathe.konkret.polynome;

import mathe.strukturen.Gruppe;
import mathe.strukturen.Halbgruppe;
import mathe.strukturen.Ring;

public class Polynomring<C> extends Polynome<C> implements Ring<Polynom<C>> {
	PolynomeMultiplikativeHalbgruppe<C> multStruktur;
	Gruppe<Polynom<C>> addStruktur;

	public Polynomring(Ring<C> koeffRing, String bezeichner) {
		this(koeffRing, new MonomHalbgruppe(bezeichner));
	}

	public Polynomring(Ring<C> koeffRing, MonomHalbgruppe monomBereich) {
		super(koeffRing, monomBereich);
		multStruktur = new PolynomeMultiplikativeHalbgruppe<>(koeffRing,
				monomBereich);
		addStruktur = new PolynomeAdditiveGruppe<>(koeffRing, monomBereich);
	}

	@Override
	public Halbgruppe<Polynom<C>> multStruktur() {
		return multStruktur;
	}

	@Override
	public Gruppe<Polynom<C>> addStruktur() {
		return addStruktur;
	}

	@Override
	public Polynom<C> getNegativ(Polynom<C> value) {
		return addStruktur().getInverse(value);
	}


	private int indexZumTeilen(Polynom<C> p,Monom monom) {
	for(int ti = 0;ti<p.size();ti++) {
		if(monomBereich.hatQuotient(p.get(ti).monom,monom)) {
			return ti;
		};
	}
	return 0;
}
	public DivMitRest<C> teile(Polynom<C> l, Polynom<C> p) {
		sortieren(p);
		sortieren(l);
		CMonom<C> maxMonom = p.get(0);
		Polynom<C> div = new Polynom<>();
		Polynom<C> rest = l;
		
		int ti = indexZumTeilen(rest, maxMonom.monom);
		

		while (rest.size() > 0 && ti < rest.size()
				&& monomBereich.hatQuotient(rest.get(ti).monom, maxMonom.monom)
				&& koeffRing.multStruktur().hatLQuotient(rest.get(ti).a,
						maxMonom.a)) {
			multStruktur.teile(div, rest, maxMonom);
			Polynom<C> mp = mult(div, p);
			Polynom<C> nmp = getNegativ(mp);
			rest = add(l, nmp);
			sortieren(rest);
			

			divisionTesten(l, p, div, rest);
		}
		divisionTesten(l, p, div, rest);

		Polynom<C> div1 = reduzieren(div);
		Polynom<C> rest1 = reduzieren(rest);

		divisionTesten(l, p, div1, rest1);

		return new DivMitRest<>(div1, rest1);
	}

	protected void divisionTesten(Polynom<C> l, Polynom<C> p, Polynom<C> div,
			Polynom<C> rest) {
		Polynom<C> testProdukt = mult(div, p);
		Polynom<C> testVergleich = add(testProdukt, rest);

		sortieren(testVergleich);
		if (!l.equals(testVergleich)) {

			System.out.println(toString(l));
			System.out.println(toString(testVergleich));
			System.out.println("div= " + toString(div));
			System.out.println("rest= " + toString(rest));

			throw new RuntimeException("Mult/Div ");
		}
	}

	public String toString(Monom m) {
		return monomBereich.toString(m);
	}

}
