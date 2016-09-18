package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import mathe.konkret.polynome.GröbnerBasisAlgo;
import mathe.konkret.polynome.MonomHalbgruppe;
import mathe.konkret.polynome.Polynom;
import mathe.konkret.polynome.Polynomring;
import mathe.konkret.rational.RationalePolynome;
import mathe.konkret.rational.RationaleZahl;
import mathe.konkret.rational.RationaleZahlen;

public class GröbnerTests {
    private static final Logger LOG = Logger.getAnonymousLogger();

    public GröbnerTests() {
    }

    @Test
    public void testGleich() {

        MonomHalbgruppe monome = new MonomHalbgruppe("x");
        Polynomring<RationaleZahl> ring = new Polynomring<>(RationaleZahlen.Q,
                monome);

        List<Polynom<RationaleZahl>> basis = createPolynomList(ring,
                " 0 1 3; 4 5 6 ");
        LOG.info(ring.toString(basis));
        assertEquals("p[0]=3 x^2 + x \np[1]=6 x^2 + 5 x + 4 \n", ring.toString(basis));
        GröbnerBasisAlgo<RationaleZahl> gb = new GröbnerBasisAlgo<>(ring);

        List<Polynom<RationaleZahl>> gröbnerBasis = gb.gröbner(basis);
        

        LOG.info(ring.toString(gröbnerBasis));
        assertEquals("p[0]=1 \n",ring.toString(gröbnerBasis));
    }

    public List<Polynom<RationaleZahl>> createPolynomList(
            Polynomring<RationaleZahl> ring, String beschreibung) {
        List<Polynom<RationaleZahl>> polynome = new ArrayList<>();
        String s[] = beschreibung.split(";");
        for (String b : s) {
            polynome.add(ring.getElement(b));
        }
        return polynome;
    }

    @Test
    public void test2() {
        RationalePolynome rp = new RationalePolynome(new MonomHalbgruppe("x",
                "y", "z"));

        try {
            List<Polynom<RationaleZahl>> basis = new ArrayList<>();
 
            Polynom<RationaleZahl> p1 = rp
                    .getElement("1 x^2 + 1 y^2 + 1 z^2 + - 1");
            basis.add(p1);

            Polynom<RationaleZahl> p2 = rp.getElement("x^2 + z ^ 2 + - 1 y");
            basis.add(p2);

            Polynom<RationaleZahl> p3 = rp.getElement("x + - 1 z");
            basis.add(p3);

            GröbnerBasisAlgo<RationaleZahl> gb = new GröbnerBasisAlgo(rp);

            List<Polynom<RationaleZahl>> gröbnerBasis = gb.gröbner(basis);
            LOG.info(rp.toString(gröbnerBasis));
            String expected = "p[0]=x  - z \np[1]=y  - 2 z^2 \np[2]=z^4 + 1/2 z^2  - 1/4 \n";
            assertEquals(expected,rp.toString(gröbnerBasis));
        } catch (Exception e) {
            LOG.log(Level.SEVERE,"Ausnahme im Test",e);
            fail("Exception aufgetreten ");
        }
    }

    @Test
    public void test3() {
        RationalePolynome rp = new RationalePolynome(new MonomHalbgruppe("s",
                "x", "y", "z"));

        try {
            List<Polynom<RationaleZahl>> basis = new ArrayList<>();

            Polynom<RationaleZahl> p1 = rp
                    .getElement("- 2 s x + 3 x^2 + 2 y z ");
            basis.add(p1);

            Polynom<RationaleZahl> p2 = rp.getElement("x^2 + y^2 + z ^2 + -1");
            basis.add(p2);
 
            Polynom<RationaleZahl> p3 = rp.getElement("2 x z + - 2 y s");
            basis.add(p3);

            Polynom<RationaleZahl> p4 = rp
                    .getElement("2 x y + - 2 z + - 2 z s");

            basis.add(p4);

            GröbnerBasisAlgo<RationaleZahl> gb = new GröbnerBasisAlgo(rp);

            List<Polynom<RationaleZahl>> gröbnerBasis = gb.gröbner(basis);
            LOG.info(rp.toString(gröbnerBasis));
            String expected = "p[0]=s  - 3/2 x  - 3/2 y z  - 167616/3835 z^6 + 36717/590 z^4  - 134419/7670 z^2 \np[1]=x^2 + y^2 + z^2  - 1 \np[2]=x y  - 19584/3835 z^5 + 1999/295 z^3  - 6403/3835 z \np[3]=x z + y z^2  - 1152/3835 z^5  - 108/295 z^3 + 2556/3835 z \np[4]=y^3 + y z^2  - y  - 9216/3835 z^5 + 906/295 z^3  - 2562/3835 z \np[5]=y^2 z  - 6912/3835 z^5 + 827/295 z^3  - 3839/3835 z \np[6]=y z^3  - y z  - 576/59 z^6 + 1605/118 z^4  - 453/118 z^2 \np[7]=z^7  - 1763/1152 z^5 + 655/1152 z^3  - 11/288 z \n";
            assertEquals(expected,rp.toString(gröbnerBasis));
        } catch (Exception e) {
            LOG.log(Level.SEVERE,"Ausnahme im Test",e);
            fail("Exception aufgetreten ");
        }
    }

}
