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

public class LogInActivity extends AppCompatActivity {

    private Authentication authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //En favor de la mantenibilidad de una aplicación
        // ud deberia programar a interfaces. En este caso podriamos
        // crear una implementación para cada tiempo de autenticación
        // pero entonces recuerde que a mayor número de clases en la app
        // se va tener la posibilidad de alcanzar el limite de numero de metodos
        // permitido para apps, que en este momento es de 65K

        // También se puede usar tecnicas o procesos cuando se este compilando
        // para optimizar el codigo. Como Obfuscation and Shrink usando ProGuard, estos nos
        // permite eliminar el codigo que no necesitamos y además protegerlo contra
        // gente que quiere decompilar nuestras aplicaciones.



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
