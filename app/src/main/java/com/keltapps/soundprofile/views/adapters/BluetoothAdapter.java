package com.keltapps.soundprofile.views.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.keltapps.soundprofile.R;
import com.keltapps.soundprofile.fragments.BluetoothFragment;
import com.keltapps.soundprofile.views.BluetoothItem;

import java.util.ArrayList;

/**
 * Created by sergio on 22/10/15 for KelpApps.
 */
public class BluetoothAdapter extends RecyclerView.Adapter<BluetoothAdapter.ViewHolderBluetooth> {
private static ArrayList<BluetoothItem> listBluetooth;
static BluetoothAdapter bluetoothAdapter;

public BluetoothAdapter(ArrayList<BluetoothItem> list) {
        listBluetooth = list;
        bluetoothAdapter = this;
        }

@Override
public ViewHolderBluetooth onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderBluetooth(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.bluetooth_item, parent, false));
        }

@Override
public void onBindViewHolder(ViewHolderBluetooth holder, int position) {
        holder.bindProfile(listBluetooth.get(position));
        }

@Override
public int getItemCount() {
        return listBluetooth.size();
        }


public static class ViewHolderBluetooth extends RecyclerView.ViewHolder {
    TextView textViewBluetooth;
    ImageView imageView;
    CheckBox checkBox;
    CardView cardView;

    public ViewHolderBluetooth(View itemView) {
        super(itemView);
        textViewBluetooth = (TextView) itemView.findViewById(R.id.bluetooth_item_textView);
        imageView = (ImageView) itemView.findViewById(R.id.bluetooth_item_imageview);
        checkBox = (CheckBox) itemView.findViewById(R.id.bluetooth_item_checkbox);
        cardView = (CardView) itemView.findViewById(R.id.bluetooth_item_cardview);
    }

    public void bindProfile(final BluetoothItem bluetoothItem) {
        textViewBluetooth.setText(bluetoothItem.getName());
        if (bluetoothItem.getConnected())
            imageView.setVisibility(View.VISIBLE);
        else
            imageView.setVisibility(View.INVISIBLE);
        checkBox.setChecked(bluetoothItem.getSelected());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked())
                    cardView.setBackgroundColor(BluetoothFragment.bluetoothFragment.getResources().getColor(R.color.grey_300));
                else
                    cardView.setBackgroundColor(BluetoothFragment.bluetoothFragment.getResources().getColor(R.color.white));
                bluetoothItem.setSelected(checkBox.isChecked());
                int from = listBluetooth.indexOf(bluetoothItem);
                listBluetooth.remove(from);
                int to = sortBluetoothItem(bluetoothItem);
                listBluetooth.add(to, bluetoothItem);
                BluetoothAdapter.bluetoothAdapter.notifyItemMoved(from, to);
            }
        });
        if (bluetoothItem.getSelected())
            cardView.setBackgroundColor(BluetoothFragment.bluetoothFragment.getResources().getColor(R.color.grey_300));
        else
            cardView.setBackgroundColor(BluetoothFragment.bluetoothFragment.getResources().getColor(R.color.white));
    }


    //Corregir ordenacion, posibles mas elementos conectados
    private int sortBluetoothItem(BluetoothItem bluetoothItem) {
        if (bluetoothItem.getSelected()) {
            if (bluetoothItem.getConnected())
                return 0;
            for (BluetoothItem item : listBluetooth) {
                if (!item.getSelected())
                    return listBluetooth.indexOf(item);
                if (item.getName().toUpperCase().compareTo(bluetoothItem.getName().toUpperCase()) >= 0 && !item.getConnected())
                    return listBluetooth.indexOf(item);
            }
        } else {
            for (BluetoothItem item : listBluetooth) {
                if (item.getSelected())
                    continue;
                if (bluetoothItem.getConnected())
                    return listBluetooth.indexOf(item);
                if (item.getName().toUpperCase().compareTo(bluetoothItem.getName().toUpperCase()) >= 0 && !item.getConnected())
                    return listBluetooth.indexOf(item);
            }
        }
        return listBluetooth.size();
    }
}
}
