package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.model.CadastroViewModel;

//TODO colocar o listenner para btnCadastrar;
//TODO pegar informações dos editText;
//TODO  corfimação de campos preenchidos;
//TODO sanitizar para impedir sql injection ou outros ataques;
//TODO efetuar o cadastro;

public  class CadastroActivity extends AppCompatActivity {

    //Variáveis
    CadastroViewModel cadastroViewModel;
    Button btnCadastrar;
    EditText etNomeUsuario;
    EditText etEmailUsuario;
    EditText etSenha;
    EditText etConfSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //inicialização das variáveis

        cadastroViewModel = new ViewModelProvider(this).get(CadastroViewModel.class);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        //Criando ClickListener
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //inicializando as variaveis
                etNomeUsuario = findViewById(R.id.etNomeUsuario);
                etEmailUsuario = findViewById(R.id.etEmailUsuario);
                etSenha = findViewById(R.id.etSenha);
                etConfSenha = findViewById(R.id.etConfSenha);
            }
        });

    }
}