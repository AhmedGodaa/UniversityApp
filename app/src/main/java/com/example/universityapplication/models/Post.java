package com.example.universityapplication.models;

public class Post {
    private String written_content;
    private int personal_image;
    private int post_image;

    public Post(String written_content, int personal_image, int post_image) {
        this.written_content = written_content;
        this.personal_image = personal_image;
        this.post_image = post_image;
    }

    public String getWritten_content() {
        return written_content;
    }

    public void setWritten_content(String written_content) {
        this.written_content = written_content;
    }

    public int getPersonal_image() {
        return personal_image;
    }

    public void setPersonal_image(int personal_image) {
        this.personal_image = personal_image;
    }

    public int getPost_image() {
        return post_image;
    }

    public void setPost_image(int post_image) {
        this.post_image = post_image;
    }
}
