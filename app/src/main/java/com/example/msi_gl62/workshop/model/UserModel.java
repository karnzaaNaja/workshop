package com.example.msi_gl62.workshop.model;

public class UserModel {
    String id;
    String name;
    String pass;
    String email;
    String tel;
    String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return getName()+"\n"
                +getPass()+"\n"
                +getEmail()+"\n"
                +getTel()+"\n"
                +getAddress();
    }
}
