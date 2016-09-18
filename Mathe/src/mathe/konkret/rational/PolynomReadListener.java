package mathe.konkret.rational;

import java.math.BigInteger;
import java.util.List;

import mathe.konkret.polynome.CMonom;
import mathe.konkret.polynome.Monom;
import mathe.konkret.polynome.MonomHalbgruppe;
import mathe.konkret.polynome.Polynom;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;

import polynom.PolynomReaderBaseListener;
import polynom.PolynomReaderParser;
import polynom.PolynomReaderParser.KopfContext;
import polynom.PolynomReaderParser.PotenzContext;
import polynom.PolynomReaderParser.ReiheContext;
import polynom.PolynomReaderParser.VorzeichenContext;

public class PolynomReadListener extends PolynomReaderBaseListener {

    private MonomHalbgruppe monomMenge = null;
    private Polynom<RationaleZahl> polynom;

    public PolynomReadListener() {
        this(MonomHalbgruppe.P2Z);
    }

    public PolynomReadListener(MonomHalbgruppe monomMenge) {
        super();
        this.monomMenge = monomMenge;
    }

    public Polynom<RationaleZahl> getPolynom() {
        return polynom;
    }

    @Override
    public void enterPolynom(@NotNull PolynomReaderParser.PolynomContext ctx) {
        polynom = new Polynom<RationaleZahl>();
    }

    @Override
    public void exitPolynom(@NotNull PolynomReaderParser.PolynomContext ctx) {
        bearbeiteKopf(ctx.kopf());
        for (ReiheContext r : ctx.reihe()) {
            bearbeiteReihe(r);
        }
    }

    private void bearbeiteReihe(ReiheContext ctx) {
        int vorz = vorzeichen(ctx.vorzeichen());
        bearbeiteCMonom(vorz, ctx.cmonom());

    }

    private void bearbeiteKopf(KopfContext ctx) {
        int vorz = vorzeichen(ctx.vorzeichen());
        bearbeiteCMonom(vorz, ctx.cmonom());
    }

    private int vorzeichen(VorzeichenContext vorzeichen) {
        int v = 1;
        if (vorzeichen != null) {
            String t = vorzeichen.getText();
            if ("-".equals(t) || "+-".equals(t)) {
                return -1;
            }
        }
        return v;
    }

    protected void bearbeiteCMonom(int vorz,
            PolynomReaderParser.CmonomContext ctx) {
        RationaleZahl koeffizient = bestimmeDenKoeffizienten(vorz, ctx);

        Monom monom = bestimmeDasMonom(ctx);

        CMonom<RationaleZahl> cmonom = new CMonom<RationaleZahl>(koeffizient,
                monom);

        polynom.addElement(cmonom);
    }

    private RationaleZahl bestimmeDenKoeffizienten(int vvorz,
            PolynomReaderParser.CmonomContext ctx) {
        String szaehler = null;
        String snenner = null;

        if (ctx.koeffizient() != null) {
            szaehler = getText(ctx.koeffizient().INT(0));
            snenner = getText(ctx.koeffizient().INT(1));
        }

        String svorz = (vvorz > 0) ? "" : "-";

        if (szaehler == null) {
            szaehler = "1";
        }

        if (snenner == null) {
            snenner = "1";
        }

        return RationaleZahlen.Q.neuesPaar(new BigInteger(
                svorz + szaehler), new BigInteger(snenner));
    }

    private String getText(TerminalNode node) {
        if (node != null) {
            return node.getText();
        }
        return null;

    }

    private String getText(ParserRuleContext vorz) {
        if (vorz != null) {
            return vorz.getText();
        }
        return "";
    }

    private Monom bestimmeDasMonom(PolynomReaderParser.CmonomContext ctx) {
        int[] exponenten = new int[monomMenge.getSize()];

        if (ctx.monom() != null) {
            List<PotenzContext> l = ctx.monom().potenz();
            for (PotenzContext pc : l) {
                exponent(exponenten, monomMenge, pc);
            }
        }
        return MonomHalbgruppe.P2Z.getElement(exponenten);
    }

    private void exponent(int[] value, MonomHalbgruppe monomMenge,
            PotenzContext pc) {

        if (pc != null && pc.VAR() != null) {
            int index = monomMenge.getIndex(pc.VAR().getText());
            if (pc.INT() != null) {
                value[index] += Integer.parseInt(pc.INT().getText());

            } else {
                value[index] += 1;
            }
        }
    }

}
