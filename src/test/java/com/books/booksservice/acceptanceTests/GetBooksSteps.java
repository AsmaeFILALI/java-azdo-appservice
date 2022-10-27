package com.books.booksservice.acceptanceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.http.ResponseEntity;

import com.books.booksservice.data.model.Book;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetBooksSteps extends CucumberBootstrap {

    private ResponseEntity<Book> responseEntity;



    @Before
    public void saveBook(){

        Book book = Book.builder().author("Asmae").title("the null").build();
        bookRepository.save(book);
    }

    @When("^the client calls /books/1")
    public void the_client_issues_GET_version() throws Throwable {

        responseEntity = testRestTemplate.getForEntity("/books/1", Book.class);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        assertEquals(responseEntity.getStatusCode().value(), statusCode);
    }

    @And("^the client receives book author (.+)$")
    public void the_client_receives_server_version_body(String author) throws Throwable {

        assertEquals(responseEntity.getBody().getAuthor(), author);
    }
}
