package mathe.konkret.polynome;

import java.util.Arrays;

public class Monom {
	int exponenten[];

	public Monom(int[] exponenten) {
		this.exponenten = exponenten;
	}

	public Monom(int count) {
		this.exponenten = new int[count];
		for (int i = 0; i < count; i++) {
			exponenten[i] = 0;
		}
	}

	public Monom(int count, int... index) {
		this.exponenten = new int[count];
		int anz = Math.min(count, index.length);
		for (int i = 0; i < anz; i++) {
			exponenten[i] = index[i];
		}
	}

	public static Monom max(Monom p, Monom q) {
		int dim = p.size();
		Monom max = new Monom(dim);
		for (int i = 0; i < dim; i++) {
			max.exponenten[i] = Math.max(p.exponenten[i], q.exponenten[i]);
		}
		return max;
	}
	

	public static Monom div(Monom p, Monom q) {
		int dim = p.size();
		Monom quotient = new Monom(dim);
		for (int i = 0; i < dim; i++) {
			quotient.exponenten[i] = p.exponenten[i] - q.exponenten[i];
		}
		return quotient;
	}

	public int size() {
		return exponenten.length;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(exponenten);
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
		Monom other = (Monom) obj;
		if (!Arrays.equals(exponenten, other.exponenten))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Monom [exponenten=" + Arrays.toString(exponenten) + "]";
	}

}
