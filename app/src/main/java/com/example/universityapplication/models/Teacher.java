package com.example.universityapplication.models;

public class Teacher {
    private String name ;
    private String subject ;
   private  int photo ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public Teacher(String name, String subject, int photo) {
        this.name = name;
        this.subject = subject;
        this.photo = photo;
    }
}
