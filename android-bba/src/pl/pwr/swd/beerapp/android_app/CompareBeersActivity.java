package pl.pwr.swd.beerapp.android_app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import pl.pwr.swd.beerapp.domain.ComparisonMatrix;
import pl.pwr.swd.beerapp.domain.Element;
import pl.pwr.swd.beerapp.services.CriteriaElementMockService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * User: Adrian
 * Date: 5/29/14
 * Time: 10:48 PM
 */
public class CompareBeersActivity extends Activity {

    ArrayList<Element> beers, criteria;
    public final static String LEFT_ELEMENT = "LEFT_ELEMENT";
    public final static String RIGHT_ELEMENT = "RIGHT_ELEMENT";
    public final static double[] RATING_VALUES = {1,3,5,7,9};


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_comparison);

        Bundle bundle = getIntent().getExtras();
        ArrayList<String> beerNames = bundle.getStringArrayList(MainActivity.CHECKED_BEERS);
        beers = getElementsFromStringArray(beerNames);
        criteria = CriteriaElementMockService.getCriteria();

        for (Element element : criteria) {
            appendComparisonHeader(element.getName());
            createComparisonViews();
        }

    }

    public void onSubmit(View view) {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.beerComparisonGridLayout);
        int gridChildCount = gridLayout.getChildCount();


        ArrayList<ComparisonMatrix> comparisonMatrixes = new ArrayList<ComparisonMatrix>();
        int criteriaCount = criteria.size();
        int numberOfComparisons = (beers.size() * beers.size() - beers.size()) / 2;

        ComparisonMatrix comparisonMatrix;

        Switch tmpSwitch;
        RatingBar tmpRatingBar;
