package com.zenith.elixer;

public class ElixerIOUtil {

    public static String getExtention(String filename) {
        String ext = filename.split("\\.")[1];
        return ext;
    }

    public static int bytesToInt(byte one, byte two, byte three, byte four) {
       return ((one&0xff)<<24) + ((two&0xff)<<16) + ((three&0xff)<<8) + ((four&0xff));
    }

    public static short bytesToShort(byte one, byte two) {
        return (short)(((one&0xff)<<8) + ((two&0xff)));
    }
}
