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
import joao.nicolly.daianny.elisa.activity.MainActivity;
import joao.nicolly.daianny.elisa.activity.PlantaActivity;
import joao.nicolly.daianny.elisa.adapter.FavoritosAdapter;
import joao.nicolly.daianny.elisa.model.Planta;
import joao.nicolly.daianny.elisa.model.PlantaComparator;
import joao.nicolly.daianny.elisa.model.MainViewModel;

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
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        favoritosAdapter = new FavoritosAdapter(new PlantaComparator(), this);
        LiveData<PagingData<Planta>> liveData = mainViewModel.getPageLv();

        liveData.observe(getViewLifecycleOwner(), new Observer<PagingData<Planta>>() {
            @Override
            public void onChanged(PagingData<Planta> plantaPagingData) {
                favoritosAdapter.submitData(getViewLifecycleOwner().getLifecycle(), plantaPagingData);
            }
        });
        RecyclerView rvFavoritosFragment = (RecyclerView) view.findViewById(R.id.rvFavoritosFragment);
        rvFavoritosFragment.setAdapter(favoritosAdapter);
        rvFavoritosFragment.setLayoutManager(new LinearLayoutManager(getContext()));

    }
    //Metodo de Navegação  que será utilizado por FavoritosAdapter para navegar para PlantaActivity
    public void navPlanta(int id){
        /*Aqui cria-se um intente para a tela PlantaActivity, uma vez que não é possível fazer isto do Adapter (Apenas de Activity e Fragmento)*/
        Intent i = new Intent(getContext(), PlantaActivity.class); // cria uma variavel intent que fará a comunicação entre ambas as telas
        i.putExtra("id",id);//Adiciona o id, que será necessário para carregar as informações da planta
        startActivity(i);//efetua a navegaçãossssssssssssssssssssssssssss
    }
}