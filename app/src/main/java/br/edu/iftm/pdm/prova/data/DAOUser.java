package br.edu.iftm.pdm.prova.data;

import java.util.ArrayList;
import java.util.Collections;

import br.edu.iftm.pdm.prova.model.Report;

public class DAOUser {
    private static DAOUser INSTANCE;
    private String nome;
    private String email;

    private DAOUser(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public static DAOUser getINSTANCE(String nome, String email) {
        if(INSTANCE == null)
            INSTANCE = new DAOUser(nome, email);
        return INSTANCE;
    }

    public static boolean isAnyoneLogged(){
        return (INSTANCE == null ? false:true);
    }
}
