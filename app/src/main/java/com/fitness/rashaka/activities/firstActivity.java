package com.fitness.rashaka.activities;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.fitness.rashaka.R;
import com.fitness.rashaka.utils.Utils;

import java.util.Locale;



public class firstActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    ImageButton mNextBtn,mPreviousBtn;
    Button  mFinishBtn;
    //mSkipBtn,

    private ViewPager mViewPager;

    ImageView zero, one, two;
    ImageView[] indicators;

    int page = 0;

    CoordinatorLayout mCoordinator;
    //colors that will change for each page
    int color1 ;
    int color2 ;
    int color3 ;

    static final String TAG = "firstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black_trans80));
        }

        setContentView(R.layout.activity_first);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        color1 = ContextCompat.getColor(this, R.color.cyan);
        color2 = ContextCompat.getColor(this, R.color.orange);
        color3 = ContextCompat.getColor(this, R.color.green);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);

        mNextBtn = (ImageButton) findViewById(R.id.intro_btn_next);
        mPreviousBtn = (ImageButton) findViewById(R.id.intro_btn_previous);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP){
            mNextBtn.setImageDrawable(
                    Utils.tintMyDrawable(ContextCompat.getDrawable(this, R.drawable.ic_chevron_right_24dp),
                            Color.WHITE));
            mPreviousBtn.setImageDrawable(
                    Utils.tintMyDrawable(ContextCompat.getDrawable(this, R.drawable.ic_chevron_left_24dp),
                            Color.WHITE));

        }

        mFinishBtn = (Button)findViewById(R.id.intro_btn_finish);

        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setCurrentItem(page);



        zero = (ImageView) findViewById(R.id.intro_indicator_0);
        one = (ImageView) findViewById(R.id.intro_indicator_1);
        two = (ImageView) findViewById(R.id.intro_indicator_2);

        mCoordinator = (CoordinatorLayout) findViewById(R.id.main_content);

        indicators = new ImageView[]{zero, one, two};

        Log.i("indicators",indicators.toString());

        final int[] colorList = new int[]{color1, color2, color3};



        final ArgbEvaluator evaluator = new ArgbEvaluator();

        updateIndicators(page);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                //color change
                int colorUpdate= (Integer) evaluator.evaluate(positionOffset, colorList[position], colorList[position == 2 ? position : position + 1])  ;
                mViewPager.setBackgroundColor(colorUpdate);

            }

            @Override
            public void onPageSelected(int position) {
                page = position;
                updateIndicators(page);
                switch (position) {
                    case 0:
                        mViewPager.setBackgroundColor(color1);

                        break;
                    case 1:
                        mViewPager.setBackgroundColor(color2);
                        mPreviousBtn.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        mViewPager.setBackgroundColor(color3);
                        mPreviousBtn.setVisibility(View.VISIBLE);
                        break;
                }



                mNextBtn.setVisibility(position == 2 ? View.GONE : View.VISIBLE);

                mFinishBtn.setVisibility(position == 2 ? View.VISIBLE : View.GONE);



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page += 1;
                mViewPager.setCurrentItem(page, true);
            }
        });

        mPreviousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                page -= 1;
                mViewPager.setCurrentItem(page, true);
            }
        });

        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//TODO : After click button finish redirect to next activity
                Intent intent = new Intent(firstActivity.this, resultsActivity.class);
                startActivity(intent);
            }
        });

    }


    void updateIndicators(int position) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackgroundResource(
                    i == position ? R.drawable.indicator_selected : R.drawable.indicator_unselected
            );
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            Locale l = Locale.getDefault();

            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }




}
