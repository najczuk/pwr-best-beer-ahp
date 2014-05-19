package pl.pwr.swd.beerapp.utils;

import Jama.Matrix;
import edu.umbc.cs.maple.utils.JamaUtils;

public class MatrixCalculations {

    public static double[] ColumnsSum (Matrix inputMatrix) {

        int columnsNumber = inputMatrix.getColumnDimension();

        double[] columnsTotal = new double[columnsNumber];

        for (int column = 0; column < columnsNumber; column++) {
            columnsTotal[column] = 0;
            for (int row = 0; row < columnsNumber; row++) {
                columnsTotal[column] += inputMatrix.get(row, column);
            }
        }
        return columnsTotal;
    }

    public static Matrix NormalizeMatrix (Matrix inputMatrix, double[] columnsTotal){

        Matrix normalizedMatrix = new Matrix(inputMatrix.getColumnDimension(), inputMatrix.getRowDimension());

        int columnsNumber = inputMatrix.getColumnDimension();
        for(int column= 0; column< columnsNumber; column++){
            for(int row= 0; row < columnsNumber; row++){
                normalizedMatrix.set(row, column,  (inputMatrix.get(row, column)/columnsTotal[column]));
            }
        }
        return normalizedMatrix;
    }

    public static double[] AverageRows (Matrix m2){

        int rowsNumber = m2.getRowDimension();
        double[] rowAvg = new double[rowsNumber];

        for(int row= 0; row < m2.getRowDimension(); row++)
        {
            rowAvg[row] = 0;
            rowAvg[row] += JamaUtils.rowsum(m2, row)/m2.getRowDimension();
        }

        return rowAvg;
    }

    public static boolean CheckCoherence(double[] columnsTotal, double[] t2){


        double CI, CR, lambdaMax = 0;
        int arrayLength = columnsTotal.length;
        double[]randomIndex = {0,0,0.58,0.90,1.12,1.24,1.32,1.41,1.45,1.49, 1.51, 1.48, 1.56, 1.57, 1.59};

        for(int length = 0; length < arrayLength;length++){

            lambdaMax = lambdaMax + columnsTotal[length] * t2[length];
        }

        CI= (lambdaMax-arrayLength)/(arrayLength-1);
        CR = CI/randomIndex[arrayLength-1];

        if (CR <= 0.1){
            return true;
        }
        else

          return false;
    }

public static Matrix Rank (double[] AvgDecisionVector, double[]...AvgCriteriaVectors){


   int matrixRows = AvgCriteriaVectors.length;
   int matrixColumns = AvgCriteriaVectors[0].length;

    Matrix sample = new Matrix(matrixRows,matrixColumns);

    Matrix sample2 = new Matrix(AvgDecisionVector, 1);

    for(int i = 0; i<matrixRows;i++){
        for(int j=0; j<matrixColumns;j++){

            sample.set(i,j,AvgCriteriaVectors[i][j]);
        }
    }

    Matrix sample3 = sample2.times(sample);

    

    return sample3;
}

}