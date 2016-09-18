package mathe.konkret.rational;

import java.math.BigInteger;

import mathe.grothendieck.GrothendieckPaar;

public class RationaleZahl extends GrothendieckPaar<BigInteger> {

    private static final long serialVersionUID = -7066984774133488051L;
    private int hashCode;

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
            hashCode = calculateHashValue();
        }
    }

    private int calculateHashValue() {
        BigInteger bi = li.multiply(re);
        return bi.intValue();
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

    @Override
    public int hashCode() {
        return hashCode;
    }

}
