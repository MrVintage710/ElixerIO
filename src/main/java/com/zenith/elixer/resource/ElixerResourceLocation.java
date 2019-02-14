package com.zenith.elixer.resource;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ElixerResourceLocation {

    private String dir;
    private String[] extensionWhiteList;
    private ElixerResourceNamespace namespace;
    private ElixerResourceManager manager;

    protected ElixerResourceLocation(ElixerResourceNamespace namespace, String dir, String... extensionWhiteList) {
        this.dir = dir;
        this.namespace = namespace;
        this.manager = namespace.getManager();
        this.extensionWhiteList = extensionWhiteList;
    }

    public Path getPath(String filename) {
        return Paths.get(manager.getResourceDir(), namespace.getName(), dir, filename);
    }

    public String getDir() {
        return dir;
    }

    public String[] getExtensionWhiteList() {
        return extensionWhiteList;
    }

    public ElixerResourceNamespace getNamespace() {
        return namespace;
    }

    public ElixerResourceManager getManager() {
        return manager;
    }
}
