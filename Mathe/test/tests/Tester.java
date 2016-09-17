package tests;



import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Iterator;

import mathe.grothendieck.DefaultPaarMenge;
import mathe.grothendieck.GrothendieckGruppe;
import mathe.grothendieck.GrothendieckPaar;
import mathe.grothendieck.Quotientenkörper;
import mathe.konkret.ganz.GanzeZahlenAdditiveGruppe;
import mathe.konkret.ganz.GanzeZahlenMultiplikativeHalbgruppe;
import mathe.konkret.ganz.GanzeZahlenRing;
import mathe.konkret.natürlich.NatürlicheZahlenAdditiveHalbgruppe;
import mathe.konkret.natürlich.NatürlicheZahlenMultiplikativeHalbgruppe;

import org.junit.Test;

public class Tester {

    public Tester() {
    }

    @Test
    public void natIterator() {
        NatürlicheZahlenAdditiveHalbgruppe h = NatürlicheZahlenAdditiveHalbgruppe.ADDITIV;

        Iterator<BigInteger> iter = h.elemente();

        for (int i = 0; i < 10; i++) {
            assertEquals(i,iter.next().intValue());
        }
    }

    @Test
    public void natTeilbar() {
        NatürlicheZahlenMultiplikativeHalbgruppe h = NatürlicheZahlenMultiplikativeHalbgruppe.MULTIPLIKATIV;

        BigInteger n2 = h.getElement("2");
        BigInteger n3 = h.getElement("3");
        BigInteger n4 = h.getElement("4");
        BigInteger n6 = h.getElement("6");

        assertTrue(h.hatLQuotient(n6, n3));
        assertTrue(h.hatLQuotient(n6, n2));
        assertTrue(h.hatLQuotient(n6, n6));

        assertFalse(h.hatLQuotient(n6, n4));

        assertEquals(n2, h.getLQuotient(n6, n3));
        assertEquals(n2, h.getRQuotient(n6, n3));

        assertEquals(n3, h.getLQuotient(n6, n2));
        assertEquals(n3, h.getRQuotient(n6, n2));
    }

    @Test
    public void natDifferenz() {
        NatürlicheZahlenAdditiveHalbgruppe h = NatürlicheZahlenAdditiveHalbgruppe.ADDITIV;

        BigInteger n2 = h.getElement("2");
        BigInteger n3 = h.getElement("3");
        BigInteger n4 = h.getElement("4");
        BigInteger n6 = h.getElement("6");

        assertTrue(h.hatLQuotient(n6, n3));
        assertTrue(h.hatLQuotient(n6, n2));
        assertTrue(h.hatLQuotient(n6, n4));
        assertTrue(h.hatLQuotient(n6, n6));

        assertFalse(h.hatLQuotient(n4, n6));
        assertFalse(h.hatLQuotient(n2, n4));

        assertEquals(n3, h.getLQuotient(n6, n3));
        assertEquals(n3, h.getRQuotient(n6, n3));

        assertEquals(n4, h.getLQuotient(n6, n2));
        assertEquals(n4, h.getRQuotient(n6, n2));
    }

    @Test
    public void natMultGrothendieck() {
        NatürlicheZahlenMultiplikativeHalbgruppe h = NatürlicheZahlenMultiplikativeHalbgruppe.MULTIPLIKATIV;

        DefaultPaarMenge<BigInteger> f = new DefaultPaarMenge<>();

        GrothendieckGruppe<BigInteger, GrothendieckPaar<BigInteger>> g = new GrothendieckGruppe<>(
                h, f);

        GrothendieckPaar<BigInteger> e = g.getEins();

        GrothendieckPaar<BigInteger> n3 = g.getElement("3");
        GrothendieckPaar<BigInteger> n6 = g.getElement("6");

        GrothendieckPaar<BigInteger> in6 = g.getInverse(n6);

     
        assertEquals("(1/1)",e.toString());
     
        assertEquals("(6/1)",n6.toString());
       
        assertEquals("(1/6)",in6.toString());

        GrothendieckPaar<BigInteger> e2 = g.op(n6, in6);

      
        assertEquals("(6/6)",e2.toString());
        assertEquals(true,g.istGleich(e, e2));

        GrothendieckPaar<BigInteger> n18 = g.op(n3, n6);
        GrothendieckPaar<BigInteger> n3_2 = g.op(n18, in6);

    
        assertEquals("(18/6)",n3_2.toString());
        assertEquals(true,g.istGleich(n3, n3_2));
    }

    @Test
    public void ganzIterator() {
        GanzeZahlenAdditiveGruppe h = GanzeZahlenAdditiveGruppe.ADDITIV;

        Iterator<BigInteger> iter = h.elemente();

        for (int i = 0; i < 10; i++) {
            BigInteger n = iter.next();
            int g = (i % 2 == 0) ? - (i / 2) :( i+1 ) /2;
            assertEquals(g,n.intValue());
        }
    }

    @Test
    public void ganzTeilbar() {
        GanzeZahlenMultiplikativeHalbgruppe h = GanzeZahlenMultiplikativeHalbgruppe.MULTIPLIKATIV;

        BigInteger n2 = h.getElement("2");
        BigInteger n3 = h.getElement("3");
        BigInteger n4 = h.getElement("4");
        BigInteger n6 = h.getElement("6");

        assertTrue(h.hatLQuotient(n6, n3));
        assertTrue(h.hatLQuotient(n6, n2));
        assertFalse(h.hatLQuotient(n6, n4));

        assertEquals(n2, h.getLQuotient(n6, n3));
        assertEquals(n2, h.getRQuotient(n6, n3));

        assertEquals(n3, h.getLQuotient(n6, n2));
        assertEquals(n3, h.getRQuotient(n6, n2));
    }

    @Test
    public void ganzRing() {
        GanzeZahlenRing h = GanzeZahlenRing.RING;

        BigInteger n2 = h.getElement("2");
        BigInteger n3 = h.getElement("3");
        BigInteger n4 = h.getElement("4");
        BigInteger n6 = h.getElement("6");

        assertEquals(n6, h.mult(n2, n3));
        assertEquals(n6, h.add(n2, n4));

    }

    @Test
    public void rationaleZahlen() {
        Quotientenkörper<BigInteger, GrothendieckPaar<BigInteger>> h = new Quotientenkörper<>(
                GanzeZahlenRing.RING);

        GrothendieckPaar<BigInteger> n2 = h.getElement("2");
        GrothendieckPaar<BigInteger> n3 = h.getElement("3");
        GrothendieckPaar<BigInteger> n4 = h.getElement("4");
        GrothendieckPaar<BigInteger> n6 = h.getElement("6");

        assertEquals(n6, h.mult(n2, n3));
        assertEquals(n6, h.add(n2, n4));

    }

}
