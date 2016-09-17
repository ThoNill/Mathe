package mathe.konkret.polynome;

import java.io.Serializable;
import java.util.Comparator;

public class CMonomOrdnung<C  extends Serializable> implements Comparator<CMonom<C>>, Serializable {
    private static final long serialVersionUID = -3599735210342966540L;
    
    MonomOrdnung ordnung;

    public CMonomOrdnung(MonomOrdnung ordnung) {
        super();
        this.ordnung = ordnung;
    }

    @Override
    public int compare(CMonom<C> o1, CMonom<C> o2) {
        return ordnung.compare(o1.monom, o2.monom);
    }

}
