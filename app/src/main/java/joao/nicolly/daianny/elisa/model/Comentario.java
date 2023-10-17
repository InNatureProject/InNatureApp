package joao.nicolly.daianny.elisa.model;

import java.util.Date;

public class Comentario {
    private String autor;//oooo
    private Date data;
    private String comentario;

    public Comentario(String autor, Date data, String comentario) {
        this.autor = autor;
        this.data = data;
        this.comentario = comentario;
    }
}
