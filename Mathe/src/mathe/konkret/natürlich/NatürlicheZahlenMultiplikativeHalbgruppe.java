package mathe.konkret.natürlich;

import java.math.BigInteger;

import mathe.strukturen.Halbgruppe;
import mathe.strukturen.KommutativesMagma;

public class NatürlicheZahlenMultiplikativeHalbgruppe extends NatürlicheZahlen
        implements Halbgruppe<BigInteger>, KommutativesMagma<BigInteger> {

    private static final long serialVersionUID = -2999399642471608750L;
    public static final NatürlicheZahlenMultiplikativeHalbgruppe MULTIPLIKATIV = new NatürlicheZahlenMultiplikativeHalbgruppe();

    private NatürlicheZahlenMultiplikativeHalbgruppe() {
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
