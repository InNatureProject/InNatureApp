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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.adapter.ComentarioComparator;
import joao.nicolly.daianny.elisa.adapter.ComentariosAdapter;
import joao.nicolly.daianny.elisa.model.objetos.Comentario;
import joao.nicolly.daianny.elisa.model.viewModel.AddComentViewModel;
import joao.nicolly.daianny.elisa.model.viewModel.ComentandoViewModel;
import joao.nicolly.daianny.elisa.util.Config;

public class ComentandoActivity extends AppCompatActivity {
    ComentariosAdapter comentariosAdapter;
    String contentComent;
    EditText etComentando;
    ImageButton btnAddComent;
    int id;
//
//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentando);






        //Para que o usuário cadastrado possa comentar'
        etComentando = findViewById(R.id.etComentando);
        btnAddComent = findViewById(R.id.btnAddComent);
        btnAddComent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentComent = etComentando.getText().toString();
                Toast.makeText(ComentandoActivity.this,"teste:" + contentComent,Toast.LENGTH_LONG).show();
                if(!taCadastrado()){
                    Intent i = new Intent(ComentandoActivity.this,LoginCadastroActivity.class);
                    startActivity(i);
                } else{
                    //para impedir multiplas requisições e bug no código impedimos o usuário de clicar mais de uma vez
                    //antes da requisição ao banco de dados ser concluida;
                    btnAddComent.setClickable(false);
                    adicionandoComentario();
                    btnAddComent.setClickable(true);
                }

            }
        });
//
        Intent i = getIntent();
        this.id = i.getIntExtra("id",0);

        // Aqui configuramos o RecycleView
        RecyclerView rvComentarios = findViewById(R.id.rvComentarios);
        rvComentarios.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvComentarios.setLayoutManager(layoutManager);

        comentariosAdapter = new ComentariosAdapter(this, new ComentarioComparator());
        rvComentarios.setAdapter(comentariosAdapter);

        //obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
        ComentandoViewModel comentandoViewModel = new ViewModelProvider(this).get(ComentandoViewModel.class);
        comentandoViewModel.setId(id);
        // O ViewModel possui o método getComentarios(), que obtém páginas/blocos de produtos do servidor
        // web. Cada página contém 10 produtos. Quando o usuário rola a tela, novas páginas de produtos
        // são obtidas do servidor.
        //
        // O método de getComentarios() retorna um LiveData, que na prática é um container que avisa
        // quando o resultado do servidor chegou. Ele guarda a página de comentarios que o servidor
        // entregou para a app.

        LiveData<PagingData<Comentario>> comentarios = comentandoViewModel.getComentarios();
        // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado contendo uma página
        // com 10 produtos será guardado dentro do LiveData. Neste momento o
        // LiveData avisa que uma nova página de produtos chegou chamando o método onChanged abaixo.

        comentarios.observe(this, new Observer<PagingData<Comentario>>() {
            /**
             * Esse método é chamado sempre que uma nova página de comentarios é entregue à app pelo
             * servidor web.
             * @param comentarioPagingData contém uma página de comentarios
             */
            @Override
            public void onChanged(PagingData<Comentario> comentarioPagingData) {
                // Adiciona a nova página de comentarios ao Adapter do RecycleView. Isso faz com que
                // novos produtos apareçam no RecycleView.
                comentariosAdapter.submitData(getLifecycle(),comentarioPagingData);

            }
        });


    }
    private Boolean taCadastrado(){
        if(Config.getTolken(this).isEmpty()){
            return false;
        } else {
            return true;
        }
    }
    private void adicionandoComentario(){
        AddComentViewModel addComentViewModel = new ViewModelProvider(this).get(AddComentViewModel.class);
        LiveData<Boolean> add = addComentViewModel.addComent(id, contentComent);

        ComentandoViewModel comentandoViewModel = new ViewModelProvider(this).get(ComentandoViewModel.class);
        comentandoViewModel.setId(id);

        add.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    /**é no view model que haverá a chamada para função de requisição do Repository requisição*/
                    Toast.makeText(ComentandoActivity.this,"Novo Comentário enviado com sucesso!",Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(ComentandoActivity.this,"Não foi possível adicionar comentário!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}