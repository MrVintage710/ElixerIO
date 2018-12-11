package com.zenith.elixer;

public class ElixerIOUtil {

    public static String getExtention(String filename) {
        String ext = filename.split("\\.")[1];
        return ext;
    }
}
