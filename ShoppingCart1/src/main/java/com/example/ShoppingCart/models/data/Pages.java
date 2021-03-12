package com.example.ShoppingCart.models.data;

import javax.persistence.*;

import org.springframework.validation.annotation.*;
@Entity
@Table(name="pages")

public class Pages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String slug;

    public String getContent() {
        return content;
    }

    public int getSorting() {
        return sorting;
    }

    public void setSorting(int sorting) {
        this.sorting = sorting;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

   // @Size(m=2,message="content must be of minimum 2 characters")
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //@Size(m=2,message="content must be of minimum 2 characters")
    private String content;
    private int sorting;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
