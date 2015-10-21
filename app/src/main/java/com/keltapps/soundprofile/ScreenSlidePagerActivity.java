package com.keltapps.soundprofile;

import android.animation.Animator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;

import com.keltapps.soundprofile.fragments.ScreenSlidePage1Fragment;
import com.keltapps.soundprofile.fragments.ScreenSlidePage2Fragment;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by sergio on 11/10/15 for KelpApps.
 */
public class ScreenSlidePagerActivity extends AppCompatActivity {
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
    Boolean start = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        final View rootLayout = findViewById(R.id.root_layout);
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
                            circularReveal(rootLayout,cx,cy);
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
        float finalRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());
        // create the animator for this view (the start radius is zero)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, cx, cy, 0,(float) Math.sqrt(cx*cx+cy*cy));
            circularReveal.setDuration(1000);
            // make the view visible and start the animation
            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
        }
    }
}
