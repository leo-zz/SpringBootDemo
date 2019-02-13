package com.spring.bean;

import org.springframework.stereotype.Component;

@Component
public class Beauty {

    private Car ca;

    //此处省略了@Autowired
    public Beauty(Car ca) {
        this.ca = ca;
    }

    @Override
    public String toString() {
        return "Beauty{" +
                "ca=" + ca +
                '}'+super.toString();
    }
}
