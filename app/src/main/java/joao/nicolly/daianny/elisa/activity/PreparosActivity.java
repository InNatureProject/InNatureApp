package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.adapter.PreparosAdapter;
import joao.nicolly.daianny.elisa.adapter.TipoPreparosComparator;

public class PreparosActivity extends AppCompatActivity {
    int id;
    PreparosAdapter preparosAdapter;//TODO: Fazer PreparosAdapter
    RecyclerView rvPreparos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparos);

        //Pegamos o id recebido pelo intent e o armazenamos
        Intent i = getIntent();
        this.id = i.getIntExtra("id",0);

        // Aqui configuramos o RecycleView
        rvPreparos = findViewById(R.id.rvPreparos);
        rvPreparos.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvPreparos.setLayoutManager(layoutManager);

        preparosAdapter = new PreparosAdapter(PreparosActivity.this, new TipoPreparosComparator());
        rvPreparos.setAdapter(preparosAdapter);
    }
}