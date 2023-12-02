package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import joao.nicolly.daianny.elisa.R;

public class ReceitaPreparoActivity extends AppCompatActivity {
    int idPlanta;
    int idTipoPreparo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_preparo);

        //Recebendo intent e armazenando idPlanta e idTipoPreparo
        //Como estaremos reulizando a api command/plantapreparo será necessário ambos para pegar as informações da receita
        Intent i = getIntent();
        this.idPlanta = i.getIntExtra("idPlanta",0);
        this.idTipoPreparo = i.getIntExtra("idTipoPreparo",0);




    }
}