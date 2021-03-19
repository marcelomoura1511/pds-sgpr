package br.com.sgpr.teste.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.sgpr.teste.business.entity.ViagemSimples;

public interface ViagemSimplesRepository extends CrudRepository<ViagemSimples, String>{
    @Query(value = "select * from viagens_simples where motorista = :motoristaId", nativeQuery = true)
    public Iterable<ViagemSimples> findAllViagensMotorista(@Param("motoristaId") String motoristaId);
}
