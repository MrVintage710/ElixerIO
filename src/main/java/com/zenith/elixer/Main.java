package com.zenith.elixer;

import com.zenith.elixer.logging.ElixerLogger;
import com.zenith.elixer.logging.LoggerForm;
import com.zenith.elixer.specification.ElixerPNG;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        ElixerResourceManager manager = new ElixerResourceManager("./res");
        manager.addNamespace("test").addResourceLocation("textures", "png");

        ElixerLogger logger = new ElixerLogger("ElixerIO");
        logger.info();
    }
}
