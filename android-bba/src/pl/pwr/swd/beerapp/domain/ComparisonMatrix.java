package pl.pwr.swd.beerapp.domain;

import Jama.Matrix;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * User: Adrian
 * Date: 5/19/14
 * Time: 11:23 PM
 */
public class ComparisonMatrix extends Matrix {
    ArrayList<Element> elements;
    String matrixName;



    public ComparisonMatrix(ArrayList<Element> elements,String matrixName){
        super(elements.size(),elements.size());
        this.elements = elements;
        this.matrixName = matrixName;
    }

    public ComparisonMatrix(double[][] doubles,String matrixName) {
        super(doubles);
        this.matrixName = matrixName;
    }

    public ComparisonMatrix(double[][] doubles) {
        super(doubles);
    }
    public String getMatrixName() {
        return matrixName;
    }

    public void setMatrixName(String matrixName) {
        this.matrixName = matrixName;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(this.getArray());
    }
}
