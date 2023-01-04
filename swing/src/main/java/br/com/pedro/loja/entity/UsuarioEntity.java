package br.com.pedro.loja.entity;

public class UsuarioEntity {
    
    private Integer UsuarioId;
    private String nome;
    private String email;
    private String senha;

    public UsuarioEntity(Integer id, String nome, String email, String senha){
        this.UsuarioId = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;

    }

    public Integer getUsuarioId() {
        return UsuarioId;
    }
    public void setUsuarioId(Integer usuarioId) {
        UsuarioId = usuarioId;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }


    
}
