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



}
