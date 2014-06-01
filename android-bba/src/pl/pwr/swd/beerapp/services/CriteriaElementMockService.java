package pl.pwr.swd.beerapp.services;

import pl.pwr.swd.beerapp.domain.Element;

import java.util.ArrayList;

/**
 * User: Adrian
 * Date: 5/18/14
 * Time: 12:14 PM
 */
public class CriteriaElementMockService  {
    public static ArrayList<Element> getCriteria() {
        ArrayList<Element> elementsArray = new ArrayList<Element>();
        elementsArray.add( new Element("ESTEROWOŚĆ"));
        elementsArray.add(  new Element("CHMIELOWOŚĆ"));
        elementsArray.add( new Element("SŁODYCZ"));
        elementsArray.add(  new Element("DOJRZAŁOŚĆ"));

        return new ArrayList<Element>(elementsArray);
    }
}
