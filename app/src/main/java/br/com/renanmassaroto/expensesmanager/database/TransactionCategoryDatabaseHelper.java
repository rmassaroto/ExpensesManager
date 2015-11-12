package br.com.renanmassaroto.expensesmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Debug;
import android.util.Log;

import java.util.ArrayList;

import br.com.renanmassaroto.expensesmanager.models.TransactionCategory;

/**
 * Created by renan on 11/11/15.
 */
public class TransactionCategoryDatabaseHelper {

    public interface OnTransactionCategoryAddedListener {
        void onTransactionCategoryAdded(TransactionCategory transactionCategory, boolean success);
    }

    public interface OnTransactionCategoryGotListener {
        void onTransactionCategoryGot(TransactionCategory transactionCategory);
    }

    public interface OnTransactionCategoriesGotListener {
        void onTransactionCategoriesGot(ArrayList<TransactionCategory> transactionCategoriesArrayList, int lastOffset);
    }

    public static void addTransactionCategory(Context context,
                                       final TransactionCategory transactionCategory,
                                       final OnTransactionCategoryAddedListener listener) {

        DatabaseHelper mDatabaseHelper = new DatabaseHelper(context);

        mDatabaseHelper.getWritableDatabaseAsync(new DatabaseHelper.OnDatabaseGotListener() {
            @Override
            public void onDatabaseGot(SQLiteDatabase sqLiteDatabase) {
                ContentValues mContentValues = new ContentValues();

                mContentValues.put(DatabaseContract.TransactionCategories.COLUMN_NAME_NAME,
                        transactionCategory.getName());
                mContentValues.put(DatabaseContract.TransactionCategories.COLUMN_NAME_HEX_COLOR,
                        transactionCategory.getColorHex());

                long newRowId = sqLiteDatabase.insert(
                        DatabaseContract.TransactionCategories.TABLE_NAME,
                        null,
                        mContentValues
                );

                if (newRowId != -1) {
                    transactionCategory.setId(newRowId);

                    Log.d("TransactionCategoryDH", "Added transaction " + transactionCategory.getName());

                    if (listener != null)
                        listener.onTransactionCategoryAdded(transactionCategory, true);
                } else {
                    transactionCategory.setId(-1);

                    if (listener != null)
                        listener.onTransactionCategoryAdded(transactionCategory, false);
                }
            }
        });
    }

    public static void getTransactionCategory(Context context, final long id,
                                              final OnTransactionCategoryGotListener listener) {

        DatabaseHelper mDatabaseHelper = new DatabaseHelper(context);

        mDatabaseHelper.getReadableDatabaseAsync(new DatabaseHelper.OnDatabaseGotListener() {
            @Override
            public void onDatabaseGot(SQLiteDatabase sqLiteDatabase) {

                // A projection that specifies which columns from the database
                // will actually be used after this query.
                String[] projection = {
                        DatabaseContract.TransactionCategories._ID,
                        DatabaseContract.TransactionCategories.COLUMN_NAME_NAME,
                        DatabaseContract.TransactionCategories.COLUMN_NAME_HEX_COLOR
                };

                String selection = DatabaseContract.TransactionCategories._ID + " = " +
                        Long.toString(id);

                Cursor mCursor = sqLiteDatabase.query(
                        DatabaseContract.TransactionCategories.TABLE_NAME,
                        projection,
                        selection,
                        null,
                        null,
                        null,
                        null
                );

                if (mCursor.moveToFirst()) {
                    TransactionCategory mTransactionCategory = new TransactionCategory(
                            mCursor.getLong(mCursor.getColumnIndexOrThrow(DatabaseContract.TransactionCategories._ID)),
                            mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.TransactionCategories.COLUMN_NAME_NAME)),
                            mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.TransactionCategories.COLUMN_NAME_HEX_COLOR))
                    );

                    listener.onTransactionCategoryGot(mTransactionCategory);
                } else {
                    listener.onTransactionCategoryGot(null);
                }
            }
        });
    }

    public static void listTransactionCategories(Context context, final int offset,
                                                 final OnTransactionCategoriesGotListener listener) {
        DatabaseHelper mDatabaseHelper = new DatabaseHelper(context);

        mDatabaseHelper.getReadableDatabaseAsync(new DatabaseHelper.OnDatabaseGotListener() {
            @Override
            public void onDatabaseGot(SQLiteDatabase sqLiteDatabase) {

                // A projection that specifies which columns from the database
                // will actually be used after this query.
                String[] projection = {
                        DatabaseContract.TransactionCategories._ID,
                        DatabaseContract.TransactionCategories.COLUMN_NAME_NAME,
                        DatabaseContract.TransactionCategories.COLUMN_NAME_HEX_COLOR
                };

//                String selection = null;
//                if (lastTransactionCategory != null) {
//                    selection = DatabaseContract.TransactionCategories._ID + " > " +
//                            lastTransactionCategory.getId();
//                }

                // Sort order
                String sortOrder = DatabaseContract.TransactionCategories.COLUMN_NAME_NAME + " ASC";

                // Limit
                String limit = "20";

                if (offset > -1)
//                    limit = "OFFSET " + Integer.toString(offset * 20);
                    limit = Integer.toString(offset * 20) + ", 20";

                Cursor mCursor = sqLiteDatabase.query(
                        DatabaseContract.TransactionCategories.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        sortOrder,
                        limit
                );

                ArrayList<TransactionCategory> transactionCategoriesArrayList = null;

                while (mCursor.moveToNext()) {
                    TransactionCategory mTransactionCategory = new TransactionCategory(
                            mCursor.getLong(mCursor.getColumnIndexOrThrow(DatabaseContract.TransactionCategories._ID)),
                            mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.TransactionCategories.COLUMN_NAME_NAME)),
                            mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.TransactionCategories.COLUMN_NAME_HEX_COLOR))
                    );

                    if (transactionCategoriesArrayList == null)
                        transactionCategoriesArrayList = new ArrayList<>();

                    transactionCategoriesArrayList.add(mTransactionCategory);
                }

                listener.onTransactionCategoriesGot(transactionCategoriesArrayList, offset);
            }
        });
    }

}
