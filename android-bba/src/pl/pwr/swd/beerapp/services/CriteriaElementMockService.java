package pl.pwr.swd.beerapp.services;

import pl.pwr.swd.beerapp.domain.Element;
import pl.pwr.swd.beerapp.services.interfaces.ICriteriaService;

/**
 * User: Adrian
 * Date: 5/18/14
 * Time: 12:14 PM
 */
public class CriteriaElementMockService implements ICriteriaService {
    public Element[] getCriteria() {
        Element[] elementsArray = new Element[4];
        elementsArray[0] = new Element("ESTEROWOŚĆ");
        elementsArray[1] = new Element("CHMIELOWOŚĆ");
        elementsArray[2] = new Element("SŁODYCZ");
        elementsArray[3] = new Element("DOJRZAŁOŚĆ");

        return elementsArray;
    }
}
