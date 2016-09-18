package mathe.konkret.polynome;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

public class Polynom<C extends Serializable> implements Iterable<CMonom<C>>,
        Serializable {
    private static final long serialVersionUID = 4911045316571765352L;

    Vector<CMonom<C>> monome;

    public Polynom() {
        monome = new Vector<>();
    }

    public void trimToSize() {
        monome.trimToSize();
    }

    public void removeElementAt(int index) {
        monome.removeElementAt(index);
    }

    public void addElement(CMonom<C> obj) {
        monome.addElement(obj);
    }

    public boolean removeElement(Object obj) {
        return monome.removeElement(obj);
    }

    public void removeAllElements() {
        monome.removeAllElements();
    }

    @Override
    public Iterator<CMonom<C>> iterator() {
        return monome.iterator();
    }

    public void sortieren(MonomOrdnung ordnung) {
        CMonomOrdnung<C> cordnung = new CMonomOrdnung<>(ordnung);
        monome.sort(cordnung);
    }

    public int size() {
        return monome.size();
    }

    public CMonom<C> get(int index) {
        return monome.get(index);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((monome == null) ? 0 : hashList());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Polynom other = (Polynom) obj;
        if (monome == null) {
            if (other.monome != null)
                return false;
        } else if (!equalsList(monome, other.monome))
            return false;
        return true;
    }

    public int hashList() {
        int h = 0;
        for (CMonom<C> m : monome) {
            h += 31 * m.hashCode();
        }
        return h;
    }

    public boolean equalsList(Vector<CMonom<C>> a, Vector<CMonom<C>> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < b.size(); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        return monome.isEmpty();
    }

    public boolean hatMonom(Monom m) {
        for (CMonom<C> cm : monome) {
            if (m.equals(cm.monom)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Polynom [monome=" + monome + "]";
    }
}
