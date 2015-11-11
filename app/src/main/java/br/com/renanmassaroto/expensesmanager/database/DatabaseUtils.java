package br.com.renanmassaroto.expensesmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.renanmassaroto.expensesmanager.models.TransactionCategory;
/**
 * Created by renan on 10/11/15.
 */
public class DatabaseUtils {

    public interface OnTransactionAddedListener {
        void onTransactionAdded(TransactionCategory transactionCategory, boolean success);
    }

    public void addTransactionCategory(Context context, final TransactionCategory transactionCategory,
                                       final OnTransactionAddedListener listener) {
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
                    listener.onTransactionAdded(transactionCategory, true);
                } else {
                    transactionCategory.setId(-1);
                    listener.onTransactionAdded(transactionCategory, false);
                }
            }
        });
    }
}