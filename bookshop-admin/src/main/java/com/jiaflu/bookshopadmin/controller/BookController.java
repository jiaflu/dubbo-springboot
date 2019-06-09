package com.jiaflu.bookshopadmin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jiaflu.bookshopapi.dto.BookCondition;
import com.jiaflu.bookshopapi.dto.BookInfo;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(BookInfo.BookListView.class)
    public List<BookInfo> query(BookCondition condition, Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		if(authentication != null) {
			System.out.println(authentication.getPrincipal());
		}

		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getSort());

		List<BookInfo> books = new ArrayList<BookInfo>();
		books.add(new BookInfo());
		books.add(new BookInfo());
		books.add(new BookInfo());
        return books;
    }

    @JsonView(BookInfo.BookDetailView.class)
    @RequestMapping(value = "/{id:\\d}", method = RequestMethod.GET)
    //@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BookInfo getInfo(@PathVariable Long id) throws Exception {
		//throw new RuntimeException("test");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		if (authentication != null) {
			System.out.println(authentication.getPrincipal());
		}

//		System.out.println(id);
//		System.out.println(token);
//		System.out.println("auth is " + auth);
		BookInfo bookInfo = new BookInfo();
		bookInfo.setName("战争与和平");
		bookInfo.setPublishDate(new Date());
		return bookInfo;
	}


	/**
	 * 异步处理 HTTP 请求
	 */
//	@JsonView(BookInfo.BookDetailView.class)
//	@RequestMapping(value = "/{id:\\d}", method = RequestMethod.GET)
//	//@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public Callable<BookInfo> getInfo(@PathVariable Long id) throws Exception {
//
//		long start = new Date().getTime();
//		System.out.println(Thread.currentThread().getName() + " 开始");
//
//		Callable<BookInfo> result = () -> {
//			System.out.println(Thread.currentThread().getName() + "线程开始");
//			Thread.sleep(1000);
//			BookInfo bookInfo = new BookInfo();
//			bookInfo.setName("战争与和平");
//			bookInfo.setPublishDate(new Date());
//
//			System.out.println(Thread.currentThread().getName() + " 线程返回，耗时： " + (new Date().getTime() - start));
//			return bookInfo;
//		};
//
//		System.out.println(Thread.currentThread().getName() + "返回，耗时： " + (new Date().getTime() - start));
//		return result;
//	}

	@RequestMapping(method = RequestMethod.POST)
	public BookInfo create(@RequestBody BookInfo info, BindingResult result) {
		if(result.hasErrors()) {
			result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		System.out.println("id is "+info.getId());
		System.out.println("name is "+info.getName());
		System.out.println("content is "+info.getContent());
		System.out.println("publishDate is "+info.getPublishDate());
		info.setId(1L);
		return info;
		//return bookService.create(info);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public BookInfo update(@Valid @RequestBody BookInfo info, BindingResult result) {
		if(result.hasErrors()) {
			result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		System.out.println("id is "+info.getId());
		System.out.println("name is "+info.getName());
		System.out.println("content is "+info.getContent());
		System.out.println("publishDate is "+info.getPublishDate());
		return info;
		//return bookService.update(info);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		System.out.println(id);

		//bookService.delete(id);
	}
}
