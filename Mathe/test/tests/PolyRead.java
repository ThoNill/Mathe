package tests;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import mathe.konkret.polynome.MonomHalbgruppe;
import mathe.konkret.polynome.Polynom;
import mathe.konkret.polynome.Polynomring;
import mathe.konkret.rational.PolynomReadListener;
import mathe.konkret.rational.RationaleZahl;
import mathe.konkret.rational.RationaleZahlen;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import polynom.PolynomReaderLexer;
import polynom.PolynomReaderParser;

public class PolyRead {
    private static final Logger LOG = Logger.getAnonymousLogger();
            

    public PolyRead() {
    }

    protected void teste(String expected, String text) {
        try {
            LOG.info("Eingabe= " + text.trim());
            ByteArrayInputStream in = new ByteArrayInputStream(text.getBytes());
            ANTLRInputStream input;
            input = new ANTLRInputStream(in);
            PolynomReaderLexer lexer = new PolynomReaderLexer(input);
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            PolynomReaderParser parser = new PolynomReaderParser(tokenStream);

            PolynomReadListener listener = new PolynomReadListener();

            parser.addParseListener(listener);

            LOG.info("Parsen ...");

            parser.polynom();

            Polynom<RationaleZahl> polynom = listener.getPolynom();

            Polynomring<RationaleZahl> ring = new Polynomring<>(
                    RationaleZahlen.Q, MonomHalbgruppe.P2Z);

            LOG.info("Polynom= " + ring.toString(polynom));
            assertEquals(expected,ring.toString(polynom));
        } catch (IOException e) {
            LOG.log(Level.SEVERE,"Ausnahme im Test",e);
            fail("Exception aufgetreten ");
        }
    }

    @Test
    public void teste() {
        teste("1 ", " 1");
        teste("-1 ", " - 1 ");
        teste("x y z ", " x y z");
        teste("-2/3 x y z ", " - 2/3 x y z");
        teste("-y z ", " - 1 y z");
        teste("-x y z + 2 ", " - 1 x y z + 2");
        teste("-x y z  - 3 x  - 2 ", " - 1 x y z - 3 x - 2");
        teste("x^2 + 3/4 z^4 ", " x^2 + 3/4 z^4");
        teste("x^2 + y^2 + z^2  - 1 ", "1 x^2 + 1 y^2 + 1 z^2 + - 1");
        teste("x^3 + x y^4 + x y ", "x y + x x x + y^3 x y");
        teste("x^3 + x y^4 ", "x x x + y^3 x y");

    }
}
