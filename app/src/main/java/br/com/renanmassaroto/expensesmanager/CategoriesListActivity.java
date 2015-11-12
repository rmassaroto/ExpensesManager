package br.com.renanmassaroto.expensesmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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

    private int lastAskedPage = 0;
    private int lastShowPage = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

//        createDefaultValues();

        getCategoriesListPage(0);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            createDefaultValues();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getCategoriesListPage(int page) {
        Log.d(LOG_TAG, "Getting page " + Integer.toString(page));
        lastAskedPage = page;

        TransactionCategoryDatabaseHelper.listTransactionCategories(
                this,
                page,
                this
        );
    }

    @Override
    public void onTransactionCategoriesGot(ArrayList<TransactionCategory> transactionCategoriesArrayList,
                                           int lastOffset) {
        if (transactionCategoriesArrayList != null) {
            Log.d(LOG_TAG, "Received a page with " + Integer.toString(transactionCategoriesArrayList.size()));

            categoriesListAdapter.addTransactionCategories(transactionCategoriesArrayList);

//            getCategoriesListPage(lastOffset + 1);
        } else {
//            Log.d(LOG_TAG, "Finished loading transaction categories with a total of " +
//                    Integer.toString(lastOffset) + " pages and " + Integer.toString(categoriesListAdapter.getItemCount()) +
//            " entries.");
        }

        lastShowPage = lastOffset;
    }

    public void createDefaultValues() {
        TransactionCategory transactionCategory1 = new TransactionCategory(
                "Lazer",
                "#FF0000"
        );

        TransactionCategory transactionCategory2 = new TransactionCategory(
                "Reparos",
                "#00FF00"
        );

        TransactionCategory transactionCategory3 = new TransactionCategory(
                "Educação",
                "#0000FF"
        );

        TransactionCategory transactionCategory4 = new TransactionCategory(
                "Outros",
                "#000000"
        );

        TransactionCategoryDatabaseHelper.addTransactionCategory(
                this,
                transactionCategory1,
                null
        );

        TransactionCategoryDatabaseHelper.addTransactionCategory(
                this,
                transactionCategory2,
                null
        );

        TransactionCategoryDatabaseHelper.addTransactionCategory(
                this,
                transactionCategory3,
                null
        );

        TransactionCategoryDatabaseHelper.addTransactionCategory(
                this,
                transactionCategory4,
                null
        );
    }

    private class OnCategoriesRecyclerViewItemClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }
}
