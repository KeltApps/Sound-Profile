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
import com.keltapps.soundprofile.fragments.WifiFragment;
import com.keltapps.soundprofile.views.WifiItem;

import java.util.ArrayList;

/**
 * Created by sergio on 11/10/15 for KelpApps.
 */
public class WifiAdapter extends RecyclerView.Adapter<WifiAdapter.ViewHolderWifi> {
    private static ArrayList<WifiItem> listWifi;
    static WifiAdapter wifiAdapter;

    public WifiAdapter(ArrayList<WifiItem> list) {
        listWifi = list;
        wifiAdapter = this;
    }

    @Override
    public ViewHolderWifi onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderWifi(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wifi_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolderWifi holder, int position) {
        holder.bindProfile(listWifi.get(position));
    }

    @Override
    public int getItemCount() {
        return listWifi.size();
    }


    public static class ViewHolderWifi extends RecyclerView.ViewHolder {
        TextView textViewWifi;
        ImageView imageView;
        CheckBox checkBox;
        CardView cardView;

        public ViewHolderWifi(View itemView) {
            super(itemView);
            textViewWifi = (TextView) itemView.findViewById(R.id.wifi_item_textView);
            imageView = (ImageView) itemView.findViewById(R.id.wifi_item_imageview);
            checkBox = (CheckBox) itemView.findViewById(R.id.wifi_item_checkbox);
            cardView = (CardView) itemView.findViewById(R.id.wifi_item_cardview);
        }

        public void bindProfile(final WifiItem wifiItem) {
            textViewWifi.setText(wifiItem.getSSID());
            if (wifiItem.getConnected())
                imageView.setVisibility(View.VISIBLE);
            else
                imageView.setVisibility(View.INVISIBLE);
            checkBox.setChecked(wifiItem.getSelected());
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked())
                        cardView.setBackgroundColor(WifiFragment.wifiFragment.getResources().getColor(R.color.grey_300));
                    else
                        cardView.setBackgroundColor(WifiFragment.wifiFragment.getResources().getColor(R.color.white));
                    wifiItem.setSelected(checkBox.isChecked());
                    int from = listWifi.indexOf(wifiItem);
                    listWifi.remove(from);
                    int to = sortWifiItem(wifiItem);
                    listWifi.add(to, wifiItem);
                    WifiAdapter.wifiAdapter.notifyItemMoved(from, to);
                }
            });
            if (wifiItem.getSelected())
                cardView.setBackgroundColor(WifiFragment.wifiFragment.getResources().getColor(R.color.grey_300));
            else
                cardView.setBackgroundColor(WifiFragment.wifiFragment.getResources().getColor(R.color.white));
        }

        private int sortWifiItem(WifiItem wifiItem) {
            if (wifiItem.getSelected()) {
                if (wifiItem.getConnected())
                    return 0;
                for (WifiItem item : listWifi) {
                    if (!item.getSelected())
                        return listWifi.indexOf(item);
                    if (item.getSSID().toUpperCase().compareTo(wifiItem.getSSID().toUpperCase()) >= 0 && !item.getConnected())
                        return listWifi.indexOf(item);
                }
            } else {
                for (WifiItem item : listWifi) {
                    if (item.getSelected())
                        continue;
                    if (wifiItem.getConnected())
                        return listWifi.indexOf(item);
                    if (item.getSSID().toUpperCase().compareTo(wifiItem.getSSID().toUpperCase()) >= 0 && !item.getConnected())
                        return listWifi.indexOf(item);
                }
            }
            return listWifi.size();
        }
    }
}
