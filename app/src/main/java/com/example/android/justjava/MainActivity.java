//Template Code: https://gist.github.com/udacityandroid/83242daf8a43b743d05e98733a35b19f
//Template Code:  https://gist.github.com/anonymous/fa134c55a4a43e8d6004

//How to write documentation comments: https://www.oracle.com/technetwork/articles/java/index-137868.html

package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/** Allows the user to order drinks. */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Updates the screen with the current order.
     *
     *@see  MainActivity.displayPrice
     *@see  MainActivity.display
     *
     * Use For: onClick
     */
    public void submitOrder(View view) {
        int quantity = 2;
        display(quantity);
        displayPrice(quantity * 5);
    }

    /** Displays the number of drinks to order on the app screen.
     *
     *@param    number - How many drinks to order
     *@see      MainActivity.displayPrice
     *
     * Example Input: display(2);
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /** Displays the given price on the app screen.
     *
     *@param    number - The price to display
     *@see      MainActivity.display
     *
     * Example Input: displayPrice(2 * 5);
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}