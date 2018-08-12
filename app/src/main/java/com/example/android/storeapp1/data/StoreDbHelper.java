package com.example.android.storeapp1.data;

/**
 * Udacity ABND Database project stage 1
 * ud845-Pets used as example code
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StoreDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = StoreDbHelper.class.getSimpleName();

    public static final String DATABASE_NAME = "store.db";
    private static final int DATABASE_VERSION = 1;

    public StoreDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_STORE_TABLE = "CREATE TABLE " + StoreContract.ProductEntry.TABLE_NAME + " ("
                + StoreContract.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + StoreContract.ProductEntry.COLUMN_PROD_NAME + " TEXT, "
                + StoreContract.ProductEntry.COLUMN_PRICE + " TEXT, "
                + StoreContract.ProductEntry.COLUMN_QTY + " TEXT, "
                + StoreContract.ProductEntry.COLUMN_SUPPLIER + " TEXT, "
                + StoreContract.ProductEntry.COLUMN_PHONE + " TEXT);";

        Log.e( LOG_TAG, "SQL_CREATE_STORE_TABLE " + SQL_CREATE_STORE_TABLE);

        db.execSQL(SQL_CREATE_STORE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // placeholder - database is at version 1
    }
}
