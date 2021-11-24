/*
    create table categorias_receitas (
	id INT AUTO_INCREMENT PRIMARY KEY,
        titulo VARCHAR(100)
    );
 */
package beans;

public class CategoriaReceita {
    
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
