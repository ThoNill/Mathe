package mathe.main;

import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import mathe.konkret.polynome.GröbnerBasisAlgo;
import mathe.konkret.polynome.MonomHalbgruppe;
import mathe.konkret.polynome.Polynom;
import mathe.konkret.rational.RationalePolynome;
import mathe.konkret.rational.RationaleZahl;

public class Main {
    public static final Logger LOG = Logger.getAnonymousLogger();

    public static final void main(String args[]) {
        test2();
        test3();
    }

    public static final void test2() {
        RationalePolynome rp = new RationalePolynome(new MonomHalbgruppe("x",
                "y", "z"));

        try {
            Polynom<RationaleZahl> p1 = rp
                    .getElement("1 x^2 + 1 y^2 + 1 z^2 + - 1");
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
            LOG.log(Level.SEVERE,"Ausnahme im Test2 ",e);
        }
    }

    public static final void test3() {
        RationalePolynome rp = new RationalePolynome(new MonomHalbgruppe("s",
                "x", "y", "z"));

        try {
            Polynom<RationaleZahl> p1 = rp
                    .getElement("- 2 s x + 3 x^2 + 2 y z ");
            Polynom<RationaleZahl> p2 = rp.getElement("x^2 + y^2 + z ^2 + -1");
            Polynom<RationaleZahl> p3 = rp.getElement("2 x z + - 2 y s");
            Polynom<RationaleZahl> p4 = rp
                    .getElement("2 x y + - 2 z + - 2 z s");

            Vector<Polynom<RationaleZahl>> basis = new Vector<>();
            basis.add(p1);
            basis.add(p2);
            basis.add(p3);
            basis.add(p4);

            GröbnerBasisAlgo<RationaleZahl> gb = new GröbnerBasisAlgo(rp);

            List<Polynom<RationaleZahl>> gröbnerBasis = gb.gröbner(basis);
            System.out.println(rp.toString(gröbnerBasis));
        } catch (Exception e) {
            LOG.log(Level.SEVERE,"Ausnahme im Test3 ",e);
        }
    }
}
