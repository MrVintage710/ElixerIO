package com.zenith.elixer.logging.instruction;

import com.zenith.elixer.logging.ElixerLogger;
import com.zenith.elixer.logging.LoggingLevel;

public abstract class LoggerInstruction {
    public abstract String getData(ElixerLogger logger, LoggingLevel level, Object... objects);
}
