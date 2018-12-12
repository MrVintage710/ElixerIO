package com.zenith.elixer.specification;

import com.zenith.elixer.ElixerIOUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public class ElixerBuffer {

    private FileInputStream in;

    public ElixerBuffer(Path path) throws FileNotFoundException {
        this.in = new FileInputStream(path.toFile());
    }

    public byte read() throws IOException {
        byte b = (byte)in.read();
        return b;
    }

    public byte[] read(int size) throws IOException {
        byte[] bytes = new byte[size];
        for(int i = 0; i < size; i++) {
            bytes[i] = read();
        }
        return bytes;
    }

    public int readInt() throws IOException {
        byte[] bytes = read(4);
        return ElixerIOUtil.bytesToInt(bytes[0], bytes[1], bytes[2], bytes[3]);
    }

    public short readShort() throws IOException {
        byte[] bytes = read(2);
        return ElixerIOUtil.bytesToShort(bytes[0], bytes[1]);
    }

    public char readChar() throws IOException {
        return (char) read();
    }

    public String readString(int length) throws IOException {
        String value = "";
        for(int i = 0; i < length; i++) {
            value += readChar();
        }
        return value;
    }
}
