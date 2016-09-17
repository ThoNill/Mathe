package mathe.konkret.natürlich;

import java.math.BigInteger;

import mathe.strukturen.Halbgruppe;
import mathe.strukturen.KommutativesMagma;

public class NatürlicheZahlenAdditiveHalbgruppe extends NatürlicheZahlen
        implements Halbgruppe<BigInteger>, KommutativesMagma<BigInteger> {

    public static final NatürlicheZahlenAdditiveHalbgruppe ADDITIV = new NatürlicheZahlenAdditiveHalbgruppe();

    private NatürlicheZahlenAdditiveHalbgruppe() {
        super();
    }

    @Override
    public BigInteger getEins() {
        return BigInteger.ZERO;
    }

    @Override
    public BigInteger op(BigInteger l, BigInteger r) {
        return l.add(r);
    }

    @Override
    public BigInteger getQuotient(BigInteger r, BigInteger re) {
        if (hatQuotient(r, re)) {
            return r.subtract(re);
        } else {
            throw new RuntimeException("kann " + re + " nicht von " + r
                    + " abziehen");
        }
    }

    @Override
    public boolean hatQuotient(BigInteger r, BigInteger re) {
        return r.compareTo(re) >= 0;
    }

}
