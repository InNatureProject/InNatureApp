package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.adapter.ComentarioComparator;
import joao.nicolly.daianny.elisa.adapter.ComentariosAdapter;
import joao.nicolly.daianny.elisa.model.viewModel.ComentandoViewModel;

public class ComentandoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentando);

        // Aqui configuramos o RecycleView
        RecyclerView rvComentarios = findViewById(R.id.rvComentarios);
        rvComentarios.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvComentarios.setLayoutManager(layoutManager);

        ComentariosAdapter comentariosAdapter= new ComentariosAdapter(this, new ComentarioComparator());
        rvComentarios.setAdapter(comentariosAdapter);

         obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
        ComentandoViewModel comentandoViewModel = new ViewModelProvider(this).get(ComentandoViewModel.class);

    }
}