package com.meass.gasstation;
public class User_coun {
    String name,number,email,coin;

    public User_coun() {
    }

    public User_coun(String name, String number, String email, String coin) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.coin = coin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }
}
