package pl.pwr.swd.beerapp.engine;

import pl.pwr.swd.beerapp.domain.ComparisonMatrix;
import pl.pwr.swd.beerapp.domain.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * User: Adrian
 * Date: 5/18/14
 * Time: 12:46 PM
 */
public class DataCollector {
    ComparisonMatrix criteriaComparisonMatrix;
    ArrayList<ComparisonMatrix> comparisonMatrixes;
    ArrayList<Element> decisions, criterias;

    public DataCollector() {
        criterias = getElementsFromConsole(new ArrayList<Element>(), "Podaj liczbę kryteriów");
        criteriaComparisonMatrix = getComparisonMatrix(criterias,"Macierz Kryteriow");
        decisions = getElementsFromConsole(new ArrayList<Element>(), "Podaj liczbę decyzji");
        comparisonMatrixes = new ArrayList<ComparisonMatrix>();

        for (Element criteria : criterias) {
            comparisonMatrixes.add(getComparisonMatrix(decisions,criteria.getName()));
        }

        for (ComparisonMatrix comparisonMatrix : comparisonMatrixes) {
            System.out.println(comparisonMatrix.getMatrixName());
            System.out.println(Arrays.deepToString(comparisonMatrix.getArray()));
        }


    }

    private ArrayList<Element> getElementsFromConsole(ArrayList<Element> elements, String promptText) {
        int elementsNum;
        Scanner scanner = new Scanner(System.in);
        System.out.println(promptText + ":");
        elementsNum = scanner.nextInt();

        for (int criteriaIndex = 0; criteriaIndex < elementsNum; criteriaIndex++) {
            System.out.println("Podaj element " + (criteriaIndex+1));
            elements.add(new Element(scanner.next()));
        }
//        scanner.close();
        return elements;

    }

    private ComparisonMatrix getComparisonMatrix(ArrayList<Element> elements,String matrixName) {
        ComparisonMatrix comparisonMatrix;
        Scanner scanner = new Scanner(System.in);
        int preferredElement = 0;
        double preferrenceLvl;
        double[] preferrenceLvlArray = new double[(elements.size() * elements.size() - elements.size()) / 2];
        int preferrenceArrayCounter = 0;
        String prompt = "";

        for (int i = 0; i < elements.size(); i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                prompt = "Co bardziej preferujesz " + elements.get(i) + "(" + i + ") czy " + elements.get(j) + "(" + j + ")?";
                System.out.println("---"+matrixName.toUpperCase()+"---");
                System.out.println(prompt);
                preferredElement = Integer.parseInt(scanner.next());
                System.out.println("Jak bardzo?");
                System.out.println("Tak samo(1)/Nieznacznie(3)/Silnie(5)/Bardzo silnie(7)/Wyjątkowo(9)");
                preferrenceLvl = scanner.nextInt();
                preferrenceLvl = preferredElement == i ? preferrenceLvl : 1 / preferrenceLvl;
                preferrenceLvlArray[preferrenceArrayCounter] = preferrenceLvl;
                preferrenceArrayCounter++;

            }
//
        }
        comparisonMatrix = initializeMatrix(preferrenceLvlArray,elements.size());
        comparisonMatrix.setElements(elements);
        comparisonMatrix.setMatrixName(matrixName);
        return comparisonMatrix;
    }

    public static ComparisonMatrix initializeMatrix(double[] p,int n) {

        double a[][] = new double[n][n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            k = 0;

            for (int j = 0; j < n; j++) {
                if (i == j)
                    a[i][j] = 1;
                else if (i < j) {

                    a[i][j] = p[k];
                    k++;
                } else if (i > j)
                    a[i][j] = 1 / a[j][i];
            }
        }


        return new ComparisonMatrix(a);
    }

    public static void showMatrix(double[][] b )
    {
        //display the elements of the matrix a
        System.out.println("\nThe matrix a is:");
        for(int i=0; i<b.length;i++)
        {
            for(int j=0; j<b[i].length; j++)
                System.out.print(b[i][j]+"    ");
            System.out.println();
        }
    }

    public ComparisonMatrix getCriteriaComparisonMatrix() {
        return criteriaComparisonMatrix;
    }

    public void setCriteriaComparisonMatrix(ComparisonMatrix criteriaComparisonMatrix) {
        this.criteriaComparisonMatrix = criteriaComparisonMatrix;
    }

    public ArrayList<ComparisonMatrix> getComparisonMatrixes() {
        return comparisonMatrixes;
    }

    public void setComparisonMatrixes(ArrayList<ComparisonMatrix> comparisonMatrixes) {
        this.comparisonMatrixes = comparisonMatrixes;
    }

    public ArrayList<Element> getDecisions() {
        return decisions;
    }

    public void setDecisions(ArrayList<Element> decisions) {
        this.decisions = decisions;
    }

    public ArrayList<Element> getCriterias() {
        return criterias;
    }

    public void setCriterias(ArrayList<Element> criterias) {
        this.criterias = criterias;
    }
}
