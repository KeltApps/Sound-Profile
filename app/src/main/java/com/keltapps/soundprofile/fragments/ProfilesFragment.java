package com.keltapps.soundprofile.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.keltapps.soundprofile.ProfilesActivity;
import com.keltapps.soundprofile.R;
import com.keltapps.soundprofile.ScreenSlidePagerActivity;
import com.keltapps.soundprofile.views.Profile;
import com.keltapps.soundprofile.views.adapters.ProfileAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by sergio on 9/10/15 for KelpApps.
 */
public class ProfilesFragment extends Fragment {
    ArrayList<Profile> listProfileAdapters = null;
    RecyclerView recyclerView;
    ProfileAdapter profileAdapter;
    public static FragmentManager fragmentManager;
    public static ProfilesFragment profilesFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frament_profiles, container, false);
        profilesFragment = this;
        fragmentManager = getFragmentManager();
        if (listProfileAdapters == null)
            listProfileAdapters = new ArrayList<>();
        listProfileAdapters = readState();

/*
        Profile profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba");
        ArrayList<String> listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        listWifiSelected.add("CampingBaltar2");
        listWifiSelected.add("BOLICHE");
        listWifiSelected.add("TERESA");
        listWifiSelected.add("TALKTALK080A7A");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba2");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba3");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba4");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba 5");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba6");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba7");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba8");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba9");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba10");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba11");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba12");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba13");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba14");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba15");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba16");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba17");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba18");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba19");
        listWifiSelected = new ArrayList<>();
        listWifiSelected.add("WLAN_54");
        profile.listWifiSelected = listWifiSelected;
        listProfileAdapters.add(profile);
*//*
        for (Profile profile: listProfileAdapters) {
         profile.listBluetoothPaired = new ArrayList<>();
            profile.listBluetoothPaired.add("CAR MULTIMEDIA");
        }*/

        recyclerView = (RecyclerView) rootView.findViewById(R.id.profiles_recyclerview);
        profileAdapter = new ProfileAdapter(listProfileAdapters);
        recyclerView.setAdapter(profileAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProfilesActivity.context, LinearLayout.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        final FloatingActionButton floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.profiles_floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfilesActivity.context, ScreenSlidePagerActivity.class);
                i.putExtra("cx", (int) (floatingActionButton.getX() + floatingActionButton.getWidth() / 2));
                i.putExtra("cy", (int) (floatingActionButton.getY() + floatingActionButton.getHeight() / 2));
                startActivity(i);
                ((Activity) ProfilesActivity.context).overridePendingTransition(0, 0);
            }
        });

        return rootView;
    }

    public void updateWifiSelected(ArrayList<String> listWifiSelected, int profilePosition) {
        if (profilePosition < 0)
            return;
        listProfileAdapters.get(profilePosition).listWifiSelected = listWifiSelected;
        saveState(listProfileAdapters);
    }

    public void updateBluetoothSelected(ArrayList<String> listBluetoothSelected, int profilePosition) {
        if (profilePosition < 0)
            return;
        listProfileAdapters.get(profilePosition).listBluetoothPaired = listBluetoothSelected;
        saveState(listProfileAdapters);
    }

    @Override
    public void onStop() {
        super.onStop();
        saveState(listProfileAdapters);
    }

    private void saveState(ArrayList<Profile> listProfileAdapters) {
        for (Profile profile : listProfileAdapters)
            profile.setExpandido(false);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ProfilesActivity.context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listProfileAdapters);
        editor.putString("listProfileAdapters", json);
        editor.commit();
    }

    private ArrayList<Profile> readState() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ProfilesActivity.context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("listProfileAdapters", null);
        Type type = new TypeToken<ArrayList<Profile>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
