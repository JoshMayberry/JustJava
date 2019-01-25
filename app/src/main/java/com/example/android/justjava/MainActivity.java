//Template Code: https://gist.github.com/udacityandroid/83242daf8a43b743d05e98733a35b19f
//Template Code:  https://gist.github.com/anonymous/fa134c55a4a43e8d6004

//How to write documentation comments: https://www.oracle.com/technetwork/articles/java/index-137868.html

package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/** Allows the user to order drinks. */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    CheckBox viewWhippedCream;
    CheckBox viewChacolate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView splashMessage = new TextView(this);
        splashMessage.setText("Loading...");
        splashMessage.setTextSize(24);
        splashMessage.setGravity(17); //https://developer.android.com/reference/android/view/Gravity.html#CENTER
        setContentView(splashMessage);
        // setContentView(R.layout.splash);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewWhippedCream = findViewById(R.id.hasWhippedCream);
        viewChacolate = findViewById(R.id.hasChacolate);
    }

    /** Increases the number of drinks to order by 1.
     *
     *@see  this.decrement
     *
     * Use For: onClick
     */
    public void increment(View view) {
        if (quantity >= 100) {
            Toast.makeText(this, "You cannot have more than 100 drinks", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity += 1;
        display(quantity);
    }

    /** Decreases the number of drinks to order by 1.
     *
     *@see  this.increment
     *
     * Use For: onClick
     */
    public void decrement(View view) {
        if (quantity <= 1) {
            Toast.makeText(this, "You cannot have less than 1 drink", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity -= 1;
        display(quantity);
    }

    private String createOrderSummary() {
        EditText viewCustomerName = findViewById(R.id.customerName);
        String summary = "Name: " + viewCustomerName.getText() + "\n";
        if (viewWhippedCream.isChecked()){
            summary += "Add Whipped Cream" + "\n";
        }
        if (viewChacolate.isChecked()){
            summary += "Add Chacolate" + "\n";
        }
        summary += "Quantity :" + quantity + "\n";
        summary += "Total: $" + calculatePrice(5) + "\n";
        summary += "Thank you!";
        return summary;
    }

    /** Updates the screen with the current order.
     *
     *@see  this.displayPrice
     *@see  this.display
     *
     * Use For: onClick
     */
    public void submitOrder(View view) {

        String priceMessage = createOrderSummary();
        displayMessage(priceMessage);
    }

    /** Displays the number of drinks to order on the app screen.
     *
     *@param    number - How many drinks to order
     *@see      this.displayPrice
     *
     * Example Input: display(2);
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private int calculatePrice(int unitPrice) {
        if (viewChacolate.isChecked()){
            unitPrice += 2;
        }
        if (viewWhippedCream.isChecked()){
            unitPrice += 1;
        }

        return quantity * unitPrice;
    }

    /** Displays the given text on the app screen.
     *
     *@param    message - The text to display
     *@see      this.display
     *
     * Example Input: displayMessage("Lorem Ipsu");
    */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);

        String logMessage = priceTextView.getText().toString();
        Log.v("MainActivity", logMessage);
    }
}