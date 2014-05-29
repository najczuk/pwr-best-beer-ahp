package pl.pwr.swd.beerapp;

import pl.pwr.swd.beerapp.engine.AHPComputer;
import pl.pwr.swd.beerapp.engine.DataCollector;

public class Main {

    public static void main(String[] args) {
        DataCollector dataCollector = new DataCollector();
        AHPComputer ahpComputer = new AHPComputer(dataCollector.getCriteriaComparisonMatrix()
                , dataCollector.getComparisonMatrixes(), dataCollector.getDecisions(), dataCollector.getCriterias());
        //ARRAYS
        double[][] criteriaComparisonArray = {{1.0, 3.0, 7.0, 9.0}, {0.3333333333333333, 1.0, 3.0, 7.0},
                {0.14285714285714285, 0.3333333333333333, 1.0, 3.0},
                {0.1111111111111111, 0.14285714285714285, 0.3333333333333333, 1.0}};
        double[][] crit1ComparisonArray = {{1.0, 1.0, 7.0}, {1.0, 1.0, 3.0},
                {0.14285714285714285, 0.3333333333333333, 1.0}};
        double[][] crit2ComparisonArray = {{1.0, 0.2, 1.0}, {5.0, 1.0, 3.0}, {1.0, 0.3333333333333333, 1.0}};
        double[][] crit3ComparisonArray = {{1.0, 1.0, 7.0}, {1.0, 1.0, 3.0}, {0.14285714285714285, 0.3333333333333333, 1.0}};
        double[][] crit4ComparisonArray = {{1.0, 7.0, 9.0}, {0.14285714285714285, 1.0, 1.0}, {0.1111111111111111, 1.0, 1.0}};

//        //ComparisonMatrixes
//        ComparisonMatrix criteriaComparisonMatrix = new ComparisonMatrix(criteriaComparisonArray, "PORÓWNANIE KRYTERIÓW");
//        ArrayList<ComparisonMatrix> comparisonMatrixes = new ArrayList<ComparisonMatrix>();
//        comparisonMatrixes.add(new ComparisonMatrix(criteriaComparisonArray, "ESTEROWOŚĆ"));
//        comparisonMatrixes.add(new ComparisonMatrix(criteriaComparisonArray, "CHMIELOWOŚĆ"));
//        comparisonMatrixes.add(new ComparisonMatrix(criteriaComparisonArray, "SŁODYCZ"));
//        comparisonMatrixes.add(new ComparisonMatrix(criteriaComparisonArray, "DOJRZAŁOŚĆ"));
//
//        //Elements and Decisions
//        ArrayList<Element>  elements = new ArrayList<Element>();
//        elements.add(new Element("Perła"));
//        elements.add(new Element("Piast"));
//        elements.add(new Element("Łomża"));
//
//        ArrayList<Element>  criterias = new ArrayList<Element>();
//        criterias.add(new Element("ESTEROWOŚĆ"));
//        criterias.add(new Element("CHMIELOWOŚĆ"));
//        criterias.add(new Element("SŁODYCZ"));
//        criterias.add(new Element("DOJRZAŁOŚĆ"));

//        AHPComputer ahpComputer = new AHPComputer(criteriaComparisonMatrix,comparisonMatrixes,elements,criterias);
        double[] rank = ahpComputer.computeRank().getRowPackedCopy();
        for (int i = 0; i < dataCollector.getDecisions().size(); i++) {
            System.out.println(dataCollector.getDecisions().get(i) + ": " + rank[i]);
        }
//        System.out.println(Arrays.deepToString(ahpComputer.computeRank().getArray()));
    }
}
