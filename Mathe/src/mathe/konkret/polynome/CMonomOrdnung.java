package mathe.konkret.polynome;

import java.util.Comparator;

public class CMonomOrdnung<C> implements Comparator<CMonom<C>> {
	MonomOrdnung ordnung;

	public CMonomOrdnung(MonomOrdnung ordnung) {
		super();
		this.ordnung = ordnung;
	}

	@Override
	public int compare(CMonom<C> o1, CMonom<C> o2) {
		return ordnung.compare(o1.monom, o2.monom);
	}

}
