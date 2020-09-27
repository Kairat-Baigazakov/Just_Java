package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int orders = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = findViewById(R.id.checkbox_view);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = findViewById(R.id.checkbox_view_chocolate);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        EditText text = findViewById(R.id.edit_text_view);
        String name = text.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(price, orders, hasWhippedCream, hasChocolate, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for" + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public int calculatePrice(boolean cream, boolean chocolate) {
        int price = 5;

        if (cream) {
            price += 1;
        }
        if (chocolate) {
            price += 2;
        }

        return price * orders;
    }

    public String createOrderSummary(int price, int orders, boolean hasWhippedCream, boolean hasChocolate, String name) {
        String text = "Name: " + name;
        text += "\nAdd whipped cream? " + hasWhippedCream;
        text += "\nAdd chocolate? " + hasChocolate;
        text += "\nQuantity: " + orders;
        text += "\nTotal: $" + price;
        text += "\nThank you!";
        return text;
    }

    public void increment(View view) {
        if (orders < 100) {
            orders = orders + 1;
        }
        display(orders);
    }

    public void decrement(View view) {
        if (orders > 1) {
            orders = orders - 1;
        }
        display(orders);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}