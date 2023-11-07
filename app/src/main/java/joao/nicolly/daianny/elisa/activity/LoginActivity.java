package joao.nicolly.daianny.elisa.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import joao.nicolly.daianny.elisa.model.LoginViewModel;

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
                            Config.setLogin(LoginActivity.this,email);
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
     * Método chamado após depois que o usuário já escolheu as permissãoes. O método indica o resultado
     */

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        final List<String> permissionsRejected= new ArrayList<>();
        if(RESULT_REQUEST_PERMISSION == requestCode){
            for(String permission: permissions){
                if(!hasPermission(permission)){
                    permissionsRejected.add(permission);
                }
            }
        }

        if (permissionsRejected.size()>0){
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                if(shouldShowRequestPermissionRationale(permissionsRejected.get(0))){
                    new AlertDialog.Builder(LoginActivity.this).setMessage("Para Usar esse app é necessário conceder pemições").setPositiveButton("OK",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), RESULT_REQUEST_PERMISSION);
                        }
                    }).create().show();

                }
            }
        }
    }



}