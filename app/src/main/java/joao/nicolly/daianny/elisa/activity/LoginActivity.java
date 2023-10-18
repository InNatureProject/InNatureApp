package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.auxiliares.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    static int RESULT_REQUEST_PERMISSION = 2;
    LoginViewModel loginViewlModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewlModel= new ViewModelProvider(this).get(LoginViewModel.class);

        Button btnAcessar=findViewById(R.id.btnAcessar);
        btnAcessar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //pega os elementos digitados pelo usuário
                EditText etEmail = findViewById(R.id.etEmail);
                EditText etSenha= findViewById(R.id.etSenha)


            }
        }
    }
}