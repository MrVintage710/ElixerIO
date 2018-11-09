package com.zenith.elixer.exception;

import com.zenith.elixer.ElixerResourceLocation;

public class ResourceMangerNullException extends Exception {

    public ResourceMangerNullException(String type) {
        super("Cannot create " + type + ": There is no ElixerResourceManger instance.");
    }
}
