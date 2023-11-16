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
import joao.nicolly.daianny.elisa.adapter.UserAdapter;
import joao.nicolly.daianny.elisa.model.MainViewModel;
import joao.nicolly.daianny.elisa.model.Planta;
import joao.nicolly.daianny.elisa.model.PlantaComparator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    private MainViewModel mainViewModel;
    private UserAdapter userAdapter;

    //CONSTRUTOR
    public UserFragment() {
        // Required empty public constructor oioi
    }

    //MÉTODOS

    /**
     * Use this factory method to create a new instance
     *
     * @return A new instance of fragment UserFragment.
     */
    //new instance retorna a inicialização  do FavoritosFragent
    public static UserFragment newInstance() {
        return new UserFragment();
    }


    @Override
    //override significa que estamos  sobrescrevendo UM MÉTODO DO PAI, ou seja, estamos mudando sua funcionalidade, o que ele faz
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

}