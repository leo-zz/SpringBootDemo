package com.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(value = "com.spring",includeFilters = {
        @ComponentScan.Filter(type=FilterType.CUSTOM,classes = MyFilter.class)
})
public class MainConfig {


}
