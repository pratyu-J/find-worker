package com.example.win7.myapplication;

/**
 * Created by win7 on 7/8/2019.
 */

public class cardValues {
    private String Name;
    private String city;
    private String job;
    private String email;
    private String phone;
    private String sex;

    public cardValues() {
    }

    public cardValues(String name, String city, String spec, String email, String phone, String sex) {
        Name = name;
        this.city = city;
        this.job = job;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

