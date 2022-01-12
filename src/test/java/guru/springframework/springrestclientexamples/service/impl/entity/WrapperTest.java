package guru.springframework.springrestclientexamples.service.impl.entity;

import java.util.List;

public class WrapperTest {
    private MetaTest meta;
    private List<CustomerTest> customers;

    public MetaTest getMeta() {
        return meta;
    }

    public void setMeta(MetaTest meta) {
        this.meta = meta;
    }

    public List<CustomerTest> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerTest> customers) {
        this.customers = customers;
    }
}
