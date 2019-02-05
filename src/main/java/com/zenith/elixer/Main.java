package com.zenith.elixer;

import com.zenith.elixer.logging.ElixerLogger;

public class Main {

    public static void main(String[] args) {
        ElixerResourceManager manager = new ElixerResourceManager("./res");
        manager.addNamespace("test").addResourceLocation("textures", "png");

        ElixerLogger logger = new ElixerLogger("ElixerIO");
        logger.info("Test 1", "Test 2");
        logger.warn("This is a test warning.");
    }
}
