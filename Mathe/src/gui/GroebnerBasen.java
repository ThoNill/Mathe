package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import mathe.konkret.polynome.GröbnerBasisAlgo;
import mathe.konkret.polynome.MonomHalbgruppe;
import mathe.konkret.polynome.Polynom;
import mathe.konkret.rational.RationalePolynome;
import mathe.konkret.rational.RationaleZahl;

/**
 * Simulationsprogramm für Herstellerbuchungen
 * 
 * @author Thomas Nill
 * 
 */
public class GroebnerBasen extends JFrame {
	private static final long serialVersionUID = -4886328326638275741L;
	RationalePolynome rp = new RationalePolynome(new MonomHalbgruppe("p", "q",
			"r", "s", "t", "u", "v", "w", "x", "y", "z"));
	JTextArea eingabe;
	JTextArea ausgabe;
	JButton berechnen;
	JButton beispiel1;
	JButton beispiel2;

	public static void main(String args[]) {
		new GroebnerBasen();
	}

	public GroebnerBasen() {
		super();
		setTitle("Gröbner Basen");
		eingabe = new JTextArea();
		eingabe.setToolTipText("Bitte eine durch ; getrennte Liste aus Plynomen eingeben");
		ausgabe = new JTextArea();
		ausgabe.setToolTipText("Die berechnete Gröbner Basis");
		ausgabe.setEditable(false);
		

		berechnen = new JButton("Gröbner Basis berechnen");
		berechnen.addActionListener(e -> {
			berechnen();
		});
		beispiel1 = new JButton("Beispiel 1");
		beispiel1.addActionListener(e -> {
			beispiel1();
		});
		beispiel2 = new JButton("Beispiel 2");
		beispiel2.addActionListener(e -> {
			beispiel2();
		});

		JPanel buttonLeiste = new JPanel(new FlowLayout());
		buttonLeiste.add(beispiel1);
		buttonLeiste.add(beispiel2);
		buttonLeiste.add(berechnen);


		Container pane = this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setLayout(new GridLayout(2, 1));

		JPanel oben = new JPanel(new BorderLayout());
		oben.add(new JLabel("Basis eines Ideals:"),BorderLayout.NORTH);
		oben.add(eingabe, BorderLayout.CENTER);
		oben.add(buttonLeiste, BorderLayout.SOUTH);
		
		JPanel unten = new JPanel(new BorderLayout());
		unten.add(new JLabel("Berechnete Gröbner-Basis des Ideals:"),BorderLayout.NORTH);
		unten.add(ausgabe, BorderLayout.CENTER);
		

		pane.add(oben);
		pane.add(unten);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dim.width /= 2;
		dim.height /= 2;
		this.setBounds( dim.width/2, dim.height/2, dim.width, dim.height);
		this.setVisible(true);
	}

	private void beispiel1() {
		eingabe.setText(" x^2 + y^2 + z^2  - 1;\n\n x^2 + z ^ 2  - y;\n\n x  - z;");
		berechnen();

	}

	private void beispiel2() {
		eingabe.setText(" - 2 s x + 3 x^2 + 2 y z;\n\n x^2 + y^2 + z ^2 - 1;\n\n 2 x z - 2 y s;\n\n 2 x y - 2 z - 2 z s");
		berechnen();
	}

	public void berechnen() {
		String texte[] = eingabe.getText().split(";");
		if (texte != null && texte.length > 0) {
			Vector<Polynom<RationaleZahl>> basis = new Vector<>();
			boolean fehlerFrei = true;
			for (String t : texte) {
				if (fehlerFrei && !"".equals(t.trim())) {
					basis.add(rp.getElement(t));
					List<String> errors = rp.getErrors();
					if (errors.size()>0) {
						StringBuilder builder = new StringBuilder();
						for (String et: errors) {
							builder.append(et);
							builder.append(";\n\n");
						}
						ausgabe.setText(builder.toString());
						rp.clearErrors();
						fehlerFrei = false;
					}
				}
			}

			if (fehlerFrei) {
				GröbnerBasisAlgo<RationaleZahl> gb = new GröbnerBasisAlgo(rp);
				List<Polynom<RationaleZahl>> gröbnerBasis = gb.gröbner(basis);
				StringBuilder builder = new StringBuilder();
				for (Polynom<RationaleZahl> p : gröbnerBasis) {
					builder.append(" ");
					builder.append(rp.toString(p));
					builder.append(";\n\n");
				}
				ausgabe.setText(builder.toString());
			}
		}

	}

}
