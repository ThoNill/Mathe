package mathe.konkret.nat�rlich;

import java.math.BigInteger;

import mathe.strukturen.Halbgruppe;
import mathe.strukturen.KommutativesMagma;

public class Nat�rlicheZahlenMultiplikativeHalbgruppe extends Nat�rlicheZahlen
        implements Halbgruppe<BigInteger>, KommutativesMagma<BigInteger> {

    public static final Nat�rlicheZahlenMultiplikativeHalbgruppe MULTIPLIKATIV = new Nat�rlicheZahlenMultiplikativeHalbgruppe();

    private Nat�rlicheZahlenMultiplikativeHalbgruppe() {
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
            throw new ArithmeticException("kann " + r + " nicht durch " + re
                    + " dividieren");
        }
    }

    @Override
    public boolean hatQuotient(BigInteger r, BigInteger re) {
        return BigInteger.ZERO.equals(r.mod(re));
    }

}
