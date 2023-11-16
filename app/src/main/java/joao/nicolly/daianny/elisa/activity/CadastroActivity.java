package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.model.CadastroViewModel;
import joao.nicolly.daianny.elisa.util.Config;

//TODO sanitizar para impedir sql injection ou outros ataques;

public  class CadastroActivity extends AppCompatActivity {

    //Variáveis teste
    CadastroViewModel cadastroViewModel;
    Button btnCadastrar;
    EditText etNomeUsuario;
    EditText etEmailUsuario;
    EditText etSenha;
    EditText etConfSenha;
    LiveData<Boolean> result;


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

                //Pegando texto dos campos
                final String etNomeUsuarioText = etNomeUsuario.getText().toString();
                final String etEmailUsuarioText= etEmailUsuario.getText().toString();
                final String etSenhaText       = etSenha.getText().toString();
                final String etConfSenhaText   = etConfSenha.getText().toString();

                //Verificando se os campos foram preenchidos
                if(etNomeUsuarioText.isEmpty()){
                    Toast.makeText(CadastroActivity.this,"O campo de nome não foi preenchido!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(etConfSenhaText.isEmpty()){
                    Toast.makeText(CadastroActivity.this,"O campo de email não foi preenchido!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(etSenhaText.isEmpty()){
                    Toast.makeText(CadastroActivity.this,"O campo de senha não foi preenchido!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(etConfSenhaText.isEmpty()){
                    Toast.makeText(CadastroActivity.this,"O campo confirmar senha não foi preenchido!",Toast.LENGTH_LONG).show();
                    return;
                }

                //Checando se as senhas não são iguais
                if(!etSenhaText.equals(etConfSenhaText)){
                    Toast.makeText(CadastroActivity.this,"A senha confirmada é diferente da desejada!",Toast.LENGTH_LONG).show();
                    return;
                }

                /* TODO fazer a sanitização aqui!
                *   Deve-se checar se não há caracteres especiais ou se há palavras como insert ou drop, etc
                *   é uma boa ideia perguntar ao João pq ele fez isso no web e pode nos falar o que é realmente nescessário
                *   Lembrar de perguntar sobre a necessidade de sanitizar o cadastro no mobile ao prof Daniel
                *   Estou avaliando a possibilidade de criar um metodo que procura na string por tais caracteres e se os encontra devolve uma confimação, se este já não existir
                *   Assim seria fácil sanitizar os dados tanto aqui quanto no Login*/

                /*TODO:para sanitizar
                *  */

                //Criando a conta
                result = cadastroViewModel.cadastrar(etNomeUsuarioText,etEmailUsuarioText,etSenhaText);
                result.observe(CadastroActivity.this, new Observer<Boolean>() {
                    //Quando a resposta do servidor chegar
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean){
                            //gardado dados do login em local seguro da aplicação
                            Config.setEmail(CadastroActivity.this,etEmailUsuarioText);
                            Config.setPassword(CadastroActivity.this,etConfSenhaText);
                            Config.setName(CadastroActivity.this,etNomeUsuarioText);

                            Toast.makeText(CadastroActivity.this,"Novo usuário cadastrado com sucesso!",Toast.LENGTH_LONG).show();

                            Intent i = new Intent(CadastroActivity.this, MainActivity.class);
                            startActivity(i);
                            //Professor falou pra fazer a navegação por último mas são literalmente duas linhas, não resisti

//                            finish();
                            //Na activity Diz que quando finalizamos (finish()) voltamos para a tela de login, ou seja, para a tela que estávamos. Porque?
                            // Não encontrei metodos referentes a isto
                            //Observei também que após o cadastro o cliente é obrigado a fazer login, mas gostaria que ao fazer o cadastro o login também acontecesse.
                            // desta maneira facilitaria a compreensão e agilizaria as coisas
                        }
                        else{
                            Toast.makeText(CadastroActivity.this,"Erro ao cadastrar novo usuário!",Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });

    }
}