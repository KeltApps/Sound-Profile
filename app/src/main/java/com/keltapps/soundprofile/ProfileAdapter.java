package com.keltapps.soundprofile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Random;

import static android.animation.ValueAnimator.ofInt;

/**
 * Created by sergio on 21/07/15 for KelpApps.
 */
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolderProfile> implements View.OnTouchListener {
    private static ArrayList<Profile> listProfile;
    private static RecyclerView recyclerView;
    private static int heightRowSubItem;
    private static int heightMore;

    public ProfileAdapter(ArrayList<Profile> list, RecyclerView recycler) {
        listProfile = list;
        recyclerView = recycler;

    }

    @Override
    public ViewHolderProfile onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_profiles_item, viewGroup, false);
        itemView.setOnTouchListener(this);
        heightRowSubItem = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25,
                ProfilesActivity.context.getResources().getDisplayMetrics());
        heightRowSubItem += (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15,
                ProfilesActivity.context.getResources().getDisplayMetrics());
        heightMore = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
                ProfilesActivity.context.getResources().getDisplayMetrics());
        return new ViewHolderProfile(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolderProfile viewHolderProfile, int i) {
        Profile item = listProfile.get(i);
        viewHolderProfile.bindProfile(item);
    }

    @Override
    public int getItemCount() {
        return listProfile.size();
    }

    private int mOriginalHeight = 0;
    private static View vAnterior = null;
    private static ViewHolderProfile holderAnterior;

    @Override
    public boolean onTouch(final View v, MotionEvent event) {
        Log.v("prueba", "On Touch");
        final Profile profile = listProfile.get(recyclerView.getChildAdapterPosition(v));
        final ViewHolderProfile holder = (ViewHolderProfile) v.getTag();
        holder.setIsRecyclable(false);
        if (event.getAction() == MotionEvent.ACTION_UP) {
            ValueAnimator valueAnimator;
            ValueAnimator valueAnimatorAnterior = null;
            if (mOriginalHeight == 0) {
                mOriginalHeight = v.getHeight();
            }
            if (!profile.getExpandido()) {
                //  holder.recyclerViewSub.setVisibility(View.VISIBLE);
                holder.coordinatorLayout.setVisibility(View.VISIBLE);
                profile.setExpandido(true);
                valueAnimator = heightImageViewMore(mOriginalHeight, mOriginalHeight + holder.heightTotal, v, holder, profile.getExpandido(), false, null, null);
                if (vAnterior != null) {
                    listProfile.get(recyclerView.getChildAdapterPosition(vAnterior)).setExpandido(false);
                    valueAnimatorAnterior = heightImageViewMore(mOriginalHeight + holderAnterior.heightTotal, mOriginalHeight, vAnterior, holderAnterior, false, true, v, holder);
                } else {
                    vAnterior = v;
                    holderAnterior = holder;
                }
            } else {
                profile.setExpandido(false);
                valueAnimator = heightImageViewMore(mOriginalHeight + holder.heightTotal, mOriginalHeight, v, holder, profile.getExpandido(), false, null, null);
                vAnterior = null;
            }
            if (valueAnimatorAnterior != null) {
                valueAnimatorAnterior.start();
            }
            valueAnimator.start();
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            ((CardView) v).setCardBackgroundColor(ProfilesActivity.context.getResources().getColor(R.color.purple_050));
        else if (!profile.getExpandido()) {
            ((CardView) v).setCardBackgroundColor(ProfilesActivity.context.getResources().getColor(R.color.white));
        }
        return true;
    }

    private ValueAnimator rotacionImageViewMore(boolean clockwise, final ImageView imageViewMore) {
        ValueAnimator valueAnimatorRotation;
        if (clockwise)
            valueAnimatorRotation = ofInt(0, 180);
        else
            valueAnimatorRotation = ofInt(180, 0);
        valueAnimatorRotation.setDuration(300);
        valueAnimatorRotation.setInterpolator(new LinearInterpolator());
        valueAnimatorRotation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                imageViewMore.setRotation((int) animation.getAnimatedValue() * 1.0f);
                imageViewMore.requestLayout();
            }
        });
        return valueAnimatorRotation;
    }

    private ValueAnimator heightImageViewMore(int from, int to, final View view, final ViewHolderProfile holder, final boolean expandido, final boolean anterior, final View viewNuevo, final ViewHolderProfile holderNuevo) {
        ValueAnimator valueAnimator = ofInt(from, to);
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new LinearInterpolator());
        if (anterior)
            ((CardView) view).setCardBackgroundColor(ProfilesActivity.context.getResources().getColor(R.color.white));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.getLayoutParams().height = (Integer) animation.getAnimatedValue();
                view.requestLayout();
                if (expandido && !anterior)
                    ((CardView) view).setCardBackgroundColor(ProfilesActivity.context.getResources().getColor(R.color.purple_050));
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // ((CardView)view).setCardElevation(10);
                if (expandido)
                    ((CardView) view).setCardBackgroundColor(ProfilesActivity.context.getResources().getColor(R.color.purple_050));
                else
                    // holder.recyclerViewSub.setVisibility(View.INVISIBLE);
                    holder.coordinatorLayout.setVisibility(View.INVISIBLE);
                if (anterior) {
                    vAnterior = viewNuevo;
                    holderAnterior = holderNuevo;
                }
            }
        });
        ValueAnimator valueAnimatorRotationAnterior;
        valueAnimatorRotationAnterior = rotacionImageViewMore(expandido, holder.imageViewMore);
        valueAnimatorRotationAnterior.start();
        return valueAnimator;
    }

    public static class ViewHolderProfile extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombre;
        SwitchCompat oSwitch;
        CardView cardView;
        ImageView imageViewMore;
        RecyclerView recyclerViewSub;
        CoordinatorLayout coordinatorLayout;
        int heightTotal = 0;
        Button buttonEditTime1;
        Button buttonEditTime2;
        ImageButton imagebuttonWifi;
        ImageButton imagebuttonBluetooth;

        public ViewHolderProfile(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.profiles_item_cardview);
            cardView.setTag(this);
            nombre = (TextView) itemView.findViewById(R.id.profiles_item_textview);
            oSwitch = (SwitchCompat) itemView.findViewById(R.id.profiles_item_switch);
            imageViewMore = (ImageView) itemView.findViewById(R.id.profiles_item_imageviewMore);
            recyclerViewSub = (RecyclerView) itemView.findViewById(R.id.profiles_item_recyclerview);
            coordinatorLayout = (CoordinatorLayout) itemView.findViewById(R.id.profiles_item_coordinatorlayout);
            Button bLunes = (Button) itemView.findViewById(R.id.profiles_item_daysL);
            bLunes.setOnClickListener(this);
            Button bMartes = (Button) itemView.findViewById(R.id.profiles_item_daysM);
            bMartes.setOnClickListener(this);
            Button bMiercoles = (Button) itemView.findViewById(R.id.profiles_item_daysX);
            bMiercoles.setOnClickListener(this);
            Button bJueves = (Button) itemView.findViewById(R.id.profiles_item_daysJ);
            bJueves.setOnClickListener(this);
            Button bViernes = (Button) itemView.findViewById(R.id.profiles_item_daysV);
            bViernes.setOnClickListener(this);
            Button bSabado = (Button) itemView.findViewById(R.id.profiles_item_daysS);
            bSabado.setOnClickListener(this);
            Button bDomingo = (Button) itemView.findViewById(R.id.profiles_item_daysD);
            bDomingo.setOnClickListener(this);
            buttonEditTime1 = (Button) itemView.findViewById(R.id.profiles_item_buttonEditTime1);
            buttonEditTime2 = (Button) itemView.findViewById(R.id.profiles_item_buttonEditTime2);
            imagebuttonWifi = (ImageButton) itemView.findViewById(R.id.profiles_item_imagebuttonEditWifi);
            imagebuttonBluetooth = (ImageButton) itemView.findViewById(R.id.profiles_item_imagebuttonEditBluetooth);
        }

        public void bindProfile(final Profile item) {
            nombre.setText(item.getNombre());
            oSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.setActivado(isChecked);
                }
            });
            Random rand = new Random();
            int randomNum = rand.nextInt((10 - 1) + 1) + 1;

            oSwitch.setChecked(item.getActivado());
            item.listProfileSubAdapters = new ArrayList<ProfileSub>();
            ProfileSub profileSub = new ProfileSub();
            profileSub.setNombre("Prueba item");
            item.listProfileSubAdapters.add(profileSub);
            profileSub = new ProfileSub();
            profileSub.setNombre("Prueba item 2");
            item.listProfileSubAdapters.add(profileSub);
            profileSub = new ProfileSub();
            profileSub.setNombre("Prueba item 3");
            item.listProfileSubAdapters.add(profileSub);
            profileSub = new ProfileSub();
            profileSub.setNombre("Prueba item 4");
            item.listProfileSubAdapters.add(profileSub);
            if (randomNum > 4) {
                profileSub = new ProfileSub();
                profileSub.setNombre("Prueba item 5");
                item.listProfileSubAdapters.add(profileSub);
                profileSub = new ProfileSub();
                profileSub.setNombre("Prueba item 6");
                item.listProfileSubAdapters.add(profileSub);
                profileSub = new ProfileSub();
                profileSub.setNombre("Prueba item 7");
                item.listProfileSubAdapters.add(profileSub);
                profileSub = new ProfileSub();
                profileSub.setNombre("Prueba item 8");
                item.listProfileSubAdapters.add(profileSub);
            }
            final ProfileSubAdapter profileSubAdapter = new ProfileSubAdapter(item.listProfileSubAdapters);
            recyclerViewSub.setAdapter(profileSubAdapter);
            recyclerViewSub.setLayoutManager(new LinearLayoutManager(ProfilesActivity.context, LinearLayout.VERTICAL, false));
            recyclerViewSub.setItemAnimator(new DefaultItemAnimator());


            recyclerViewSub.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                @Override
                public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP && !profileSubAdapter.clicItem) {
                        Log.v("prueba", "Intercept INSIDE");
                    }
                    Log.v("prueba", "Intercept");
                    return false;
                }

                @Override
                public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                    Log.v("prueba", "Event");

                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                    Log.v("prueba", "Request");
                }
            });

            heightTotal = heightRowSubItem * item.listProfileSubAdapters.size() + heightMore;
            if (item.getExpandido()) {
                cardView.setCardBackgroundColor(ProfilesActivity.context.getResources().getColor(R.color.purple_050));
                vAnterior = cardView;
            } else {
                cardView.setCardBackgroundColor(ProfilesActivity.context.getResources().getColor(R.color.white));
            }
            buttonEditTime1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button)v;
                    String sTime = (String) button.getText();
                    String sHora = sTime.substring(0,sTime.indexOf(":"));
                    String sMinutos = sTime.substring(sTime.indexOf(":")+1);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(ProfilesActivity.context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            buttonEditTime1.setText(Integer.toString(hourOfDay) +":"+ Integer.toString(minute));
                        }
                    }, Integer.parseInt(sHora),Integer.parseInt(sMinutos), true);
                    timePickerDialog.show();
                    timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(ProfilesActivity.context.getResources().getColor(R.color.white));
                    timePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(ProfilesActivity.context.getResources().getColor(R.color.white));
                }
            });
            buttonEditTime2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button)v;
                    String sTime = (String) button.getText();
                    String sHora = sTime.substring(0,sTime.indexOf(":"));
                    String sMinutos = sTime.substring(sTime.indexOf(":")+1);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(ProfilesActivity.context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            buttonEditTime2.setText(Integer.toString(hourOfDay) +":"+ Integer.toString(minute));
                        }
                    }, Integer.parseInt(sHora),Integer.parseInt(sMinutos), true);
                    timePickerDialog.show();
                    timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(ProfilesActivity.context.getResources().getColor(R.color.white));
                    timePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(ProfilesActivity.context.getResources().getColor(R.color.white));
                }
            });
            imagebuttonWifi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageButton imageButton = (ImageButton)v;
                //    imageButton.setImageResource(Drawable.);
                }
            });
        }

        @Override
        public void onClick(View v) {

            Button button = (Button) v;
            if (button.getCurrentTextColor() == -1) {
                button.setTextColor(ProfilesActivity.context.getResources().getColor(R.color.black));
                v.setBackgroundColor(Color.TRANSPARENT);
            } else {
                button.setTextColor(ProfilesActivity.context.getResources().getColor(R.color.white));
                button.setBackgroundColor(ProfilesActivity.context.getResources().getColor(R.color.purple_icons));
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    button.setBackgroundDrawable(ProfilesActivity.context.getResources().getDrawable(R.drawable.roundedbutton));
                } else {
                    button.setBackground(ProfilesActivity.context.getResources().getDrawable(R.drawable.roundedbutton));
                }
            }
        }


    }
}
