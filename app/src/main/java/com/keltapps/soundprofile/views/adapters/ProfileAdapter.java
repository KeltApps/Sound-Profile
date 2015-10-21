package com.keltapps.soundprofile.views.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.method.ScrollingMovementMethod;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.keltapps.soundprofile.R;
import com.keltapps.soundprofile.fragments.ProfilesFragment;
import com.keltapps.soundprofile.fragments.WifiFragment;
import com.keltapps.soundprofile.views.Profile;
import com.keltapps.soundprofile.views.ProfileSub;

import java.util.ArrayList;
import java.util.Random;

import static android.animation.ValueAnimator.ofFloat;
import static android.animation.ValueAnimator.ofInt;

/**
 * Created by sergio on 21/07/15 for KelpApps.
 */
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolderProfile> {
    private static ProfileAdapter adapter;
    private static ArrayList<Profile> listProfile;
    private static int intOriginalHeight = 0;

    public ProfileAdapter(ArrayList<Profile> list) {
        listProfile = list;
    }

    @Override
    public ViewHolderProfile onCreateViewHolder(ViewGroup viewGroup, int i) {
        adapter = this;
        return new ViewHolderProfile(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.profiles_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolderProfile viewHolderProfile, int i) {
        Profile item = listProfile.get(i);
        viewHolderProfile.bindProfile(item, i);
    }

    @Override
    public int getItemCount() {
        return listProfile.size();
    }


    public static class ViewHolderProfile extends RecyclerView.ViewHolder {
        CardView cardView;
        Button buttonName;
        CheckBox checkBox;
        SwitchCompat oSwitch;
        Button buttonEditTime1;
        Button buttonEditTime2;
        TextView textViewGuion;
        Button bLunes;
        Button bMartes;
        Button bMiercoles;
        Button bJueves;
        Button bViernes;
        Button bSabado;
        Button bDomingo;
        com.keltapps.soundprofile.views.RippleView rippleViewWifi;
        ImageView imageViewWifi;
        TextView textViewWifi;
        com.keltapps.soundprofile.views.RippleView rippleViewBluetooth;
        ImageView imageViewBluetooth;
        ImageView imagebuttonDelete;
        ImageView imageViewMore;
        RecyclerView recyclerViewSub;
        CoordinatorLayout coordinatorLayout;
        com.keltapps.soundprofile.views.RippleView rippleViewMore;
        private int intHeightRowSubItem;
        int cardElevation;
        int cardElevationClick;


        public ViewHolderProfile(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.profiles_item_cardview);
            buttonName = (Button) itemView.findViewById(R.id.profiles_item_buttonName);
            checkBox = (CheckBox) itemView.findViewById(R.id.profiles_item_checkbox);
            oSwitch = (SwitchCompat) itemView.findViewById(R.id.profiles_item_switch);
            buttonEditTime1 = (Button) itemView.findViewById(R.id.profiles_item_buttonEditTime1);
            buttonEditTime2 = (Button) itemView.findViewById(R.id.profiles_item_buttonEditTime2);
            textViewGuion = (TextView) itemView.findViewById(R.id.profiles_item_textviewguion);
            bLunes = (Button) itemView.findViewById(R.id.profiles_item_daysL);
            bMartes = (Button) itemView.findViewById(R.id.profiles_item_daysM);
            bMiercoles = (Button) itemView.findViewById(R.id.profiles_item_daysX);
            bJueves = (Button) itemView.findViewById(R.id.profiles_item_daysJ);
            bViernes = (Button) itemView.findViewById(R.id.profiles_item_daysV);
            bSabado = (Button) itemView.findViewById(R.id.profiles_item_daysS);
            bDomingo = (Button) itemView.findViewById(R.id.profiles_item_daysD);
            rippleViewWifi = (com.keltapps.soundprofile.views.RippleView) itemView.findViewById(R.id.profiles_item_rippleWifi);
            imageViewWifi = (ImageView) itemView.findViewById(R.id.profiles_item_imageViewEditWifi);
            textViewWifi = (TextView) itemView.findViewById(R.id.profiles_item_textViewWifi);
            rippleViewBluetooth = (com.keltapps.soundprofile.views.RippleView) itemView.findViewById(R.id.profiles_item_rippleBluetooth);
            imageViewBluetooth = (ImageView) itemView.findViewById(R.id.profiles_item_imageViewEditBluetooth);
            imagebuttonDelete = (ImageButton) itemView.findViewById(R.id.profiles_item_imagebuttonDelete);
            imageViewMore = (ImageView) itemView.findViewById(R.id.profiles_item_imageviewMore);
            rippleViewMore = (com.keltapps.soundprofile.views.RippleView) itemView.findViewById(R.id.profiles_item_rippleimageviewMore);
            recyclerViewSub = (RecyclerView) itemView.findViewById(R.id.profiles_item_recyclerview);
            coordinatorLayout = (CoordinatorLayout) itemView.findViewById(R.id.profiles_item_coordinatorlayout);
            intHeightRowSubItem = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 125,
                    ProfilesFragment.profilesFragment.getResources().getDisplayMetrics());
            cardElevation = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
                    ProfilesFragment.profilesFragment.getResources().getDisplayMetrics());
            cardElevationClick = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
                    ProfilesFragment.profilesFragment.getResources().getDisplayMetrics());
        }

        public void bindProfile(final Profile item, int position) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageViewWifi.setTransitionName("wifi" + position);
                rippleViewWifi.setTransitionName("wifiBackground" + position);
                imageViewBluetooth.setTransitionName("bluetooth" + position);
            }
            if (item.getExpandido()) {
                if (intOriginalHeight != 0) {
                    cardView.setCardBackgroundColor(ProfilesFragment.profilesFragment.getResources().getColor(R.color.purple_200));
                    imageViewMore.setRotation(180);
                    cardView.getLayoutParams().height = intOriginalHeight + intHeightRowSubItem;
                    coordinatorLayout.setVisibility(View.VISIBLE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        cardView.setTranslationZ(cardElevationClick);
                }
            } else {
                if (intOriginalHeight != 0) {
                    cardView.setCardBackgroundColor(ProfilesFragment.profilesFragment.getResources().getColor(R.color.purple_300));
                    imageViewMore.setRotation(180);
                    if (intOriginalHeight != 0)
                        cardView.getLayoutParams().height = intOriginalHeight;
                    coordinatorLayout.setVisibility(View.VISIBLE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        cardView.setTranslationZ(cardElevation);
                }
            }
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandCardView(item);
                }
            });
            buttonName.setText(item.getNombre());
            buttonName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater layoutInflater = LayoutInflater.from(ProfilesFragment.profilesFragment.getActivity());
                    View promptView = layoutInflater.inflate(R.layout.dialog_item_name, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ProfilesFragment.profilesFragment.getActivity(), R.style.AppCompatAlertDialogStyle);
                    alertDialogBuilder.setView(promptView);
                    final EditText editText = (EditText) promptView.findViewById(R.id.dialog_item_name_edittext);

                    alertDialogBuilder.setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    String sEditText = editText.getText().toString();
                                    if (!sEditText.matches("") && sEditText.trim().length() > 0) {
                                        buttonName.setText(editText.getText() + " ");
                                        item.setNombre(editText.getText() + " ");
                                    }
                                }
                            })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = alertDialogBuilder.create();
                    alert.show();
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    Window window = alert.getWindow();
                    lp.copyFrom(window.getAttributes());
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    window.setAttributes(lp);
                }
            });
            oSwitch.setChecked(item.getActivado());
            oSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.setActivado(isChecked);
                }
            });
            checkBox.setChecked(item.getTimeActive());
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = (CheckBox) v;
                    if (!checkBox.isChecked()) {
                        buttonEditTime1.setAlpha(ProfilesFragment.floatAlphaDisabled);
                        buttonEditTime2.setAlpha(ProfilesFragment.floatAlphaDisabled);
                        textViewGuion.setAlpha(ProfilesFragment.floatAlphaDisabled);

                    } else {
                        buttonEditTime1.setAlpha(1);
                        buttonEditTime2.setAlpha(1);
                        textViewGuion.setAlpha(1);
                    }
                    item.setTimeActive(checkBox.isChecked());
                }
            });
            if (!item.getTimeActive()) {
                buttonEditTime1.setAlpha(ProfilesFragment.floatAlphaDisabled);
                buttonEditTime2.setAlpha(ProfilesFragment.floatAlphaDisabled);
                textViewGuion.setAlpha(ProfilesFragment.floatAlphaDisabled);
            } else {
                buttonEditTime1.setAlpha(1);
                buttonEditTime2.setAlpha(1);
                textViewGuion.setAlpha(1);
            }
            buttonEditTime1.setText(item.getInitialTime());
            buttonEditTime1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;
                    String sTime = (String) button.getText();
                    String sHora = sTime.substring(0, sTime.indexOf(":"));
                    String sMinutos = sTime.substring(sTime.indexOf(":") + 1);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(ProfilesFragment.profilesFragment.getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            String sHourOfDay;
                            if (hourOfDay < 10)
                                sHourOfDay = "0" + Integer.toString(hourOfDay);
                            else
                                sHourOfDay = Integer.toString(hourOfDay);
                            String sMinute;
                            if (minute < 10)
                                sMinute = "0" + Integer.toString(minute);
                            else
                                sMinute = Integer.toString(minute);
                            buttonEditTime1.setText(sHourOfDay + ":" + sMinute);
                            item.setInitialTime(sHourOfDay + ":" + sMinute);
                        }
                    }, Integer.parseInt(sHora), Integer.parseInt(sMinutos), true);
                    timePickerDialog.show();
                    timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(ProfilesFragment.profilesFragment.getResources().getColor(R.color.white));
                    timePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(ProfilesFragment.profilesFragment.getResources().getColor(R.color.white));
                }
            });
            buttonEditTime2.setText(item.getFinalTime());
            buttonEditTime2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;
                    String sTime = (String) button.getText();
                    String sHora;
                    String sMinutos;
                    if (sTime.contains(":")) {
                        sHora = sTime.substring(0, sTime.indexOf(":"));
                        sMinutos = sTime.substring(sTime.indexOf(":") + 1);
                    } else {
                        sHora = "9";
                        sMinutos = "0";
                    }
                    TimePickerDialog timePickerDialog = new TimePickerDialog(ProfilesFragment.profilesFragment.getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            String sHourOfDay;
                            if (hourOfDay < 10)
                                sHourOfDay = "0" + Integer.toString(hourOfDay);
                            else
                                sHourOfDay = Integer.toString(hourOfDay);
                            String sMinute;
                            if (minute < 10)
                                sMinute = "0" + Integer.toString(minute);
                            else
                                sMinute = Integer.toString(minute);
                            buttonEditTime2.setText(sHourOfDay + ":" + sMinute);
                            item.setFinalTime(sHourOfDay + ":" + sMinute);
                        }
                    }, Integer.parseInt(sHora), Integer.parseInt(sMinutos), true);

                    timePickerDialog.setButton(TimePickerDialog.BUTTON_NEUTRAL, "Ninguno", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            buttonEditTime2.setText(ProfilesFragment.profilesFragment.getResources().getString(R.string.clock_none));
                        }
                    });
                    timePickerDialog.show();
                    timePickerDialog.getButton(TimePickerDialog.BUTTON_NEUTRAL).setTextColor(ProfilesFragment.profilesFragment.getResources().getColor(R.color.white));
                    timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(ProfilesFragment.profilesFragment.getResources().getColor(R.color.white));
                    timePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(ProfilesFragment.profilesFragment.getResources().getColor(R.color.white));
                }
            });
            setDayButton(bLunes, 0, item, false);
            setDayButton(bMartes, 1, item, false);
            setDayButton(bMiercoles, 2, item, false);
            setDayButton(bJueves, 3, item, false);
            setDayButton(bViernes, 4, item, false);
            setDayButton(bSabado, 5, item, false);
            setDayButton(bDomingo, 6, item, false);
            bLunes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDayButton(v, 0, item, true);
                }
            });
            bMartes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDayButton(v, 1, item, true);
                }
            });
            bMiercoles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDayButton(v, 2, item, true);
                }
            });
            bJueves.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDayButton(v, 3, item, true);
                }
            });
            bViernes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDayButton(v, 4, item, true);
                }
            });
            bSabado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDayButton(v, 5, item, true);
                }
            });
            bDomingo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDayButton(v, 6, item, true);
                }
            });
            String sWifiSelected = "";
            Boolean initial = true;
            for (String wifi : item.listWifiSelected) {
                if (initial) {
                    sWifiSelected = wifi;
                    initial = false;
                } else
                    sWifiSelected += ", " + wifi;
            }
            textViewWifi.setText(sWifiSelected);
            textViewWifi.setMovementMethod(new ScrollingMovementMethod());
            rippleViewWifi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProfilesFragment fragmentOne = ProfilesFragment.profilesFragment;
                    WifiFragment fragmentTwo = new WifiFragment();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Transition changeTransform = TransitionInflater.from(ProfilesFragment.profilesFragment.getActivity()).
                                inflateTransition(R.transition.change_image_transform);
                        Transition explodeTransform = TransitionInflater.from(ProfilesFragment.profilesFragment.getActivity()).
                                inflateTransition(android.R.transition.slide_bottom);
                        changeTransform.setDuration(500);
                        explodeTransform.setDuration(300);
                        fragmentOne.setSharedElementReturnTransition(changeTransform);
                        //  fragmentOne.setExitTransition(explodeTransform);

                        fragmentTwo.setSharedElementEnterTransition(changeTransform);
                        fragmentTwo.setEnterTransition(explodeTransform);
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("wifiSelectedList", item.listWifiSelected);
                        bundle.putInt("profileIndex", listProfile.indexOf(item));
                        fragmentTwo.setArguments(bundle);
                        FragmentTransaction ft = ProfilesFragment.fragmentManager.beginTransaction()
                                .replace(R.id.profiles_fragment_container, fragmentTwo)
                                .addToBackStack("transaction")
                                .addSharedElement(imageViewWifi, "wifiImageView")
                                .addSharedElement(rippleViewWifi, "wifiImageViewBackground");
                        ft.commit();
                    }
                }
            });
            rippleViewBluetooth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            imagebuttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = listProfile.indexOf(item);
                    listProfile.remove(index);
                    ProfileAdapter.adapter.notifyItemRemoved(index);
                }
            });
            rippleViewMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandCardView(item);
                }
            });

            Random rand = new Random();
            int randomNum = rand.nextInt((10 - 1) + 1) + 1;
            item.listProfileSubAdapters = new ArrayList<ProfileSub>();
            ProfileSub profileSub = new ProfileSub();
            profileSub.setNombre("Prueba item");
            item.listProfileSubAdapters.add(profileSub);
            profileSub = new ProfileSub();
            profileSub.setNombre("Prueba item 2 como ejemplo");
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
            recyclerViewSub.setLayoutManager(new LinearLayoutManager(ProfilesFragment.profilesFragment.getActivity(), LinearLayout.HORIZONTAL, false));
            recyclerViewSub.setItemAnimator(new DefaultItemAnimator());
        }

        private void setDayButton(View view, int day, Profile item, boolean click) {
            Button button = (Button) view;
            if (item.days == null)
                item.days = new Boolean[7];
            if (item.days[day] == null) {
                item.days[day] = false;
            }
            if (click)
                item.days[day] = !item.days[day];
            if (!item.days[day]) {
                button.setTextColor(ProfilesFragment.profilesFragment.getResources().getColor(R.color.black));
                button.setAlpha(ProfilesFragment.floatAlphaDisabled);
                view.setBackgroundColor(Color.TRANSPARENT);
            } else {
                button.setTextColor(ProfilesFragment.profilesFragment.getResources().getColor(R.color.purple_300));
                button.setAlpha(1);
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    button.setBackgroundDrawable(ProfilesFragment.profilesFragment.getResources().getDrawable(R.drawable.roundedbutton));
                } else {
                    button.setBackground(ProfilesFragment.profilesFragment.getResources().getDrawable(R.drawable.roundedbutton));
                }
            }
        }

        private ValueAnimator animationRotacion(boolean clockwise, final ImageView imageViewMore) {
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

        private ValueAnimator animatorHeight(final View view, final boolean expandir) {
            ValueAnimator valueAnimator;
            if (expandir) {
                valueAnimator = ofInt(view.getHeight(), intOriginalHeight + intHeightRowSubItem);
                coordinatorLayout.setVisibility(View.VISIBLE);
            } else
                valueAnimator = ofInt(view.getHeight(), intOriginalHeight);
            valueAnimator.setDuration(300);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    view.getLayoutParams().height = (Integer) animation.getAnimatedValue();
                    view.requestLayout();
                }
            });
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    setIsRecyclable(true);
                }
            });
            ValueAnimator valueAnimatorRotationAnterior;
            valueAnimatorRotationAnterior = animationRotacion(expandir, imageViewMore);
            valueAnimatorRotationAnterior.start();
            return valueAnimator;
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private ValueAnimator animatorExpand(final View view, final boolean expandir) {
            ValueAnimator valueAnimator;
            if (expandir)
                valueAnimator = ofFloat(view.getTranslationZ(), cardElevationClick);
            else
                valueAnimator = ofFloat(cardView.getTranslationZ(), cardElevation);
            valueAnimator.setDuration(300);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    view.setTranslationZ((float) animation.getAnimatedValue());
                    cardView.requestLayout();
                }
            });
            return valueAnimator;
        }

        private void expandCardView(Profile item) {
            if (intOriginalHeight == 0) {
                intOriginalHeight = itemView.getHeight();
            }
            setIsRecyclable(false);
            ValueAnimator valueAnimator;
            ValueAnimator valueAnimator2;
            if (!item.getExpandido()) {
                cardView.setCardBackgroundColor(ProfilesFragment.profilesFragment.getResources().getColor(R.color.purple_200));
                item.setExpandido(true);
            } else {
                cardView.setCardBackgroundColor(ProfilesFragment.profilesFragment.getResources().getColor(R.color.purple_300));
                item.setExpandido(false);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                valueAnimator2 = animatorExpand(cardView, item.getExpandido());
                valueAnimator2.start();
            }
            valueAnimator = animatorHeight(cardView, item.getExpandido());
            valueAnimator.start();
        }


    }
}
