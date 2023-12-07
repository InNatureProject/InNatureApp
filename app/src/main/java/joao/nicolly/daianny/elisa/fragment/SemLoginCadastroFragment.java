package joao.nicolly.daianny.elisa.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.activity.CadastroActivity;
import joao.nicolly.daianny.elisa.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SemLoginCadastroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SemLoginCadastroFragment extends Fragment {

    Button btnTelaEntrar;
    Button btnTelaCadastro;


    public SemLoginCadastroFragment() {
        // Required empty public constructor
    }
    public static SemLoginCadastroFragment newInstance() {
        SemLoginCadastroFragment fragment = new SemLoginCadastroFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sem_login_cadastro, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savadInstanceState) {
        super.onViewCreated(view, savadInstanceState);

        //pega os elementos na tela
        btnTelaEntrar = view.findViewById(R.id.btnTelaEntrar);
        btnTelaCadastro = view.findViewById(R.id.btnTelaCadastro);

        //Faz o intent para LoginActivity, ou seja , nos direciona para a tela de login
        btnTelaEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        //Faz o intent para CadastroActivity, ou seja , nos direciona para a tela de cadastro
        btnTelaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CadastroActivity.class);
                startActivity(i);
            }
        });

    }
}