package mathe.konkret.rational;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import mathe.konkret.polynome.MonomHalbgruppe;
import mathe.konkret.polynome.Polynom;
import mathe.konkret.polynome.Polynomring;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import polynom.PolynomReaderLexer;
import polynom.PolynomReaderParser;

public class RationalePolynome extends Polynomring<RationaleZahl> implements
        ANTLRErrorListener {
   
    private static final long serialVersionUID = 1245028469516612006L;

    private static final String FEHLER = "Fehler";

    private static final Logger LOG = Logger.getLogger(RationalePolynome.class
            .getName());

    private MonomHalbgruppe monomBereich;
    private List<String> parserErrors;

    public RationalePolynome(MonomHalbgruppe monomBereich) {
        super(RationaleZahlen.Q, monomBereich);
        this.monomBereich = monomBereich;
        parserErrors = new Vector<>();
    }

    @Override
    public Polynom<RationaleZahl> getElement(String text) {
        try {

            ByteArrayInputStream in = new ByteArrayInputStream(text.getBytes());
            ANTLRInputStream input;
            input = new ANTLRInputStream(in);
            PolynomReaderLexer lexer = new PolynomReaderLexer(input);
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            PolynomReaderParser parser = new PolynomReaderParser(tokenStream);

            parser.addErrorListener(this);

            PolynomReadListener listener = new PolynomReadListener(monomBereich);

            parser.addParseListener(listener);

            parser.polynom();

            return listener.getPolynom();
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Ausnahme im getElement ", e);
        }

        return null;
    }

    public List<String> getErrors() {
        return parserErrors;
    }

    public void clearErrors() {
        parserErrors.clear();
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
            Object offendingSymbol, int line, int charPositionInLine,
            String msg, RecognitionException e) {
        parserErrors.add("Fehler in Position " + charPositionInLine);
    }

    @Override
    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex,
            int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
        parserErrors.add(FEHLER);

    }

    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa,
            int startIndex, int stopIndex, BitSet conflictingAlts,
            ATNConfigSet configs) {
        parserErrors.add(FEHLER);
    }

    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa,
            int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
        parserErrors.add(FEHLER);
    }

}
