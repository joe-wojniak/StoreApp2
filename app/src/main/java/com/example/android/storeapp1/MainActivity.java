package com.example.android.storeapp1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.storeapp1.data.StoreContract;
import com.example.android.storeapp1.data.StoreDbHelper;

public class MainActivity extends AppCompatActivity {

    private StoreDbHelper mDbHelper; //Helper class to access database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new StoreDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the database.
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                StoreContract.ProductEntry._ID,
                StoreContract.ProductEntry.COLUMN_PROD_NAME,
                StoreContract.ProductEntry.COLUMN_PRICE,
                StoreContract.ProductEntry.COLUMN_QTY,
                StoreContract.ProductEntry.COLUMN_SUPPLIER,
                StoreContract.ProductEntry.COLUMN_PHONE};

        // Perform a query on the products table
        Cursor cursor = db.query(
                StoreContract.ProductEntry.TABLE_NAME,   // The table to query
                projection,                             // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_store);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The products table contains <number of rows in Cursor> products.
            // _id - product name - price - quantity - supplier name - supplier phone number
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The products table contains " + cursor.getCount() + " products.\n\n");
            displayView.append(StoreContract.ProductEntry._ID + " - " +
                    StoreContract.ProductEntry.COLUMN_PROD_NAME + " - " +
                    StoreContract.ProductEntry.COLUMN_PRICE + " - " +
                    StoreContract.ProductEntry.COLUMN_QTY + " - " +
                    StoreContract.ProductEntry.COLUMN_SUPPLIER + " - " +
                    StoreContract.ProductEntry.COLUMN_PHONE + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(StoreContract.ProductEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(StoreContract.ProductEntry.COLUMN_PROD_NAME);
            int priceColumnIndex = cursor.getColumnIndex(StoreContract.ProductEntry.COLUMN_PRICE);
            int qtyColumnIndex = cursor.getColumnIndex(StoreContract.ProductEntry.COLUMN_QTY);
            int supplierColumnIndex = cursor.getColumnIndex(StoreContract.ProductEntry.COLUMN_SUPPLIER);
            int phoneColumnIndex = cursor.getColumnIndex(StoreContract.ProductEntry.COLUMN_PHONE);
            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQty = cursor.getInt(qtyColumnIndex);
                String currentSupplier = cursor.getString(supplierColumnIndex);
                String currentPhone = cursor.getString(phoneColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - $" +
                        currentPrice + " - " +
                        currentQty + " - " +
                        currentSupplier + " - " +
                        currentPhone));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

}
