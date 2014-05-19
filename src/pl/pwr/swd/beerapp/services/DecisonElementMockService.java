package pl.pwr.swd.beerapp.services;

import pl.pwr.swd.beerapp.domain.Element;
import pl.pwr.swd.beerapp.services.interfaces.IDecisionService;

/**
 * User: Adrian
 * Date: 5/18/14
 * Time: 12:14 PM
 */
public class DecisonElementMockService implements IDecisionService {

    public Element[] getDecisions(){
        Element[] elementsArray = new Element[6];
        elementsArray[0] = new Element("Perła");
        elementsArray[1] = new Element("Żywiec");
        elementsArray[2] = new Element("Książ");
        elementsArray[3] = new Element("Harnaś");
        elementsArray[4] = new Element("Żytorillo");
        elementsArray[5] = new Element("Heineken");

        return elementsArray;
    }
}
