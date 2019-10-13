package com.example.win7.myapplication;

/**
 * Created by win7 on 6/24/2019.
 */

public class userProfile {
    public String name;
    public String city;
    public String email;
    public String phone;
    public String sex;
    public String age;
    public String dob;
    public String job;

    public userProfile(){

    }

    public userProfile(String name, String city, String email, String phone, String sex, String age, String dob, String job) {
        this.name = name;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.age = age;
        this.dob = dob;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}






