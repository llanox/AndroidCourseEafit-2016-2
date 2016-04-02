package co.gabo.mobile.mystores.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    private static final String TAG = DBAdapter.class.getSimpleName();    

    static final String DATABASE_NAME = "StoresDB";
    static final int DATABASE_VERSION = 1;
    
    static final String DATABASE_CREATE = StoreData.CREATE_TABLE;
    

    private final Context context;
    private DatabaseHelper DBHelper;
    protected SQLiteDatabase db;
    
    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version" + oldVersion + " to "+ newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS "+StoreData.DATABASE_TABLE);
            onCreate(db);
        }
    }
    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }
    
    
    

}

