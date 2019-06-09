package com.jiaflu.bookshopapi.service;

import com.jiaflu.bookshopapi.dto.BookCondition;
import com.jiaflu.bookshopapi.dto.BookInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookInfo getInfo(Long id);

    Page<BookInfo> query(BookCondition condition, Pageable pageable);

    BookInfo create(BookInfo info);

    BookInfo update(BookInfo info);

    void delete(Long id);

    void task() throws Exception;

}
