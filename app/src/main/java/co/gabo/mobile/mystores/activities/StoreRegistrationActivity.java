package co.gabo.mobile.mystores.activities;

import android.content.Intent;
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

public class StoreRegistrationActivity extends AppCompatActivity {

    private StoreData mStoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        mStoreData = new StoreDataImpl(this);
        mStoreData.open();

    }



    public void save(View view){

        // Importante usar nombre nemotécnicos para los id's de los
        // componentes gráficos en los layouts

        EditText name = (EditText)this.findViewById(R.id.et_store_name);
        EditText address = (EditText)this.findViewById(R.id.et_store_address);
        EditText owner = (EditText)this.findViewById(R.id.et_store_owner);
        EditText city = (EditText)this.findViewById(R.id.et_store_city);

        //Procesos como la validación de un componente esta vacio se pueden
        //encapsular en una clase con en un método estatico.
        //Recuerden que la invocación de un metodo estatico es mucho más rapido que un metodo en una instancia.
        if(UIUtil.isEmpty(name)||
           UIUtil.isEmpty(address)||
           UIUtil.isEmpty(owner)||
           UIUtil.isEmpty(city)
                ){
            // Siempre use el archivo strings para los mensajes. Así, sera más fácil traducir la
            // aplicación a otro idiomas o cambiar mensajes que se usen en varias partes de la
            // app
            Snackbar.make(view,R.string.err_msg_empty_fields,Snackbar.LENGTH_LONG).show();
            return;
        }

        Store store = new Store();
        store.setName(name.getText().toString());
        store.setOwner(owner.getText().toString());
        store.setAddress(address.getText().toString());
        store.setCity(city.getText().toString());
        // Aqui enviamos cualuqier valor ya que en nuestra base de datos
        // en la tabla stores este campo es not null
        store.setLocation("");

        // Insertamos en la base de datos
        mStoreData.insert(store);

        // Después de almacenado el store cerramos la actividad de registro con this.finish().
        // Y regresamos a la que debe estar en pause, que es la StoreListActivity.
        this.finish();







    }

}
