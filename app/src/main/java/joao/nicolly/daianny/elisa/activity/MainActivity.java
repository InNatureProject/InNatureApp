package joao.nicolly.daianny.elisa.activity;

/**
 * App do In Nature. Pela app será possível
 * visualizar as informações,
 * criar uma conta, logar, deletar a conta e
 * favoritar receitas assím como escrever comentários.*/

/* TODO fazer nave navegação da home, favoritos e usuarios */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import joao.nicolly.daianny.elisa.R;

public class MainActivity extends AppCompatActivity {
    //Variáveis

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mudando com qual item selecionado a activity deve iniciar
        bottomNavigationView = findViewById(R.id.btNav);
//        bottomNavigationView.setSelectedItemId(R.id.homeViewOp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.favViewOp:
                        toolbar.setBackgroundColor(000000);
                        break;
                    case R.id.homeViewOp:
                        toolbar.setBackgroundColor(111111);
                        break;
                    case R.id.perfilViewOp:
                        toolbar.setBackgroundColor(444444);
                        break;

                }
                return false;
            }
        });
    }
}