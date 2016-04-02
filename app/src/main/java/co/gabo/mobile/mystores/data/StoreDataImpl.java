package co.gabo.mobile.mystores.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;

import java.util.ArrayList;
import java.util.List;

import co.gabo.mobile.mystores.model.Store;

/**
 * Created by juangabrielgutierrez on 4/1/16.
 */
public class StoreDataImpl extends DBAdapter implements StoreData {




    public StoreDataImpl(Context ctx) {
        super(ctx);
    }

    @Override
    public List<Store> findAll() {

        List<Store> stores = new ArrayList<Store>();

        Store store = new Store();
        store.setName("Pollos Mario");
        store.setOwner("Julio Mario Santo Domingo");
        store.setAddress("Calle 8 36 68 Barrio Poblado");

        stores.add(store);


        return stores;
    }

    public long insert(Store store)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, store.getName());
        initialValues.put(KEY_ADDRESS, store.getAddress());
        initialValues.put(KEY_OWNER, store.getOwner());
        initialValues.put(KEY_LOCATION, store.getLocation());
        initialValues.put(KEY_CITY,store.getCity());

        return db.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    @Override
    public DBAdapter open() throws SQLException {
        return super.open();
    }
}
