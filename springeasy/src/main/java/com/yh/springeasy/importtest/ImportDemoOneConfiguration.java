package com.yh.springeasy.importtest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *  one {@link Configuration},
 */
@Import(ImportDemoOne.class)
@Configuration
public class ImportDemoOneConfiguration {
}
