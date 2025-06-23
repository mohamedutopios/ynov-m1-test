package org.example.userunittestmockitointegration.controller;


import org.example.userunittestmockitointegration.model.User;
import org.example.userunittestmockitointegration.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    UserService userService;

    @Autowired
    private Environment environment;

    private String baseUrl;


    @BeforeEach
    public void setup(){
        String port = environment.getProperty("local.server.port");
        baseUrl = "http://localhost:" + port + "/api/users";
        userService.deleteAllUsers();
    }


    @Test
    public void testGetAllUsers() {
        User use = new User(null, "Michel","michel@gmail.com");
        userService.saveUser(use);

        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(baseUrl, User[].class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).hasSize(1);
        assertThat(responseEntity.getBody()).isNotEmpty();

    }


    @Test
    public void testGetUserById() {
        User use = new User(null, "Michel","michel@gmail.com");
        User user1 =userService.saveUser(use);

        ResponseEntity<User> responseEntity = restTemplate.getForEntity(baseUrl + "/" + user1.getId(), User.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getName()).isEqualTo("Michel");

    }












}
