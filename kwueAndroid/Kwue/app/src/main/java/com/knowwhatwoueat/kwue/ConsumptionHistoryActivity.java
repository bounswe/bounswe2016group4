package com.knowwhatwoueat.kwue;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ConsumptionHistoryActivity extends AppCompatActivity {
    private List<Food> consumptionHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //// TODO: 26.10.2016 Remove these hardcodes, pull it from backend
        addDummyFood();
        String[] dummyString = {"  ", "asd"};
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.consumption_history_list_item,R.id.FoodName, dummyString);
        ListView listView = (ListView) findViewById(R.id.cunsumptionlist);
        listView.setAdapter(adapter);
    }
    private void addDummyFood(){
        consumptionHistory = new ArrayList<Food>();
        consumptionHistory.add(new Food(new Server(),"description","Balik",null,null,null));
        consumptionHistory.add(new Food(new Server(),"description","Kebap",null,null,null));
        consumptionHistory.add(new Food(new Server(),"description","Tatlı",null,null,null));
        consumptionHistory.add(new Food(new Server(),"description","Su",null,null,null));
        consumptionHistory.add(new Food(new Server(),"description","Kola",null,null,null));
        consumptionHistory.add(new Food(new Server(),"description","Sandviç",null,null,null));
        consumptionHistory.add(new Food(new Server(),"description","Boza",null,null,null));
        consumptionHistory.add(new Food(new Server(),"description","Brokoli",null,null,null));
        consumptionHistory.add(new Food(new Server(),"description","Tavuk",null,null,null));
    }


}
