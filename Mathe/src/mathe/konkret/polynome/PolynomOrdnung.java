package mathe.konkret.polynome;

import java.util.Comparator;

public class PolynomOrdnung<C> implements Comparator<Polynom<C>> {
	MonomOrdnung ordnung;

	public PolynomOrdnung(MonomOrdnung ordnung) {
		super();
		this.ordnung = ordnung;
	}

	@Override
	public int compare(Polynom<C> o1,Polynom<C> o2) {
		if (o1.size() == 0 && o2.size()>0) {
			return -1;
		}
		if (o1.size() > 0 && o2.size() ==0) {
			return 1;
		}
		int min = Math.min(o1.size(),o2.size());
		for (int i =0;i<min;i++) {
			int vergl = ordnung.compare(o1.get(i).monom, o2.get(i).monom);
			if (vergl != 0) {
				return vergl;
			}
		}
		return o1.size() - o2.size();
	}

}
