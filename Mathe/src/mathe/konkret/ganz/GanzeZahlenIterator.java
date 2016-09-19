package mathe.konkret.ganz;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class GanzeZahlenIterator implements Iterator<BigInteger> {
    private BigInteger aktuell = null;
    private boolean positiv = true;
    private int grenze = 0;
    
    public GanzeZahlenIterator() {
        this(0);
    }

    public GanzeZahlenIterator(int grenze) {
        super();
        this.grenze = grenze;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public BigInteger next() {
        if (aktuell == null) {
            aktuell = BigInteger.ZERO;
            positiv = true;
            return aktuell;
        } else {
            if (positiv) {
                aktuell = aktuell.add(BigInteger.ONE);
                positiv = false;
                if (grenze > 0 && aktuell.intValue() >grenze) {
                    throw new NoSuchElementException("Grenzwert ueberschritten");
                }
                return aktuell;
            } else {
                positiv = true;
                return aktuell.negate();
            }

        }
    }

}
