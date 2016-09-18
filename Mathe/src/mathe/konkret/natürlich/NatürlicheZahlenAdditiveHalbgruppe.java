package mathe.konkret.nat�rlich;

import java.math.BigInteger;

import mathe.strukturen.Halbgruppe;
import mathe.strukturen.KommutativesMagma;

public class Nat�rlicheZahlenAdditiveHalbgruppe extends Nat�rlicheZahlen
        implements Halbgruppe<BigInteger>, KommutativesMagma<BigInteger> {

    public static final Nat�rlicheZahlenAdditiveHalbgruppe ADDITIV = new Nat�rlicheZahlenAdditiveHalbgruppe();

    private Nat�rlicheZahlenAdditiveHalbgruppe() {
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
            throw new ArithmeticException("kann " + re + " nicht von " + r
                    + " abziehen");
        }
    }

    @Override
    public boolean hatQuotient(BigInteger r, BigInteger re) {
        return r.compareTo(re) >= 0;
    }

}
