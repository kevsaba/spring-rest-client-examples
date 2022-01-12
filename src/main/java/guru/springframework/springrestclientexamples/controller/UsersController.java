package guru.springframework.springrestclientexamples.controller;

import guru.springframework.springrestclientexamples.service.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;

@Controller
public class UsersController {

    ApiService apiService;

    public UsersController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @PostMapping("/usersNormal")
    public String getUsers(Model model, ServerWebExchange serverWebExchange) {
        MultiValueMap<String, String> formDataMap = serverWebExchange.getFormData().block(); //this is the reactive way of getting the form field data that was populated
        String limit1 = formDataMap.getFirst("limit");
        int limit = 1;
        if (limit1 == null || Integer.parseInt(limit1) < 1) {
            System.out.println("limit not allowed setting it to 1");
        } else {
            limit = Integer.parseInt(limit1);
        }
        model.addAttribute("users", apiService.getUsers(limit));
        return "users";
    }

    @PostMapping("/users")
    public String getUsersReactive(Model model, ServerWebExchange serverWebExchange) {
        model.addAttribute("users", apiService
                .getUsersReactive(serverWebExchange
                        .getFormData()
                        .map(data -> Integer.valueOf(data.getFirst("limit")))));
        return "users";
    }
}
