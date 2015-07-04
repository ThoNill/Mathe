package mathe.konkret.polynome;

public class CMonom<C> {
	C a; // Koeffizient;
	Monom monom;

	public CMonom(C a, Monom m) {
		this.a = a;
		this.monom = m;
	}

	public CMonom(C a, int count, int... exp) {
		this(a, new Monom(count, exp));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((monom == null) ? 0 : monom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CMonom<C> other = (CMonom<C>) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (monom == null) {
			if (other.monom != null)
				return false;
		} else if (!monom.equals(other.monom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CMonom [a=" + a + ", monom=" + monom + "]";
	}

}
