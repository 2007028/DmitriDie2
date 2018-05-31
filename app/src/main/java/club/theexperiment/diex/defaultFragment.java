package club.theexperiment.diex;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import android.net.Uri;


public class defaultFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Hide back button
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        super.onCreateView(inflater, container, savedInstanceState);
        //Link View and Buttons
        View rootView = inflater.inflate(R.layout.fragment_default, container, false);

        Button mD2Button = (Button) rootView.findViewById(R.id.d2Button);
        //Create method to execute on button press
        mD2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set Die instance to a Preset with specified number of sides
                MainActivity.dice = new dPreset(2);
                //videoView.start();
                //Load rolling screen fragment
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new dPresetFragment());
                ft.commit();
            }

        });
        Button mD4Button = (Button) rootView.findViewById(R.id.d4Button);
        mD4Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.dice = new dPreset(4);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new dPresetFragment());
                ft.commit();
            }

        });
        Button mD6Button = (Button) rootView.findViewById(R.id.d6Button);
        mD6Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.dice = new dPreset(6);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new dPresetFragment());
                ft.commit();
            }

        });
        Button mD8Button = (Button) rootView.findViewById(R.id.d8Button);
        mD8Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.dice = new dPreset(8);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new dPresetFragment());
                ft.commit();
            }

        });
        Button mD10Button = (Button) rootView.findViewById(R.id.d10Button);
        mD10Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.dice = new dPreset(10);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new dPresetFragment());
                ft.commit();
            }

        });
        Button mD12Button = (Button) rootView.findViewById(R.id.d12Button);
        mD12Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.dice = new dPreset(12);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new dPresetFragment());
                ft.commit();
            }

        });
        Button mD20Button = (Button) rootView.findViewById(R.id.d20Button);
        mD20Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.dice = new dPreset(20);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new dPresetFragment());
                ft.commit();
            }

        });

        Button mPyanitsaButton = (Button) rootView.findViewById(R.id.pButton);
        mPyanitsaButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new PyanitsaFragment());
                ft.commit();
            }

        });

        Button mCustomButton = (Button) rootView.findViewById(R.id.customButton);
        mCustomButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Load fragment for custom dice
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new dCustomFragment());
                ft.commit();
            }

        });




        return rootView;
    }
}
