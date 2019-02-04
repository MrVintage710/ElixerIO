package com.zenith.elixer.logging.instruction;

public abstract class LoggerFunction extends LoggerInstruction {

    String[] args;

    protected LoggerFunction(String... args) {
        this.args = args;
    }
}
