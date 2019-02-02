package com.zenith.elixer;

import com.zenith.elixer.specification.ElixerPNG;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        ElixerResourceManager manager = new ElixerResourceManager("./res");

        manager.addNamespace("test").addResourceLocation("textures", "png");
        Path test = manager.getPath("test","base1bg.png");
        ElixerPNG png = new ElixerPNG(test);
        System.out.print("Done!");
    }
}
