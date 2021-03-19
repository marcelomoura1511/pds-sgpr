package br.com.sgpr.teste.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "rotas")
public class VisaoRotas {
    private String idRota;
    private String nome;
    private String origem;
    private String destino;

    @Id
    @Column(name = "id_rota")
    public String getIdRota(){
        return idRota;
    }

    @Column(name = "id_rota")
    public void setIdRota(String novoId){
        idRota = novoId;
    }

    @Column(name = "nome_rota")
    public String getNome(){
        return nome;
    }

    @Column(name = "nome_rota")
    public void setNome(String novoNome){
        nome = novoNome;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
