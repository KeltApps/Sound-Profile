package com.keltapps.soundprofile.fragments;

import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.keltapps.soundprofile.R;
import com.keltapps.soundprofile.views.RippleView;

import java.util.ArrayList;

/**
 * Created by sergio on 11/10/15 for KelpApps.
 */
public class ScreenSlidePage1Fragment extends Fragment {
    static final String STATE_DAYS = "buttonDays";
    static final String STATE_CHECKBOX = "checkbox";
    static final String STATE_WIFI = "wifi";
    static final String STATE_BLUETOOTH = "bluetooth";

    public static ScreenSlidePage1Fragment screenSlidePage1Fragment;
    ImageButton imageButtonWifi;
    ImageButton imageButtonBluetooth;
    RippleView rippleViewWifi;
    RippleView rippleViewBluetooth;
    TextView textViewWifi;
    TextView textViewBluetooth;
    boolean[] days;
    CheckBox checkBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        screenSlidePage1Fragment = this;
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page_1, container, false);
        EditText editText = (EditText) rootView.findViewById(R.id.slide_page_1_edittext);
        checkBox = (CheckBox) rootView.findViewById(R.id.slide_page_1_checkbox);
        final Button buttonEditTime1 = (Button) rootView.findViewById(R.id.slide_page_1_buttonEditTime1);
        final Button buttonEditTime2 = (Button) rootView.findViewById(R.id.slide_page_1_buttonEditTime2);
        final TextView textViewGuion = (TextView) rootView.findViewById(R.id.slide_page_1_textview_guion);
        Button buttonMonday = (Button) rootView.findViewById(R.id.slide_page_1_daysL);
        Button buttonTuesday = (Button) rootView.findViewById(R.id.slide_page_1_daysM);
        Button buttonWednesday = (Button) rootView.findViewById(R.id.slide_page_1_daysX);
        Button buttonThursday = (Button) rootView.findViewById(R.id.slide_page_1_daysJ);
        Button buttonFriday = (Button) rootView.findViewById(R.id.slide_page_1_daysV);
        Button buttonSaturday = (Button) rootView.findViewById(R.id.slide_page_1_daysS);
        Button buttonSunday = (Button) rootView.findViewById(R.id.slide_page_1_daysD);
        imageButtonWifi = (ImageButton) rootView.findViewById(R.id.slide_page_1_imagebuttonWifi);
        rippleViewWifi = (RippleView) rootView.findViewById(R.id.slide_page_1_rippleWifi);
        imageButtonBluetooth = (ImageButton) rootView.findViewById(R.id.slide_page_1_imagebuttonbluetooth);
        rippleViewBluetooth = (RippleView) rootView.findViewById(R.id.slide_page_1_rippleBluetooth);
        textViewWifi = (TextView) rootView.findViewById(R.id.slide_page_1_textviewWifi);
        textViewBluetooth = (TextView) rootView.findViewById(R.id.slide_page_1_textviewbluetooth);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                if (!checkBox.isChecked()) {
                    buttonEditTime1.setAlpha((float) (getResources().getInteger(R.integer.alpha_light_text_disabled) * 0.01));
                    buttonEditTime2.setAlpha((float) (getResources().getInteger(R.integer.alpha_light_text_disabled) * 0.01));
                    textViewGuion.setAlpha((float) (getResources().getInteger(R.integer.alpha_light_text_disabled) * 0.01));

                } else {
                    buttonEditTime1.setAlpha((float) (getResources().getInteger(R.integer.alpha_light_text_primary) * 0.01));
                    buttonEditTime2.setAlpha((float) (getResources().getInteger(R.integer.alpha_light_text_primary) * 0.01));
                    textViewGuion.setAlpha((float) (getResources().getInteger(R.integer.alpha_light_text_primary) * 0.01));
                }
            }
        });
        if (savedInstanceState != null) {
            checkBox.setChecked(savedInstanceState.getBoolean(STATE_CHECKBOX));
            if (!checkBox.isChecked()) {
                buttonEditTime1.setAlpha((float) (getResources().getInteger(R.integer.alpha_light_text_disabled) * 0.01));
                buttonEditTime2.setAlpha((float) (getResources().getInteger(R.integer.alpha_light_text_disabled) * 0.01));
                textViewGuion.setAlpha((float) (getResources().getInteger(R.integer.alpha_light_text_disabled) * 0.01));

            } else {
                buttonEditTime1.setAlpha((float) (getResources().getInteger(R.integer.alpha_light_text_primary) * 0.01));
                buttonEditTime2.setAlpha((float) (getResources().getInteger(R.integer.alpha_light_text_primary) * 0.01));
                textViewGuion.setAlpha((float) (getResources().getInteger(R.integer.alpha_light_text_primary) * 0.01));
            }
            days = savedInstanceState.getBooleanArray(STATE_DAYS);
            setDayButton(buttonMonday, 0, days, false);
            setDayButton(buttonTuesday, 1, days, false);
            setDayButton(buttonWednesday, 2, days, false);
            setDayButton(buttonThursday, 3, days, false);
            setDayButton(buttonFriday, 4, days, false);
            setDayButton(buttonSaturday, 5, days, false);
            setDayButton(buttonSunday, 6, days, false);
            updateWifiSelected(savedInstanceState.getStringArrayList(STATE_WIFI));
            updateBluetoothSelected(savedInstanceState.getStringArrayList(STATE_BLUETOOTH));
        } else {
            days = new boolean[7];
            for (int i = 0; i <= 6; i++)
                days[i] = true;
        }
        buttonEditTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String sTime = (String) button.getText();
                String sHora = sTime.substring(0, sTime.indexOf(":"));
                String sMinutos = sTime.substring(sTime.indexOf(":") + 1);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
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
                    }
                }, Integer.parseInt(sHora), Integer.parseInt(sMinutos), true);
                timePickerDialog.show();
                timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.white));
                timePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.white));
            }
        });

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
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
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
                    }
                }, Integer.parseInt(sHora), Integer.parseInt(sMinutos), true);

                timePickerDialog.setButton(TimePickerDialog.BUTTON_NEUTRAL, getResources().getString(R.string.clock_none), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        buttonEditTime2.setText(getResources().getString(R.string.clock_none));
                    }
                });
                timePickerDialog.show();
                timePickerDialog.getButton(TimePickerDialog.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.white));
                timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.white));
                timePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.white));
            }
        });

        buttonMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDayButton(v, 0, days, true);
            }
        });
        buttonTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDayButton(v, 1, days, true);
            }
        });
        buttonWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDayButton(v, 2, days, true);
            }
        });
        buttonThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDayButton(v, 3, days, true);
            }
        });
        buttonFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDayButton(v, 4, days, true);
            }
        });
        buttonSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDayButton(v, 5, days, true);
            }
        });
        buttonSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDayButton(v, 6, days, true);
            }
        });
        imageButtonWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callWifiSelection();
            }
        });
        rippleViewWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callWifiSelection();
            }
        });
        textViewWifi.setMovementMethod(new ScrollingMovementMethod());

        imageButtonBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBluetoothSelection();
            }
        });
        rippleViewBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBluetoothSelection();
            }
        });
        textViewBluetooth.setMovementMethod(new ScrollingMovementMethod());
        return rootView;
    }

    private void callWifiSelection() {
        OnScreenSlidePage1Listener mCallback = (OnScreenSlidePage1Listener) getActivity();
        ArrayList<String> listSelectedWifi = new ArrayList<>();
        String stringSelectedWifi = textViewWifi.getText().toString();
        if (stringSelectedWifi.equals("")) {
            mCallback.onScreenSlideWifiSelected(new ArrayList<String>());
            return;
        }
        while (stringSelectedWifi.contains(", ")) {
            listSelectedWifi.add(stringSelectedWifi.substring(0, stringSelectedWifi.indexOf(",")));
            stringSelectedWifi = stringSelectedWifi.substring(stringSelectedWifi.indexOf(", ") + 2, stringSelectedWifi.length());
        }
        listSelectedWifi.add(stringSelectedWifi);
        mCallback.onScreenSlideWifiSelected(listSelectedWifi);
    }

    private void callBluetoothSelection() {
        OnScreenSlidePage1Listener mCallback = (OnScreenSlidePage1Listener) getActivity();
        ArrayList<String> listSelectedBluetooth = new ArrayList<>();
        String stringSelectedBluetooth = textViewBluetooth.getText().toString();
        if (stringSelectedBluetooth.equals("")) {
            mCallback.onScreenSlideBluetoothSelected(new ArrayList<String>());
            return;
        }
        while (stringSelectedBluetooth.contains(", ")) {
            listSelectedBluetooth.add(stringSelectedBluetooth.substring(0, stringSelectedBluetooth.indexOf(",")));
            stringSelectedBluetooth = stringSelectedBluetooth.substring(stringSelectedBluetooth.indexOf(", ") + 2, stringSelectedBluetooth.length());
        }
        listSelectedBluetooth.add(stringSelectedBluetooth);
        mCallback.onScreenSlideBluetoothSelected(listSelectedBluetooth);
    }

    private void setDayButton(View view, int day, boolean[] days, boolean click) {
        Button button = (Button) view;
        if (click)
            days[day] = !days[day];
        if (!days[day]) {
            button.setTextColor(getResources().getColor(R.color.black));
            button.setAlpha((float) (getResources().getInteger(R.integer.alpha_light_text_disabled) * 0.01));
            view.setBackgroundColor(Color.TRANSPARENT);
        } else {
            button.setTextColor(getResources().getColor(R.color.white));
            button.setAlpha((float) (getResources().getInteger(R.integer.alpha_light_text_primary) * 0.01));
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                button.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbutton));
            } else {
                button.setBackground(getResources().getDrawable(R.drawable.roundedbutton_purple_300));
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBooleanArray(STATE_DAYS, days);
        savedInstanceState.putBoolean(STATE_CHECKBOX, checkBox.isChecked());
        String stringSelectedWifi = textViewWifi.getText().toString();
        if (stringSelectedWifi.equals(""))
            savedInstanceState.putStringArrayList(STATE_WIFI, new ArrayList<String>());
        else {
            ArrayList<String> listSelectedWifi = new ArrayList<>();
            while (stringSelectedWifi.contains(", ")) {
                listSelectedWifi.add(stringSelectedWifi.substring(0, stringSelectedWifi.indexOf(",")));
                stringSelectedWifi = stringSelectedWifi.substring(stringSelectedWifi.indexOf(", ") + 2, stringSelectedWifi.length());
            }
            listSelectedWifi.add(stringSelectedWifi);
            savedInstanceState.putStringArrayList(STATE_WIFI, listSelectedWifi);
        }
        String stringSelectedBluetooth = textViewBluetooth.getText().toString();
        if (stringSelectedBluetooth.equals(""))
            savedInstanceState.putStringArrayList(STATE_BLUETOOTH, (new ArrayList<String>()));
        else {
            ArrayList<String> listSelectedBluetooth = new ArrayList<>();
            while (stringSelectedBluetooth.contains(", ")) {
                listSelectedBluetooth.add(stringSelectedBluetooth.substring(0, stringSelectedBluetooth.indexOf(",")));
                stringSelectedBluetooth = stringSelectedBluetooth.substring(stringSelectedBluetooth.indexOf(", ") + 2, stringSelectedBluetooth.length());
            }
            listSelectedBluetooth.add(stringSelectedBluetooth);
            savedInstanceState.putStringArrayList(STATE_BLUETOOTH, listSelectedBluetooth);
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    public interface OnScreenSlidePage1Listener {
        void onScreenSlideWifiSelected(ArrayList<String> listWifiSelected);
        void onScreenSlideBluetoothSelected(ArrayList<String> listBluetoothSelected);
    }

    public void updateWifiSelected(ArrayList<String> listWifiSelected) {
        if (listWifiSelected.isEmpty()) {
            imageButtonWifi.setVisibility(View.VISIBLE);
            textViewWifi.setText("");
            rippleViewWifi.setVisibility(View.INVISIBLE);
            return;
        }
        imageButtonWifi.setVisibility(View.INVISIBLE);
        rippleViewWifi.setVisibility(View.VISIBLE);
        String stringWifiSelected = "";
        boolean start = true;
        for (String s : listWifiSelected) {
            if (start) {
                stringWifiSelected += s;
                start = false;
            } else
                stringWifiSelected += ", " + s;
        }
        textViewWifi.setText(stringWifiSelected);
    }

    public void updateBluetoothSelected(ArrayList<String> listBluetoothSelected) {
        if (listBluetoothSelected.isEmpty()) {
            imageButtonBluetooth.setVisibility(View.VISIBLE);
            textViewBluetooth.setText("");
            rippleViewBluetooth.setVisibility(View.INVISIBLE);
            return;
        }
        imageButtonBluetooth.setVisibility(View.INVISIBLE);
        rippleViewBluetooth.setVisibility(View.VISIBLE);
        String stringBluetoothSelected = "";
        boolean start = true;
        for (String s : listBluetoothSelected) {
            if (start) {
                stringBluetoothSelected += s;
                start = false;
            } else
                stringBluetoothSelected += ", " + s;
        }
        textViewBluetooth.setText(stringBluetoothSelected);
    }

}
