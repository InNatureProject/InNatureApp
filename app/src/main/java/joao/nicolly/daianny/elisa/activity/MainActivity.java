package joao.nicolly.daianny.elisa.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.auxiliares.MainViewModel;

public class MainActivity extends AppCompatActivity {
    //Variáveis

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pegamdo modelo de visualização
        final MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        //Mudando com qual item selecionado a activity deve iniciar
        bottomNavigationView = findViewById(R.id.btNav);
//        bottomNavigationView.setSelectedItemId(R.id.homeViewOp);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mainViewModel.setNavigationOpSelected(item.getItemId());
                switch (item.getItemId()){
                    case R.id.favViewOp:
                        break;
                    case R.id.homeViewOp:
                        break;
                    case R.id.perfilViewOp:
                        break;

                }
                return false;
            }
        });
    }
}