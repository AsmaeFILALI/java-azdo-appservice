package com.books.booksservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.books.booksservice.data.model.Book;
import com.books.booksservice.service.BooksService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(BooksController.class)
public class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BooksService booksService;

    private Book bookFixture = Book.builder().author("lma3ti").title("samiro layl").build();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void addbook_shouldReturn_200() throws JsonProcessingException, Exception {
        when(booksService.saveBook(any(Book.class))).thenReturn(bookFixture);

        mockMvc.perform(MockMvcRequestBuilders.post("/books/add")
                .content(tojson(bookFixture))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getbook_shouldReturn_200() throws JsonProcessingException, Exception {
        
        when(booksService.getBook(Long.valueOf(1))).thenReturn(bookFixture);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value("lma3ti"))
                .andExpect(jsonPath("$.title").value("samiro layl"));
    }

    private String tojson(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

}
