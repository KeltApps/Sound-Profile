package com.keltapps.soundprofile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.keltapps.soundprofile.ProfilesActivity;
import com.keltapps.soundprofile.R;
import com.keltapps.soundprofile.views.Changes;
import com.keltapps.soundprofile.views.adapters.ChangesAdapter;

import java.util.ArrayList;

/**
 * Created by sergio on 11/10/15 for KelpApps.
 */
public class ScreenSlidePage2Fragment extends Fragment {
    ArrayList<Changes> listChanges = null;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page_2, container, false);


        if(listChanges == null)
            listChanges = new ArrayList<Changes>();

        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));
        listChanges.add(new Changes(getResources().getDrawable(R.drawable.ic_network_wifi_purple_800_24dp),"Wifi"));

        recyclerView = (RecyclerView) rootView.findViewById(R.id.slide_page_2_recyclerview);
        ChangesAdapter changesAdapter = new ChangesAdapter(listChanges);
        recyclerView.setAdapter(changesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProfilesActivity.context, LinearLayout.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

}