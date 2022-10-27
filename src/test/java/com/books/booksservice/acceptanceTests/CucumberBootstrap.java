package com.books.booksservice.acceptanceTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.books.booksservice.data.BookRepository;

import io.cucumber.spring.CucumberContextConfiguration;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class CucumberBootstrap {

    @Autowired
    protected TestRestTemplate testRestTemplate;
    @Autowired 
    protected BookRepository bookRepository;
}
