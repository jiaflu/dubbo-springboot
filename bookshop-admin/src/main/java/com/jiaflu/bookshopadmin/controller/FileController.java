package com.jiaflu.bookshopadmin.controller;

import com.jiaflu.bookshopapi.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;


@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping(value = "/upload")
    public FileInfo update(MultipartFile file) throws Exception {
        System.out.println(file.getContentType());
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        String path = "C:\\Users\\jiaflu\\workspace\\dubbo-springboot\\bookshop-admin\\src\\main\\java\\com\\jiaflu\\bookshopadmin\\controller";
        String extention = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        File localfile = new File(path, new Date().getTime() + "." + extention);
        file.transferTo(localfile);

        return new FileInfo(localfile.getAbsolutePath());
    }

    @GetMapping(value = "/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = "C:\\Users\\jiaflu\\workspace\\dubbo-springboot\\bookshop-admin\\src\\main\\java\\com\\jiaflu\\bookshopadmin\\controller\\1559996292839.txt";

        try(InputStream inputStream = new FileInputStream(filePath);
            OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }

}
