package com.spring.config;

import com.spring.MyFilter;
import com.spring.color.Red;
import com.spring.importAnnotation.MyImportBeanDefinitionRegistrar;
import com.spring.importAnnotation.MySeletor;
import org.springframework.context.annotation.*;
//方式1：
//@PropertySource({
//        "a.yaml",
//        "b.yaml"}
//        )
//方式2：
//@PropertySource("a.yaml")
//@PropertySource("b.yaml")
//方式3：
@PropertySources(value={
        @PropertySource("a.yaml"),
        @PropertySource("b.yaml")}
        )
@Configuration
@ComponentScan(value = "com.spring",includeFilters = {
        @ComponentScan.Filter(type=FilterType.CUSTOM,classes = MyFilter.class)
},useDefaultFilters = false)
@Import({Red.class, MySeletor.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig {


}
