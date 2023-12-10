package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.model.viewModel.SetImageViewModel;
import joao.nicolly.daianny.elisa.util.Config;

public class ChooseImageActivity extends AppCompatActivity {
    //VARIAVEIS
    ImageView ivMaleUser;
    ImageView ivFemaleUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);

        //INICIALIZANDO VARIAVEIS

        ivMaleUser = findViewById(R.id.ivMaleUser);
        ivFemaleUser= findViewById(R.id.ivFemaleUser);

        String maleImage = "https://raw.githubusercontent.com/InNatureProject/innatureimages/main/maleUser.png";
        String femaleImage = "https://raw.githubusercontent.com/InNatureProject/innatureimages/main/femaleUser.png";

        //Colocando imagem nos imageView
        Picasso.with(this)
                .load(maleImage)
                .into(ivMaleUser);

        Picasso.with(this)
                .load(femaleImage)
                .into(ivFemaleUser);

        //Quando o usuário clicar em uma das imagens guardatemos sua escolha no Banco de dados e voltamos para o main
        ivMaleUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivMaleUser.setClickable(false);
                setImage(maleImage);
                ivMaleUser.setClickable(true);
                Intent i = new Intent(ChooseImageActivity.this, MainActivity.class);
                startActivity(i);

            }
        });
        ivFemaleUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivFemaleUser.setClickable(false);
                setImage(femaleImage);
                ivFemaleUser.setClickable(true);
                Intent i = new Intent(ChooseImageActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    private void setImage(String url){
        SetImageViewModel setImageViewModel = new ViewModelProvider(this).get(SetImageViewModel.class);
        LiveData<Boolean> sucess = setImageViewModel.setNewImageUser(url);

        sucess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Toast.makeText(ChooseImageActivity.this,"Imagem de usuário modificada com sucesso!",Toast.LENGTH_LONG).show();
                    setImageOnConfig(url);
                }else{
                    Toast.makeText(ChooseImageActivity.this,"Não foi possível modificar a imagem!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void setImageOnConfig(String url){
        Config.setImagem(this,url);
    }
}