package br.com.renanmassaroto.expensesmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.com.renanmassaroto.expensesmanager.adapter.NavigationDrawerAdapter;

public class OverviewActivity extends AppCompatActivity {
//        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView navDrawerRecyclerView;
    private LinearLayoutManager navDrawerLinearLayoutManager;
    private NavigationDrawerAdapter navDrawerAdapter;

//    private View showingSecondaryFabOverlay;
//    private View fabSecondaryLine;
//
//    private FloatingActionButton primaryFloatingActionButton;
//    private FloatingActionButton secondaryFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navDrawerRecyclerView = (RecyclerView) findViewById(R.id.nav_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        navDrawerRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        navDrawerLinearLayoutManager = new LinearLayoutManager(this);
        navDrawerRecyclerView.setLayoutManager(navDrawerLinearLayoutManager);

        // specify an adapter (see also next example)
        navDrawerAdapter = new NavigationDrawerAdapter(this, new OnNavigationDrawerItemClickListener());
        navDrawerRecyclerView.setAdapter(navDrawerAdapter);

//        showingSecondaryFabOverlay = findViewById(R.id.showing_secondary_fab_screen_overlay);
//
//        fabSecondaryLine = findViewById(R.id.fab_secondary_line);
//
//        primaryFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab_primary);
//        secondaryFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab_secondary);
//
//        primaryFloatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//
//                if (secondaryFloatingActionButton.getVisibility() == View.INVISIBLE) {
//
//                    Animation showSecondaryFabAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.show_secondary_fab_animation);
//
//                    showSecondaryFabAnimation.setAnimationListener(new Animation.AnimationListener() {
//                        @Override
//                        public void onAnimationStart(Animation animation) {
////                            fabSecondaryLine.setVisibility(View.VISIBLE);
//                            showingSecondaryFabOverlay.setVisibility(View.VISIBLE);
//                            secondaryFloatingActionButton.setVisibility(View.VISIBLE);
//                        }
//
//                        @Override
//                        public void onAnimationEnd(Animation animation) {
//
//                        }
//
//                        @Override
//                        public void onAnimationRepeat(Animation animation) {
//
//                        }
//                    });
//
//                    secondaryFloatingActionButton.startAnimation(showSecondaryFabAnimation);
//                } else {
//
//                    Animation hideSecondaryFabAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.hide_secondary_fab_animation);
//
//                    hideSecondaryFabAnimation.setAnimationListener(new Animation.AnimationListener() {
//                        @Override
//                        public void onAnimationStart(Animation animation) {
//
//                        }
//
//                        @Override
//                        public void onAnimationEnd(Animation animation) {
////                            fabSecondaryLine.setVisibility(View.GONE);
//                            showingSecondaryFabOverlay.setVisibility(View.GONE);
//                            secondaryFloatingActionButton.setVisibility(View.INVISIBLE);
//                        }
//
//                        @Override
//                        public void onAnimationRepeat(Animation animation) {
//
//                        }
//                    });
//
//                    secondaryFloatingActionButton.startAnimation(hideSecondaryFabAnimation);
//                }
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    private class OnNavigationDrawerItemClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int position = navDrawerRecyclerView.getChildAdapterPosition(view);

            if (navDrawerAdapter.getItemName(position).equals(getString(R.string.categories_management_activity_name))) {
                Intent mIntent = new Intent(OverviewActivity.this, CategoriesListActivity.class);
                startActivity(mIntent);
            }
        }
    }
}