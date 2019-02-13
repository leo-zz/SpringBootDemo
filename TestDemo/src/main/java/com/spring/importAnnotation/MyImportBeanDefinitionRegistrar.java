package com.spring.importAnnotation;

import com.spring.color.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    /**
     * @importingClassMetadata @import所在配置类的注解信息
     * @registry  容器bean定义的注册器
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //需要使用全限定名
        boolean b1 = registry.containsBeanDefinition("com.spring.color.Blue");
        boolean b = registry.containsBeanDefinition("com.spring.color.Red");

        if(b&b1){
            RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
            registry.registerBeanDefinition("rainBow",beanDefinition);
        }
    }
}
