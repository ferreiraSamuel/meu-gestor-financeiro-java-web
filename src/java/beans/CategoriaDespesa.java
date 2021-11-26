/*
    create table categorias_despesas (
	id INT AUTO_INCREMENT PRIMARY KEY,
        titulo VARCHAR(100)
    );
 */
package beans;

import java.io.Serializable;

public class CategoriaDespesa implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private int id;
    private String titulo;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
