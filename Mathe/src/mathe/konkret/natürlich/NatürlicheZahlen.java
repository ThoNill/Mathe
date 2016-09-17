package mathe.konkret.nat�rlich;

import java.math.BigInteger;
import java.util.Iterator;

import mathe.strukturen.Menge;

public class Nat�rlicheZahlen implements Menge<BigInteger> {
    public static final Nat�rlicheZahlen MENGE = new Nat�rlicheZahlen();

    Nat�rlicheZahlen() {
        super();
    }

    @Override
    public Iterator<BigInteger> iterator() {
        return new Nat�rlicheZahlenIterator();
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
