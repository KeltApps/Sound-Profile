package com.keltapps.soundprofile;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.keltapps.soundprofile.fragments.BluetoothFragment;
import com.keltapps.soundprofile.fragments.ProfilesFragment;
import com.keltapps.soundprofile.fragments.WifiFragment;

import java.util.ArrayList;


public class ProfilesActivity extends AppCompatActivity implements WifiFragment.OnWifiSelectedListener,BluetoothFragment.OnBluetoothSelectedListener {
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);
        context = this;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ProfilesFragment profilesFragment = new ProfilesFragment();
        fragmentTransaction.add(R.id.profiles_fragment_container, profilesFragment, "Profiles");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profiles, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWifiSelected(ArrayList<String> listWifiSelected, int profilePosition) {
        ProfilesFragment profilesFragment = (ProfilesFragment)
                getFragmentManager().findFragmentByTag("Profiles");

        if (profilesFragment != null) {
            profilesFragment.updateWifiSelected(listWifiSelected, profilePosition);
            getFragmentManager().popBackStack();
        } else {
            // Otherwise, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected article
            ProfilesFragment newProfilesFragment = new ProfilesFragment();
            Bundle args = new Bundle();
            args.putStringArrayList("listWifiSelected", listWifiSelected);
            args.putInt("profilePosition", profilePosition);
            newProfilesFragment.setArguments(args);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.profiles_fragment_container, newProfilesFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }


    @Override
    public void onBluetoothSelected(ArrayList<String> listBluetoothSelected, int profilePosition) {
        ProfilesFragment profilesFragment = (ProfilesFragment)
                getFragmentManager().findFragmentByTag("Profiles");

        if (profilesFragment != null) {
            profilesFragment.updateBluetoothSelected(listBluetoothSelected, profilePosition);
            getFragmentManager().popBackStack();
        } else {
            // Otherwise, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected article
            ProfilesFragment newProfilesFragment = new ProfilesFragment();
            Bundle args = new Bundle();
            args.putStringArrayList("listBluetoothSelected", listBluetoothSelected);
            args.putInt("profilePosition", profilePosition);
            newProfilesFragment.setArguments(args);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.profiles_fragment_container, newProfilesFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }
}
