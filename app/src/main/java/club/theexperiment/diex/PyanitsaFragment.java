package club.theexperiment.diex;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KDT on 5/30/2018.
 */

public class PyanitsaFragment extends android.support.v4.app.Fragment {

    int[] cards = {14, 14, 14, 14, 13, 13, 13, 13, 12, 12, 12, 12, 11, 11, 11, 11, 10, 10, 10, 10, 9, 9,
            9, 9, 8, 8, 8, 8, 7, 7, 7, 7, 6, 6, 6, 6};
     List playerone = new ArrayList();
     List playertwo = new ArrayList();
     List bucket = new ArrayList();
    private int[] rollStrings;
    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    private Button nextButton;
    private int p1win;
    private int p2win;
    String x; String y;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View rootView = inflater.inflate(R.layout.pyanitsa, container, false);
        nextButton = (Button) rootView.findViewById(R.id.nextButton);



        Log.d(LOG_TAG, "A");
        //Shuffle cards
            for (int i = 35; i>0; i--) {
                //Set sides to specified number of sides

                MainActivity.dice = new dCustom(1, i);
                MainActivity.dice.roll(1);
                rollStrings = MainActivity.dice.getRolls();

                playerone.add(cards[rollStrings[0]]);
                MainActivity.dice.setNumberOfDice(i-1);
                MainActivity.dice.roll(1);
                rollStrings = MainActivity.dice.getRolls();
                playertwo.add(cards[rollStrings[0]]);

            }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerone.size() > 0 && playertwo.size() > 0) {
                    int pl = (int) playerone.get(0);
                    int p2 = (int) playertwo.get(0);
                    while (pl == p2) {
                        int i = 0;

                            bucket.add(playerone.get(0));
                            bucket.add(playertwo.get(0));
                            playerone.remove(playerone.get(0));
                            playertwo.remove(playertwo.get(0));
                            i++;
                            if ((int) playerone.get(i) > (int) playertwo.get(i)) {
                                for (int k = 0; k < bucket.size(); k++) {
                                    playerone.add(bucket.get(i));

                                }
                                bucket.clear();
                                break;
                            }

                                if ((int) playerone.get(i) < (int) playertwo.get(i)) {
                                    for (int k = 0; k < bucket.size(); k++) {
                                        playertwo.add(bucket.get(i));

                                    }
                                    bucket.clear();
                                    break;
                    }

                        if (pl > p2) {
                            playerone.add(playerone.get(0));
                            playerone.remove(playerone.get(0));
                            playerone.add(playertwo.get(0));
                            playertwo.remove(playertwo.get(0));
                        }
                    if (pl < p2) {
                        playertwo.add(playertwo.get(0));
                        playertwo.remove(playertwo.get(0));
                        playertwo.add(playerone.get(0));
                        playerone.remove(playerone.get(0));
                    }

                }}

                else if (playerone.size() < 0) {

                    p2win += 1;
                }

                else if (playerone.size() > 0) {

                    p1win += 1;
                }

                x = Integer.toString(p1win);
                y = Integer.toString(p2win);


            }
        });
        Log.i(LOG_TAG, "B");

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.listview, playerone);
        //ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(getActivity(), R.layout.listview, playertwo);
        //Display String array
        //setListAdapter(arrayAdapter);



        TextView plwinsx = (TextView) rootView.findViewById(R.id.plwins);
        plwinsx.setText(x);

        TextView p2winsx = (TextView) rootView.findViewById(R.id.p2wins);
        p2winsx.setText(y);






        return rootView;
    }
}
