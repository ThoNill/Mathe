package tests;

import java.io.ByteArrayInputStream;

import java.io.IOException;
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

	public PolyRead() {
	}

	protected void teste(String expected,String text) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(text.getBytes());
			ANTLRInputStream input;
			input = new ANTLRInputStream(in);
			PolynomReaderLexer lexer = new PolynomReaderLexer(input);
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);

			PolynomReaderParser parser = new PolynomReaderParser(tokenStream);

	
			PolynomReadListener listener = new PolynomReadListener();
			
			parser.addParseListener(listener);
			
			System.out.println("Parsen ...");
			
			parser.polynom();
			
			Polynom<RationaleZahl> polynom = listener.getPolynom();
			
			Polynomring<RationaleZahl> ring = new Polynomring<>(RationaleZahlen.Q, MonomHalbgruppe.P2Z);
		
			System.out.println("Eingabe= " + text.trim());
			System.out.println("Polynom= " + ring.toString(polynom));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void teste() {
		teste(""," 1");
		teste(""," - 1 ");
		teste(""," x y z");
		teste(""," - 2/3 x y z");
		teste(""," - 1 y z");
		teste(""," - 1 x y z + 2");
		teste(""," - 1 x y z - 3 x - 2");
		teste(""," x^2 + 3/4 z^4");
		teste("","1 x^2 + 1 y^2 + 1 z^2 + - 1");
		teste("","x y + x x x + y^3 x y");
		teste("","x x x + y^3 x y");
		
	}
}
