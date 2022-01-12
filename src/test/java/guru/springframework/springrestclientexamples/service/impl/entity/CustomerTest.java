package guru.springframework.springrestclientexamples.service.impl.entity;

import java.io.Serializable;

public class CustomerTest implements Serializable {

    private String firstname;
    private String lastname;
    private String customer_url;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCustomer_url() {
        return customer_url;
    }

    public void setCustomer_url(String customer_url) {
        this.customer_url = customer_url;
    }

    @Override
    public String toString() {
        return "CustomerTest{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", customer_url='" + customer_url + '\'' +
                '}';
    }
}
