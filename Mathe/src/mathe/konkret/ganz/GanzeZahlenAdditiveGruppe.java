package mathe.konkret.ganz;

import java.math.BigInteger;

import mathe.strukturen.Gruppe;

public class GanzeZahlenAdditiveGruppe extends GanzeZahlen implements
        Gruppe<BigInteger> {

    public static final GanzeZahlenAdditiveGruppe ADDITIV = new GanzeZahlenAdditiveGruppe();

    private GanzeZahlenAdditiveGruppe() {
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
    public BigInteger getInverse(BigInteger value) {
        return value.negate();
    }

    public boolean hatInverse(BigInteger element) {
        return true;
    }

    public BigInteger getQuotient(BigInteger r, BigInteger re) {
        return r.subtract(re);
    }

    public boolean hatQuotient(BigInteger r, BigInteger re) {
        return true;
    }

}
