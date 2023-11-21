package joao.nicolly.daianny.elisa.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**Esta classe armazena as informações referentes ao objeto TipoPreparo.*/

public class TipoPreparo{
    // Declaracao da sua classe
    private int id;
    private String text;

    public TipoPreparo(int id, String text){
        this.id = id;
        this.text = text;
    }
    public TipoPreparo(Parcel in){
        
    }


    //Métodos get

    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }

}
