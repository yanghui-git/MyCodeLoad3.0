package com.yh.springeasy.springscanner;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import(MyBeanRegistry.class)
@Component
public class TestScannerImport {
}
