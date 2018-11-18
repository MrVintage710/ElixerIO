package com.zenith.elixer;

public class ElixerResourceLocation {

    private String dir;
    private String[] extensionWhiteList;

    public ElixerResourceLocation(String dir, String... extensionWhiteList) {
        this.dir = dir;
        this.extensionWhiteList = extensionWhiteList;
    }

    public String getDir() {
        return dir;
    }

    public String[] getExtensionWhiteList() {
        return extensionWhiteList;
    }
}
