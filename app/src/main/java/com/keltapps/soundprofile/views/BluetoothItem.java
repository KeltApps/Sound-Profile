package com.keltapps.soundprofile.views;

/**
 * Created by sergio on 22/10/15 for KelpApps.
 */
public class BluetoothItem {
    String name;
    Boolean connected;
    Boolean selected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }


    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
