package com.zenith.elixer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class ElixerResourceManager {

    private static ElixerResourceManager instance;

    private String resourceDir;
    private HashMap<String, ElixerResourceLocation> locationMap = new HashMap<>();
    private ArrayList<ElixerResourceLocation> resourceLocations = new ArrayList<>();

    private ElixerResourceManager(String resourceDir) {
        this.resourceDir = resourceDir;
    }

    public static ElixerResourceManager createResourceManger(String resourceDir) {
        ElixerResourceManager.instance = new ElixerResourceManager(resourceDir);
        return ElixerResourceManager.instance;
    }

    public static boolean isInitialized() {
        if(ElixerResourceManager.instance == null)
            return false;

        return true;
    }

    private ElixerResourceLocation getResourceLocation(String extension) {
        return locationMap.get(extension);
    }

    private String checkExtension(String filename) {
        String extension = "";
        try {
            var proxy = filename.split("\\.");
            extension = proxy[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("File name '" + filename + "' must include file extension.");
        }

        return extension;
    }

    public void addResourceLocation(ElixerResourceLocation resourceLocation) {
        for(String extension: resourceLocation.getExtensionWhiteList()) {
            if(!locationMap.containsKey(extension)) {
                locationMap.put(extension, resourceLocation);
            } else {
                System.out.print("Did not add resource location to extension '" + extension + "' because it already has a value.");
            }
        }
    }

    public Path getPath(String filename) {
        var ex = checkExtension(filename);
        return Paths.get(resourceDir, getResourceLocation(ex).getDir(), filename);
    }

    public FileInputStream getStream(String filename) throws FileNotFoundException {
        var extension = checkExtension(filename);

        FileInputStream stream = new FileInputStream(resourceDir + "/" + getResourceLocation(extension).getDir() + "/" + filename);
        return stream;
    }
}
