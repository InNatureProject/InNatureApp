package joao.nicolly.daianny.elisa.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.util.Config;
import joao.nicolly.daianny.elisa.model.viewModel.LoginViewModel;


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
                final String email =etEmail.getText().toString();

                EditText etSenha= findViewById(R.id.etSenha);
                final String senha=etSenha.getText().toString();

                final String etEmailLogin = etEmail.getText().toString();
                final String etSenhaLogin= etSenha.getText().toString();
                //TODO: Sanitizar aqui

                if(etEmailLogin.isEmpty()){
                    Toast.makeText(LoginActivity.this,"O campo de email  não foi preenchido!",Toast.LENGTH_LONG).show();
                    return ;
                }
                if(etSenhaLogin.isEmpty()){
                    Toast.makeText(LoginActivity.this,"O campo de senha não foi preenchido!",Toast.LENGTH_LONG).show();
                    return;
                }



                //Aqui checamos se há espaços em senha e email e caso haja pedimos que o usuário os retire
                //Este paço é importante pois caso haja espaço a API do joão rejeitará o cadastro/login do usuário
                //A única campo, até então, que permite espaço é o nome
                if(verifEspaco(etEmailLogin)){
                    Toast.makeText(LoginActivity.this,"Não devem haver espaços no email!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(verifEspaco(etSenhaLogin)){
                    Toast.makeText(LoginActivity.this,"Não devem haver espaços na senha!",Toast.LENGTH_LONG).show();
                    return;
                }
                //Informamos ao usuário o tamanho mínimo da senha
                //Caso a senha tenha menos de 6 dígitos o cadastro será impedido de ocorrer
                if(etSenhaLogin.length() <6){
                    Toast.makeText(LoginActivity.this,"A senha precisa ter no mínimo 6 dígitos!",Toast.LENGTH_LONG).show();
                    return;
                }
                /*Validando email
                 *      Aqui verificamos se a variável contendo o email  possui string com extensão de emails comuns*/
                if(    !( etEmailLogin.contains("@gmail.com") ||
                        etEmailLogin.contains("@outlook.com") ||
                        etEmailLogin.contains("@hotmail.com"))){
                    Toast.makeText(LoginActivity.this,"Favor inserir um email válido!",Toast.LENGTH_LONG).show();
                    return;
                }
                //Sanitizando campos
                //aqui checamos se não há caracteres maliciosos na string, como por exemplo os utilizados em sql inject
                if(sanStr(etEmailLogin)){
                    Toast.makeText(LoginActivity.this,"Favor utilizar apenas letras e números no nome!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(sanStr(etSenhaLogin)){
                    Toast.makeText(LoginActivity.this,"Favor utilizar apenas letras e números na senha!",Toast.LENGTH_LONG).show();
                    return;
                }




                //método  login está no viewmodel, envia informações de senha e email para o servidor.
                // O servidor tem que verificar as informações
                LiveData<Boolean> resultLD= loginViewlModel.login(senha,email);

                // LiveData trás a resposta do servidor
                resultLD.observe(LoginActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        // aBoolean contém o resultado do loguin. Se a resposta for true, as informações foram  enviadas
                        // e estão corretas. Guardando a senha e o email
                        if(aBoolean){
                            // guarda os dados de login e senha no app
                            Config.setEmail(LoginActivity.this,email);
                            Config.setPassword(LoginActivity.this,senha);

                            Toast.makeText(LoginActivity.this,"Login realizado com sucesso",Toast.LENGTH_LONG).show();

                            Intent i =new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(i);
                        }
                        else{

                            android.widget.Toast.makeText(LoginActivity.this, "Não foi possível realizar login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
        // caso o usuário não tenha login ele não poderá favoritar ou comentar

    }
    // Verificação das permições necessárias
    private void checkForPermissions(List<String>permissions){
        List<String> permissionsNotGrated= new ArrayList<>();

        for(String permission: permissions) {
            if (!hasPermission(permission)) {
                permissionsNotGrated.add(permission);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (permissionsNotGrated.size()>0) {
                requestPermissions(permissionsNotGrated.toArray(new String[permissionsNotGrated.size()]), RESULT_REQUEST_PERMISSION);
            }
        }
    }
   // Verifica se a permissão foi concedida
    private boolean hasPermission(String permission) {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            return ActivityCompat.checkSelfPermission(LoginActivity.this,permission)== PackageManager.PERMISSION_GRANTED;


        }
        return false;
    }

    /**
     * Método chamado depois que o usuário já escolheu as permissãoes. O método indica o resultado
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] gratResults){
    super.onRequestPermissionsResult(requestCode,permissions,gratResults);
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

    private Boolean sanStr(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '`' || str.charAt(i) == '´' || str.charAt(i) == '|' || str.charAt(i) == '/') {
                return true;
            }
        }
        return false;
    }
}