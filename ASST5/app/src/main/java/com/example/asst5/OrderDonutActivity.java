package com.example.asst5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.asst5.Enums.DONUT_FLAVORS;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class containing all methods and parameters necessary for the Ordering Donut Activity.
 * @author Craig Li, Prerak Patel
 */
public class OrderDonutActivity extends Activity implements Serializable {

    private ListView donutFlavors;
    private ArrayList<String> donutFlavorsList = new ArrayList<>();
    private Donut currentDonut;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_donut);

        currentDonut = new Donut();

        donutFlavors = (ListView) findViewById(R.id.donutFlavors);

        for( DONUT_FLAVORS flavor : DONUT_FLAVORS.values()){
            donutFlavorsList.add(flavor.toString());
        }

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                donutFlavorsList);

        donutFlavors.setAdapter(adapter);

        donutFlavors.setOnItemClickListener((parent, view, position, id) -> {
            currentDonut.setFlavor(donutFlavorsList.get(position).toString());
            Intent intent = new Intent(OrderDonutActivity.this, DonutQuantityActivity.class);
            intent.putExtra("currentDonut", currentDonut);
            startActivity(intent);
        });


    }




}
