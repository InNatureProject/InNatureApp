package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.adapter.PreparosAdapter;

public class PreparosActivity extends AppCompatActivity {
    int id;
    PreparosAdapter preparosAdapter;//TODO: Fazer PreparosAdapter


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparos);

        //Pegamos o id recebido pelo intent e o armazenamos
        Intent i = getIntent();
        this.id = i.getIntExtra("id",0);
    }
}