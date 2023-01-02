package br.com.pedro.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pedro.entity.UsuarioEntity;
import br.com.pedro.util.ConexaoDB;

public class UsuarioRepository {
    

    private ConexaoDB con = new ConexaoDB();

    public UsuarioEntity salvar( UsuarioEntity usuarioEntity){

        if ( usuarioEntity.getUsuarioId()==null || usuarioEntity.getUsuarioId()==0){
            String sqlInsert = "insert into usuario (  nome, email, senha )  values ( '" + 
                usuarioEntity.getNome() + "','" + 
                usuarioEntity.getEmail() + "','" + 
                usuarioEntity.getSenha() + "')";
                int id = con.executeInsertGetId(sqlInsert);
                usuarioEntity.setUsuarioId(id);
 
        } else {
            String sqlUpdate = "update usuario set " + 
                " nome = '" + usuarioEntity.getNome() + "'," +
                " email = '" + usuarioEntity.getEmail() + "'," +
                " senha = '" + usuarioEntity.getSenha() + "'" + 
                " where usuario_id = " + usuarioEntity.getUsuarioId();

                con.executeUpdate(sqlUpdate);

        }

        return usuarioEntity;
    }

    public void excluir( Integer id){
        String sql = "delete from usuario where usuario_id = " + id;

        con.executeUpdate(sql);
    }

    public UsuarioEntity selecionarId( Integer id) throws SQLException{
        String sql = "select usuario_id,nome, email, senha where usuario_id =" + id;

        ResultSet rs = con.executeSelect(sql);
        if ( rs.next()){
            return bdParaEntity(rs);
        }

        return null;
    }

    public List<UsuarioEntity> listar() throws SQLException{
        List<UsuarioEntity> lista = new ArrayList<UsuarioEntity>();
        String sql = "select usuario_id, nome, email, senha from usuario";

        ResultSet rs = con.executeSelect(sql);

        while(rs.next()){
            lista.add( bdParaEntity(rs));
        }

        return lista;
    }
    private UsuarioEntity bdParaEntity(ResultSet rs) throws SQLException{
        UsuarioEntity usuarioEntity = new UsuarioEntity(
            rs.getInt("usuario_id"),
            rs.getString("nome"),
            rs.getString("email"),
            rs.getString("senha")
        );
        return usuarioEntity;
    }
}
