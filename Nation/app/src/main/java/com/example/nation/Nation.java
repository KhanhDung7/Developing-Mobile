package com.example.nation;

import android.util.Log;

import java.text.DecimalFormat;

public class Nation {
    private String CountryCode;
    private String Name;
    private String Capital;
    private int Area;
    private int Population;

    public Nation(String countryCode, String name, String capital, int area, int population) {
        CountryCode = countryCode;
        Name = name;
        Capital = capital;
        Area = area;
        Population = population;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getArea() {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(Area);
    }


    public String getCapital() {
        return Capital;
    }

    public void setCapital(String capital) {
        Capital = capital;
    }

    public void setArea(int area) {
        Area = area;
    }

    public String getPopulation() {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(Population);
    }

    public void setPopulation(int population) {
        Population = population;
    }

    public String getUrlLogo() {
        return "http://img.geonames.org/flags/x/" + CountryCode.toLowerCase() + ".gif";
    }

    public String getUrlMap() {
        return "http://img.geonames.org/img/country/250/" + CountryCode + ".png";
    }
}
