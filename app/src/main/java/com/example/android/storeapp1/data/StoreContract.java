package com.example.android.storeapp1.data;

import android.provider.BaseColumns;

public final class StoreContract {

    private StoreContract(){}

    public static final class ProductEntry implements BaseColumns {
        public final static String TABLE_NAME = "products";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PROD_NAME = "product";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QTY = "quantity";
        public static final String COLUMN_SUPPLIER = "supplier_name";
        public static final String COLUMN_PHONE = "supplier_phone_number";
    }
}
