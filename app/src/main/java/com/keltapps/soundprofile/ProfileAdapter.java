package com.keltapps.soundprofile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sergio on 21/07/15.
 */
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolderProfile> implements View.OnTouchListener {
    private ArrayList<Profile> listProfile;
    private View.OnTouchListener listener;
    private RecyclerView recyclerView;

    public ProfileAdapter(ArrayList<Profile> list, RecyclerView recycler) {
        listProfile = list;
        recyclerView = recycler;
    }

    @Override
    public ViewHolderProfile onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_profiles_item, viewGroup, false);
        itemView.setOnTouchListener(this);
        ViewHolderProfile viewHolderProfile = new ViewHolderProfile(itemView);
        return viewHolderProfile;
    }

    @Override
    public void onBindViewHolder(ViewHolderProfile viewHolderProfile, int i) {
        Profile item = listProfile.get(i);
        viewHolderProfile.bindProfile(item);
    }


    @Override
    public int getItemCount() {
        return listProfile.size();
    }

    public void setOnTouchListener(View.OnTouchListener listener) {
        this.listener = listener;
    }

    private int mOriginalHeight = 0;
    private View vAnterior = null;
    @Override
    public boolean onTouch(final View v, MotionEvent event) {
        if (listener != null) {
            ViewHolderProfile holder = (ViewHolderProfile) v.getTag();
            holder.setIsRecyclable(false);
            if (event.getAction() == MotionEvent.ACTION_UP) {

              if (mOriginalHeight == 0) {
                    mOriginalHeight = v.getHeight();
                }

                ValueAnimator valueAnimator;
                ValueAnimator valueAnimatorAnterior = null;
                if (v.getHeight() < (mOriginalHeight + (int) (mOriginalHeight * 1.5))) {
                    ViewGroup group = (ViewGroup) v;
                    group = (ViewGroup) group.getChildAt(0);
                    LinearLayout linearLayout = (LinearLayout) group.getChildAt(3);
                    linearLayout.setVisibility(View.VISIBLE);
                    valueAnimator = ValueAnimator.ofInt(mOriginalHeight, mOriginalHeight + (int) (mOriginalHeight * 1.5));
                    listProfile.get(recyclerView.getChildAdapterPosition(v)).setExpandido(true);
                    listProfile.get(recyclerView.getChildAdapterPosition(v)).setHeightExpandido(mOriginalHeight * 1.5);
                    if(vAnterior != null) {
                        listProfile.get(recyclerView.getChildAdapterPosition(vAnterior)).setExpandido(false);
                        valueAnimatorAnterior = ValueAnimator.ofInt(mOriginalHeight + (int) (mOriginalHeight * 1.5), mOriginalHeight);
                    }else
                        vAnterior = v;
                } else {
                    valueAnimator = ValueAnimator.ofInt(mOriginalHeight + (int) (mOriginalHeight * 1.5), mOriginalHeight);
                    listProfile.get(recyclerView.getChildAdapterPosition(v)).setExpandido(false);
                }
                valueAnimator.setDuration(300);
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Integer value = (Integer) animation.getAnimatedValue();
                        v.getLayoutParams().height = value.intValue();
                        v.requestLayout();
                    }
                });
                if(valueAnimatorAnterior != null) {
                    valueAnimatorAnterior.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Integer value = (Integer) animation.getAnimatedValue();
                            vAnterior.getLayoutParams().height = value.intValue();
                            vAnterior.requestLayout();
                        }
                    });

                    valueAnimatorAnterior.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            vAnterior = v;
                        }
                    });
                    valueAnimatorAnterior.start();
                }
                valueAnimator.start();

            }
            return listener.onTouch(v, event);
        }
        return false;
    }

    public static class ViewHolderProfile extends RecyclerView.ViewHolder {
        TextView nombre;
        SwitchCompat oSwitch;
        Button button;
        RelativeLayout relativeLayout;
        LinearLayout linearLayoutGone;
        public ViewHolderProfile(View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.profiles_item_relativelayout);
            relativeLayout.setTag(this);
            nombre = (TextView) itemView.findViewById(R.id.profiles_item_textview);
            oSwitch = (SwitchCompat) itemView.findViewById(R.id.profiles_item_switch);
            button = (Button) itemView.findViewById(R.id.profiles_item_buttonExapnd);
            linearLayoutGone = (LinearLayout) itemView.findViewById(R.id.profiles_item_linearlayout_gone);
        }

        public void bindProfile(Profile item) {
            nombre.setText(item.getNombre());
            oSwitch.setChecked(item.getActivado());

            if (item.getExpandido()) {
                linearLayoutGone.setVisibility(View.VISIBLE);
                linearLayoutGone.getLayoutParams().height = ((Double)item.getHeightExpandido()).intValue();
                linearLayoutGone.requestLayout();
            }
            else
               linearLayoutGone.setVisibility(View.GONE);
        }
    }
}
