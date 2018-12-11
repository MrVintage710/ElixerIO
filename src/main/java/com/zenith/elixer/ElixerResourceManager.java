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
    private ArrayList<ElixerResourceNamespace> namespaces = new ArrayList<>();

    public ElixerResourceManager(String resourceDir) {
        this.resourceDir = resourceDir;
    }

    public ElixerResourceNamespace addNamespace(String name) {
        ElixerResourceNamespace namespace = getNamespace(name);
        if(namespace == null) {
            namespace = new ElixerResourceNamespace(this, name);
            namespaces.add(namespace);
        }

        return namespace;
    }

    public ElixerResourceNamespace getNamespace(String name) {
        for(ElixerResourceNamespace namespace: namespaces) {
            if(name == namespace.getName()) {
                return namespace;
            }
        }

        return null;
    }

    public Path getPath(String namespace, String filename) {
        return getNamespace(namespace).getPath(filename);
    }

    public Path getPath(String namespace, String dir, String filename) {
        return getNamespace(namespace).getPath(dir, filename);
    }

    public String getResourceDir() {
        return resourceDir;
    }
}
