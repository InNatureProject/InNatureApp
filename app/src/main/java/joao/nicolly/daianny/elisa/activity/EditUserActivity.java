package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.model.viewModel.ImageUserViewModel;
import joao.nicolly.daianny.elisa.model.viewModel.ReceitaPreparoViewModel;
import joao.nicolly.daianny.elisa.util.Config;

public class EditUserActivity extends AppCompatActivity {

    //VARIÁVEIS

    ImageView imgvFotoPerfil;
    ImageView ivMudarFotoPerfil;
    TextView tvEmailVinc;
    TextView tvNomeVinc;
    EditText etNovoNome;
    EditText etNovaSenha;
    EditText etConfNovaSenha;

    Button btnConfMudancas;
    Button btnDelConta;

    //QUANDO A TELA ESTIVER SENDO CRIADA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        /*Setando a imagem de perfil*/
        imgvFotoPerfil = findViewById(R.id.imgvFotoPerfil);
        //Para que o usuário seja capaz de ver sua imagem
        if(Config.getImagem(this).isEmpty()){
            Picasso.with(this)
                    .load("https://raw.githubusercontent.com/InNatureProject/innatureimages/main/default_ImageUser.jpg")
                    .into(imgvFotoPerfil);
        }else{
            Picasso.with(this)
                    .load(Config.getImagem(this))
                    .into(imgvFotoPerfil);
        }
        //Para editar a imagem de usuário
        ivMudarFotoPerfil = findViewById(R.id.ivMudarFotoPerfil);
        ivMudarFotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditUserActivity.this,ChooseImageActivity.class);
                startActivity(i);
            }
        });

        /*aqui pegamos os textView nome e email da tela e
        modificamos o conteúdo para que receba o nome e email do usuário*/

        tvEmailVinc = findViewById(R.id.tvEmailVinc);
        tvEmailVinc.setText(Config.getEmail(EditUserActivity.this));

        tvNomeVinc = findViewById(R.id.tvNomeVinc);
        tvNomeVinc.setText(Config.getName(EditUserActivity.this));

        btnConfMudancas = findViewById(R.id.btnConfMudancas);

        //Se o botão de confirmar mudanças for acionado
        btnConfMudancas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pegamos os elementos que contem as novas informações
                etNovoNome = findViewById(R.id.etNovoNome);
                etNovaSenha = findViewById(R.id.etNovaSenha);
                etConfNovaSenha = findViewById(R.id.etConfNovaSenha);

                //pegamos as informações dentro deles
                String novoNome = etNovoNome.getText().toString();
                String novaSenha = etNovaSenha.getText().toString();
                String confNovaSenha = etConfNovaSenha.getText().toString();

                /**Só modificamos as informações cujos campos possuíam strings, se o campo estáva vazio não a modificamos*/
                if(!novoNome.isEmpty()){
                    Config.setName(EditUserActivity.this,novoNome);
                    Toast.makeText(EditUserActivity.this,"Nome de usuário atualizado com sucesso!",Toast.LENGTH_LONG).show();
                    //TODO:devemos enviar  o novo nome para ser salvo no banco de dados, provavelmete será gerado um novo tolken de acesso
                }
                if(!novaSenha.isEmpty()){
                    if(novaSenha.equals(confNovaSenha)){
                        Config.setPassword(EditUserActivity.this,novaSenha);
                        //mensagem avisando o sucesso ao atualizar a senha
                        Toast.makeText(EditUserActivity.this,"Senha atualizada com sucesso!",Toast.LENGTH_LONG).show();
                        //TODO: devemos enviar a nova senha para ser salva no banco de dados, provavelmete será gerado um novo tolking de acesso
                    }
                    else{
                        //mensagem de falha
                        Toast.makeText(EditUserActivity.this,"A senha confirmada é diferente da desejada!",Toast.LENGTH_LONG).show();
                    }
                }
                Intent i = new Intent(EditUserActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        //Caso o usuário opte por deletar a conta:
        btnDelConta = findViewById(R.id.btnDelConta);
        btnDelConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: enviar uma requisição ao banco de dados pedindo pra deletar a conta
            }
        });




    }
}