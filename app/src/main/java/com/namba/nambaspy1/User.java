package com.namba.nambaspy1;

public class User {

    private String firstname;
    private String lastname;
    private int avatar;

    public User(String firstname, String lastname, int avatar) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.avatar = avatar;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
