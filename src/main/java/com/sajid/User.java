package com.sajid;

public class User {
    private int id;
    private String name;
    private int age;
    private int mobileNumber;
    private String email;

    public User() {
    }

    public User(String name, int age, int mobileNumber, String email) {
        this.name = name;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public User(int id, String name, int age, int mobileNumber, String email) {
        this(name, age, mobileNumber, email);
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }
}
