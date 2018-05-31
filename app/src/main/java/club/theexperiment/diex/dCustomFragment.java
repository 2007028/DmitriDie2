package club.theexperiment.diex;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;

public class dCustomFragment extends ListFragment {
    private String numDice;
    private String numSides;
    private int nD;
    private int nS;
    private Button mDCustomRollButton;
    private String[] rollStrings;
    private int sum;



    public boolean onOptionsItemSelected(MenuItem item){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, new defaultFragment());
        ft.commit();
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Uri uri;

        //Link Views and Buttons
        final View rootView = inflater.inflate(R.layout.fragment_dcustom, container, false);

        final VideoView videoView = (VideoView) rootView.findViewById(R.id.video_view);

        uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.d6_1);
        videoView.setVideoURI(uri);

        mDCustomRollButton = (Button) rootView.findViewById(R.id.dCustomRollButton);
        //Set method for when roll button clicked
        mDCustomRollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Reset sum to 0
                sum = 0;
                //Link textbox for number of sides
                numDice = ((EditText) rootView.findViewById(R.id.dCustomRolls)).getText().toString();
                numSides = ((EditText) rootView.findViewById(R.id.dCustomSides)).getText().toString();
                if (numDice.equals("") || numSides.equals("")) {}
                else {
                    //Parse number of sides in textbox
                    nD = Integer.parseInt(numDice);
                    nS = Integer.parseInt(numSides);
                    //Set sides to specified number of sides
                    MainActivity.dice = new dCustom(nD, nS);
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
                    //Add sum String to array//
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

                }
            }

        });
        return rootView;

    }
}
