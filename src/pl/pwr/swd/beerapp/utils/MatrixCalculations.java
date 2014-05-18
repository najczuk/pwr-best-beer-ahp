package pl.pwr.swd.beerapp.utils;


import Jama.Matrix;
import edu.umbc.cs.maple.utils.JamaUtils;

import java.util.Arrays;

public class MatrixCalculations {

    public static double[] ColumnsSum (Matrix m1) {

        int columnsNumber = m1.getColumnDimension();

        double[] columnTotal = new double[columnsNumber];

        for (int column = 0; column < columnsNumber; column++) {
            columnTotal[column] = 0;
            for (int row = 0; row < columnsNumber; row++) {
                columnTotal[column] += m1.get(row, column);
            }

        }
        return columnTotal;
    }

    public static Matrix NormalizeMatrix (Matrix m1, double[] t1){

        Matrix normalizedMatrix = new Matrix(m1.getColumnDimension(), m1.getRowDimension());

        int columnsNumber = m1.getColumnDimension();
       for(int column= 0; column< columnsNumber; column++)
       {
            for(int row= 0; row < columnsNumber; row++)
            {
                normalizedMatrix.set(row, column,  (m1.get(row, column)/t1[column]));
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

    public static boolean CheckCoherence(double[] t1, double[] t2){


        double CI, CR, lambdaMax = 0;
        int arrayLength = t1.length;
        double[]randomIndex = {0,0,0.58,0.90,1.12,1.24,1.32,1.41,1.45,1.49, 1.51, 1.48, 1.56, 1.57, 1.59};

        for(int length = 0; length < arrayLength;length++){

            lambdaMax = lambdaMax + t1[length] * t2[length];
        }

        CI= (lambdaMax-arrayLength)/(arrayLength-1);
        CR = CI/randomIndex[arrayLength-1];

        if (CR <= 0.1){
            return true;
        }
        else

          return false;
    }



}