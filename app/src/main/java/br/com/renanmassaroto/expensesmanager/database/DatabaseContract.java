package br.com.renanmassaroto.expensesmanager.database;

import android.content.ContentValues;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by renancardosomassaroto on 11/6/15.
 */
public class DatabaseContract {

    private static final String PRIMARY_KEY = " PRIMARY KEY";

    private static final String NULL_TYPE = " NULL";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String TEXT_TYPE = " TEXT";
    private static final String BLOB_TYPE = " BLOB";

    private static final String COMMA_SEP = ",";

    // To prevent someone from instantiating the contract class
    public DatabaseContract() {

    }

//    public static class BaseContract implements BaseColumns {
//        public static final String PRIMARY_KEY = " PRIMARY KEY";
//
//        public static final String NULL_TYPE = " NULL";
//        public static final String INTEGER_TYPE = " INTEGER";
//        public static final String REAL_TYPE = " REAL";
//        public static final String TEXT_TYPE = " TEXT";
//        public static final String BLOB_TYPE = " BLOB";
//
//        public static final String COMMA_SEP = ",";
//    }

    public static class AccountTypes implements BaseColumns {
        public static final String TABLE_NAME = "account_types";

        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_HEX_COLOR = "hex_color";

        public static final String SQL_CREATE_ACCOUNT_TYPES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + INTEGER_TYPE + PRIMARY_KEY + "," +
                        COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_HEX_COLOR + TEXT_TYPE +
                        ")";

        public static final String SQL_DELETE_ACCOUNT_TYPES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class Accounts implements BaseColumns {
        public static final String TABLE_NAME = "accounts";

        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_BALANCE = "balance";
        public static final String COLUMN_NAME_ACCOUNT_LIMIT = "account_limit";
        public static final String COLUMN_NAME_TYPE_ID = "type_id";

        public static final String SQL_CREATE_ACCOUNTS =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + INTEGER_TYPE + PRIMARY_KEY + "," +
                        COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_BALANCE + REAL_TYPE + COMMA_SEP +
                        COLUMN_NAME_ACCOUNT_LIMIT + REAL_TYPE + COMMA_SEP +
                        COLUMN_NAME_TYPE_ID + INTEGER_TYPE +
                        ")";

        public static final String SQL_DELETE_ACCOUNTS =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class TransactionCategories implements BaseColumns {
        public static final String TABLE_NAME = "transaction_categories";

        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_HEX_COLOR = "hex_color";

        public static final String SQL_CREATE_TRANSACTION_CATEGORIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + INTEGER_TYPE + PRIMARY_KEY + "," +
                        COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_HEX_COLOR + TEXT_TYPE +
                        ")";

        public static final String SQL_DELETE_TRANSACTION_CATEGORIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        //TODO: Learn how to get strings in other languages here
        public static final String SQL_INSERT_DEFAULT_VALUES =
                "INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME_NAME + ", " + COLUMN_NAME_HEX_COLOR +
                        ") VALUES ('Lazer', '#8bc34a'), ('Educação', '#3f51b5'), " +
                        "('Entretenimento', '#9c27b0'), ('Saúde', '#f44336')";
    }

    public static class Transactions implements BaseColumns {
        public static final String TABLE_NAME = "transactions";

        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_VALUE = "value";
        public static final String COLUMN_NAME_CATEGORY_ID = "category_id";
        public static final String COLUMN_NAME_ACCOUNT_ID = "account_id";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_RELATED_TRANSACTION_ID = "transaction_id";
        public static final String COLUMN_NAME_ADDED = "added";

        public static final String SQL_CREATE_TRANSACTIONS =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + INTEGER_TYPE + PRIMARY_KEY + "," +
                        COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_VALUE + REAL_TYPE + COMMA_SEP +
                        COLUMN_NAME_CATEGORY_ID + INTEGER_TYPE + COMMA_SEP +
                        COLUMN_NAME_ACCOUNT_ID + INTEGER_TYPE + COMMA_SEP +
                        COLUMN_NAME_DATE + INTEGER_TYPE + COMMA_SEP +
                        COLUMN_NAME_RELATED_TRANSACTION_ID + INTEGER_TYPE + COMMA_SEP +
                        COLUMN_NAME_ADDED + INTEGER_TYPE +
                        ")";

        public static final String SQL_DELETE_TRANSACTIONS =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class Wishlist implements BaseColumns {
        public static final String TABLE_NAME = "wishlist";

        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_VALUE = "value";
        public static final String COLUMN_NAME_CATEGORY_ID = "category_id";

        public static final String SQL_CREATE_WISHLIST =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + INTEGER_TYPE + PRIMARY_KEY + "," +
                        COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_VALUE + REAL_TYPE + COMMA_SEP +
                        COLUMN_NAME_CATEGORY_ID + INTEGER_TYPE +
                        ")";

        public static final String SQL_DELETE_WISHLIST =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}