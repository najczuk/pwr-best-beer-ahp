package pl.pwr.swd.beerapp.domain;

import Jama.Matrix;

/**
 * User: Adrian
 * Date: 5/19/14
 * Time: 11:23 PM
 */
public class CriteriaMatrix extends Matrix {

    public CriteriaMatrix(int dimension){
        super(dimension,dimension);
    }
}
