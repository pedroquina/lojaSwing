package br.com.pedro.loja.service;

import java.util.List;

import br.com.pedro.loja.entity.CategoriaEntity;
import br.com.pedro.loja.repository.CategoriaRepository;

public class CategoriaService {
    
    CategoriaRepository categoriaRepository = new CategoriaRepository();

    public List<CategoriaEntity> listar(){
        List<CategoriaEntity> lista = categoriaRepository.listar();

        return lista;
    }

    public void excluir(Integer id){
        categoriaRepository.excluir(id);
    }

    public CategoriaEntity salvar(CategoriaEntity categoriaEntity){
        return categoriaRepository.salvar(categoriaEntity);
    }


}
