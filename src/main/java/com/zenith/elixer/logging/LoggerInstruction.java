package com.zenith.elixer.logging;

public abstract class LoggerInstruction {
    public abstract String getData(ElixerLogger logger, LoggingLevel level, Object... objects);
}
