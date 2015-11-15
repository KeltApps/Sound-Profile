package com.keltapps.soundprofile.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.keltapps.soundprofile.ProfilesActivity;
import com.keltapps.soundprofile.R;
import com.keltapps.soundprofile.views.DividerItemDecoration;
import com.keltapps.soundprofile.views.WifiItem;
import com.keltapps.soundprofile.views.adapters.WifiAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by sergio on 10/10/15 for KelpApps.
 */
public class WifiFragment extends Fragment {
   //  mCallback;
    public static WifiFragment wifiFragment;
    RecyclerView recyclerView;
    ArrayList<String> listWifiSelected;
    ArrayList<WifiItem> listWifi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        wifiFragment = this;
        View rootView = inflater.inflate(R.layout.fragment_wifi, container, false);
        Bundle bundle = getArguments();
        listWifiSelected = bundle.getStringArrayList("wifiSelectedList");
        final int profileIndex = bundle.getInt("profileIndex");
        setHasOptionsMenu(true);
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    try {
                        OnWifiSelectedListener mCallback = (OnWifiSelectedListener) getActivity();
                        ArrayList<String> listRefresh = new ArrayList<>();
                        for (WifiItem item : listWifi) {
                            if (!item.getSelected())
                                break;
                            listRefresh.add(item.getSSID());
                        }
                        mCallback.onWifiSelected(listRefresh, profileIndex);
                    } catch (ClassCastException e) {
                        throw new ClassCastException(getActivity().toString()
                                + " must implement OnWifiSelectedListener");
                    }
                    return true;
                }
                return false;
            }
        });
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ProfilesActivity.context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean("showAgainWifiBluetooth", true);
        editor.commit();
        boolean bShowAgain = sharedPrefs.getBoolean("showAgainWifiBluetooth", true);
        WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled() && bShowAgain) {
            final Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.dialog_checkbox);
            final CheckBox dialogCheckBox = (CheckBox) dialog.findViewById(R.id.dialog_checkbox_checkbox);
            Button dialogButtonPositive = (Button) dialog.findViewById(R.id.dialog_checkbox_positivebutton);
            dialogButtonPositive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    dialogDontShowAgain(dialogCheckBox);
                    dialog.dismiss();
                }
            });
            Button dialogButtonNegative = (Button) dialog.findViewById(R.id.dialog_checkbox_negativebutton);
            dialogButtonNegative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogDontShowAgain(dialogCheckBox);
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_wifi_recyclerview);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        final WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        WifiItem wifiItemCurrent = null;
        listWifi = new ArrayList<>();
        if (!wifiManager.isWifiEnabled()) {
            for (String sWifi : listWifiSelected) {
                WifiItem wifiItem = new WifiItem();
                wifiItem.setSSID(sWifi);
                wifiItem.setConnected(false);
                wifiItem.setSelected(true);
                listWifi.add(wifiItem);
            }
        } else {
            List<WifiConfiguration> listWifiConfiguration = wifiManager.getConfiguredNetworks();
            String currentSSID = wifiManager.getConnectionInfo().getSSID();
            currentSSID = currentSSID.replaceAll("\"", "");
            for (WifiConfiguration wifi : listWifiConfiguration) {
                WifiItem wifiItem = new WifiItem();
                String ssid = wifi.SSID;
                ssid = ssid.replaceAll("\"", "");
                wifiItem.setSSID(ssid);
                wifiItem.setSelected(false);
                for (String wifiSelected : listWifiSelected) {
                    if (wifiSelected.equals(wifiItem.getSSID())) {
                        wifiItem.setSelected(true);
                        break;
                    }
                }
                if (currentSSID.equals(ssid)) {
                    wifiItem.setConnected(true);
                    wifiItemCurrent = wifiItem;
                    continue;
                }
                wifiItem.setConnected(false);
                listWifi.add(wifiItem);
            }
        }
        Collections.sort(listWifi, new WifiItemComparator());
        int wifiSelected = listWifiSelected.size();
        if (wifiItemCurrent != null) {
            if (wifiItemCurrent.getSelected())
                listWifi.add(0, wifiItemCurrent);
            else {
                listWifi.add(wifiSelected, wifiItemCurrent);
                wifiSelected++;
            }
        }
        WifiAdapter wifiAdapter = new WifiAdapter(listWifi);
        recyclerView.setAdapter(wifiAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void dialogDontShowAgain(CheckBox checkBox) {
        if (checkBox.isChecked()) {
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ProfilesActivity.context);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putBoolean("showAgainWifiBluetooth", false);
            editor.commit();
        }
    }

    private class WifiItemComparator implements Comparator<WifiItem> {
        public int compare(WifiItem first, WifiItem second) {
            if (first.getSelected() && second.getSelected() || !first.getSelected() && !second.getSelected())
                return first.getSSID().toUpperCase().compareTo(second.getSSID().toUpperCase());
            if (first.getSelected())
                return -1;
            else
                return 1;
        }
    }

    public interface OnWifiSelectedListener {
        void onWifiSelected(ArrayList<String> listWifiSelected, int profilePosition);
    }


}
