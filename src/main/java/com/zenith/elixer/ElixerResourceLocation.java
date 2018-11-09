package com.zenith.elixer;

import com.zenith.elixer.exception.ResourceMangerNullException;

public class ElixerResourceLocation {

    private String dir;
    private String[] extensionWhiteList;

    public ElixerResourceLocation(String dir, String... extensionWhiteList) throws ResourceMangerNullException {
        if(ElixerResourceManager.isInitialized()) {
            this.dir = dir;
            this.extensionWhiteList = extensionWhiteList;
        } else {
            throw new ResourceMangerNullException("resource location");
        }
    }

    public String getDir() {
        return dir;
    }

    public String[] getExtensionWhiteList() {
        return extensionWhiteList;
    }
}
