package com.github.ugurcany.citiesbb.data;

public class City {

    private String _id;
    private String name;
    private String country;
    private Coordinates coord;

    public City(String _id, String name, String country, Coordinates coord) {
        this._id = _id;
        this.name = name;
        this.country = country;
        this.coord = coord;
    }

    public String getDisplayName(){
        return name + ", " + country;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

}
