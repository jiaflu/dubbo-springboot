package com.jiaflu.bookshop.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.List;

@Entity
public class Book extends DomainImpl {

    private String name;

    @ManyToOne
    private Category category;

    // BookAuthor 与 Book 的联系由 BookAuthor 端的 book 维护
    @OneToMany(mappedBy = "book")
    private List<BookAuthor> authors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
