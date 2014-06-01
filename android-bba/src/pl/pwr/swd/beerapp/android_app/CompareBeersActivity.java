package pl.pwr.swd.beerapp.android_app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import pl.pwr.swd.beerapp.domain.Element;
import pl.pwr.swd.beerapp.services.CriteriaElementMockService;

import java.util.ArrayList;

/**
 * User: Adrian
 * Date: 5/29/14
 * Time: 10:48 PM
 */
public class CompareBeersActivity extends Activity {

    ArrayList<Element> beers, criteria;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_comparison);

        Bundle bundle = getIntent().getExtras();
        ArrayList<String> beerNames = bundle.getStringArrayList(MainActivity.CHECKED_BEERS);
        beers = getElementsFromStringArray(beerNames);
        criteria = CriteriaElementMockService.getCriteria();

        for (Element currentCriteria : criteria) {

        }

    }

    private void appendComparisonView(String firstElementName, String secondElementName) {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.beerComparisonGridLayout);

        TextView firstElement = new TextView(this);
        firstElement.setText(firstElementName);
//        GridLayout.LayoutParams firstLP=(GridLayout.LayoutParams)firstElement.getLayoutParams();
//        firstLP.columnSpec = GridLayout.spec(0,2);
//        firstElement.setLayoutParams(firstLP);

        TextView secondElement = new TextView(this);
        secondElement.setText(secondElementName);

        Switch aSwitch = new Switch(this);

        RatingBar ratingBar = new RatingBar(this);
        ratingBar.setNumStars(5);
        ratingBar.setMax(5);
        ratingBar.setRating(1f);
        ratingBar.setStepSize(1);

        gridLayout.addView(firstElement);
        gridLayout.addView(aSwitch);
        gridLayout.addView(secondElement);
        gridLayout.addView(ratingBar);


        GridLayout.LayoutParams switchParams = new GridLayout.LayoutParams();
        switchParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        switchParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
        switchParams.rightMargin = 5;
        switchParams.topMargin = 5;
        switchParams.setGravity(Gravity.CENTER);
        switchParams.columnSpec = GridLayout.spec(1,2);
        aSwitch.setLayoutParams(switchParams);

        GridLayout.LayoutParams ratingParams = new GridLayout.LayoutParams();
        ratingParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        ratingParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
        ratingParams.rightMargin = 5;
        ratingParams.topMargin = 5;
        ratingParams.setGravity(Gravity.CENTER);
        ratingParams.columnSpec = GridLayout.spec(0,4);
        ratingBar.setLayoutParams(ratingParams);

//        whichBestRow.addView(firstElement, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
//        whichBestRow.addView(aSwitch, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.1f));
//        whichBestRow.addView(secondElement, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
//        whichBestRow.addView(ratingBar, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
//
//        tableLayout.addView(whichBestRow);


    }
    private void appendComparisonHeader(String comparisonName){
        TextView headerElement = new TextView(this);
        headerElement.setText(comparisonName);
        GridLayout.LayoutParams ratingParams = new GridLayout.LayoutParams();
        ratingParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        ratingParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
        ratingParams.rightMargin = 5;
        ratingParams.topMargin = 5;
        ratingParams.setGravity(Gravity.CENTER);
        ratingParams.columnSpec = GridLayout.spec(0,4);
        headerElement.setLayoutParams(ratingParams);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.beerComparisonGridLayout);
        gridLayout.addView(headerElement);


    }


    private ArrayList<Element> getElementsFromStringArray(ArrayList<String> beersNames) {
        ArrayList<Element> beers = new ArrayList<Element>();

        for (String beerName : beersNames) {
            beers.add(new Element(beerName));
            Log.d("CHOOSED_BEER", beerName);
        }
        return beers;
    }


}