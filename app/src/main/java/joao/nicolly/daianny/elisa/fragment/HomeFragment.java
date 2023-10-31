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
import joao.nicolly.daianny.elisa.adapter.HomeAdapter;
import joao.nicolly.daianny.elisa.model.MainViewModel;
import joao.nicolly.daianny.elisa.model.Planta;
import joao.nicolly.daianny.elisa.model.PlantaComparator;

//TODO: aqui te, que requisitar todas as plantas cadastradas e ordenálas  ou por ordem alfabética ou pelas mais populares

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    //VARIÁVEIS

    private MainViewModel mainViewModel;
    private HomeAdapter homeAdapter;

    //CONSTRUTORES
    public HomeFragment() {
        // Required empty public constructor
    }

    //MÉTODOS'

    /**
     * Use this factory method to create a new instance
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Delete os parametors
    //new instance retorna uma variável do tipo HomeFragment
    public static HomeFragment newInstance() {return new HomeFragment(); }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void OnViewCreated(@NonNull View view, @Nullable Bundle savadInstanceState){
        super.onViewCreated(view,savadInstanceState);
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        homeAdapter = new HomeAdapter(new PlantaComparator());
        LiveData<PagingData<Planta>> liveData = mainViewModel.getPageLv();

        liveData.observe(getViewLifecycleOwner(), new Observer<PagingData<Planta>>() {
            @Override
            public void onChanged(PagingData<Planta> plantaPagingData) {
                homeAdapter.submitData(getViewLifecycleOwner().getLifecycle(), plantaPagingData);
            }
        });
        RecyclerView rvHomeFragment = (RecyclerView) view.findViewById(R.id.rvHomeFragment);
        rvHomeFragment.setAdapter(homeAdapter);
        rvHomeFragment.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    //TODO: a pesquisa ainda não foi feita
    /*TODO: Para fazer a parte da pesquisa é necessário colocar um listerning na variável do botão de pesquisa
    *   quando o botão for apertado disparará uma requisição para o banco de dados procurando plantas que contenham a
    *   string inserida */
}