package mathe.konkret.nat�rlich;

import java.math.BigInteger;
import java.util.Iterator;

public class Nat�rlicheZahlenIterator implements Iterator<BigInteger> {
	private BigInteger aktuell = null;

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public BigInteger next() {
		aktuell = (aktuell == null) ? BigInteger.ZERO : aktuell
				.add(BigInteger.ONE);
		return aktuell;
	}

}
