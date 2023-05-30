package com.demo;

import com.spring.ToyContainer;

import java.util.HashMap;
import java.util.Map;

public class TorySpringApp {
    public static void main(String[] args) throws Exception {
        ToyContainer ctx = new ToyContainer(new String[]{"com.demo.BasicController", "com.demo.MovieServiceImpl"});
        ctx.run();
    }
}
