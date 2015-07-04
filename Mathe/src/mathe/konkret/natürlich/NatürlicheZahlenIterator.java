package mathe.konkret.natürlich;

import java.math.BigInteger;
import java.util.Iterator;

public class NatürlicheZahlenIterator implements Iterator<BigInteger> {
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
