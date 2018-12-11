package com.zenith.elixer.specification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class ElixerFileSpecification {

    private ElixerBuffer buffer;

    public ElixerFileSpecification(Path path) {
        try {
            this.buffer = new ElixerBuffer(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.encode(buffer);
    }

    protected abstract void encode(ElixerBuffer buffer);
}
