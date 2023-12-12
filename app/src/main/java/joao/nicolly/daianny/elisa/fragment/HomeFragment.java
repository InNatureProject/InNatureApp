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
import android.widget.EditText;
import android.widget.ImageButton;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.activity.PlantaActivity;
import joao.nicolly.daianny.elisa.adapter.HomeAdapter;
import joao.nicolly.daianny.elisa.model.viewModel.PlantasViewModel;
import joao.nicolly.daianny.elisa.model.objetos.Planta;
import joao.nicolly.daianny.elisa.adapter.PlantaComparator;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    //VARIÁVEIS

//    PlantaBuscadaViewModew plantaBuscadaViewModel;
    private PlantasViewModel plantasViewModel;
    private HomeAdapter homeAdapter;
    private RecyclerView rvHomeFragment;
//    private PlantaBuscadaAdapter plantaBuscadaAdapter;
    private EditText etPesquisa;
    private ImageButton imbtnPesquisa;
    private String plantaBuscada;

    //CONSTRUTORES
    public HomeFragment() {
        // Required empty public constructor
    }

    //MÉTODOS'

    /**
     * Use this factory method to create a new instance
     * @return A new instance of fragment HomeFragment.
     */

    //new instance retorna a inicioalização de HomeFragment
    public static HomeFragment newInstance() {return new HomeFragment(); }

    //override significa que estamos  sobrescrevendo UM MÉTODO DO PAI, ou seja, estamos mudando sua funcionalidade, o que ele faz
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    //override significa que estamos  sobrescrevendo UM MÉTODO DO PAI, ou seja, estamos mudando sua funcionalidade, o que ele faz
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savadInstanceState){
        super.onViewCreated(view,savadInstanceState);

        //iniciando as variáveis mainViewModel e home Adapter
        rvHomeFragment = view.findViewById(R.id.rvHomeFragment);

        plantasViewModel = new ViewModelProvider(getActivity()).get(PlantasViewModel.class);
        homeAdapter = new HomeAdapter(new PlantaComparator(),this);//homeAdapter está pronto

        //liveData recebe
        LiveData<PagingData<Planta>> liveData = plantasViewModel.getPageLv();

        liveData.observe(getViewLifecycleOwner(), new Observer<PagingData<Planta>>() {
            @Override
            public void onChanged(PagingData<Planta> plantaPagingData) {
                homeAdapter.submitData(getViewLifecycleOwner().getLifecycle(), plantaPagingData);
            }
        });
        rvHomeFragment = (RecyclerView) view.findViewById(R.id.rvHomeFragment);
        rvHomeFragment.setAdapter(homeAdapter);
        rvHomeFragment.setLayoutManager(new LinearLayoutManager(getContext()));


        /**Fazendo Pesquisa*/
//        imbtnPesquisa = view.findViewById(R.id.imbtnPesquisa);
//        etPesquisa = view.findViewById(R.id.etPesquisa);

//        imbtnPesquisa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String textEtPesquisa = etPesquisa.getText().toString();
////                pesquisandoPlanta(textEtPesquisa);
//            }
//        });


    }
    public void navPlanta(int id){
        Intent i = new Intent(getContext(), PlantaActivity.class); // cria uma variavel intent que fará a comunicação entre ambas as telas// cria uma variavel intent que fará a comunicação entre ambas as telas
        i.putExtra("id",id);
        startActivity(i);
    }

//    private void pesquisandoPlanta(String strPesquisa){
//        plantaBuscada = strPesquisa.replaceAll(" ","&");
//        // obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
//        plantaBuscadaViewModel= new ViewModelProvider(getActivity()).get(PlantaBuscadaViewModew.class);
//        plantaBuscadaViewModel.setPlantaBuscada(plantaBuscada);
//        plantaBuscadaAdapter = new PlantaBuscadaAdapter(new PlantaComparator(),this);
//
//        LiveData<PagingData<Planta>> liveData = plantaBuscadaViewModel.getPageLv();
//        liveData.observe(this, new Observer<PagingData<Planta>>() {
//            @Override
//            public void onChanged(PagingData<Planta> plantaPagingData) {
//                plantaBuscadaAdapter.submitData(getViewLifecycleOwner().getLifecycle(), plantaPagingData);
//            }
//        });
//        rvHomeFragment.setAdapter(plantaBuscadaAdapter);
//    }

    //TODO: a pesquisa ainda não foi feita
    /*TODO: Para fazer a parte da pesquisa é necessário colocar um listerning na variável do botão de pesquisa
    *   quando o botão for apertado disparará uma requisição para o banco de dados procurando plantas que contenham a
    *   string inserida */
}