package co.gabo.mobile.mystores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void pushButton(View view) {

        // Toast.makeText(this, "Que mas pues?",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, SignUpActivity.class);
        this.startActivity(intent);


    }
}
