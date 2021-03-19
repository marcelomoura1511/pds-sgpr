package br.com.sgpr.teste.business.util;

import java.util.ArrayList;

public class Mensagem {
    private String status;
    private ArrayList<String> erros;

    public Mensagem(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getErros() {
        return erros;
    }

    public void setErros(ArrayList<String> erros) {
        this.erros = erros;
    }
    
    public void addErro(String error) {
        if(erros == null) {
            erros = new ArrayList<>();
        }
        erros.add(error);
    }
    
}
