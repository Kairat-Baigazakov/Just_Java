package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        int orders = 1;
        int priceCapucino = 5;
        display(orders);
        displayPrice(priceCapucino * orders);
    }

    public void increment(View view) {
        int orders = 0;
        orders++;
        display(orders);
    }

    public void decrement(View view) {
        int orders = 1;
        display(orders);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}