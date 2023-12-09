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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.activity.EditUserActivity;
import joao.nicolly.daianny.elisa.activity.AjudaActivity;
import joao.nicolly.daianny.elisa.activity.MainActivity;
import joao.nicolly.daianny.elisa.model.viewModel.PlantasViewModel;
import joao.nicolly.daianny.elisa.util.Config;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {
    //VARIÁVEIS
    ImageView imgUser;
    TextView tvName;
    TextView tvEmail;
    Button btnEditarPerfil;
    Button btnAjuda;
    Button btnDeslogar;


    private PlantasViewModel plantasViewModel;

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

        imgUser =view.findViewById(R.id.imgUser);
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        btnEditarPerfil = view.findViewById(R.id.btnEditarPerfil);
        btnAjuda = view.findViewById(R.id.btnAjuda);
        btnDeslogar  = view.findViewById(R.id.btnDeslogar);

        //Para que o usuário seja capaz de ver sua imagem
        if(Config.getImagem(view.getContext()).isEmpty()){
            Picasso.with(view.getContext())
                    .load("https://raw.githubusercontent.com/InNatureProject/innatureimages/main/default_ImageUser.jpg")
                    .into(imgUser);
        }else{
            Picasso.with(view.getContext())
                    .load(Config.getImagem(view.getContext()))
                    .into(imgUser);
        }
        //Para que o usuário seja capaz de visualizar seu nome e email:
        tvName.setText(Config.getName(view.getContext()));
        tvEmail.setText(Config.getEmail(view.getContext()));

        /**Cada botão leva para uma tela diferente, então colocaremos onclick listenner em todos e criaremos um intent levando para a tela desejada*/

        //Para editar o perfil
        btnEditarPerfil.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(getContext(), EditUserActivity.class); // cria uma variavel intent que fará a comunicação entre ambas as telas
                startActivity(i); // inicializa o intente, ou seja, vai para a tela desejada.
            }
        });


        //Intente para AjudaActivity
        btnAjuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AjudaActivity.class); // cria uma variavel intent que fará a comunicação entre ambas as telas
                startActivity(i); // inicializa o intente, ou seja, vai para a tela desejada.
            }
        });

        btnDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Config.setName(getContext(),"");
                Config.setEmail(getContext(),"");
                Config.setPassword(getContext(),"");
                Config.setImagem(getContext(),"");

                Intent i = new Intent(getContext(), MainActivity.class); // cria uma variavel intent que fará a comunicação entre ambas as telas
                startActivity(i); // inicializa o intente, ou seja, vai para a tela desejada
            }
        });






    }

}