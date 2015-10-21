package com.keltapps.soundprofile.views.adapters;

import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.keltapps.soundprofile.ProfilesActivity;
import com.keltapps.soundprofile.R;
import com.keltapps.soundprofile.fragments.ProfilesFragment;
import com.keltapps.soundprofile.fragments.WifiFragment;
import com.keltapps.soundprofile.views.ProfileSub;

import java.util.ArrayList;

/**
 * Created by sergio on 30/07/15 for KelpApps.
 */
public class ProfileSubAdapter extends RecyclerView.Adapter<ProfileSubAdapter.ViewHolderProfileSub> {
    private ArrayList<ProfileSub> listProfileSub;

    public ProfileSubAdapter(ArrayList<ProfileSub> list) {
        listProfileSub = list;
    }

    @Override
    public ViewHolderProfileSub onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolderProfileSub(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.profiles_subitem, viewGroup, false));
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


    public static class ViewHolderProfileSub extends RecyclerView.ViewHolder {
        TextView nombre;
        CardView cardView;
        ImageView vWwifi;

        public ViewHolderProfileSub(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.profiles_subitem_textview);
            cardView = (CardView) itemView.findViewById(R.id.profiles_subitem_cardview);
            vWwifi =(ImageView) itemView.findViewById(R.id.profiles_subitem_imageView);
        }

        public void bindProfile(final ProfileSub item) {
           // nombre.setText(item.getNombre());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                vWwifi.setTransitionName(item.getNombre());
            }
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProfilesFragment fragmentOne = ProfilesFragment.profilesFragment;
                    WifiFragment fragmentTwo = new WifiFragment();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {


                            String imageTransitionName = vWwifi.getTransitionName();



                        Bundle bundle = new Bundle();
                        bundle.putString("TRANS_NAME", imageTransitionName);
                        fragmentTwo.setArguments(bundle);



                        Transition changeTransform = TransitionInflater.from(ProfilesActivity.context).
                                inflateTransition(R.transition.change_image_transform);
                        Transition explodeTransform = TransitionInflater.from(ProfilesActivity.context).
                                inflateTransition(android.R.transition.explode);
                        // Setup exit transition on first fragment
                        fragmentOne.setSharedElementReturnTransition(changeTransform);
                        fragmentOne.setExitTransition(explodeTransform);

                        // Setup enter transition on second fragment
                        fragmentTwo.setSharedElementEnterTransition(changeTransform);
                        fragmentTwo.setEnterTransition(explodeTransform);

                        // Find the shared element (in Fragment A)

                        // Add second fragment by replacing first
                        FragmentTransaction ft = ProfilesFragment.fragmentManager.beginTransaction()
                                .replace(R.id.profiles_fragment_container, fragmentTwo)
                                .addToBackStack("transaction")
                                .addSharedElement(vWwifi, "wifiImageView");
                        // Apply the transaction
                        ft.commit();

                    }




/*

                    WifiFragment wifiFragment = new WifiFragment();
                    FragmentTransaction fragmentTransaction = ProfilesFragment.fragmentManager.beginTransaction();
                    if (Build.VERSION.SDK_INT >= 21)
                        fragmentTransaction.addSharedElement(vWwifi, "wifiImageView");
                    fragmentTransaction.replace(R.id.profiles_fragment_container, wifiFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();*/
                }
            });
        }
    }

}

