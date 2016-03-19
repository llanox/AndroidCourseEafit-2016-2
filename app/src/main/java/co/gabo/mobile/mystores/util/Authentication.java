package co.gabo.mobile.mystores.util;

/**
 * Created by juangabrielgutierrez on 3/18/16.
 */
public interface Authentication {

    boolean authorize(String sEmail, String sPassword);

    boolean hasAnActiveUser();
}
