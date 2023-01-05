package br.com.pedro.loja.entity;

import java.time.LocalDateTime;

public class CategoriaEntity {
    
    private Integer categoriaId;
    private String nome;
    private LocalDateTime criado; // poderia utilizar a classe java.util.Date

    public CategoriaEntity(Integer id, String nome, LocalDateTime criado){
        this.categoriaId = id;
        this.nome = nome;
        this.criado = criado;        
    }


    public Integer getCategoriaId() {
        return categoriaId;
    }
    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public LocalDateTime getCriado() {
        return criado;
    }
    public void setCriado(LocalDateTime criado) {
        this.criado = criado;
    }

    
}
