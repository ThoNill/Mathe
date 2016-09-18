package mathe.grothendieck;

import java.io.Serializable;

public class GrothendieckPaar<K extends Serializable> implements Serializable {
    public K li;
    public K re;

    public GrothendieckPaar(K li, K re) {
        super();
        this.li = li;
        this.re = re;
    }

    @Override
    public String toString() {
        return "(" + li.toString() + "/" + re.toString() + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((li == null) ? 0 : li.hashCode());
        result = prime * result + ((re == null) ? 0 : re.hashCode());
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
        GrothendieckPaar other = (GrothendieckPaar) obj;
        if (li == null) {
            if (other.li != null)
                return false;
        } else if (!li.equals(other.li))
            return false;
        if (re == null) {
            if (other.re != null)
                return false;
        } else if (!re.equals(other.re))
            return false;
        return true;
    }

}
