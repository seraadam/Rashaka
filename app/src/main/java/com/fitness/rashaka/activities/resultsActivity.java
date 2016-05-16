package com.fitness.rashaka.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.fitness.rashaka.R;

public class resultsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CharSequence mTitle;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_results);



       // TextView results = (TextView) findViewById(R.id.results);
      //  results.setText(R.string.bmi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            navigationView.getMenu().performIdentifierAction(R.id.nav_camera, 0);


        }

    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(resultsActivity.this, firstActivity.class);
            startActivity(intent);
            super.onBackPressed();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        if ( ! drawer.isDrawerOpen(GravityCompat.START) )
        { // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.results, menu);


            restoreActionBar();
            return true;
        }

        return super.onCreateOptionsMenu( menu );
    }

    public void restoreActionBar () {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled( true );
        actionBar.setTitle( mTitle );
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment outputfragment = null;
        Fragment inputfragment = null;

        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            mTitle = "BMI input";
            // Handle the camera action
            // by me : i add the fragment
            inputfragment = new InputFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, inputfragment).commit();

            Log.e("fragment",inputfragment.toString());
//here i should send the fragments data to the user refrence and the to the output fragment

            Toast.makeText(getApplicationContext(),"camera Selected", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_gallery) {
            //i need to transition the main fragment with another one
            mTitle = "BMI calculations";
            outputfragment = new resultsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, outputfragment).commit();

            Log.e("fragment",outputfragment.toString());
            Toast.makeText(getApplicationContext(),"gallery Selected", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
