package com.pedalada.app.model.objects;

public class TeamInfo {

    private String id;

    private String name;



    @Override
    public String toString() {

        return "TeamInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {

        return name;
    }

    public String getId() {

        return id;
    }
}
