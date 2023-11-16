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
import android.widget.TextView;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.activity.EditUserActivity;
import joao.nicolly.daianny.elisa.activity.AjudaActivity;
import joao.nicolly.daianny.elisa.activity.SobreNosActivity;
import joao.nicolly.daianny.elisa.model.MainViewModel;
import joao.nicolly.daianny.elisa.util.Config;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {
    //VARIÁVEIS
    TextView tvName;
    TextView tvEmail;
    Button btnEditarPerfil;
    Button btnSobreNos;
    Button btnAjuda;
    Button btnDeslogar;


    private MainViewModel mainViewModel;

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

    public void onViewCreated(@NonNull View view, @Nullable Bundle savadInstanceState) {
        super.onViewCreated(view, savadInstanceState);

        // INICIALIZANDO VARIÁVEIS
        // Aqui pegamos cada elemento da tela e jogamos dentro de sua variável

        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        btnEditarPerfil = view.findViewById(R.id.btnEditarPerfil);
        btnSobreNos = view.findViewById(R.id.btnSobreNos);
        btnAjuda = view.findViewById(R.id.btnAjuda);
        btnDeslogar  = view.findViewById(R.id.btnDeslogar);

        //Para que o usuário seja capaz de visualizar seu nome e email:
        tvName.setText(Config.getName(view.getContext()));//TODO: perguntar o prof se assim está certo
        tvEmail.setText(Config.getEmail(view.getContext()));//TODO: perguntar ao prof se assim está certo

        /**Cada botão leva para uma tela diferente, então colocaremos onclick listenner em todos e criaremos um intent levando para a tela desejada*/

        //Para editar o perfil
        btnEditarPerfil.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(view.getContext(), EditUserActivity.class); // cria uma variavel intent que fará a comunicação entre ambas as telas
                startActivity(i); // inicializa o intente, ou seja, vai para a tela desejada.
            }
        });

        //Para saber mais sobre nós
        btnSobreNos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), SobreNosActivity.class); // cria uma variavel intent que fará a comunicação entre ambas as telas
                startActivity(i); // inicializa o intente, ou seja, vai para a tela desejada.
            }
        });


        //Intente para PrecisaAjudaActivity
        btnAjuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), AjudaActivity.class); // cria uma variavel intent que fará a comunicação entre ambas as telas
                startActivity(i); // inicializa o intente, ou seja, vai para a tela desejada.
            }
        });

        btnAlgoErrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), AjudaActivity.class); // cria uma variavel intent que fará a comunicação entre ambas as telas
                startActivity(i); // inicializa o intente, ou seja, vai para a tela desejada.
            }
        });






    }

}