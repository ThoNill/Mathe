package mathe.konkret.polynome;

import java.io.Serializable;

public class DivMitRest<C extends Serializable> {
    public Polynom<C> div;
    public Polynom<C> rest;

    public DivMitRest(Polynom<C> div, Polynom<C> rest) {
        super();
        this.div = div;
        this.rest = rest;
    }

}
