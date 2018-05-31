package club.theexperiment.diex;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    //Create Die instance to use for rolls
    public static Die dice;
    public static String PACKAGE_NAME;
    public Uri uri;

    //Override onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load fragment_default onto screen
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_frame, new defaultFragment());
        fragmentTransaction.commit();
        PACKAGE_NAME = getApplicationContext().getPackageName();
    }

    //Method to call when back button pressed
    public boolean onOptionsItemSelected(MenuItem item){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, new defaultFragment());
        fragmentTransaction.commit();
        return true;

    }
}
