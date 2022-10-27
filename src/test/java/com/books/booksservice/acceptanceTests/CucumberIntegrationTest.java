package com.books.booksservice.acceptanceTests;

import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectClasspathResource("features")
public class CucumberIntegrationTest {
    
}
