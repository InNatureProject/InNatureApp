package joao.nicolly.daianny.elisa.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.activity.PlantaActivity;
import joao.nicolly.daianny.elisa.adapter.FavoritosAdapter;
import joao.nicolly.daianny.elisa.model.objetos.Planta;
import joao.nicolly.daianny.elisa.adapter.PlantaComparator;
import joao.nicolly.daianny.elisa.model.viewModel.MainViewModel;

//TODO: aqui tem que haver a requisição das receitas que o usuário favoritou
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritosFragment extends Fragment {

    //Variáveis

    private MainViewModel mainViewModel;
    private FavoritosAdapter favoritosAdapter;


    //CONTRUTOR
    public FavoritosFragment() {
        // Required empty public constructor
    }

    //MÉTODOS
    /**
     * Use this factory method to create a new instance
     * @return A new instance of fragment FavoritosFragment.
     */
    //new instance retorna a inicialização  do FavoritosFragent
    public static FavoritosFragment newInstance() {return new FavoritosFragment(); }


    @Override     //override significa que estamos  sobrescrevendo UM MÉTODO DO PAI, ou seja, estamos mudando sua funcionalidade, o que ele faz

    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false);
    }

    //Após a tela ser criada deverá ocorrer


    @Override    //override significa que estamos  sobrescrevendo UM MÉTODO DO PAI, ou seja, estamos mudando sua funcionalidade, o que ele faz
    public void onViewCreated(@NonNull View view, @Nullable Bundle savadInstanceState){
        super.onViewCreated(view,savadInstanceState);

        /**Aqui inicializamos o mainViewModel e o favoritosAdapter*/
        // obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        favoritosAdapter = new FavoritosAdapter(new PlantaComparator(), this);
        // O ViewModel possui o método getPageLv, que obtém páginas/blocos de produtos do
        // servidor
        // web. Cada página contém 20 plantas. Quando o usuário rola a tela, novas páginas de produtos
        // são obtidas do servidor.
        //
        // O método de getPageLv retorna um LiveData, que na prática é um container que avisa
        // quando o resultado do servidor chegou. Ele guarda a página de produtos que o servidor
        // entregou para a app.
        LiveData<PagingData<Planta>> liveData = mainViewModel.getPageLv();

        // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado contendo uma página
        // com 10 produtos será guardado dentro do LiveData. Neste momento o
        // LiveData avisa que uma nova página de produtos chegou chamando o método onChanged abaixo.
        liveData.observe(getViewLifecycleOwner(), new Observer<PagingData<Planta>>() {
            /**
             * Esse método é chamado sempre que uma nova página de produtos é entregue à app pelo
             * servidor web.
             * @param plantaPagingData contém uma página de produtos
             */
            @Override
            public void onChanged(PagingData<Planta> plantaPagingData) {
                // Adiciona a nova página de produtos ao Adapter do RecycleView. Isso faz com que
                // novos produtos apareçam no RecycleView.
                favoritosAdapter.submitData(getViewLifecycleOwner().getLifecycle(), plantaPagingData);
            }
        });
        RecyclerView rvFavoritosFragment = (RecyclerView) view.findViewById(R.id.rvFavoritosFragment);
        rvFavoritosFragment.setAdapter(favoritosAdapter);
        rvFavoritosFragment.setLayoutManager(new LinearLayoutManager(getContext()));

    }
    public void navPlanta(int id){
        Intent i = new Intent(getContext(), PlantaActivity.class); // cria uma variavel intent que fará a comunicação entre ambas as telas// cria uma variavel intent que fará a comunicação entre ambas as telas
        i.putExtra("id",id);
        startActivity(i);
    }
}