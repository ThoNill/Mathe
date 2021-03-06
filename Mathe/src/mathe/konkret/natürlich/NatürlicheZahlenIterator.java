package mathe.konkret.natürlich;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class NatürlicheZahlenIterator implements Iterator<BigInteger> {
    private BigInteger aktuell = null;
    private int grenze = 0;
    
    public NatürlicheZahlenIterator() {
        this(0);
    }

    public NatürlicheZahlenIterator(int grenze) {
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
