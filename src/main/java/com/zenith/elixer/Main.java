package com.zenith.elixer;

import com.zenith.elixer.exception.ResourceMangerNullException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ElixerResourceManager manager = ElixerResourceManager.createResourceManger("./res");

        try {
            manager.addResourceLocation(new ElixerResourceLocation("data", "txt"));
        } catch (ResourceMangerNullException e) {
            e.printStackTrace();
        }

        var path = manager.getPath("test.txt");

        var br = new BufferedReader(new FileReader(path.toFile()));

        String st;
        while ((st = br.readLine()) != null)
            System.out.println(st);
    }
}
