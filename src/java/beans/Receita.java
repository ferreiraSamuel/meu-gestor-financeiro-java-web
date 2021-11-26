/*
    create table receitas (
	id INT AUTO_INCREMENT PRIMARY KEY,
        titulo VARCHAR(100),
        descricao VARCHAR(100),
        valor DOUBLE(10,2),
        categoria_id INT
    ); 
 */
package beans;

import java.io.Serializable;

public class Receita implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private int id;
    private String titulo;
    private String descricao;
    private Double valor;
    private int categoria_id;
    private String categoria_label;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }
    
    public String getCategoria_label() {
        return categoria_label;
    }

    public void setCategoria_label(String categoria_label) {
        this.categoria_label = categoria_label;
    }
    
}
