package com.example.exemplojsoncomlibhttp.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contato {

    @SerializedName("agenda")
    @Expose

    private List<Agenda> agenda;
    @SerializedName("adicionais")
    @Expose

    private List<Adicional> adicionais;

    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Agenda> agenda) {
        this.agenda = agenda;
    }

    public List<Adicional> getAdicionais() {
        return adicionais;
    }

    public void setAdicionais(List<Adicional> adicionais) {
        this.adicionais = adicionais;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "agenda=" + agenda +
                ", adicionais=" + adicionais +
                '}';
    }
}
