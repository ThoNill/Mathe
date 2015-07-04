package mathe.konkret.rational;

import java.math.BigInteger;

import mathe.grothendieck.GrothendieckPaar;

public class RationaleZahl extends GrothendieckPaar<BigInteger> {

	public RationaleZahl(BigInteger li, BigInteger re) {
		super(li, re);
		BigInteger ggt = li.gcd(re);
		if (!BigInteger.ONE.equals(ggt)) {
			this.li = li.divide(ggt);
			this.re = re.divide(ggt);
			
			if (this.re.compareTo(BigInteger.ZERO) < 0) {
				this.li = this.li.negate();
				this.re = this.re.negate();
			}
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RationaleZahl other = (RationaleZahl) obj;
		
		return li.multiply(other.re).equals(re.multiply(other.li));
		
	}

}
