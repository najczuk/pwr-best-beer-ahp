package pl.pwr.swd.beerapp.domain;

import Jama.Matrix;

import java.util.ArrayList;

/**
 * User: Adrian
 * Date: 5/19/14
 * Time: 11:23 PM
 */
public class ComparisonMatrix extends Matrix {
    ArrayList<Element> elements;

    public ComparisonMatrix(ArrayList<Element> elements){
        super(elements.size(),elements.size());
        this.elements = elements;
    }

    public ComparisonMatrix(double[][] doubles) {
        super(doubles);
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }
}
