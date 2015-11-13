package br.com.renanmassaroto.expensesmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

/**
 * Created by renancardosomassaroto on 11/6/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "database.db";

    private Context context;
    private OnDatabaseGotListener onDatabaseGotListener;

    public interface OnDatabaseGotListener {
        void onDatabaseGot(SQLiteDatabase sqLiteDatabase);
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseContract.Accounts.SQL_CREATE_ACCOUNTS);
        sqLiteDatabase.execSQL(DatabaseContract.AccountTypes.SQL_CREATE_ACCOUNT_TYPES);
        sqLiteDatabase.execSQL(DatabaseContract.TransactionCategories.SQL_CREATE_TRANSACTION_CATEGORIES);
        sqLiteDatabase.execSQL(DatabaseContract.Transactions.SQL_CREATE_TRANSACTIONS);
        sqLiteDatabase.execSQL(DatabaseContract.Wishlist.SQL_CREATE_WISHLIST);

        sqLiteDatabase.execSQL(DatabaseContract.TransactionCategories.SQL_INSERT_DEFAULT_VALUES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DatabaseContract.Accounts.SQL_DELETE_ACCOUNTS);
        sqLiteDatabase.execSQL(DatabaseContract.AccountTypes.SQL_DELETE_ACCOUNT_TYPES);
        sqLiteDatabase.execSQL(DatabaseContract.TransactionCategories.SQL_DELETE_TRANSACTION_CATEGORIES);
        sqLiteDatabase.execSQL(DatabaseContract.Transactions.SQL_DELETE_TRANSACTIONS);
        sqLiteDatabase.execSQL(DatabaseContract.Wishlist.SQL_DELETE_WISHLIST);

        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }

    public void getWritableDatabaseAsync(OnDatabaseGotListener listener) {
        this.onDatabaseGotListener = listener;
        new GetWritableDatabaseAsyncTask(context).execute();
    }

    public void getReadableDatabaseAsync(OnDatabaseGotListener listener) {
        this.onDatabaseGotListener = listener;
        new GetReadableDatabaseAsyncTask(context).execute();
    }

    private class GetWritableDatabaseAsyncTask extends AsyncTask<Void, Void, SQLiteDatabase> {

        private Context context;

        public GetWritableDatabaseAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected SQLiteDatabase doInBackground(Void... voids) {
            DatabaseHelper mDatabaseHelper = new DatabaseHelper(context);

            return mDatabaseHelper.getReadableDatabase();
        }

        @Override
        protected void onPostExecute(SQLiteDatabase sqLiteDatabase) {
            if (onDatabaseGotListener != null)
                onDatabaseGotListener.onDatabaseGot(sqLiteDatabase);
        }
    }

    private class GetReadableDatabaseAsyncTask extends AsyncTask<Void, Void, SQLiteDatabase> {

        private Context context;

        public GetReadableDatabaseAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected SQLiteDatabase doInBackground(Void... voids) {
            DatabaseHelper mDatabaseHelper = new DatabaseHelper(context);

            return mDatabaseHelper.getReadableDatabase();
        }

        @Override
        protected void onPostExecute(SQLiteDatabase sqLiteDatabase) {
            if (onDatabaseGotListener != null)
                onDatabaseGotListener.onDatabaseGot(sqLiteDatabase);
        }
    }
}