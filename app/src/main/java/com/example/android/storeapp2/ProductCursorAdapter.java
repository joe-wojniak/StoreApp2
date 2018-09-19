package com.example.android.storeapp2;
/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Sections of example code ud845-pets were modified to complete
 * ABND Project Database App Stage 2: Sept-2018
 */

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.storeapp2.data.StoreContract;

public class ProductCursorAdapter extends CursorAdapter {
    
    private Context mContext;
    
    public ProductCursorAdapter(Context context, Cursor c) {
        
        super(context, c, 0);
        this.mContext = context;
    }

    //new list item No data set yet
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    //binding the data to the list item. one current product is set to on text list item layout
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //name price quantity to modify in the list item layout
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        final TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        Button sellButton = (Button) view.findViewById(R.id.sell_button);
        //find the right columns
        int nameColumnIndex = cursor.getColumnIndex(StoreContract.ProductEntry.COLUMN_PROD_NAME);
        int priceColumnIndex = cursor.getColumnIndex(StoreContract.ProductEntry.COLUMN_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(StoreContract.ProductEntry.COLUMN_QTY);
        //to read the attributes from the current product
        String prodName = cursor.getString(nameColumnIndex);
        String prodPrice = cursor.getString(priceColumnIndex);
        final String[] prodQuantity = {cursor.getString(quantityColumnIndex)};
        //update the view with the current attributes
        nameTextView.setText(prodName);
        priceTextView.setText(prodPrice);
        quantityTextView.setText(prodQuantity[0]);

        //set up button listener
        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Qty = Integer.parseInt(prodQuantity[0]);
                if (Qty>0){
                    Qty = Qty -1;
                    quantityTextView.setText(Integer.toString(Qty));
                    prodQuantity[0] = Integer.toString(Qty);
                }
            }
        });
    }
}