package com.leo;

import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class AnnotationTest {

    @Test
    public void testIfContainAnnotation() throws NoSuchMethodException {
        Service annotation = AnnotationUtils.findAnnotation(AnnotationTest.class, Service.class);
        System.out.println("AnnotationTest.class "+(annotation==null?"未找到":"找到了")+"注解 @Service");

        Test annotation1 = AnnotationUtils.findAnnotation(AnnotationTest.class, Test.class);
        System.out.println("AnnotationTest.class "+(annotation1==null?"未找到":"找到了")+"注解 @Test");

        Component annotation2 = AnnotationUtils.findAnnotation(AnnotationTest.class, Component.class);
        System.out.println("AnnotationTest.class "+(annotation2==null?"未找到":"找到了")+"注解 @Component");

        Method testIfContainAnnotation = AnnotationTest.class.getMethod("testIfContainAnnotation", null);
        Test annotation3 = AnnotationUtils.findAnnotation(testIfContainAnnotation, Test.class);
        System.out.println("方法 testIfContainAnnotation "+(annotation3==null?"未找到":"找到了")+"注解 @Test");
    }
}