//        Log.d("klasy", "i+1: " + gridLayout.getChildAt(currentChildIndex + 1).getClass().toString() + " i+3: " + gridLayout.getChildAt(currentChildIndex + 3).getClass().toString());

        int currentChildIndex = 0;
        for (int i = 0; i < criteriaCount; i++) {
            currentChildIndex++;
            int currentLimit = currentChildIndex + 4 * numberOfComparisons;

            double[] preferrenceLvlArray = new double[numberOfComparisons];
            int preferenceArrayCounter=0;
            double preferrenceLvl=1;
            String switchValue = LEFT_ELEMENT;
            double ratingValue;

            for (; currentChildIndex < currentLimit; currentChildIndex += 4) {
                if (gridLayout.getChildAt(currentChildIndex + 1) instanceof Switch
                        && gridLayout.getChildAt(currentChildIndex + 3) instanceof RatingBar) {
                    tmpSwitch = (Switch) gridLayout.getChildAt(currentChildIndex + 1);
                    tmpRatingBar = (RatingBar) gridLayout.getChildAt(currentChildIndex + 3);

                    switchValue = tmpSwitch.getText().toString();
                    ratingValue = RATING_VALUES[Math.round(tmpRatingBar.getRating())-1];
                    preferrenceLvl = switchValue.equals(LEFT_ELEMENT)?ratingValue:(1/ratingValue);

                    Log.d("klasy", "preference lvl:"+ratingValue);

                    preferrenceLvlArray[preferenceArrayCounter] = preferrenceLvl;
                    preferenceArrayCounter++;
                }
                comparisonMatrix = initializeMatrix(preferrenceLvlArray,beers.size());
                comparisonMatrixes.add(comparisonMatrix);


//                Log.d("klasy", "i+1: (" + (currentChildIndex + 1) + ") " + gridLayout.getChildAt(currentChildIndex + 1).getClass().toString()
//                        + " i+3: " + (currentChildIndex + 3) + ") " + gridLayout.getChildAt(currentChildIndex + 3).getClass().toString());

            }

            Log.d("klasy","preference array: " + Arrays.toString(preferrenceLvlArray));


        }
    }


    private void appendComparisonView(String firstElementName, String secondElementName) {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.beerComparisonGridLayout);

        TextView firstElement = new TextView(this);
        firstElement.setText(firstElementName);

        TextView secondElement = new TextView(this);
        secondElement.setText(secondElementName);

        Switch aSwitch = new Switch(this);
        aSwitch.setTextOn("");

        RatingBar ratingBar = new RatingBar(this);
        ratingBar.setNumStars(5);
        ratingBar.setMax(5);
        ratingBar.setRating(1f);
        ratingBar.setStepSize(1);

        gridLayout.addView(firstElement);
        gridLayout.addView(aSwitch);
        gridLayout.addView(secondElement);
        gridLayout.addView(ratingBar);

        setSwitchParams(aSwitch);
        setRatingParams(ratingBar);

    }

    private void setRatingParams(RatingBar ratingBar) {
        GridLayout.LayoutParams ratingParams = new GridLayout.LayoutParams();
        ratingParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        ratingParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
        ratingParams.rightMargin = 5;
        ratingParams.topMargin = 5;
        ratingParams.setGravity(Gravity.CENTER);
        ratingParams.columnSpec = GridLayout.spec(0, 4);
        ratingBar.setLayoutParams(ratingParams);
    }

    private void setSwitchParams(Switch aSwitch) {
        GridLayout.LayoutParams switchParams = new GridLayout.LayoutParams();
        switchParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        switchParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
        switchParams.rightMargin = 5;
        switchParams.topMargin = 5;
        switchParams.setGravity(Gravity.CENTER);
        switchParams.columnSpec = GridLayout.spec(1, 2);
        aSwitch.setLayoutParams(switchParams);
    }

    private void appendComparisonHeader(String comparisonName) {
        TextView headerElement = new TextView(this);
        headerElement.setText(comparisonName);
        setHeaderParams(headerElement);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.beerComparisonGridLayout);
        gridLayout.addView(headerElement);


    }

    private void setHeaderParams(TextView headerElement) {
        headerElement.setTextColor(Color.parseColor("#000000"));
        GridLayout.LayoutParams ratingParams = new GridLayout.LayoutParams();
        ratingParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        ratingParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
        ratingParams.rightMargin = 5;
        ratingParams.topMargin = 5;
        ratingParams.setGravity(Gravity.CENTER);
        ratingParams.columnSpec = GridLayout.spec(0, 4);
        headerElement.setLayoutParams(ratingParams);
    }


    private ArrayList<Element> getElementsFromStringArray(ArrayList<String> beersNames) {
        ArrayList<Element> beers = new ArrayList<Element>();

        for (String beerName : beersNames) {
            beers.add(new Element(beerName));
            Log.d("CHOOSED_BEER", beerName);
        }
        return beers;
    }

    private void createComparisonViews() {
        for (int firstElement = 0; firstElement < beers.size(); firstElement++) {
            for (int secondElement = firstElement + 1; secondElement < beers.size(); secondElement++) {
                appendComparisonView(beers.get(firstElement).getName(), beers.get(secondElement).getName());
            }
        }


    }

    private ComparisonMatrix getComparisonMatrix(ArrayList<Element> elements, String matrixName) {
        ComparisonMatrix comparisonMatrix;
        Scanner scanner = new Scanner(System.in);
        int preferredElement = 0;
        double preferrenceLvl;
        double[] preferrenceLvlArray = new double[(elements.size() * elements.size() - elements.size()) / 2];
        int preferrenceArrayCounter = 0;
        String prompt = "";

        for (int i = 0; i < elements.size(); i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                prompt = "Co bardziej preferujesz " + elements.get(i) + "( 0 ) czy " + elements.get(j) + "( 1 )?";
                System.out.println("---" + matrixName.toUpperCase() + "---");
                System.out.println(prompt);
                preferredElement = Integer.parseInt(scanner.next()) == 0 ? i : j;
                System.out.println("Jak bardzo?");
                System.out.println("Tak samo(1)/Nieznacznie(3)/Silnie(5)/Bardzo silnie(7)/Wyjątkowo(9)");
                preferrenceLvl = scanner.nextInt();
                preferrenceLvl = preferredElement == i ? preferrenceLvl : 1 / preferrenceLvl;
                preferrenceLvlArray[preferrenceArrayCounter] = preferrenceLvl;
                preferrenceArrayCounter++;

            }
//
        }
        comparisonMatrix = initializeMatrix(preferrenceLvlArray, elements.size());
        comparisonMatrix.setElements(elements);
        comparisonMatrix.setMatrixName(matrixName);
        return comparisonMatrix;
    }

    private static ComparisonMatrix initializeMatrix(double[] p, int n) {

        double a[][] = new double[n][n];
        int k = 0;
        for (int i = 0; i < n; i++) {
//            k = 0;

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


}