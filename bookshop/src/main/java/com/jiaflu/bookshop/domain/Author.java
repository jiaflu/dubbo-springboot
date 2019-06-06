package com.jiaflu.bookshop.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

@Entity
public class Author extends DomainImpl {

    private String name;

    @Email
    private String email;

    @Column(columnDefinition = "INT(3)")
    private int age;

    @Temporal(TemporalType.DATE)
    private Date birthday;

//    @Enumerated(EnumType.STRING)
//    private Sex sex;

    @Embedded
    private Address address;

    @ElementCollection
    private List<Address> addresses;

    @OneToMany(mappedBy = "author")
    @OrderBy("book.name ASC")
    private List<BookAuthor> books;

    @OneToOne
    private AuthorInfo info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
