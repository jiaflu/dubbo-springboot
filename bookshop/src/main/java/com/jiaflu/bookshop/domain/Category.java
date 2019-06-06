package com.jiaflu.bookshop.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class Category extends DomainImpl {

    @Column(length = 10, nullable = false, unique = true)
    private String name;

    @Transient
    private String xxxx;

    // Book 和 Category 的联系由 Book 一端的 category 维护
    @OneToMany(mappedBy = "category")
    private List<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXxxx() {
        return xxxx;
    }

    public void setXxxx(String xxxx) {
        this.xxxx = xxxx;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
