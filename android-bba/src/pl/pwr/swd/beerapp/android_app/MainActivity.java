package pl.pwr.swd.beerapp.android_app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import pl.pwr.swd.beerapp.android_app.exception.NotEnoughElementsCheckedException;
import pl.pwr.swd.beerapp.domain.Element;

import java.util.ArrayList;

public class MainActivity extends Activity {
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
            ArrayList<Element> choosenBeers = getChoosedBeers(beerRadioButtons);
        } catch (NotEnoughElementsCheckedException e) {
            Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    private ArrayList<Element> getChoosedBeers(LinearLayout beerRadioButtons) throws NotEnoughElementsCheckedException {
        int count = beerRadioButtons.getChildCount();
        CheckBox currentCheckBox;
        ArrayList<Element> listOfCheckedBeers = new ArrayList<Element>();
        for (int i = 0; i < count; i++) {
            View o = beerRadioButtons.getChildAt(i);
            if (o instanceof CheckBox && ((CheckBox) o).isChecked()) {
                currentCheckBox = (CheckBox) o;
                listOfCheckedBeers.add(new Element(
                        currentCheckBox.getText().toString()));
            }
        }
        if (listOfCheckedBeers.size() < 2)
            throw new NotEnoughElementsCheckedException("Należy zaznaczyć przynajmniej dwa piwa");

        return listOfCheckedBeers;
    }
}
