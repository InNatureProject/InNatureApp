package joao.nicolly.daianny.elisa.fragment;

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

    /**
     * Use this factory method to create a new instance
     * @return A new instance of fragment FavoritosFragment.
     */

    //MÉTODOS

    //new instance retorna uma variável do tipo FavoritosFragent
    public static FavoritosFragment newInstance() {return new FavoritosFragment(); }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false);
    }

    //Após a tela ser criada deverá ocorrer
    public void OnViewCreated(@NonNull View view, @Nullable Bundle savadInstanceState){
        super.onViewCreated(view,savadInstanceState);
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        favoritosAdapter = new FavoritosAdapter(new PlantaComparator());
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
}