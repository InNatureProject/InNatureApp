package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import joao.nicolly.daianny.elisa.R;
import joao.nicolly.daianny.elisa.model.objetos.ReceitaPreparo;
import joao.nicolly.daianny.elisa.model.viewModel.ReceitaPreparoViewModel;

public class ReceitaPreparoActivity extends AppCompatActivity {
    int idPlanta;
    int idTipoPreparo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_preparo);

        //Recebendo intent e armazenando idPlanta e idTipoPreparo
        //Como estaremos reulizando a api command/plantapreparo será necessário ambos para pegar as informações da receita
        Intent i = getIntent();
        this.idPlanta = i.getIntExtra("idPlanta",0);
        this.idTipoPreparo = i.getIntExtra("idTipoPreparo",0);

        ReceitaPreparoViewModel receitaPreparoViewModel = new ViewModelProvider(this).get(ReceitaPreparoViewModel.class);
        // O ViewModel possui o método getProductDetailsLD, que obtém os detalhes de um produto em
        // específico do servidor web.
        //
        // O método getReceita retorna um LiveData, que na prática é um container que avisa
        // quando o resultado do servidor chegou. Ele guarda os detalhes de uma receita que o servidor
        // entregou para a app.
        LiveData<ReceitaPreparo> receita = receitaPreparoViewModel.getReceita(idPlanta,idTipoPreparo);
        // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado contendo uma receita
        // será guardado dentro do LiveData. Neste momento o
        // LiveData avisa que a receita chegou chamando o método onChanged abaixo.
        receita.observe(this, new Observer<ReceitaPreparo>() {
            @Override
            public void onChanged(ReceitaPreparo receitaPreparo) {
                // recenta contém os detalhes da ReceitaPreparo que foram entregues pelo servidor web
                if(receita != null){

                    TextView tvNomeReceitaPreparo = findViewById(R.id.tvNomeReceitaPreparo);
                    tvNomeReceitaPreparo.setText(receitaPreparo.getTitulo());

                    String indicacao = organizaTexto(receitaPreparo.getIndicacao());
                    String contraindicacao = organizaTexto(receitaPreparo.getContraindicação());
                    String efeitoColateral = organizaTexto(receitaPreparo.getEfeitoColateral());


                    TextView tvReceitaPreparo = findViewById(R.id.tvReceitaPreparo);
                    tvReceitaPreparo.setText("\n" +
                            "Preparo:\n" +
                            "\n" +
                            receitaPreparo.getReceita() +"\n" +
                            "\n" +
                            "Indicação:\n" +
                            "\n" +
                            indicacao +
                            "\n" +
                            "Contraindicação:\n" +
                            "\n" +
                            contraindicacao +
                            "\n" +
                            "Efeitos colaterais:\n" +
                            "\n" +
                            efeitoColateral);

                }else {
                    Toast.makeText(ReceitaPreparoActivity.this, "Não foi possível obter os detalhes da receita", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    private String organizaTexto(ArrayList<String> jay){
        String str = "";
        for(int i = 0; i < jay.size(); i++){
            str = str + jay.get(i) + ";\n";
        }
        return str;
    }
}