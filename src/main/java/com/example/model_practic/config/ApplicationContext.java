package com.example.model_practic.config;

import java.util.Properties;

public class ApplicationContext {
    private static final Properties PROPERTIES = Config.getProperties();

    public static Properties getPROPERTIES() {
        return PROPERTIES;
    }
}
