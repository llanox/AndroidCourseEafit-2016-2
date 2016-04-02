package co.gabo.mobile.mystores.activities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import co.gabo.mobile.mystores.R;
import co.gabo.mobile.mystores.data.StoreData;
import co.gabo.mobile.mystores.data.StoreDataImpl;
import co.gabo.mobile.mystores.model.Store;
import co.gabo.mobile.mystores.util.UIUtil;

public class StoreActivity extends AppCompatActivity {

    private StoreData mStoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        mStoreData = new StoreDataImpl(this);
        mStoreData.open();

    }


    public void save(View view){

        EditText name = (EditText)this.findViewById(R.id.et_store_name);
        EditText address = (EditText)this.findViewById(R.id.et_store_address);
        EditText owner = (EditText)this.findViewById(R.id.et_store_owner);
        EditText city = (EditText)this.findViewById(R.id.et_store_city);

        if(UIUtil.isEmpty(name)||
           UIUtil.isEmpty(address)||
           UIUtil.isEmpty(owner)||
           UIUtil.isEmpty(city)
                ){
            Snackbar.make(view,"There are empty fields!!!",Snackbar.LENGTH_LONG).show();
            return;
        }

        Store store = new Store();
        store.setName(name.getText().toString());
        store.setOwner(owner.getText().toString());
        store.setAddress(address.getText().toString());
        store.setCity(city.getText().toString());
        store.setLocation("");

        mStoreData.insert(store);

        this.finish();
    }

}
