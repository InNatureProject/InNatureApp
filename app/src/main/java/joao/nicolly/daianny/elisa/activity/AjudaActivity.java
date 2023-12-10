package joao.nicolly.daianny.elisa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import joao.nicolly.daianny.elisa.R;

public class AjudaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatarproblema);

        //VARIAVEIS

        CheckBox cbTrava;
        CheckBox cbSemAcesso;
        CheckBox cbMuitoLento;
        CheckBox cbOutro;
        EditText etDescreva;
        Button btnSend;
        String texto;



        //INICIALIZANDO VARIÁVEIS
        cbTrava = findViewById(R.id.cbTrava);
        cbSemAcesso = findViewById(R.id.cbSemAcesso);
        cbMuitoLento = findViewById(R.id.cbMuitoLento);
        cbOutro = findViewById(R.id.cbOutro);
        etDescreva = findViewById(R.id.etDescreva);
        btnSend = findViewById(R.id.btnSend);
        texto = "";

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbTrava.isSelected()){
                    final String strTrava = cbTrava.getText().toString();
                }
                if(cbSemAcesso.isSelected()){
                    final String strSemAcesso = cbSemAcesso.getText().toString();
                }
                if(cbMuitoLento.isSelected()){
                    final String strMuitoLento = cbMuitoLento.getText().toString();
                }
                if(cbOutro.isSelected()){
                    final String strOutro = etDescreva.getText().toString();
                }
            }
        });


    }

}
