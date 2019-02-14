package com.zenith.elixer.resource;

import com.zenith.elixer.ElixerIOUtil;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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

    public Path getPathR(String filename) {
        Path result = Paths.get(resourceDir);
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL u = classLoader.getResource(filename);
            if(u != null) {
                result = Paths.get(u.toURI());
            } else {
                ElixerIOUtil.logger.err("File could not be found.");
                return null;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
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
