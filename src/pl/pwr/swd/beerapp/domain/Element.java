package pl.pwr.swd.beerapp.domain;

/**
 * User: Adrian
 * Date: 5/18/14
 * Time: 11:26 AM
 */
public class  Element{
    public String name;

    public Element(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Element decision = (Element) o;

        if (!name.equals(decision.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
