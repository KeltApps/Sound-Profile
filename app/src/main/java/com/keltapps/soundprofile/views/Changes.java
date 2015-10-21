package com.keltapps.soundprofile.views;

import android.graphics.drawable.Drawable;

/**
 * Created by sergio on 12/10/15 for KelpApps.
 */
public class Changes {
    Drawable drawable = null;
    String nombre = null;

    public Changes(Drawable drawable, String nombre) {
        this.drawable = drawable;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

}
