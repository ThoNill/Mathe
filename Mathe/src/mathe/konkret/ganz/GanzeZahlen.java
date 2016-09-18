package mathe.konkret.ganz;

import java.math.BigInteger;
import java.util.Iterator;

import mathe.strukturen.Menge;

public class GanzeZahlen implements Menge<BigInteger> {
  
    private static final long serialVersionUID = -2895807888359644896L;
    public static final GanzeZahlen MENGE = new GanzeZahlen();

    GanzeZahlen() {
    }

    @Override
    public Iterator<BigInteger> iterator() {
        return new GanzeZahlenIterator();
    }

    @Override
    public BigInteger getElement(String beschreibung) {
        return new BigInteger(beschreibung);
    }

    @Override
    public boolean istElement(BigInteger element) {
        return true;
    }

    @Override
    public boolean istGleich(BigInteger a, BigInteger b) {
        return a.equals(b);
    }

}
