package com.yh.springeasy.importtest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;

/**
 *  two {@link ImportSelector}
 */
@Import(ImportDemoTwoSelector.class)
@Configuration
public class ImportDemoTwoConfiguration {
}
