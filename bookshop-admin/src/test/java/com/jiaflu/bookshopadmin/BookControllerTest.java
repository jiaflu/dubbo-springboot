package com.jiaflu.bookshopadmin;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookshopAdminApplication.class)
public class BookControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform(get("/book")
                .param("categoryId", "1")
                .param("name", "战争")
                .param("page", "1")
                .param("size", "15")
                .param("sort", "name,desc", "createdTime,asc")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String result = mockMvc.perform(get("/book/1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value("战争与和平"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        mockMvc.perform(get("/book/10")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        String content = "{\"id\":null,\"name\":\"战争与和平\",\"content\":null, \"publishDate\":"+new Date().getTime()+"}";
        String result = mockMvc.perform(post("/book")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        String content = "{\"id\":1,\"name\":\"战争与和平\",\"content\":null, \"publishDate\":\"2017-05-05\"}";
        mockMvc.perform(put("/book/1")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(delete("/book/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenCookieOrHeaderExists() throws Exception {
        mockMvc.perform(get("/book/1")
                .cookie(new Cookie("token", "123456"))
                .header("auth", "xxxxxx")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
