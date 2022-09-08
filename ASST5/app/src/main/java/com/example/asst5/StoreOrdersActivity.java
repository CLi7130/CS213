package com.example.asst5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Class containing all methods and parameters necessary for the Store Orders Activity.
 * @author Craig Li, Prerak Patel
 */
public class StoreOrdersActivity extends AppCompatActivity {

    private StoreOrders storeOrders;
    private DecimalFormat money = new DecimalFormat("$#,##0.00");
    private ArrayList<String> orderNumbers = new ArrayList<>();

    private TextView so_total, so_tax, so_subtotal, orderDisplay;
    private Spinner orderNumSPIN;
    private Button removeOrderBTN;
    private int orderNumber;
    private ArrayAdapter<String> arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        storeOrders = com.example.asst5.MainActivity.getStoreOrders();

        so_total = (TextView)findViewById(R.id.so_total);
        so_tax = (TextView)findViewById(R.id.so_tax);
        so_subtotal = (TextView)findViewById(R.id.so_subtotal);
        orderDisplay = (TextView)findViewById(R.id.orderDisplay);
        orderNumSPIN = (Spinner)findViewById(R.id.orderNumSPIN);
        removeOrderBTN = (Button)findViewById(R.id.removeOrderBTN);

        for(Order order : storeOrders.getStoreOrders()){
            //add order number to spinner
            orderNumbers.add(Integer.toString(order.getOrderNumber()));
        }

        arrayAdapter = new ArrayAdapter<String>(StoreOrdersActivity.this,
                android.R.layout.simple_spinner_item, orderNumbers);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumSPIN.setAdapter(arrayAdapter);

        orderNumSPIN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Calls setDisplay() when a new order number is selected.
             * @param parentView
             * @param selectedItemView
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //set total/tax/display
                //set listview to orders
                setDisplay();
            }

            /**
             * Empty method, no action needed if nothing is selected.
             * @param parentView
             */
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //continue/break? Don't need to take any action here.
            }
        });
        removeOrderBTN.setOnClickListener(v -> {
            if(storeOrders.getNumStoreOrder() == 0){
                checkIfEmpty();
            }
            else{
                orderNumber = Integer.parseInt(orderNumSPIN.getSelectedItem().toString());
                int orderIndex = storeOrders.findOrder(orderNumber);
                Order tempOrder = storeOrders.getStoreOrders().get(orderIndex);

                for(int i = 0; i < orderNumbers.size(); i++) {
                    if(orderNumber == Integer.parseInt(orderNumbers.get(i))) {
                        orderNumbers.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }

                storeOrders.remove(tempOrder);
                Toast.makeText(this, R.string.order_removed, Toast.LENGTH_SHORT).show();
            }
            if(storeOrders.getNumStoreOrder() > 0){
                orderNumSPIN.setSelection(0,true);
            }
            else{
                checkIfEmpty();
            }

            setDisplay();
        });



    }
    /**
     * Sets Display for Store Orders
     */
    private void setDisplay(){
        if(storeOrders.getNumStoreOrder() == 0) {
            checkIfEmpty();
            so_subtotal.setText(null);
            so_tax.setText(null);
            so_total.setText(null);
            orderDisplay.setText(R.string.no_store_orders);

        }
        else{
            orderNumber = Integer.parseInt(orderNumSPIN.getSelectedItem().toString());
            int orderIndex = storeOrders.findOrder(orderNumber);
            Order tempOrder = storeOrders.getStoreOrders().get(orderIndex);

            orderDisplay.setText(tempOrder.print());
            double subtotal = tempOrder.getSubtotal();
            double tax = tempOrder.getOrderTax();
            double total = subtotal + tax;
            so_subtotal.setText(money.format(subtotal));
            so_tax.setText(money.format(tax));
            so_total.setText(money.format(total));
        }


    }

    /**
     * Checks to see if the order is empty, disables buttons if order is empty.
     *
     */
    private void checkIfEmpty() {
        if(storeOrders.getNumStoreOrder() == 0) {
            removeOrderBTN.setEnabled(false);
            orderNumSPIN.setEnabled(false);
        }
        else{
            removeOrderBTN.setEnabled(true);
            orderNumSPIN.setEnabled(true);
        }

    }
}