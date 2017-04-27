package com.matchmove.mmpay.test;

import org.junit.Test;

import java.io.File;

/**
 * Created by Administrator on 2017/4/25.
 */
public class FileTest {

    @Test
    public void testRead() {
        File file = new File("classpath:test.properties");
        System.out.println(file.getName());

    }
}
