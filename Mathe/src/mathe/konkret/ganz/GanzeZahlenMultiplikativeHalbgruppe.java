package mathe.konkret.ganz;

import java.math.BigInteger;

import mathe.strukturen.Halbgruppe;
import mathe.strukturen.KommutativesMagma;

public class GanzeZahlenMultiplikativeHalbgruppe extends GanzeZahlen implements
		Halbgruppe<BigInteger>, KommutativesMagma<BigInteger> {

	public static GanzeZahlenMultiplikativeHalbgruppe MULTIPLIKATIV = new GanzeZahlenMultiplikativeHalbgruppe();

	private GanzeZahlenMultiplikativeHalbgruppe() {
		super();
	}

	@Override
	public BigInteger getEins() {
		return BigInteger.ONE;
	}

	@Override
	public BigInteger op(BigInteger l, BigInteger r) {
		return l.multiply(r);
	}

	@Override
	public BigInteger getQuotient(BigInteger r, BigInteger re) {
		if (hatQuotient(r, re)) {
			return r.divide(re);
		} else {
			throw new RuntimeException("kann " + r + " nicht durch " + re
					+ " dividieren");
		}
	}

	@Override
	public boolean hatQuotient(BigInteger r, BigInteger re) {
		return BigInteger.ZERO.equals(r.mod(re));
	}

}
