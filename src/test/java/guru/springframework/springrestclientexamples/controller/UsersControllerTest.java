package guru.springframework.springrestclientexamples.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersControllerTest {

    @Autowired
    ApplicationContext applicationContext;
    WebTestClient webClient;

    @Before
    public void setUp() throws Exception {
        webClient = WebTestClient.bindToApplicationContext(applicationContext).build();
    }

    @Test
    public void getIndex() {
        webClient.get().uri("/").exchange().expectStatus().isOk();
    }

    @Test
    public void getUsers() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("limit","2");

        webClient.post().uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData(map))
                .exchange()
                .expectStatus()
                .isOk();
    }
}