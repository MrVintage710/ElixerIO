package com.zenith.elixer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class ElixerResourceManager {

    private String resourceDir;
    private HashMap<String, ElixerResourceLocation> locationMap = new HashMap<>();
    private ArrayList<ElixerResourceManager> children = new ArrayList<>();

    public ElixerResourceManager(String resourceDir) {
        this.resourceDir = resourceDir;
    }

    private ElixerResourceLocation getResourceLocation(String extension) {
        ElixerResourceLocation value = null;

        if(isMapped(extension)) {
            value = locationMap.get(extension);
        } else {
            for(ElixerResourceManager manager: children) {
               value = manager.getResourceLocation(extension);
               if(value != null)
                   break;
            }
        }

        return value;
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

    private boolean isMapped(String extention) {
        return locationMap.containsKey(extention);
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

    public void addChild(ElixerResourceManager manager) {
        children.add(manager);
    }

    public Path getPathRaw(String filepath) {
        return Paths.get(resourceDir, filepath);
    }

    public Path getPath(String filename) {
        var ex = checkExtension(filename);
        return Paths.get(resourceDir, getResourceLocation(ex).getDir(), filename);
    }

    public File getFile(String filename) {
        return getPath(filename).toFile();
    }

    public File getFileRaw(String filepath) {
        return getPathRaw(filepath).toFile();
    }

    public FileInputStream getStream(String filename) throws FileNotFoundException {
        var extension = checkExtension(filename);

        FileInputStream stream = new FileInputStream(resourceDir + "/" + getResourceLocation(extension).getDir() + "/" + filename);
        return stream;
    }
}
