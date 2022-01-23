package com.wergnet.wergnetoil.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// Used when setup to access in BacicAuth authentication
//@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SecuredControllerRestTemplateIntegrationTest {
 
    @Autowired
    private TestRestTemplate template;
 
    // ... other methods
 
    @Test
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() {
        ResponseEntity<String> result = template.withBasicAuth("admin@wergnetoil.com", "admin")
          .getForEntity("/cards/1", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}