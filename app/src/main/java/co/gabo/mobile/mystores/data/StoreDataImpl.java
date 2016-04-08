package co.gabo.mobile.mystores.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

        Store store = null;
        Cursor c = db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME, KEY_ADDRESS,KEY_CITY,KEY_OWNER,KEY_LOCATION}, null, null, null, null, null);

        if (c.moveToFirst())
        {
            do {

                store = new Store();
               // store.setId(c.getLong(c.getColumnIndex(KEY_ROWID)));
                store.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                store.setAddress(c.getString(c.getColumnIndex(KEY_ADDRESS)));
                store.setOwner(c.getString(c.getColumnIndex(KEY_OWNER)));
                store.setLocation(c.getString(c.getColumnIndex(KEY_LOCATION)));
                stores.add(store);

            } while (c.moveToNext());
        }

        return stores;
    }

    public void insert(Store store)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, store.getName());
        initialValues.put(KEY_ADDRESS, store.getAddress());
        initialValues.put(KEY_OWNER, store.getOwner());
        initialValues.put(KEY_LOCATION, store.getLocation());
        initialValues.put(KEY_CITY,store.getCity());

        db.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    @Override
    public DBAdapter open() throws SQLException {
        return super.open();
    }
}
