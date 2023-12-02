package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.adapter.PreparosAdapter;
import joao.nicolly.daianny.elisa.adapter.TipoPreparosComparator;
import joao.nicolly.daianny.elisa.model.PreparosViewModel;
import joao.nicolly.daianny.elisa.model.TipoPreparo;

public class PreparosActivity extends AppCompatActivity {
    int id;
    PreparosAdapter preparosAdapter;
    PreparosViewModel preparosViewModel;
    RecyclerView rvPreparos;
    //TODO: falta fazer o método no InNatureRepository responsável por carregar tipoPreparo, mas não estou encontrando o local em que é chamado


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

        preparosAdapter = new PreparosAdapter(this, new TipoPreparosComparator());
        rvPreparos.setAdapter(preparosAdapter);


        // obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
        preparosViewModel = new ViewModelProvider(this).get(PreparosViewModel.class);
        preparosViewModel.putId(id);

        // O ViewModel possui o método getTipoPreparos, que obtém páginas/blocos de produtos do servidor
        // web. Cada página contém 10 produtos. Quando o usuário rola a tela, novas páginas de produtos
        // são obtidas do servidor.
        //
        // O método de getTipoPreparos retorna um LiveData, que na prática é um container que avisa
        // quando o resultado do servidor chegou. Ele guarda a página de produtos que o servidor
        // entregou para a app.
        LiveData<PagingData<TipoPreparo>> tipoPreparos = preparosViewModel.getTipoPreparos();

        // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado contendo uma página
        // com 10 produtos será guardado dentro do LiveData. Neste momento o
        // LiveData avisa que uma nova página de produtos chegou chamando o método onChanged abaixo.
        tipoPreparos.observe(this, new Observer<PagingData<TipoPreparo>>() {
            /**
             * Esse método é chamado sempre que uma nova página de produtos é entregue à app pelo
             * servidor web.
             * @param tipoPreparoPagingData contém uma página de produtos
             */
            @Override
            public void onChanged(PagingData<TipoPreparo> tipoPreparoPagingData) {
                // Adiciona a nova página de produtos ao Adapter do RecycleView. Isso faz com que
                // novos produtos apareçam no RecycleView.
                preparosAdapter.submitData(getLifecycle(),tipoPreparoPagingData);
            }
        });
    }

    public void startReceitaPreparoActivity(int id){
        Intent i = new Intent(PreparosActivity.this,ReceitaPreparoActivity.class);
        i.putExtra("id",id);
        startActivity(i);
    }
}