package com.keltapps.soundprofile;

import java.util.ArrayList;

/**
 * Created by sergio on 21/07/15.
 */
public class Profile {
    String nombre = null;
    Boolean activado = false;
    Boolean expandido = false;
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
}
