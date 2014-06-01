package pl.pwr.swd.beerapp.android_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;
import pl.pwr.swd.beerapp.android_app.exception.NotEnoughElementsCheckedException;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public final static String CHECKED_BEERS = "pl.pwr.swd.beerapp.android_app.CHECKED_BEERS";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void submitBeers(View view) {
        LinearLayout beerRadioButtons = (LinearLayout) findViewById(R.id.beerRadioButtonsContainer);

        try {

            ArrayList<String> choosenBeers = getChoosedBeers(beerRadioButtons);
            Intent compareBeers = new Intent(this, CompareBeersActivity.class);
            compareBeers.putStringArrayListExtra(CHECKED_BEERS, choosenBeers);
            startActivity(compareBeers);

        } catch (NotEnoughElementsCheckedException e) {
            Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
        catch (Exception e){
            Log.d("intent_piwka",e.getMessage());

        }


    }

    private ArrayList<String> getChoosedBeers(LinearLayout beerRadioButtons) throws NotEnoughElementsCheckedException {
        int count = beerRadioButtons.getChildCount();
        CheckBox currentCheckBox;
        ArrayList<String> listOfCheckedBeers = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            View o = beerRadioButtons.getChildAt(i);
            if (o instanceof CheckBox && ((CheckBox) o).isChecked()) {
                currentCheckBox = (CheckBox) o;
                listOfCheckedBeers.add(
                        currentCheckBox.getText().toString());
            }
        }
        if (listOfCheckedBeers.size() < 2)
            throw new NotEnoughElementsCheckedException("Wyberz przynajmniej dwa piwa.");

        return listOfCheckedBeers;
    }
}
