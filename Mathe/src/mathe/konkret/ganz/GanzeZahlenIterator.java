package mathe.konkret.ganz;

import java.math.BigInteger;
import java.util.Iterator;

public class GanzeZahlenIterator implements Iterator<BigInteger> {
	private BigInteger aktuell = null;
	private boolean positiv = true;

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public BigInteger next() {
		if (aktuell == null) {
			aktuell = BigInteger.ZERO;
			positiv = true;
			return aktuell;
		} else {
			if (positiv) {
				aktuell = aktuell.add(BigInteger.ONE);
				positiv = false;
				return aktuell;
			} else {
				positiv = true;
				return aktuell.negate();
			}

		}
	}

}
