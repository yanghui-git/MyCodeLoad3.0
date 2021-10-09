package com.yh.springeasy.condition;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConditionalOnClassDemo {

    String name;

    int age;
}
