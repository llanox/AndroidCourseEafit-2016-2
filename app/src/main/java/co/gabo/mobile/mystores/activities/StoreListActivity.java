package co.gabo.mobile.mystores.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import co.gabo.mobile.mystores.R;
import co.gabo.mobile.mystores.data.StoreData;
import co.gabo.mobile.mystores.data.StoreDataImpl;
import co.gabo.mobile.mystores.model.Store;

public class StoreListActivity extends AppCompatActivity {

    private ListView mListView;
    private StoreData mStoreData;
    private ArrayAdapter<Store> mStoreArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StoreListActivity.this,StoreActivity.class);
                StoreListActivity.this.startActivity(intent);

            }
        });

        mListView = (ListView)this.findViewById(R.id.list);
        mStoreData = new StoreDataImpl(this);
        mStoreData.open();


        List<Store> stores = mStoreData.findAll();

        int resource = android.R.layout.simple_list_item_1;
        mStoreArrayAdapter = new ArrayAdapter<Store>(this,resource,stores);
        mListView.setAdapter(mStoreArrayAdapter);



    }

    @Override
    protected void onResume() {
        super.onResume();






    }
}
