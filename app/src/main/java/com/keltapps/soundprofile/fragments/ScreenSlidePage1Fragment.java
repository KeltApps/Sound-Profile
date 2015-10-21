package com.keltapps.soundprofile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keltapps.soundprofile.R;

/**
 * Created by sergio on 11/10/15 for KelpApps.
 */
public class ScreenSlidePage1Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page_1, container, false);

        return rootView;
    }

}
