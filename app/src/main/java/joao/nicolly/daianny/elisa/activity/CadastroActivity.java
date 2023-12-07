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
import joao.nicolly.daianny.elisa.model.viewModel.CadastroViewModel;
import joao.nicolly.daianny.elisa.util.Config;



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


                /**A sanitização começa a partir daqui
                 * Esta etapa é importante para garantir que não enviemos informações irrelevantes ou equivocadas para o banco de dados
                 * validamos se as variaveis com as informações que pegamos do formulário não estão vazias, com dados que impeção o cadastro
                 * ou se não são nocivas.*/

                //Verificando se os campos foram preenchidos
                if(etNomeUsuarioText.isEmpty()){
                    Toast.makeText(CadastroActivity.this,"O campo de nome não foi preenchido!",Toast.LENGTH_LONG).show();
                    return ;
                }
                if(etConfSenhaText.isEmpty()){
                    Toast.makeText(CadastroActivity.this,"O campo de email não foi preenchido!",Toast.LENGTH_LONG).show();
                    return ;
                }
                if(etSenhaText.isEmpty()){
                    Toast.makeText(CadastroActivity.this,"O campo de senha não foi preenchido!",Toast.LENGTH_LONG).show();
                    return ;
                }
                if(etConfSenhaText.isEmpty()){
                    Toast.makeText(CadastroActivity.this,"O campo confirmar senha não foi preenchido!",Toast.LENGTH_LONG).show();
                    return ;
                }

                //Checando se as senhas não são iguais
                if(!etSenhaText.equals(etConfSenhaText)){
                    Toast.makeText(CadastroActivity.this,"A senha confirmada é diferente da desejada!",Toast.LENGTH_LONG).show();
                    return ;
                }

                //Aqui checamos se há espaços em senha e email e caso haja pedimos que o usuário os retire
                //Este paço é importante pois caso haja espaço a API do joão rejeitará o cadastro/login do usuário
                //A única campo, até então, que permite espaço é o nome
                if(verifEspaco(etEmailUsuarioText)){
                    Toast.makeText(CadastroActivity.this,"Não devem haver espaços no email!",Toast.LENGTH_LONG).show();
                    return ;
                }
                if(verifEspaco(etSenhaText)){
                    Toast.makeText(CadastroActivity.this,"Não devem haver espaços na senha!",Toast.LENGTH_LONG).show();
                    return ;
                }
                //Informamos ao usuário o tamanho mínimo da senha
                //Caso a senha tenha menos de 6 dígitos o cadastro será impedido de ocorrer
                if(etSenhaText.length() <6){
                    Toast.makeText(CadastroActivity.this,"A senha precisa ter no mínimo 6 dígitos!",Toast.LENGTH_LONG).show();
                    return ;
                }
                /*Validando email
                 *      Aqui verificamos se a variável contendo o email  possui string com extensão de emails comuns*/
                if(    !( etEmailUsuarioText.contains("@gmail.com") ||
                        etEmailUsuarioText.contains("@outlook.com") ||
                        etEmailUsuarioText.contains("@hotmail.com"))){
                    Toast.makeText(CadastroActivity.this,"Favor inserir um email válido!",Toast.LENGTH_LONG).show();
                    return ;
                }
                //Sanitizando campos
                //aqui checamos se não há caracteres maliciosos na string, como por exemplo os utilizados em sql inject
                if(sanStr(etNomeUsuarioText)){
                    Toast.makeText(CadastroActivity.this,"Favor utilizar apenas letras e números no nome!",Toast.LENGTH_LONG).show();
                    return ;
                }
                if(sanStr(etSenhaText)){
                    Toast.makeText(CadastroActivity.this,"Favor utilizar apenas letras e números na senha!",Toast.LENGTH_LONG).show();
                    return ;
                }

                /** A parte de sanitização acabou aqui.
                 * Para conseguir impedir os espaços e os caracteres maliciosos utilizei dois metodos  que estão no final
                 * da página o sanStr() e o verifEspaco(). é só passar para ele a variável contendo a string a ser analizada
                 * e eles vão devolver um Boolean. caso a string tenha caracteres maliciosos ou espaços eles devolvem true.
                 */

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

                            finish();

                            Toast.makeText(CadastroActivity.this,"Novo usuário cadastrado com sucesso!",Toast.LENGTH_LONG).show();

                            Intent i = new Intent(CadastroActivity.this, MainActivity.class);
                            startActivity(i);
                            //Professor falou pra fazer a navegação por último mas são literalmente duas linhas, não resisti


                            //Na activity Diz que quando finalizamos (finish()) voltamos para a tela de login, ou seja, para a tela que estávamos. Porque?
                            // Não encontrei metodos referentes a isto
                            //Observei também que após o cadastro o cliente é obrigado a fazer login, mas gostaria que ao fazer o cadastro o login também acontecesse.
                            // desta maneira facilitaria a compreensão e agilizaria as coisas
                        }
                        else{
                            Toast.makeText(CadastroActivity.this,"Erro ao cadastrar novo usuário!",Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });



            }
        });

    }
    //método que verifica se há espaços na string
    private Boolean verifEspaco(String str){
        for(int i=0; i<str.length(); i++ ){
            if( str.charAt(i) == ' ' ) {
                return true;
            }
        }
        return false;
    }

    private Boolean sanStr(String str){
        for(int i=0; i<str.length(); i++ ){
            if( str.charAt(i) == '`' || str.charAt(i) == '´' || str.charAt(i) == '|' || str.charAt(i) == '/'  ) {
                return true;
            }
        }
        return false;
    }
}