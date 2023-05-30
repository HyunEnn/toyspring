package com.demo;


public class MovieDto {
    private int idx;
    private String title;
    private String image;
    private String content;


    public MovieDto(int idx, String title, String image, String content) {
        this.idx = idx;
        this.title = title;
        this.image = image;
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdx() {
        return idx;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "idx=" + idx +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
