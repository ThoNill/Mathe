package tests;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.logging.Logger;

import org.junit.Test;

import mathe.konkret.ganz.GanzeZahlenRing;
import mathe.konkret.polynome.DivMitRest;
import mathe.konkret.polynome.MonomHalbgruppe;
import mathe.konkret.polynome.Polynom;
import mathe.konkret.polynome.Polynome;
import mathe.konkret.polynome.Polynomring;

public class PolynomeTests {
    public static final Logger LOG = Logger.getAnonymousLogger();

    public PolynomeTests() {
    }

    @Test
    public void testGleich() {
        GanzeZahlenRing ganzeZahlen = GanzeZahlenRing.RING;
        MonomHalbgruppe monome = new MonomHalbgruppe("x");

        Polynome<BigInteger> p = new Polynome<>(ganzeZahlen, monome);
        Polynom<BigInteger> p1 = p.getElement(
                p.getMonom(new BigInteger("3"), 1),
                p.getMonom(new BigInteger("2"), 2),
                p.getMonom(new BigInteger("3"), 0));

        Polynom<BigInteger> p2 = p.getElement(" 3 3 2");

        assertTrue(p.istGleich(p1, p2));
    }

    @Test
    public void testUnGleich() {
        GanzeZahlenRing ganzeZahlen = GanzeZahlenRing.RING;
        MonomHalbgruppe monome = new MonomHalbgruppe("x");

        Polynome<BigInteger> p = new Polynome<>(ganzeZahlen, monome);
        Polynom<BigInteger> p1 = p.getElement(
                p.getMonom(new BigInteger("3"), 5),
                p.getMonom(new BigInteger("2"), 2),
                p.getMonom(new BigInteger("3"), 0));

        Polynom<BigInteger> p2 = p.getElement(
                p.getMonom(new BigInteger("2"), 2),
                p.getMonom(new BigInteger("3"), 1),
                p.getMonom(new BigInteger("3"), 0));

        assertFalse(p.istGleich(p1, p2));
    }

    @Test
    public void testMult() {
        GanzeZahlenRing ganzeZahlen = GanzeZahlenRing.RING;
        MonomHalbgruppe monome = new MonomHalbgruppe("x");

        Polynomring<BigInteger> p = new Polynomring<>(ganzeZahlen, monome);
        Polynom<BigInteger> p1 = p.getElement(
                p.getMonom(new BigInteger("1"), 1),
                p.getMonom(new BigInteger("1"), 0));

        Polynom<BigInteger> p2 = p.getElement(
                p.getMonom(new BigInteger("1"), 1),
                p.getMonom(new BigInteger("-1"), 0));

        Polynom<BigInteger> p1m2 = p.mult(p1, p2);

        Polynom<BigInteger> soll = p.getElement(
                p.getMonom(new BigInteger("1"), 2),
                p.getMonom(new BigInteger("-1"), 0));

        assertTrue(p.istGleich(soll, p1m2));

        DivMitRest<BigInteger> div = p.teile(p1m2, p2);

        assertTrue(p.istGleich(div.div, p1));
    }

    public void testMult(Polynomring<BigInteger> p, Polynom<BigInteger> p1,
            Polynom<BigInteger> p2) {
        LOG.info("p1=" + p.toString(p1));
        LOG.info("p1=" + p.toString(p2));
        Polynom<BigInteger> produkt = p.mult(p1, p2);
        LOG.info("produkt=" + p.toString(produkt));
        DivMitRest<BigInteger> div1 = p.teile(produkt, p2);
        DivMitRest<BigInteger> div2 = p.teile(produkt, p1);
        LOG.info("div1=" + p.toString(div1.div));
        LOG.info("div2=" + p.toString(div2.div));
        assertTrue(p.istGleich(div1.div, p1));
        assertTrue(p.istGleich(div2.div, p2));
    }

    public void testMult(Polynomring<BigInteger> p, String beschreibung1,
            String beschreibung2) {
        testMult(p, p.getElement(beschreibung1), p.getElement(beschreibung2));
    }

    @Test
    public void testMultDiv() {
        GanzeZahlenRing ganzeZahlen = GanzeZahlenRing.RING;
        MonomHalbgruppe monome = new MonomHalbgruppe("x");

        Polynomring<BigInteger> p = new Polynomring<>(ganzeZahlen, monome);

        testMult(p, "2 3 1", "4 5 7");
        testMult(p, "2 -3 1", "4 5 7");
        testMult(p, "2 0 1", "0 5 7");
    }

}
