grammar PolynomReader;

polynom : kopf ( reihe )*;

kopf : vorzeichen? cmonom;
reihe : vorzeichen cmonom;

vorzeichen : (PN)+;


cmonom : koeffizient  monom | koeffizient| monom;

monom : potenz (potenz)*;

potenz : VAR (EXP INT)?;

koeffizient : INT (QUOT INT)?;

WS : [ \n\r] -> skip ;
PN : [\+\-];
VAR : [pqrstuvwxyz];
INT : [0-9]+;
EXP : [\^];
QUOT : [\/];

