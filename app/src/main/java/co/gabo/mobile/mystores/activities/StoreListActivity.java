package co.gabo.mobile.mystores.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import co.gabo.mobile.mystores.R;
import co.gabo.mobile.mystores.data.StoreData;
import co.gabo.mobile.mystores.data.StoreDataImpl;
import co.gabo.mobile.mystores.model.Store;

public class StoreListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mListView;
    private StoreData mStoreData;
    private List<Store> mCurrentStoreList;
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

                Intent intent = new Intent(StoreListActivity.this,StoreRegistrationActivity.class);
                StoreListActivity.this.startActivity(intent);

            }
        });

        mListView = (ListView)this.findViewById(R.id.list);
        mStoreData = new StoreDataImpl(this);
        mStoreData.open();


        List<Store> stores = mStoreData.findAll();

        int resource = android.R.layout.simple_list_item_1;
        // Aqui usa el ArrayApdater mas simple con el que viene android
        mStoreArrayAdapter = new ArrayAdapter<Store>(this,resource,stores);
        mListView.setAdapter(mStoreArrayAdapter);
        mListView.setOnItemClickListener(this);





    }

    @Override
    protected void onResume() {
        super.onResume();

        mCurrentStoreList = mStoreData.findAll();
        mStoreArrayAdapter.clear();
        mStoreArrayAdapter.addAll(mCurrentStoreList);
        mStoreArrayAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, WeatherActivity.class);

        Store selectedStore = mCurrentStoreList.get(position);

        // Aquí estamos enviando el objeto store
        // a la actividad WeatherActivity.
        // Para ello usamos un bundle ( Una estructura Key, Value)
        Bundle bundle = new Bundle();

        // Mala Practica aqui, recuerden que estos String, la clave "store" debería
        // ser una constante static en una clase constantes o en esta misma clase.
        bundle.putSerializable("store", selectedStore);

        // Este forma de pasar datos no es la más eficiente, pero si la más practica.
        // Ya que para el proceso de marshalling y unmarshalling en objetos serializados
        // se usa recursión lo que es computacionalmente costoso.

        // La más eficiente es usando objetos que implementen Parcelable, es más eficiente
        // pero no es la más practica. Cuando digo practica me refiero a la facilidad de mantener
        // o el impacto o cambios necesarios a hacer en el proyecto cuando agrego un atributo
        // adicional al objeto


        // Aqui agregamos nuestra estrucutra de datos bundle al intent
        intent.putExtras(bundle);

        // Iniciamos la otra actividad con los datos que enviamos en el Bundle
        this.startActivity(intent);
        //Matamos la actividad actual


    }
}
