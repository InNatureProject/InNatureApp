package joao.nicolly.daianny.elisa.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import joao.nicolly.daianny.elisa.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SemLoginCadastroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SemLoginCadastroFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters

    public SemLoginCadastroFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static SemLoginCadastroFragment newInstance() {
        SemLoginCadastroFragment fragment = new SemLoginCadastroFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sem_login_cadastro, container, false);
    }
}