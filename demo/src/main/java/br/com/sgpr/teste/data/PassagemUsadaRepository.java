package br.com.sgpr.teste.data;

import org.springframework.data.repository.CrudRepository;

import br.com.sgpr.teste.business.entity.PassagemUsada;

public interface PassagemUsadaRepository extends CrudRepository<PassagemUsada, String>{
    
}
