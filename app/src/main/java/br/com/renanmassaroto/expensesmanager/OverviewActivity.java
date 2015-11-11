package br.com.renanmassaroto.expensesmanager;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import br.com.renanmassaroto.expensesmanager.adapter.NavigationDrawerAdapter;
import br.com.renanmassaroto.expensesmanager.database.TransactionCategoryDatabaseHelper;
import br.com.renanmassaroto.expensesmanager.models.TransactionCategory;

public class OverviewActivity extends AppCompatActivity {
//        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView navDrawerRecyclerView;
    private LinearLayoutManager navDrawerLayoutManager;
    private NavigationDrawerAdapter navDrawerAdapter;

//    private View showingSecondaryFabOverlay;
//    private View fabSecondaryLine;
//
//    private FloatingActionButton primaryFloatingActionButton;
//    private FloatingActionButton secondaryFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navDrawerRecyclerView = (RecyclerView) findViewById(R.id.nav_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        navDrawerRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        navDrawerLayoutManager = new LinearLayoutManager(this);
        navDrawerRecyclerView.setLayoutManager(navDrawerLayoutManager);

        // specify an adapter (see also next example)
        navDrawerAdapter = new NavigationDrawerAdapter(this);
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

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
    }

//    public void getList(final int offset, final int pageNumber) {
//        Log.d("getList", "Getting page " + Integer.toString(pageNumber) + " with offset " + Integer.toString(offset));
//
//        TransactionCategoryDatabaseHelper.listTransactionCategories(
//                this,
//                offset,
//                new TransactionCategoryDatabaseHelper.OnTransactionCategoriesGotListener() {
//                    @Override
//                    public void onTransactionCategoriesGot(ArrayList<TransactionCategory> transactionCategoriesArrayList) {
//                        if (transactionCategoriesArrayList != null) {
//                            TransactionCategory lastTransaction = transactionCategoriesArrayList.get(
//                                    transactionCategoriesArrayList.size() - 1
//                            );
//
//                            getList(offset + transactionCategoriesArrayList.size(), pageNumber + 1);
//
//                            for (TransactionCategory transaction : transactionCategoriesArrayList) {
//                                Log.d("OnCategoriesGotListener", "Found category " + transaction.getName());
//                            }
//
//                        } else {
//                            Log.d("OnCategoriesGotListener", "Finished getting categories");
//                        }
//                    }
//                }
//        );
//    }

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

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camara) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
}