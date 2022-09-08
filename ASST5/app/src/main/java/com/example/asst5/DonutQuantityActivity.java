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

/**
 * Class containing all methods and parameters necessary for the Donut Quantity Activity.
 * @author Craig Li, Prerak Patel
 */
public class DonutQuantityActivity extends AppCompatActivity {

    private final static int DONUT_MIN_QTY = 1;
    private final static int DONUT_MAX_QTY = 6;
    private String[] choices = new String[DONUT_MAX_QTY];
    private com.example.asst5.Donut currentDonut;
    private com.example.asst5.Order currentOrder;
    private int quantity;
    private DecimalFormat money = new DecimalFormat("$#,##0.00");
    private TextView flavorDisplay, subtotal;
    private Spinner quantityMenu;
    private Button addToOrderBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_quantity);

        currentDonut = (com.example.asst5.Donut) getIntent().getSerializableExtra("currentDonut");
        currentOrder = com.example.asst5.MainActivity.getCurrentOrder();

        flavorDisplay = (TextView) findViewById(R.id.flavorDisplay);
        subtotal = (TextView)findViewById(R.id.subtotal);
        quantityMenu = (Spinner) findViewById(R.id.quantityMenu);
        addToOrderBTN = (Button) findViewById(R.id.addToOrderBTN);



        for(int i = DONUT_MIN_QTY; i <= DONUT_MAX_QTY; i++){
            choices[i - 1] = Integer.toString(i);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DonutQuantityActivity.this,
                                            android.R.layout.simple_spinner_item, choices);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantityMenu.setAdapter(arrayAdapter);

        quantityMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Updates the donut price if a new Quantity is selected.
             * @param parentView
             * @param selectedItemView
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                quantity = Integer.parseInt(quantityMenu.getSelectedItem().toString());
                currentDonut.setQuantity(quantity);
                currentDonut.itemPrice();
                subtotal.setText(money.format(currentDonut.getPrice()));
            }

            /**
             * Empty method, we don't need to take action if nothing is selected
             * @param parentView
             */
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //continue/break?
            }
        });
        addToOrderBTN.setOnClickListener(v -> {
            quantity = Integer.parseInt(quantityMenu.getSelectedItem().toString());
            currentDonut.setQuantity(quantity);
            currentDonut.itemPrice();
            currentOrder.add(currentDonut);

            Toast.makeText(this, R.string.added_to_order, Toast.LENGTH_SHORT).show();
        });
        flavorDisplay.setText(currentDonut.getFlavor());
        quantity = Integer.parseInt(quantityMenu.getItemAtPosition(0).toString());
        currentDonut.setQuantity(quantity);
        currentDonut.itemPrice();
        subtotal.setText(money.format(currentDonut.getPrice()));
    }

}