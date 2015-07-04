package mathe.konkret.ganz;

import java.math.BigInteger;

import mathe.strukturen.Gruppe;
import mathe.strukturen.Halbgruppe;
import mathe.strukturen.Ring;

public class GanzeZahlenRing extends GanzeZahlen implements Ring<BigInteger> {
	public static GanzeZahlenRing RING = new GanzeZahlenRing();

	private GanzeZahlenRing() {
		super();
	}

	@Override
	public Halbgruppe<BigInteger> multStruktur() {
		return GanzeZahlenMultiplikativeHalbgruppe.MULTIPLIKATIV;
	}

	@Override
	public Gruppe<BigInteger> addStruktur() {
		return GanzeZahlenAdditiveGruppe.ADDITIV;
	}

	
	public BigInteger ggt(BigInteger a, BigInteger b) {
		return a.gcd(b);
	}

}
