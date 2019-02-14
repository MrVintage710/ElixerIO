package com.zenith.elixer;

import com.zenith.elixer.logging.ElixerLogger;
import com.zenith.elixer.resource.ElixerResourceManager;
import com.zenith.elixer.resource.ElixerResourceNamespace;
import com.zenith.elixer.specification.ElixerBuffer;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ElixerResourceManager manager = new ElixerResourceManager("./res");
        ElixerResourceNamespace imageNamespace =  manager.addNamespace("test").addResourceLocation("textures", "png");

        ElixerBuffer buffer = new ElixerBuffer(manager.getPathR("base4bg.png"));
        System.out.println(buffer.readAllString());
    }
}
