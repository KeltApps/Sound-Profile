package com.keltapps.soundprofile.views;

import java.util.ArrayList;

/**
 * Created by sergio on 21/07/15.
 */
public class Profile {
    String nombre = null;
    Boolean activado = false;
    Boolean expandido = false;
    Boolean timeActive = false;
    String initialTime = null;
    String finalTime = null;

    public Boolean[] days = new Boolean[7];

    public ArrayList<String> listWifiSelected = null;
    public ArrayList<String> listBluetoothPaired = null;
    public ArrayList<ProfileSub> listProfileSubAdapters = null;

    public Boolean getExpandido() {
        return expandido;
    }

    public void setExpandido(Boolean expandido) {
        this.expandido = expandido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivado() {
        return activado;
    }

    public void setActivado(Boolean activado) {
        this.activado = activado;
    }

    public Boolean getTimeActive() {
        return timeActive;
    }

    public void setTimeActive(Boolean timeActive) {
        this.timeActive = timeActive;
    }

    public String getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(String initialTime) {
        this.initialTime = initialTime;
    }

    public String getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(String finalTime) {
        this.finalTime = finalTime;
    }

}
