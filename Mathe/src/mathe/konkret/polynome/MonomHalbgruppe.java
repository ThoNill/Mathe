package mathe.konkret.polynome;

import java.io.Serializable;
import java.util.Iterator;

import javax.management.RuntimeErrorException;

import mathe.strukturen.Halbgruppe;
import mathe.strukturen.KommutativesMagma;

public class MonomHalbgruppe implements Halbgruppe<Monom>,
        KommutativesMagma<Monom>, Serializable {
    private static final long serialVersionUID = -538130000480045952L;

    public static final MonomHalbgruppe P2Z = new MonomHalbgruppe("p", "q",
            "r", "s", "t", "u", "v", "w", "x", "y", "z");
    
    String[] bezeichner;

    private final Monom eins;


    public MonomHalbgruppe(String... bezeichner) {
        this.bezeichner = bezeichner;
        eins = new Monom(bezeichner.length);
    }

    public int getIndex(String key) {
        for (int i = 0; i < bezeichner.length; i++) {
            if (key.equals(bezeichner[i])) {
                return i;
            }
        }
        throw new RuntimeErrorException(null, "Der Bezeichner " + key
                + " wurde nicht gefunden");
    }

    @Override
    public Monom getEins() {
        return eins;
    }

    @Override
    public Monom op(Monom l, Monom r) {
        int[] summe = new int[bezeichner.length];
        for (int i = 0; i < bezeichner.length; i++) {
            summe[i] = l.exponenten[i] + r.exponenten[i];
        }
        return new Monom(summe);
    }

    @Override
    public Monom getElement(String beschreibung) {
        String[] werte = beschreibung.split(" +");
        int[]  m= new int[bezeichner.length];
        for (int i = 0; i < werte.length; i++) {
            m[i] = Integer.parseInt(werte[i]);
        }
        return new Monom(m);
    }

    @Override
    public boolean istElement(Monom element) {
        return element.exponenten.length == bezeichner.length;
    }

    @Override
    public boolean istGleich(Monom a, Monom b) {
        if (a.exponenten.length != b.exponenten.length) {
            return false;
        }
        for (int i = 0; i < bezeichner.length; i++) {
            if (a.exponenten[i] != b.exponenten[i])
                return false;
        }
        return true;
    }

    @Override
    public Iterator<Monom> iterator() {
        return null;
    }

    @Override
    public boolean hatQuotient(Monom l, Monom r) {
        for (int i = 0; i < bezeichner.length; i++) {
            if (l.exponenten[i] < r.exponenten[i])
                return false;
        }
        return true;
    }

    @Override
    public Monom getQuotient(Monom l, Monom r) {
        return Monom.div(l, r);
    }

    public int getSize() {
        return bezeichner.length;
    }

    @Override
    public String toString(Monom m) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < bezeichner.length; i++) {
            if (m.exponenten[i] > 0) {
                buffer.append(bezeichner[i]);
                if (m.exponenten[i] > 1) {
                    buffer.append("^");
                    buffer.append(m.exponenten[i]);
                }
                buffer.append(" ");
            }

        }
        return buffer.toString();
    }

    public Monom getElement(int[] exponenten) {
        return new Monom(exponenten);
    }

}
