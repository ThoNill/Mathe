package mathe.konkret.rational;

import java.math.BigInteger;

import mathe.grothendieck.Quotientenkörper;
import mathe.konkret.ganz.GanzeZahlenRing;

public class RationaleZahlen extends
        Quotientenkörper<BigInteger, RationaleZahl> {
   
    private static final long serialVersionUID = 2511090182054801282L;
    public static final RationaleZahlen Q = new RationaleZahlen();

    private RationaleZahlen() {
        super(GanzeZahlenRing.RING);
    }

    @Override
    public RationaleZahl neuesPaar(BigInteger li, BigInteger re) {
        return new RationaleZahl(li, re);
    }

    @Override
    public String toString(RationaleZahl r) {
        if (r.re.compareTo(BigInteger.ZERO) < 0) {
            r.li = r.li.negate();
            r.re = r.re.negate();
        }
        if (BigInteger.ONE.equals(r.re)) {
            return r.li.toString();
        }
        return r.li.toString() + "/" + r.re.toString();
    }

}
