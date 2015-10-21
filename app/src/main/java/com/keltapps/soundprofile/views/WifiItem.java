package com.keltapps.soundprofile.views;

/**
 * Created by sergio on 18/10/15 for KelpApps.
 */
public class WifiItem {
    String SSID;
    Boolean connected;
    Boolean selected;

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
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
