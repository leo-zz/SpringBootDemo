package com.spring.importAnnotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MySeletor implements ImportSelector {
    @Override
    /**
     *  importingClassMetadata  @import所在配置类的注解信息
     *
     *  return 返回需要加载的类的全限定名。
     */
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.spring.color.Blue","com.spring.color.Yellow"};
    }
}
