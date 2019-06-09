package com.jiaflu.bookshop.service.impl;



import com.jiaflu.bookshop.domain.Book;
import com.jiaflu.bookshop.repository.BookRepository;
import com.jiaflu.bookshop.support.QueryResultConverter;
import com.jiaflu.bookshopapi.dto.BookCondition;
import com.jiaflu.bookshopapi.dto.BookInfo;
import com.jiaflu.bookshopapi.service.BookService;
import org.apache.commons.lang.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("bookService")
//@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;


    @Override
    @Cacheable("books")
    public BookInfo getInfo(Long id) {
        Book book = bookRepository.findById(id).get();
        BookInfo info = new BookInfo();
        BeanUtils.copyProperties(book, info);

        return info;
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "books", allEntries = true)
    public Page<BookInfo> query(BookCondition condition, Pageable pageable) {

        return null;
    }

    private void updateUserBalance() {
        // TODO Auto-generated method stub

    }

    private void updateStock(BookInfo bookInfo) {
        // TODO Auto-generated method stub

    }

    private void createOrder(BookInfo bookInfo) {
        // TODO Auto-generated method stub

    }

    @Override
    public BookInfo create(BookInfo info) {

        if(StringUtils.equals(info.getName(), "b")){
            throw new RuntimeException("测试事务");
        }

        Book book = new Book();
        book.setName(info.getName());
        bookRepository.save(book);
        info.setId(book.getId());
        return info;
    }

    @Override
    public BookInfo update(BookInfo info) {
        Book book = bookRepository.findById(info.getId()).get();
        book.setName(info.getName());
        bookRepository.save(book);
        return info;
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Scheduled(cron = "0/3 * * * * *")
    public void task() throws Exception {
        Map<String, JobParameter> param = new HashMap<>();
        param.put("startTime", new JobParameter(new Date()));
    }

}

