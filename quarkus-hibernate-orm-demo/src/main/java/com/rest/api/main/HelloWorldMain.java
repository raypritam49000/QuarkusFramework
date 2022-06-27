package com.rest.api.main;

import io.quarkus.runtime.QuarkusApplication;

public class HelloWorldMain implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
        System.out.println("Hello Pritam Ray");
        return 0;
    }
}