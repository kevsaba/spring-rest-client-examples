package guru.springframework.springrestclientexamples.service.impl;

import guru.springframework.springrestclientexamples.service.impl.entity.CustomerTest;
import guru.springframework.springrestclientexamples.service.impl.entity.WrapperTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateTest {
    private static String URL = "https://api.predic8.de:443/shop";

    @Test
    public void getCustomersRestTemplate() {
        String url = URL.concat("/customers/");
        RestTemplate rt = new RestTemplate();
        WrapperTest wrapper = rt.getForObject(url, WrapperTest.class);

        System.out.println("Response");
        System.out.println(wrapper.getCustomers().get(0).getFirstname());
    }

//    @Test
//    public void createCustomersRestTemplate() {
//        String url = URL.concat("/customers/");
//        RestTemplate rt = new RestTemplate();
//        CustomerTest ct = new CustomerTest();
//        ct.setFirstname("kev");
//        ct.setLastname("saba");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<CustomerTest> request = new HttpEntity<>(ct, headers);
//        ResponseEntity<String> returned = rt.postForEntity(url, request, String.class);
//
//        System.out.println("Response");
//        System.out.println(returned);
//    }

    @Test
    public void createCustomersRestTemplate() {
        String url = URL.concat("/customers/");
        RestTemplate rt = new RestTemplate();

        Map<String, String> map = new HashMap<>();
        map.put("firstname", "kev");
        map.put("lastname", "saba");

        CustomerTest returned = rt.postForObject(url, map, CustomerTest.class);

        System.out.println("Response");
        System.out.println(returned.toString());
    }


    @Test
    public void putCustomersRestTemplate() {
        String url = URL.concat("/customers/");
        RestTemplate rt = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("firstname", "kev");
        map.put("lastname", "saba");

        CustomerTest returned = rt.postForObject(url, map, CustomerTest.class);
        String id = returned.getCustomer_url().split("/")[3];

        System.out.println("Response");
        System.out.println(returned.toString());

        System.out.println("now updating it");

        map.put("firstname", "kev 2");
        map.put("lastname", "saba 2");

        rt.put(url + id, map);
        CustomerTest returned2 = rt.getForObject(url + id, CustomerTest.class);

        System.out.println("Updated Response");
        System.out.println(returned2.toString());
    }

}
