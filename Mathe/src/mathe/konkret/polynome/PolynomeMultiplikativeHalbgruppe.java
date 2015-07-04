package mathe.konkret.polynome;

import mathe.strukturen.Gruppe;
import mathe.strukturen.Ring;

public class PolynomeMultiplikativeHalbgruppe<C> extends Polynome<C> implements
		Gruppe<Polynom<C>> {
	public Polynom<C> einheit;
	
	public PolynomeMultiplikativeHalbgruppe(Ring<C> koeffRing,
			MonomHalbgruppe monomBereich) {
		super(koeffRing, monomBereich);
		einheit = new Polynom<C>();
		einheit.addElement(new CMonom<C>(koeffRing.getEins(), monomBereich
				.getEins()));
	}

	@Override
	public Polynom<C> getEins() {
		return einheit;
	}

	@Override
	public Polynom<C> op(Polynom<C> l, Polynom<C> r) {
		Polynom<C> produkt = new Polynom<>();
		for (CMonom<C> ml : l) {
			for (CMonom<C> mr : r) {
				CMonom<C> mp = mult(ml, mr);
				produkt.addElement(mp);
			}
		}
		return reduzieren(produkt);
	}
	

	public Polynom<C> teile(Polynom<C> div,Polynom<C> l,CMonom<C> m) {
		for (CMonom<C> ml : l) {
			if (monomBereich.hatQuotient(ml.monom, m.monom)
					&& koeffRing.multStruktur().hatLQuotient(ml.a, m.a)) {
				C aDiv = koeffRing.multStruktur().getLQuotient(ml.a, m.a);
		
				Monom mDiv = monomBereich.getQuotient(ml.monom, m.monom);
				CMonom cMonom = new CMonom<C>(aDiv, mDiv);
				div.addElement(cMonom);
			} 
		}
		return div;
	}

	private CMonom<C> mult(CMonom<C> ml, CMonom<C> mr) {
		C a = koeffRing.mult(ml.a, mr.a);
		Monom m = monomBereich.op(ml.monom, mr.monom);
		return new CMonom<C>(a, m);
	}

	@Override
	public Polynom<C> getInverse(Polynom<C> value) {
		Polynom<C> neg = new Polynom<>();
		int anz = value.size();
		for (int i = 1; i < anz; anz++) {
			CMonom<C> m = value.get(i);
			C a = koeffRing.getNegativ(m.a);
			CMonom<C> negCMonom = new CMonom<C>(a, m.monom);
			neg.addElement(negCMonom);

		}
		return neg;
	}

}
