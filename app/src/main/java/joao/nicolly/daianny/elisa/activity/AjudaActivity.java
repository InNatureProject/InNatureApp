package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import joao.nicolly.daianny.elisa.R;

public class AjudaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatarproblema);

        //VARIAVEIS


        EditText etDescreva;
        Button btnSend;
        String texto = null;



        //INICIALIZANDO VARIÁVEIS

        etDescreva = findViewById(R.id.etDescreva);
        btnSend = findViewById(R.id.btnSend);
        String email="in.nature.project@gmail.com";
        String assunto= "Relatar problema";

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Pega o campo com descrição do problema
                String texto = etDescreva.getText().toString();

                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:" + email));


                i.putExtra(Intent.EXTRA_EMAIL, email);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(AjudaActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_LONG).show();
                }
            }


        });


    }

}
