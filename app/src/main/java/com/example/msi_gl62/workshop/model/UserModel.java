package com.example.msi_gl62.workshop.model;

public class UserModel {
    private String id;
    private String name;
    private String email;
    private String tel;
    private String address;
    private String sex;



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


    public String getSex() {
        return sex;
    }


    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return getName() + "\n"
                + getEmail() + "\n"
                + getTel() + "\n"
                + getSex() + "\n"
                + getAddress();
    }
}
