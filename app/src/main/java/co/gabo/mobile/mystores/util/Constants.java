package co.gabo.mobile.mystores.util;

/**
 * Created by Juan Gabriel Gutierrez
 *
 * Esta clase me permite mantener todas las variables constantes en una clase.
 * Puede crear clases internas para organizar mejor las contantes usadas en la
 * app
 *
 */
public class Constants {

    public static final String WEATHER_API_BASE_URL="http://api.openweathermap.org/data/2.5/";
    public static final String QUERY_WEATHER_BY_CITY ="weather?q=%s";
    public static final String API_ID_PARAMETER ="&APPID=%s";
    public static final String METRICS_PARAMETER ="&units=%s";


    public static final String WEATHER_BY_CITY_PATH = WEATHER_API_BASE_URL+QUERY_WEATHER_BY_CITY+METRICS_PARAMETER+API_ID_PARAMETER;


}
