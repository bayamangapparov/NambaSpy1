package com.namba.nambaspy1;

public class Comment {
    String comment;
    String date;
    int raiting;
    User user;

    public Comment(String comment, String date, int raiting, User user) {
        this.comment = comment;
        this.date = date;
        this.raiting = raiting;
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
