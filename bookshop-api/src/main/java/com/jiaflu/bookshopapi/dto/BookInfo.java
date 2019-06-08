package com.jiaflu.bookshopapi.dto;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.Date;

public class BookInfo implements Serializable {

    public interface BookListView {};

    // BookDetailView 需要继承 BookListView，因为在显示详情的时候需要把 BookListView 中的属性也要显示
    public interface BookDetailView extends BookListView {};

    private Long id;

    private String name;

    private String content;

    private Date publishDate;

    public BookInfo() {
    }
    public BookInfo(String name) {
        this.name = name;
    }

    @JsonView(BookListView.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonView(BookListView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(BookDetailView.class)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
