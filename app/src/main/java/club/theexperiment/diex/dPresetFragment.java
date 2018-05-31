package club.theexperiment.diex;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class dPresetFragment extends ListFragment {
    //Declare variables
    private String numDice;
    private int nD;
    private Button mDPresetRollButton;
    private Button graphButton;
    private String[] rollStrings;
    private int sum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Show back button
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Uri uri;

        //Link Views and Buttons
        final View rootView = inflater.inflate(R.layout.fragment_dpreset, container, false);

        final VideoView videoView = (VideoView) rootView.findViewById(R.id.video_view);

        mDPresetRollButton = (Button) rootView.findViewById(R.id.dPresetRollButton);

        graphButton = (Button) rootView.findViewById(R.id.graphButton);

        //Set location of video to play



        //Set method for when roll button clicked
        mDPresetRollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Reset sum to 0
                sum = 0;
                //Link textbox for number of sides
                numDice = ((EditText) rootView.findViewById(R.id.dPresetRolls)).getText().toString();
                //Parse number of sides in textbox
                if (numDice.equals("")){}
                else {
                    nD = Integer.parseInt(numDice);

                    //Set sides to specified number of sides
                    MainActivity.dice.setNumberOfDice(nD);
                    MainActivity.dice.roll();
                    //Uri dumbUri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/"+ R.raw.d6_1);
                    Uri uri = MainActivity.dice.getUri(MainActivity.dice.getFirstRoll());
                    videoView.setVideoURI(uri);
                    //Roll dice for roll array
                    videoView.start();
                    MainActivity.dice.roll();


                    //Create new array to store string versions of roll ints
                    rollStrings = new String[MainActivity.dice.getRolls().length + 1];
                    //Create array strings specifying how many times each side was rolled and add sum
                    for (int i = 0; i < MainActivity.dice.getRolls().length; i++) {
                        rollStrings[i] = (i + 1) + "s: " + Integer.toString(MainActivity.dice.getRolls()[i]);
                        sum += (i + 1) * MainActivity.dice.getRolls()[i];
                    }
                    //Add sum String to array
                    rollStrings[rollStrings.length - 1] = "Sum: " + Integer.toString(sum);
                    //Create Array adapter to display String array
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.listview, rollStrings);
                    //Display String array
                    setListAdapter(arrayAdapter);
                    //Hide Keyboard
                    InputMethodManager inputManager = (InputMethodManager)
                            getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);


                    //Create method to execute on button press
                    graphButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.content_frame, new graphFragment());
                            ft.commit();
                        }

                    });
                }
            }

        });
        return rootView;

    }


}
