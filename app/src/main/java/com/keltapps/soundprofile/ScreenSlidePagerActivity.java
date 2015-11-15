package com.keltapps.soundprofile;

import android.animation.Animator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;

import com.keltapps.soundprofile.fragments.BluetoothFragment;
import com.keltapps.soundprofile.fragments.ScreenSlidePage1Fragment;
import com.keltapps.soundprofile.fragments.ScreenSlidePage2Fragment;
import com.keltapps.soundprofile.fragments.WifiFragment;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

/**
 * Created by sergio on 11/10/15 for KelpApps.
 */
public class ScreenSlidePagerActivity extends AppCompatActivity implements ScreenSlidePage1Fragment.OnScreenSlidePage1Listener, WifiFragment.OnWifiSelectedListener, BluetoothFragment.OnBluetoothSelectedListener {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    CirclePageIndicator mIndicator;
    //private ScreenSlidePage1Fragment screenSlidePage1Fragment;
    boolean start = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        final View rootLayout = findViewById(R.id.activity_screen_slide_root_layout);
        final int cx = getIntent().getExtras().getInt("cx");
        final int cy = getIntent().getExtras().getInt("cy");
        if (savedInstanceState == null) {
            rootLayout.setVisibility(View.INVISIBLE);

            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (start) {
                            circularReveal(rootLayout, cx, cy);
                            start = false;
                        }
                    }
                });
            }
        }
        getSupportActionBar().setTitle("New Tweak");
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.activity_screen_slide_viewpager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mIndicator = (CirclePageIndicator) findViewById(R.id.activity_screen_slide_indicator);
        mIndicator.setViewPager(mPager);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onScreenSlideWifiSelected(ArrayList<String> listWifiSelected) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("wifiSelectedList", listWifiSelected);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WifiFragment wifiFragment = new WifiFragment();
        wifiFragment.setArguments(bundle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition explodeTransform = TransitionInflater.from(this).
                    inflateTransition(android.R.transition.slide_bottom);
            explodeTransform.setDuration(300);
            wifiFragment.setEnterTransition(explodeTransform);
            wifiFragment.setExitTransition(explodeTransform);
            fragmentTransaction.add(R.id.activity_screen_slide_root_layout, wifiFragment, "WifiFragment");
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onScreenSlideBluetoothSelected(ArrayList<String> listBluetoothSelected) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("bluetoothPairedList", listBluetoothSelected);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BluetoothFragment bluetoothFragment = new BluetoothFragment();
        bluetoothFragment.setArguments(bundle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition explodeTransform = TransitionInflater.from(this).
                    inflateTransition(android.R.transition.slide_bottom);
            explodeTransform.setDuration(300);
            bluetoothFragment.setEnterTransition(explodeTransform);
            bluetoothFragment.setExitTransition(explodeTransform);
            fragmentTransaction.add(R.id.activity_screen_slide_root_layout, bluetoothFragment, "BluetoothFragment");
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onWifiSelected(ArrayList<String> listWifiSelected, int profilePosition) {
        WifiFragment wifiFragment = (WifiFragment) getFragmentManager().findFragmentByTag("WifiFragment");
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.remove(wifiFragment);
        fragmentTransaction.commit();
        if (ScreenSlidePage1Fragment.screenSlidePage1Fragment != null)
            ScreenSlidePage1Fragment.screenSlidePage1Fragment.updateWifiSelected(listWifiSelected);
    }

    @Override
    public void onBluetoothSelected(ArrayList<String> listBluetoothSelected, int profilePosition) {
        BluetoothFragment bluetoothFragment = (BluetoothFragment) getFragmentManager().findFragmentByTag("BluetoothFragment");
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.remove(bluetoothFragment);
        fragmentTransaction.commit();
        if (ScreenSlidePage1Fragment.screenSlidePage1Fragment != null)
            ScreenSlidePage1Fragment.screenSlidePage1Fragment.updateBluetoothSelected(listBluetoothSelected);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ScreenSlidePage1Fragment();
                case 1:
                    return new ScreenSlidePage2Fragment();
                default:
                    return new ScreenSlidePage1Fragment();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    private void circularReveal(View rootLayout, int cx, int cy) {
        // create the animator for this view (the start radius is zero)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, cx, cy, 0, (float) Math.sqrt(cx * cx + cy * cy));
            circularReveal.setDuration(1000);
            // make the view visible and start the animation
            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
        }
    }
}
