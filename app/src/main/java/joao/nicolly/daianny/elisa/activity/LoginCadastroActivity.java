package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import joao.nicolly.daianny.elisa.R;

public class LoginCadastroActivity extends AppCompatActivity {

    @Override
protected void onCreate(Bundle savedInstanceState) { //criando a classe que herda os valores da classe AppCompatActivity
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login_cadastro);
    Button btnEntrarAct = findViewById(R.id.btnEntrarAct); //variável do tipo botão que está na interface
    btnEntrarAct.setOnClickListener(new View.OnClickListener() { //ouvidor de clicks
        @Override
        public void onClick(View view) { //metodo acionado assim que o ouvidor for ativad
            Intent i = new Intent(LoginCadastroActivity.this,LoginActivity.class); //variável criada para passar as telas de mainActivity para o ProximoActivity
            startActivity(i); //inicia a ação
            return false;
        }
    });
}
}
