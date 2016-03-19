package co.gabo.mobile.mystores.util;

import android.widget.EditText;

/**
 * Created by juangabrielgutierrez on 3/18/16.
 */
public class UIUtil {


    public static boolean isEmpty(EditText component) {

        String content;

        if(component == null)
            return true;

        content = component.getText().toString();

        if(content.trim().isEmpty() )
            return true;


        return false;
    }
}
