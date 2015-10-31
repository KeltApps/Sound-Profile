package com.keltapps.soundprofile.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;

import com.keltapps.soundprofile.ProfilesActivity;
import com.keltapps.soundprofile.R;
import com.keltapps.soundprofile.views.BluetoothItem;
import com.keltapps.soundprofile.views.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

/**
 * Created by sergio on 21/10/15 for KelpApps.
 */
public class BluetoothFragment extends Fragment {
    OnBluetoothSelectedListener mCallback;
    public static BluetoothFragment bluetoothFragment;
    RecyclerView recyclerView;
    ArrayList<String> listBluetoothSelected;
    ArrayList<BluetoothItem> listBluetooth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bluetoothFragment = this;
        View rootView = inflater.inflate(R.layout.fragment_bluetooth, container, false);
        Bundle bundle = getArguments();
        listBluetoothSelected = bundle.getStringArrayList("bluetoothPairedList");
        final int profileIndex = bundle.getInt("profileIndex");
        setHasOptionsMenu(true);
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    try {
                        mCallback = (OnBluetoothSelectedListener) getActivity();
                        ArrayList<String> listRefresh = new ArrayList<>();
                        for (BluetoothItem item : listBluetooth) {
                            if (!item.getSelected())
                                break;
                            listRefresh.add(item.getName());
                        }
                        mCallback.onBluetoothSelected(listRefresh, profileIndex);
                    } catch (ClassCastException e) {
                        throw new ClassCastException(getActivity().toString()
                                + " must implement OnBluetoothSelectedListener");
                    }
                    return true;
                }
                return false;
            }
        });
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(ProfilesActivity.context);
        boolean bShowAgain = sharedPrefs.getBoolean("showAgainWifiBluetooth", true);
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled() && bShowAgain) {
            final Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.dialog_checkbox);
            TextView dialogTextView = (TextView) dialog.findViewById(R.id.dialog_checkbox_textview);
            dialogTextView.setText(getActivity().getResources().getString(R.string.dialog_bluetooth_enabled));
            final CheckBox dialogCheckBox = (CheckBox) dialog.findViewById(R.id.dialog_checkbox_checkbox);
            Button dialogButtonPositive = (Button) dialog.findViewById(R.id.dialog_checkbox_positivebutton);
            dialogButtonPositive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
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
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_bluetooth_recyclerview);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        listBluetooth = new ArrayList<>();
        Set<BluetoothDevice> pairedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            for (String sBluetooth : listBluetoothSelected) {
                BluetoothItem bluetoothItem = new BluetoothItem();
                bluetoothItem.setName(sBluetooth);
                bluetoothItem.setConnected(false);
                bluetoothItem.setSelected(true);
                listBluetooth.add(bluetoothItem);
            }
        } else {
            for (BluetoothDevice bluetooth : pairedDevices) {
                BluetoothItem bluetoothItem = new BluetoothItem();
                String name = bluetooth.getName();
                bluetoothItem.setName(name);
                bluetoothItem.setSelected(false);
                for (String bluetoothSelected : listBluetoothSelected) {
                    if (bluetoothSelected.equals(bluetoothItem.getName())) {
                        bluetoothItem.setSelected(true);
                        break;
                    }
                }
                bluetoothItem.setConnected(bluetooth.getBondState() == BluetoothDevice.BOND_BONDED);
                listBluetooth.add(bluetoothItem);
            }
        }
        Collections.sort(listBluetooth, new BluetoothItemComparator());
        com.keltapps.soundprofile.views.adapters.BluetoothAdapter bluetoothAdapter = new com.keltapps.soundprofile.views.adapters.BluetoothAdapter(listBluetooth);
        recyclerView.setAdapter(bluetoothAdapter);
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

    private class BluetoothItemComparator implements Comparator<BluetoothItem> {
        public int compare(BluetoothItem first, BluetoothItem second) {
            if (first.getSelected() && second.getSelected() || !first.getSelected() && !second.getSelected())
                return first.getName().toUpperCase().compareTo(second.getName().toUpperCase());
            if (first.getSelected())
                return -1;
            else
                return 1;
        }
    }
    public interface OnBluetoothSelectedListener {
        void onBluetoothSelected(ArrayList<String> listBluetoothSelected, int profilePosition);
    }

}
