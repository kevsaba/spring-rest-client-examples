package guru.springframework.springrestclientexamples.service.impl;

import guru.springframework.api.domain.User;
import guru.springframework.springrestclientexamples.service.ApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    private final String api_url;
    RestTemplate restTemplate;

    public ApiServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String api_url) {
        this.restTemplate = restTemplate;
        this.api_url = api_url;
    }

    @Override
    public List<User> getUsers(Integer limit) {
        String uri = UriComponentsBuilder.fromUriString(api_url)
                .queryParam("_limit", limit)
                .toUriString();

        User[] users = restTemplate.getForObject(uri, User[].class);
        return Arrays.asList(users);
    }

    @Override
    public Flux<User> getUsersReactive(Mono<Integer> limit) {
        return WebClient
                .create(api_url)
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("_limit",limit.block()).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(User.class);
    }
}
