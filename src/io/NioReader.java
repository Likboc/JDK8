package io;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class NioReader {
    protected String a = "1";
    public static void main(String[] args) throws IOException {

    }
    public String getA(){
        return this.a;
    }
}
