package joao.nicolly.daianny.elisa.activity;

/**
 * App do In Nature. Pela app será possível
 * visualizar as informações,
 * criar uma conta, logar, deletar a conta e
 * favoritar receitas assím como escrever comentários.*/

/* TODO fazer nave navegação da home, favoritos e usuarios */
//TODO: colocar home como opção selecionada ao iniciar
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.fragment.FavoritosFragment;
import joao.nicolly.daianny.elisa.fragment.HomeFragment;
import joao.nicolly.daianny.elisa.fragment.UserFragment;
import joao.nicolly.daianny.elisa.model.MainViewModel;

public class MainActivity extends AppCompatActivity {
    //Variáveis
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);

        //Mudando com qual item selecionado a activity deve iniciar
        bottomNavigationView = findViewById(R.id.btNav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                vm.setNavigationOpSelected(item.getItemId());
                switch (item.getItemId()){
                    case R.id.favViewOp:

                        //TODO: o erro é porque falta alterar os parametros necessários em FavoritosFragment
                        FavoritosFragment favoritosFragment = FavoritosFragment.newInstance();
                        setFragment(favoritosFragment);
                        break;
                    case R.id.homeViewOp:
                        //TODO: o erro é porque falta alterar os parametros necessários em HomeFragment
                        HomeFragment homeFragment = HomeFragment.newInstance();
                        setFragment(homeFragment);
                        break;
                    case R.id.perfilViewOp:
                        //TODO: o erro é porque falta alterar os parametros necessários em UserFragment
                        UserFragment userFragment = UserFragment.newInstance();
                        setFragment(userFragment);
                        break;

                }
                return false;
            }
        });
    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragContainer,fragment);
        fragmentTransaction.commit();
    }
}