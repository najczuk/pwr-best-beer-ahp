package pl.pwr.swd.beerapp.engine;

import Jama.Matrix;
import pl.pwr.swd.beerapp.domain.ComparisonMatrix;
import pl.pwr.swd.beerapp.domain.Element;
import pl.pwr.swd.beerapp.utils.MatrixCalculations;

import java.util.ArrayList;

/**
 * User: Adrian
 * Date: 5/18/14
 * Time: 12:47 PM
 */
public class AHPComputer {
    ComparisonMatrix criteriaComparisonMatrix;
    ArrayList<ComparisonMatrix> comparisonMatrixes;
    ArrayList<Element> decisions, criterias;
    double[][] decisionComparisonsVectors;
    double[] avgCriteriaVector;


    public AHPComputer(ComparisonMatrix criteriaComparisonMatrix, ArrayList<ComparisonMatrix> comparisonMatrixes,
                       ArrayList<Element> decisions, ArrayList<Element> criterias) {
        this.criteriaComparisonMatrix = criteriaComparisonMatrix;
        this.comparisonMatrixes = comparisonMatrixes;
        this.decisions = decisions;
        this.criterias = criterias;
        intializeCriteriaAvgVector();
        initializeDecisionComparisonsAvgVecotrs();
    }

    public Matrix computeRank() {
        Matrix rank = MatrixCalculations.rank(avgCriteriaVector, decisionComparisonsVectors);
        return rank;
    }

    private void intializeCriteriaAvgVector() {
        avgCriteriaVector = MatrixCalculations.rowsAvg(criteriaComparisonMatrix);
    }

    private void initializeDecisionComparisonsAvgVecotrs() {
        decisionComparisonsVectors = new double[comparisonMatrixes.size()][decisions.size()];
        for (int comparisonMatrixIndex = 0; comparisonMatrixIndex < comparisonMatrixes.size(); comparisonMatrixIndex++) {
            decisionComparisonsVectors[comparisonMatrixIndex] = MatrixCalculations
                    .rowsAvg(comparisonMatrixes.get(comparisonMatrixIndex));
        }
    }

    public static boolean checkCoherence(Matrix matrix) {
        return MatrixCalculations.checkCoherence(matrix);
    }
}
