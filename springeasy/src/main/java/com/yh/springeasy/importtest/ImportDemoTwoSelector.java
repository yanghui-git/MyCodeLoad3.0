package com.yh.springeasy.importtest;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class ImportDemoTwoSelector implements ImportSelector {

    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                "com.yh.springeasy.importtest.ImportDemoTwo"
        };
    }
}
