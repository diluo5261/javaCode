package com.dilo;

import org.junit.Test;

import static org.junit.Assert.*;

public class HelloMavenTest {

    @Test
    public void add() {
        HelloMaven helloMaven = new HelloMaven();
        int add = helloMaven.add(5, 6);
        System.out.println("5+6="+add);
    }
}