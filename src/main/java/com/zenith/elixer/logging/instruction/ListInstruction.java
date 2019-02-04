package com.zenith.elixer.logging.instruction;

import com.zenith.elixer.logging.ElixerLogger;
import com.zenith.elixer.logging.LoggingLevel;

public class ListInstruction extends LoggerFunction {

    public ListInstruction(String separator) {
        super(separator);
    }

    @Override
    public String getData(ElixerLogger logger, LoggingLevel level, Object... objects) {
        String result = "";
        for(Object object: objects) {
            result += (object.toString() + args[0]);
        }
        return result;
    }
}
