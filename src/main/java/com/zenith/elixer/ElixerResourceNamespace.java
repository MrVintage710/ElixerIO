package com.zenith.elixer;

import java.nio.file.Path;
import java.util.ArrayList;

public class ElixerResourceNamespace {

    private String name;
    private ElixerResourceManager manager;
    private ArrayList<ElixerResourceLocation> resourceLocations = new ArrayList<>();

    protected ElixerResourceNamespace(ElixerResourceManager manager, String name) {
        this.name = name;
        this.manager = manager;
    }

    private ElixerResourceLocation getResourceLocation(String filename) {
        String ext = ElixerIOUtil.getExtention(filename);
        for(ElixerResourceLocation location: resourceLocations) {
            for(String e: location.getExtensionWhiteList()) {
                if(e.equals(ext)) {
                    return location;
                }
            }
        }

        return null;
    }

    private ElixerResourceLocation getResourceLocation(String dir, String filename) {
        String ext = ElixerIOUtil.getExtention(filename);
        for(ElixerResourceLocation location: resourceLocations) {
            for(String e: location.getExtensionWhiteList()) {
                if(e.equals(ext) && dir.equals(location.getDir())) {
                    return location;
                }
            }
        }

        return null;
    }

    public ElixerResourceNamespace addResourceLocation(String dir, String... extensions) {
        resourceLocations.add(new ElixerResourceLocation(this, dir, extensions));
        return this;
    }

    public Path getPath(String filename) {
        return getResourceLocation(filename).getPath(filename);
    }

    public Path getPath(String dir, String filename) {
        return getResourceLocation(dir, filename).getPath(filename);
    }

    public String getName() {
        return name;
    }

    public ElixerResourceManager getManager() {
        return manager;
    }
}
