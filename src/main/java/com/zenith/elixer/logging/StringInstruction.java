package com.zenith.elixer.logging;

public class StringInstruction extends LoggerInstruction {
    String string;

    public StringInstruction(String string) {
        this.string = string;
    }

    @Override
    public String getData(ElixerLogger logger, LoggingLevel level, Object... objects) {
        return string;
    }
}
