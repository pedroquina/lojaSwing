package br.com.pedro.service;

import java.sql.SQLException;
import java.util.List;

import br.com.pedro.entity.UsuarioEntity;
import br.com.pedro.repository.UsuarioRepository;

public class UsuarioService {
    
    UsuarioRepository usuarioRepository = new UsuarioRepository();

    public UsuarioEntity salvar( UsuarioEntity usuarioEntity){

        return usuarioRepository.salvar(usuarioEntity);
    }

    public void excluir( Integer id){
        usuarioRepository.excluir(id);
    }

    public UsuarioEntity selecionarId( Integer id){

        try {
            return usuarioRepository.selecionarId(id);
        } catch (SQLException e) {
            System.out.println("Erro :" + e.getMessage());

        }
        return null;
    }

    public List<UsuarioEntity> listar(){
        
        try {
            return usuarioRepository.listar();
        } catch (SQLException e) {
            System.out.println("Erro : " + e.getMessage());
        }

        return null;
    }    

    
}
