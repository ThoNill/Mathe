package mathe.konkret.natürlich;

import java.math.BigInteger;
import java.util.Iterator;

import mathe.strukturen.Menge;

public class NatürlicheZahlen implements Menge<BigInteger> {
    public static final NatürlicheZahlen MENGE = new NatürlicheZahlen();

    NatürlicheZahlen() {
        super();
    }

    @Override
    public Iterator<BigInteger> iterator() {
        return new NatürlicheZahlenIterator();
    }

    @Override
    public BigInteger getElement(String beschreibung) {
        return new BigInteger(beschreibung);
    }

    @Override
    public boolean istElement(BigInteger element) {
        return element.compareTo(BigInteger.ZERO) >= 0;
    }

    @Override
    public boolean istGleich(BigInteger a, BigInteger b) {
        return a.equals(b);
    }

}
