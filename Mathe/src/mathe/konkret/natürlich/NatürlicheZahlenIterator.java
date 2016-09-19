package mathe.konkret.nat�rlich;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Nat�rlicheZahlenIterator implements Iterator<BigInteger> {
    private BigInteger aktuell = null;
    private int grenze = 0;
    
    public Nat�rlicheZahlenIterator() {
        this(0);
    }

    public Nat�rlicheZahlenIterator(int grenze) {
        super();
        this.grenze = grenze;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public BigInteger next() {
        aktuell = (aktuell == null) ? BigInteger.ZERO : aktuell
                .add(BigInteger.ONE);
        if (grenze > 0 && aktuell.intValue() >grenze) {
            throw new NoSuchElementException("Grenzwert ueberschritten");
        }
        return aktuell;
    }

}
