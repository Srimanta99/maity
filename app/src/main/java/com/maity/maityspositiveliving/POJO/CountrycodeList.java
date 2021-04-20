package com.maity.maityspositiveliving.POJO;

public class CountrycodeList {
    String id,country_name;

    public CountrycodeList(String id, String country_name) {
        this.id = id;
        this.country_name = country_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}
