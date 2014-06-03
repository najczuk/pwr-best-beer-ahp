package pl.pwr.swd.beerapp.android_app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.TextView;
import pl.pwr.swd.beerapp.domain.Element;

import java.util.ArrayList;

/**
 * User: Adrian
 * Date: 6/3/14
 * Time: 4:17 PM
 */
public class ResultsActivity extends Activity {
//    ArrayList<Element> beers, criteria;
    ArrayList<String> ranks,beerNames;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        Bundle bundle = getIntent().getExtras();
        beerNames = bundle.getStringArrayList(MainActivity.CHECKED_BEERS);
        ranks = bundle.getStringArrayList(CompareBeersActivity.RANK);

//        beers = getElementsFromStringArray(beerNames);
//        criteria = CriteriaElementMockService.getCriteria();

        for (String beerName : beerNames) {
            Log.d("resultsss", beerName);
        }

        for (String rank : ranks) {
            Log.d("resultsss", rank);
        }
//        fillGridLayoutWithResults();


    }

    private void fillGridLayoutWithResults() {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.resultsGridLayout);

        addRankElement(gridLayout, "Piwo", "Wskaźnik");

        for (int i = 0; i < ranks.size(); i++) {
            addRankElement(gridLayout, beerNames.get(i), ranks.get(i));
        }

    }

    private void addRankElement(GridLayout gridLayout, String beerName, String rank) {
        TextView leftElement = new TextView(this);
        leftElement.setText(beerName);
        TextView rightElement = new TextView(this);
        rightElement.setText(rank);

        gridLayout.addView(leftElement);
        gridLayout.addView(rightElement);
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