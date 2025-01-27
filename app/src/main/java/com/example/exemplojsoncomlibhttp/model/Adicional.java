package com.example.exemplojsoncomlibhttp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Adicional {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Adicional{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
