package com.pedalada.app.model.objects;

import com.google.gson.annotations.SerializedName;

public class AuthResult {

    private String token;

    private String name;

    @SerializedName("photoURL")
    private String photoUrl;

    private int pedaladas;

    public AuthResult() {

    }

    public AuthResult(String token, String name, String photoUrl, int pedaladas) {

        this.token = token;
        this.name = name;
        this.photoUrl = photoUrl;
        this.pedaladas = pedaladas;
    }

    @Override
    public String toString() {

        return "AuthResult{" +
                "token='" + token + '\'' +
                ", name='" + name + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", pedaladas='" + pedaladas + '\'' +
                '}';
    }

    public String getName() {

        return name;
    }

    public String getPhotoUrl() {

        return photoUrl;
    }

    public int getPedaladas() {

        return pedaladas;
    }


    public String getToken() {

        return token;
    }

}
