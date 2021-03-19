package br.com.sgpr.teste.business.exceptions;

import java.util.ArrayList;

public class BusinessExceptions extends Exception{
    private ArrayList<String> listOfMenssages;

    public BusinessExceptions(String msg){
        super(msg);
    }

    public BusinessExceptions(ArrayList<String> msg){
        listOfMenssages = msg;
    }

    public ArrayList<String> getListOfMenssagens(){
        return listOfMenssages;
    }
}
