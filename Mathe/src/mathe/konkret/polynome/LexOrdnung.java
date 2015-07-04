package mathe.konkret.polynome;

public class LexOrdnung implements MonomOrdnung {

	@Override
	public int compare(Monom o1, Monom o2) {
		if (o1.exponenten.length != o2.exponenten.length) {
			throw new IllegalArgumentException("unvergleichbare Monome");
		}
		int dim = o1.exponenten.length;

		for (int i = 0; i < dim; i++) {
			if (o1.exponenten[i] < o2.exponenten[i]) {
				return 1;
			}
			if (o1.exponenten[i] > o2.exponenten[i]) {
				return -1;
			}
		}
		return 0;
	}

}
