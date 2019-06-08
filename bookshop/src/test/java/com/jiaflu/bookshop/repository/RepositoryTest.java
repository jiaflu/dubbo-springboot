package com.jiaflu.bookshop.repository;

import com.jiaflu.bookshop.BaseTest;
import com.jiaflu.bookshop.domain.Book;
import com.jiaflu.bookshop.domain.Ebook;
import com.jiaflu.bookshop.domain.PrintBook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Optional;

public class RepositoryTest extends BaseTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test
    public void test1() {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Book book = bookRepository.findById(1L).get();
        book.setName("美女与野兽");
        bookRepository.saveAndFlush(book);
        System.out.println("success");
        transactionManager.commit(status);
    }

    @Test
    public void test2() {
        System.out.println(bookRepository.findByName("1").getName());
    }

    @Test
    public void test4(){

        PrintBook printBook = new PrintBook();
        printBook.setName("1");
        bookRepository.save(printBook);

        Ebook ebook = new Ebook();
        ebook.setName("2");
        bookRepository.save(ebook);

        List<Book> books = bookRepository.findAll();
        books.stream().forEach(book -> System.out.println(book.getClass().getSimpleName()));


    }

}
