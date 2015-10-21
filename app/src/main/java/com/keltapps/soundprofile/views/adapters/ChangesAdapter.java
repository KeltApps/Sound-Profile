package com.keltapps.soundprofile.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keltapps.soundprofile.R;
import com.keltapps.soundprofile.views.Changes;

import java.util.ArrayList;

/**
 * Created by sergio on 12/10/15 for KelpApps.
 */
public class ChangesAdapter extends RecyclerView.Adapter<ChangesAdapter.ViewHolderChangesAdapter>{
private ArrayList<Changes> listChanges;

public ChangesAdapter(ArrayList<Changes>list){
        listChanges=list;
        }

@Override
public ViewHolderChangesAdapter onCreateViewHolder(ViewGroup viewGroup,int i){
        return new ViewHolderChangesAdapter(LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.changes_item,viewGroup,false));
        }

@Override
public void onBindViewHolder(ViewHolderChangesAdapter viewHolderChangesAdapter,int i){
        Changes item=listChanges.get(i);
        viewHolderChangesAdapter.bindProfile(item);
        }


@Override
public int getItemCount(){
        return listChanges.size();
        }


public static class ViewHolderChangesAdapter extends RecyclerView.ViewHolder {

    public ViewHolderChangesAdapter(View itemView) {
        super(itemView);
    }

    public void bindProfile(final Changes item) {

    }
}

}

