package br.com.sgpr.teste.business.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name="viagens_simples")
public class ViagemSimples{
    private int id;
    private String origem;
    private String destino;
    private String data;
    private String hora_saida;
    private float preco;
    private String motorista;
    private String onibus;
    private String rota;
    private String status;
    private String empresa;
    private int assentosDisponiveis;

    @Id
    public int getId(){
        return id;
    }

    public void setId(int novoId){
        id = novoId;
    }

    public String getOrigem(){
        return origem;
    }

    public void setOrigem(String novaOrigem){
        origem = novaOrigem;
    }

    public String getDestino(){
        return destino;
    }

    public void setDestino(String novoDestino){
        destino = novoDestino;
    }

    @Column(name="data_viagem")
    public String getData(){
        return data;
    }

    @Column(name="data_viagem")
    public void setData(String novaData){
        data = novaData;
    }

    @Column(name="hora_saida")
    public String getHoraSaida(){
        return hora_saida;
    }

    @Column(name="hora_saida")
    public void setHoraSaida(String novaHoraSaida){
        hora_saida = novaHoraSaida;
    }

    public float getPreco(){
        return preco;
    }

    public void setPreco(float novoPreco){
        preco = novoPreco;
    }

    public String getMotorista(){
        return motorista;
    }

    public void setMotorista(String novoMotorista){
        motorista = novoMotorista;
    }

    public String getOnibus(){
        return onibus;
    }

    public void setOnibus(String novoOnibus){
        onibus = novoOnibus;
    }

    public String getRota(){
        return rota;
    }

    public void setRota(String novaRota){
        rota = novaRota;
    }

    
    @Column(name="estado")
    public String getStatus(){
        return status;
    }

    @Column(name="estado")
    public void setStatus(String novoStatus){
        status = novoStatus;
    }

    public String getEmpresa(){
        return empresa;
    }

    public void setEmpresa(String novaEmpresa){
        empresa = novaEmpresa;
    }

    @Column(name = "assentos_disponiveis")
    public int getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public void setAssentosDisponiveis(int assentosDisponiveis) {
        this.assentosDisponiveis = assentosDisponiveis;
    }
}   
