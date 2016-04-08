package co.gabo.mobile.mystores;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import co.gabo.mobile.mystores.model.Store;

public class WeatherActivity extends AppCompatActivity {

    private static final String URL_QUERY="http://api.openweathermap.org/data/2.5/weather?q=%s";
    private Store mStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        if(this.getIntent()!=null){

            Bundle bundle = this.getIntent().getExtras();

            mStore = (Store) bundle.getSerializable("store");

            DownloadData downloadData = new DownloadData();
          //  downloadData.execute(mStore.getCity());

        }



    }



    String queryToAPI(String ciudad) {
        String temp =null;
        try {

            String query = String.format(URL_QUERY, URLEncoder.encode(ciudad, "utf-8"));

            //set the download URL, a url that points to a file on the internet
            //this is the file to be downloaded
            URL url = new URL(query);

            //create the new connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //set up some things on the connection
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);

            //and connect!
            urlConnection.connect();

            //this will be used in reading the data from the internet
            String response = urlConnection.getResponseMessage();
            InputStream data = (InputStream) urlConnection.getContent();

            InputStreamReader is = new InputStreamReader(data);
            StringBuilder sb=new StringBuilder();
            BufferedReader br = new BufferedReader(is);
            String read = br.readLine();

            while(read != null) {
                //System.out.println(read);
                sb.append(read);
                read = br.readLine();

            }




            JSONObject object = new JSONObject(sb.toString());
            JSONObject main = (JSONObject) object.get("main");
            temp = main.getString("temp");






            //catch some possible errors...
        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
        catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return temp;
    }




    class DownloadData extends AsyncTask<String,Long,String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            TextView temperature = (TextView) WeatherActivity.this.findViewById(R.id.id_store_temperature);
            temperature.setText("Iniciando consulta del clima...");
        }



        @Override
        protected String doInBackground(String... params) {

            String city = params[0];
            String temp = WeatherActivity.this.queryToAPI(city);
            return temp;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            TextView temperature = (TextView) WeatherActivity.this.findViewById(R.id.id_store_temperature);
            temperature.setText(result+"Kº Es la temperatura actual");
        }





    }


}




