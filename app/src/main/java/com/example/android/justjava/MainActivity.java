//Template Code: https://gist.github.com/udacityandroid/83242daf8a43b743d05e98733a35b19f
//Template Code:  https://gist.github.com/anonymous/fa134c55a4a43e8d6004

//How to write documentation comments: https://www.oracle.com/technetwork/articles/java/index-137868.html

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
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
    EditText viewCustomerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView splashMessage = new TextView(this);
        splashMessage.setText(getString(R.string.loading));
        splashMessage.setTextSize(24);
        splashMessage.setGravity(17); //https://developer.android.com/reference/android/view/Gravity.html#CENTER
        setContentView(splashMessage);
        // setContentView(R.layout.splash);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewWhippedCream = findViewById(R.id.hasWhippedCream);
        viewChacolate = findViewById(R.id.hasChacolate);
        viewCustomerName = findViewById(R.id.customerName);
    }

    /** Increases the number of drinks to order by 1.
     *
     *@see  this.decrement
     *
     * Use For: onClick
     */
    public void increment(View view) {
        if (quantity >= 100) {
            Toast.makeText(this, getString(R.string.toast_overflow), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, getString(R.string.toast_underflow), Toast.LENGTH_SHORT).show();
            return;
        }
        quantity -= 1;
        display(quantity);
    }

    private String createOrderSummary() {
        String name = viewCustomerName.getText().toString();
        String summary = getString(R.string.recept_name,name) + "\n";
        if (viewWhippedCream.isChecked()){
            summary += getString(R.string.recept_cream) + "\n";
        }
        if (viewChacolate.isChecked()){
            summary += getString(R.string.recept_coco) + "\n";
        }
        summary += getString(R.string.recept_quantity, quantity) + "\n";
        summary += getString(R.string.recept_total, calculatePrice(5)) + "\n";
        summary += getString(R.string.recept_ending);
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

        //https://developer.android.com/guide/components/intents-common?utm_source=udacity&utm_medium=course&utm_campaign=android_basics#Email
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.recept_subject, viewCustomerName.getText().toString()));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

//        //https://developer.android.com/guide/components/intents-common?utm_source=udacity&utm_medium=course&utm_campaign=android_basics#Maps
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6, -122.3"));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }

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