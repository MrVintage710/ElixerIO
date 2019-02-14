package com.zenith.elixer.logging;

import java.util.HashMap;

public class ElixerLogger {

    private String namespace;
    private String currForm = "default";
    private HashMap<String, LoggerForm> forms = new HashMap<>();

    public ElixerLogger(String namespace) {
        this.namespace = namespace;

        forms.put("default", LoggerForm.DEFAULT);
    }

    public void info(Object... objects) {
        System.out.println(forms.get(currForm).getOut(this, LoggingLevel.INFO, objects));
    }

    public void warn(Object... objects) {
        System.out.println(forms.get(currForm).getOut(this, LoggingLevel.WARN, objects));
    }

    public void err(Object... objects) {
        System.err.println(forms.get(currForm).getOut(this, LoggingLevel.ERR, objects));
    }

    public void errEnd(Object... objects) {
        System.err.println(forms.get(currForm).getOut(this, LoggingLevel.ERR, objects));
        System.exit(1);
    }

    public String getNamespace() {
        return namespace;
    }
}
