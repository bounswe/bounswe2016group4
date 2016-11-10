package com.knowwhatwoueat.kwue.Activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.knowwhatwoueat.kwue.Adapters.ConsumptionListAdapter;
import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.DataModels.Server;
import com.knowwhatwoueat.kwue.R;

import java.util.ArrayList;
import java.util.List;

public class ConsumptionHistoryActivity extends AppCompatActivity {
    private List<Food> consumptionHistory;
    private String[] consumptionList;
    private String[] imageList;
    private Toolbar toolbar;
    //public Bitmap photo = new Bitmap();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_history);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        //// TODO: 26.10.2016 Remove these hardcodes, pull it from backend
        addDummyFood();
        consumptionList = getFoodNames();
        imageList = getImageUrls();
        //ListAdapter adapter = new ConsumptionListAdapter(this,consumptionList,consumptionList,);
        ListAdapter adapter = new ConsumptionListAdapter(this,consumptionHistory);
        ListView listView = (ListView) findViewById(R.id.consumptionlist);

        listView.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);

        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        }


        return super.onCreateOptionsMenu(menu);
    }
    private void addDummyFood(){
        consumptionHistory = new ArrayList<Food>();
        String url =  "http://adanamreklam.com/firmalar/s_176_Adana.png";
        consumptionHistory.add(new Food(new Server(),"description","Balik",null,null,null,url));
        consumptionHistory.add(new Food(new Server(),"description","Kebap",null,null,null,url));
        consumptionHistory.add(new Food(new Server(),"description","Tatlı",null,null,null,url));
        consumptionHistory.add(new Food(new Server(),"description","Su",null,null,null,url));
        consumptionHistory.add(new Food(new Server(),"description","Kola",null,null,null,url));
        consumptionHistory.add(new Food(new Server(),"description","Sandviç",null,null,null,url));
        consumptionHistory.add(new Food(new Server(),"description","Boza",null,null,null,url));
        consumptionHistory.add(new Food(new Server(),"description","Brokoli",null,null,null,url));
        consumptionHistory.add(new Food(new Server(),"description","Tavuk",null,null,null,url));
    }

    private String[] getFoodNames(){
        String[] foods = new String[consumptionHistory.size()];
        for(int i = 0 ; i< consumptionHistory.size();i++){
            foods[i] = consumptionHistory.get(i).getName();
        }
        return foods;
    }

    private String[] getImageUrls(){
        String[] urls = new String[consumptionHistory.size()];

        for(int i = 0 ; i< consumptionHistory.size();i++){
            urls[i] = consumptionHistory.get(i).getImageUrl();
        }
        return urls;
    }

}
