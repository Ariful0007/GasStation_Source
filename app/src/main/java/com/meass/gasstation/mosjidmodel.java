package com.meass.gasstation;

public class mosjidmodel {
    String name,location,fazor,jahor,asor,magrib,esha,jumma,sunset, sunrise,datetime,time;

    public mosjidmodel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFazor() {
        return fazor;
    }

    public void setFazor(String fazor) {
        this.fazor = fazor;
    }

    public String getJahor() {
        return jahor;
    }

    public void setJahor(String jahor) {
        this.jahor = jahor;
    }

    public String getAsor() {
        return asor;
    }

    public void setAsor(String asor) {
        this.asor = asor;
    }

    public String getMagrib() {
        return magrib;
    }

    public void setMagrib(String magrib) {
        this.magrib = magrib;
    }

    public String getEsha() {
        return esha;
    }

    public void setEsha(String esha) {
        this.esha = esha;
    }

    public String getJumma() {
        return jumma;
    }

    public void setJumma(String jumma) {
        this.jumma = jumma;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public mosjidmodel(String name, String location, String fazor, String jahor, String asor, String magrib,
                       String esha, String jumma, String sunset, String sunrise, String datetime, String time) {
        this.name = name;
        this.location = location;
        this.fazor = fazor;
        this.jahor = jahor;
        this.asor = asor;
        this.magrib = magrib;
        this.esha = esha;
        this.jumma = jumma;
        this.sunset = sunset;
        this.sunrise = sunrise;
        this.datetime = datetime;
        this.time = time;
    }
}
