package com.pedalada.app.model.objects;

public class AuthResult {

    private String token;

    private String name;

    private String photoUrl;

    private int pedaladas;

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
