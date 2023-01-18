package br.com.pedro.loja.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pedro.loja.entity.CategoriaEntity;
import br.com.pedro.util.ConexaoDB;

public class CategoriaRepository {
    
    ConexaoDB conexao = new ConexaoDB();

    public List<CategoriaEntity> listar(){
        String sql = "select categoria_id, nome,criado from categoria order by nome";
        ResultSet dados = conexao.executeSelect(sql);
        List<CategoriaEntity> lista = new ArrayList<CategoriaEntity>();
        try {
            while( dados.next()){
                CategoriaEntity categoria = new CategoriaEntity(
                    dados.getInt("categoria_id"),
                    dados.getString("nome"),
                    dados.getTimestamp("criado").toLocalDateTime()
                    );
                lista.add(categoria);// adicionar objeto com os atributos preenchidos
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return lista;
    }

    public void excluir( Integer id){
        String sql = "delete from categoria where categoria_id =" + id;

        conexao.executeUpdate(sql);
    }


    public CategoriaEntity salvar(CategoriaEntity categoriaEntity){

        if ( categoriaEntity.getCategoriaId()==null || categoriaEntity.getCategoriaId()==0){ // insert
            String sql = "insert into categoria ( nome, criado ) values (" +
                "'" + categoriaEntity.getNome() + "'," +
                "'" + categoriaEntity.getCriado().toString() + "');";
                int id = conexao.executeInsertGetId(sql);
                categoriaEntity.setCategoriaId(id);

        }else { // update
            String sql = "update categoria set " + 
                "nome='" + categoriaEntity.getNome() + "'," + 
                "criado='" + categoriaEntity.getCriado().toString() + "'" +
                " where categoria_id = " + categoriaEntity.getCategoriaId();

                conexao.executeUpdate(sql);

        }

        return categoriaEntity;
    }

}
