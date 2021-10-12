package com.yh.springeasy.other;

import com.yh.springeasy.oher.PropertiesListener;
import org.junit.Test;

import java.util.Properties;


public class PropertiesListenerTest {

    @Test
    public void test() {
        this.addListener("test", "::", this::testPropertiesListener);
    }

    private void testPropertiesListener(Properties properties) {
        System.out.println("PropertiesListener test");
    }

    public PropertiesListener addListener(String application, String location, PropertiesListener listener) {
        return null;
    }
}
