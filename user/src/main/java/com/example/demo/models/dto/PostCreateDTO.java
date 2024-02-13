package com.example.demo.models.dto;

public class PostCreateDTO {
    private String text;

    public PostCreateDTO(){

    }

    public PostCreateDTO(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
