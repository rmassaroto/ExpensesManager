package br.com.renanmassaroto.expensesmanager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import br.com.renanmassaroto.expensesmanager.adapter.CategoriesListAdapter;
import br.com.renanmassaroto.expensesmanager.database.TransactionCategoryDatabaseHelper;
import br.com.renanmassaroto.expensesmanager.models.TransactionCategory;

/**
 * Created by renan on 11/11/15.
 */
public class CategoriesListActivity extends AppCompatActivity implements TransactionCategoryDatabaseHelper.OnTransactionCategoriesGotListener {

    public static final String LOG_TAG = "CategoriesListActivity";

    private RecyclerView categoriesRecyclerView;
    private LinearLayoutManager categoriesLinearLayoutManager;
    private CategoriesListAdapter categoriesListAdapter;

    private ActionMode mActionMode;

    private LinearLayout emptyCategoriesListInfo;

    private boolean loading = false;
    private int page = 0;

    private ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_menu_categories_list, menu);

            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_delete:
                    //TODO: Delete
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // This displays the up arrow on the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        emptyCategoriesListInfo = (LinearLayout) findViewById(R.id.empty_categories_list);
        emptyCategoriesListInfo.setVisibility(View.INVISIBLE);

        categoriesRecyclerView = (RecyclerView) findViewById(R.id.categories_list_recycler_view);

        // Setting to improve performance if changes
        // in content do not change the layout size of the RecyclerView
        categoriesRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        categoriesLinearLayoutManager = new LinearLayoutManager(this);
        categoriesRecyclerView.setLayoutManager(categoriesLinearLayoutManager);

        // specify an adapter
        categoriesListAdapter = new CategoriesListAdapter(this,
                new OnCategoriesRecyclerViewItemClickListener());

        categoriesRecyclerView.setAdapter(categoriesListAdapter);

        categoriesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount = categoriesLinearLayoutManager.getChildCount();
                int totalItemCount = categoriesLinearLayoutManager.getItemCount();
                int pastVisibleItems = categoriesLinearLayoutManager.findFirstVisibleItemPosition();

                if (!loading) {
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        getCategoriesListPage(page);
                    }
                }
            }
        });

        getCategoriesListPage(page);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                onBackPressed();
                return true;
        }
    }

    public void getCategoriesListPage(int page) {
        loading = true;

        TransactionCategoryDatabaseHelper.listTransactionCategories(
                this,
                page,
                this
        );
    }

    @Override
    public void onTransactionCategoriesGot(ArrayList<TransactionCategory> transactionCategoriesArrayList,
                                           int lastOffset) {
        loading = false;
        page++;

        if (transactionCategoriesArrayList != null) {

            Log.d(LOG_TAG, "Received a page with " + Integer.toString(transactionCategoriesArrayList.size()));

            categoriesListAdapter.addTransactionCategories(transactionCategoriesArrayList);
        } else {
            if (categoriesListAdapter.getItemCount() == 0) {
                emptyCategoriesListInfo.setVisibility(View.VISIBLE);
            }
        }
    }

    private class OnCategoriesRecyclerViewItemClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (mActionMode == null) {
                mActionMode = startSupportActionMode(mActionModeCallBack);
            }

            View item = categoriesRecyclerView.getChildAt((int) view.getTag());


            if (item.isSelected()) {
//                item.setBackgroundColor(getColor(R.color.white));
                item.setBackground(null);

                item.setSelected(false);
            } else {
                item.setBackgroundColor(getColor(R.color.dividerColor));

                item.setSelected(true);
            }

        }
    }
}
