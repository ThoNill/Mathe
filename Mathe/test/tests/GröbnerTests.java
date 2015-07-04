package tests;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

import mathe.konkret.polynome.GröbnerBasisAlgo;
import mathe.konkret.polynome.MonomHalbgruppe;
import mathe.konkret.polynome.Polynom;
import mathe.konkret.polynome.Polynomring;
import mathe.konkret.rational.RationalePolynome;
import mathe.konkret.rational.RationaleZahl;
import mathe.konkret.rational.RationaleZahlen;

public class GröbnerTests {

	public GröbnerTests() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testGleich() {
		
		MonomHalbgruppe monome = new MonomHalbgruppe("x");
		Polynomring<RationaleZahl> ring = new Polynomring<>(RationaleZahlen.Q, monome);

		List<Polynom<RationaleZahl>> basis = createPolynomList(ring," 0 1 3; 4 5 6 ");
		System.out.println(ring.toString(basis));
		GröbnerBasisAlgo<RationaleZahl> gb = new GröbnerBasisAlgo<>(ring);
		
		List<Polynom<RationaleZahl>> gröbnerBasis = gb.gröbner(basis);
		System.out.println(ring.toString(gröbnerBasis));
	}
	
	
	public List<Polynom<RationaleZahl>> createPolynomList(Polynomring<RationaleZahl> ring,String beschreibung) {
		Vector<Polynom<RationaleZahl>> polynome = new Vector<>();
		String s[] = beschreibung.split(";");
		for(String b :s ) {
			polynome.add(ring.getElement(b));
		}
		return polynome;
	}

	@Test
	public void test2() {
		RationalePolynome rp = new RationalePolynome(new MonomHalbgruppe("x","y","z"));
		
		try {
			Polynom<RationaleZahl> p1 = rp.getElement("1 x^2 + 1 y^2 + 1 z^2 + - 1");
			Polynom<RationaleZahl> p2 = rp.getElement("x^2 + z ^ 2 + - 1 y");
			Polynom<RationaleZahl> p3 = rp.getElement("x + - 1 z");
			
			Vector<Polynom<RationaleZahl>> basis = new Vector<>();
			basis.add(p1);
			basis.add(p2);
			basis.add(p3);
			GröbnerBasisAlgo<RationaleZahl> gb = new GröbnerBasisAlgo(rp);
			
			List<Polynom<RationaleZahl>> gröbnerBasis = gb.gröbner(basis);
			System.out.println(rp.toString(gröbnerBasis));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		RationalePolynome rp = new RationalePolynome(new MonomHalbgruppe("s","x","y","z"));
		
		try {
			Polynom<RationaleZahl> p1 = rp.getElement("- 2 s x + 3 x^2 + 2 y z ");
			Polynom<RationaleZahl> p2 = rp.getElement("x^2 + y^2 + z ^2 + -1");
			Polynom<RationaleZahl> p3 = rp.getElement("2 x z + - 2 y s");
			Polynom<RationaleZahl> p4 = rp.getElement("2 x y + - 2 z + - 2 z s");
			
			Vector<Polynom<RationaleZahl>> basis = new Vector<>();
			basis.add(p1);
			basis.add(p2);
			basis.add(p3);
			basis.add(p4);
			
			GröbnerBasisAlgo<RationaleZahl> gb = new GröbnerBasisAlgo(rp);
			
			List<Polynom<RationaleZahl>> gröbnerBasis = gb.gröbner(basis);
			System.out.println(rp.toString(gröbnerBasis));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
