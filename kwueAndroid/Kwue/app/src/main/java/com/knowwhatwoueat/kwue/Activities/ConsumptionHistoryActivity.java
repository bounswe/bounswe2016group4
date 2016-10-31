package com.knowwhatwoueat.kwue.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.DataModels.Server;
import com.knowwhatwoueat.kwue.R;

import java.util.ArrayList;
import java.util.List;

public class ConsumptionHistoryActivity extends SearchableActivity {
    private List<Food> consumptionHistory;
    private String[] consumptionList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_history);

        //// TODO: 26.10.2016 Remove these hardcodes, pull it from backend
        addDummyFood();
        consumptionList = getFoodNames();
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.consumption_history_list_item,R.id.FoodName, consumptionList);
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

    private String[] getFoodNames(){
        String[] foods = new String[consumptionHistory.size()];
        for(int i = 0 ; i< consumptionHistory.size();i++){
            foods[i] = consumptionHistory.get(i).getName();
        }
        return foods;
    }
}
