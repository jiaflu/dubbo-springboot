package com.jiaflu.bookshop.repository;

import com.jiaflu.bookshop.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrintBookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
}
