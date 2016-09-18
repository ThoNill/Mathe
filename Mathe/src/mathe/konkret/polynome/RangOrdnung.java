package mathe.konkret.polynome;

public class RangOrdnung extends LexOrdnung {

    @Override
    public int compare(Monom o1, Monom o2) {
        if (o1.exponenten.length != o2.exponenten.length) {
            throw new IllegalArgumentException("unvergleichbare Monome");
        }

        int ro1 = calculateRang(o1);
        int ro2 = calculateRang(o2);

        if (ro1 > ro2) {
            return 1;
        }

        if (ro1 < ro2) {
            return -1;
        }
        return super.compare(o1, o2);

    }

    protected int calculateRang(Monom monom) {
        int dim = monom.exponenten.length;
        int rang = 0;
        for (int i = 0; i < dim; i++) {
            rang += monom.exponenten[i];
        }
        return rang;
    }

}
