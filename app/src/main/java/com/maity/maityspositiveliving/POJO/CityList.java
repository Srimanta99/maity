package com.maity.maityspositiveliving.POJO;

public class CityList {
    String city_id,city_name,country_id;

    public CityList(String city_id, String city_name, String country_id) {
        this.city_id = city_id;
        this.city_name = city_name;
        this.country_id = country_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }
}
