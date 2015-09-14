package com.keltapps.soundprofile;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sergio on 30/07/15 for KelpApps.
 */
public class ProfileSubAdapter extends RecyclerView.Adapter<ProfileSubAdapter.ViewHolderProfileSub> implements View.OnTouchListener {
    private ArrayList<ProfileSub> listProfileSub;
    public boolean clicItem;

    public ProfileSubAdapter(ArrayList<ProfileSub> list) {
        listProfileSub = list;
    }

    @Override
    public ViewHolderProfileSub onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_profiles_subitem, viewGroup, false);
        itemView.setOnTouchListener(this);
        return new ViewHolderProfileSub(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolderProfileSub viewHolderProfileSub, int i) {
        ProfileSub item = listProfileSub.get(i);
        viewHolderProfileSub.bindProfile(item);
    }


    @Override
    public int getItemCount() {
        return listProfileSub.size();
    }

    @Override
    public boolean onTouch(final View v, MotionEvent event) {
        Log.v("prueba", "On Touch Sub");

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            clicItem = true;
            v.setBackgroundColor(ProfilesActivity.context.getResources().getColor(R.color.purple_100));
        } else {
            v.setBackgroundColor(ProfilesActivity.context.getResources().getColor(R.color.purple_200));
        }
        if (event.getAction() == MotionEvent.ACTION_UP)
            clicItem = false;
        return true;
    }

    public static class ViewHolderProfileSub extends RecyclerView.ViewHolder {
        TextView nombre;

        public ViewHolderProfileSub(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.profiles_subitem_textview);
        }

        public void bindProfile(final ProfileSub item) {
            nombre.setText(item.getNombre());
        }
    }
}

