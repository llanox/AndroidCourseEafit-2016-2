package co.gabo.mobile.mystores.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import co.gabo.mobile.mystores.R;
import co.gabo.mobile.mystores.util.Authentication;
import co.gabo.mobile.mystores.util.AuthenticationImpl;
import co.gabo.mobile.mystores.util.UIUtil;

public class MainActivity extends AppCompatActivity {

    private Authentication authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authentication = new AuthenticationImpl(this);

        if(authentication.hasAnActiveUser()){
            Intent intent = new Intent(this, StoreListActivity.class);
            this.startActivity(intent);
            this.finish();
        }


    }


    @Override
    public void onBackPressed() {



    }

    public void logIn(View view) {

        EditText email = (EditText)this.findViewById(R.id.et_username);
        EditText password = (EditText)this.findViewById(R.id.et_password);

        if(UIUtil.isEmpty(email) || UIUtil.isEmpty(password)){
            Toast.makeText(this,R.string.err_msg_empty_fields,Toast.LENGTH_LONG).show();
            Snackbar.make(view,R.string.err_msg_empty_fields,Snackbar.LENGTH_LONG).show();
            return;
        }

        String sEmail = email.getText().toString();
        String sPassword = password.getText().toString();


        if(authentication.authorize(sEmail, sPassword)){

            Intent intent = new Intent(this, StoreListActivity.class);
            this.startActivity(intent);
            this.finish();

        }



    }
}
