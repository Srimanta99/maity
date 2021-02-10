package com.example.maityspositiveliving.POJO;

public class StateList {
    String id,state_name,country_id;

    public StateList(String id, String state_name, String country_id) {
        this.id = id;
        this.state_name = state_name;
        this.country_id = country_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }
}
