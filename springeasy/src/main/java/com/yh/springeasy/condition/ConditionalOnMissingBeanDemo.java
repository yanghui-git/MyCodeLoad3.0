package com.yh.springeasy.condition;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConditionalOnMissingBeanDemo {

    String name;

    int age;
}
