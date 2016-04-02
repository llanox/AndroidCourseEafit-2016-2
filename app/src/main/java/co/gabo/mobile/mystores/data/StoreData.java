package co.gabo.mobile.mystores.data;

import java.util.List;

import co.gabo.mobile.mystores.model.Store;

/**
 * Created by juangabrielgutierrez on 4/1/16.
 */
public interface StoreData {

    String DATABASE_TABLE = "stores";
    String KEY_ROWID = "_id";
    String KEY_NAME = "name";
    String KEY_ADDRESS = "address";
    String KEY_OWNER = "owner";
    String KEY_LOCATION = "location";

    String CREATE_TABLE =
            "create table "+StoreData.DATABASE_TABLE+" "+
                    "("+StoreData.KEY_ROWID+" integer primary key autoincrement, "
                    + ""+StoreData.KEY_NAME+" text not null, "
                    +StoreData.KEY_ADDRESS+" text not null," +
                    ""+StoreData.KEY_OWNER+" text not null," +
                    ""+StoreData.KEY_LOCATION+" text not null );";

    List<Store> findAll();


    DBAdapter open();
}
