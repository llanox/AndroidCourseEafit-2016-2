package co.gabo.mobile.mystores.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import co.gabo.mobile.mystores.activities.MainActivity;

/**
 * Created by juangabrielgutierrez on 3/18/16.
 */
public class AuthenticationImpl implements Authentication{

    public static final String USER_DATA = "user_data";
    public static final String USER_KEY = "user_key";
    public static final String TOKEN_KEY = "token_key";
    private Context mContext;

    public AuthenticationImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean authorize(String sEmail, String sPassword) {


        //Logica de autenticacion con el Backend
        String token = "jshjashjashjahsqwqwqwqe";

        saveUser(sEmail,token);

        return true;
    }

    @Override
    public boolean hasAnActiveUser() {
        SharedPreferences pref = mContext.getSharedPreferences(USER_DATA, Activity.MODE_PRIVATE);
        String user = pref.getString(USER_KEY,"");

        if(!user.isEmpty())
            return true;


        return false;
    }

    private void saveUser(String sEmail, String token) {

        SharedPreferences pref = mContext.getSharedPreferences(USER_DATA, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(USER_KEY,sEmail);
        editor.putString(TOKEN_KEY,token);
        editor.commit();

    }


}
