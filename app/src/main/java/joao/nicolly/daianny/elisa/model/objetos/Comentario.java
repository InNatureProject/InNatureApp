package joao.nicolly.daianny.elisa.model.objetos;

import java.util.Date;

public class Comentario {
    int id;
    private String autor;
    private String comentario;
    private String fotoAutor;

    public Comentario(int id,String autor,String fotoAutor, String comentario) {
        this.autor = autor;
        this.fotoAutor = fotoAutor;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }


    public String getComentario() {
        return comentario;
    }

    public String getFotoAutor() {
        return fotoAutor;
    }
}
