//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.exceptions;

import com.mojang.brigadier.*;

public class CommandSyntaxException extends Exception
{
    public static final int CONTEXT_AMOUNT = 10;
    public static boolean ENABLE_COMMAND_STACK_TRACES;
    public static BuiltInExceptionProvider BUILT_IN_EXCEPTIONS;
    private final CommandExceptionType type;
    private final Message message;
    private final String input;
    private final int cursor;
    
    public CommandSyntaxException(final CommandExceptionType type, final Message message) {
        super(message.getString(), null, CommandSyntaxException.ENABLE_COMMAND_STACK_TRACES, CommandSyntaxException.ENABLE_COMMAND_STACK_TRACES);
        this.type = type;
        this.message = message;
        this.input = null;
        this.cursor = -1;
    }
    
    public CommandSyntaxException(final CommandExceptionType type, final Message message, final String input, final int cursor) {
        super(message.getString(), null, CommandSyntaxException.ENABLE_COMMAND_STACK_TRACES, CommandSyntaxException.ENABLE_COMMAND_STACK_TRACES);
        this.type = type;
        this.message = message;
        this.input = input;
        this.cursor = cursor;
    }
    
    @Override
    public String getMessage() {
        String message = this.message.getString();
        final String context = this.getContext();
        if (context != null) {
            message = message + " at position " + this.cursor + ": " + context;
        }
        return message;
    }
    
    public Message getRawMessage() {
        return this.message;
    }
    
    public String getContext() {
        if (this.input == null || this.cursor < 0) {
            return null;
        }
        final StringBuilder builder = new StringBuilder();
        final int cursor = Math.min(this.input.length(), this.cursor);
        if (cursor > 10) {
            builder.append("...");
        }
        builder.append(this.input.substring(Math.max(0, cursor - 10), cursor));
        builder.append("<--[HERE]");
        return builder.toString();
    }
    
    public CommandExceptionType getType() {
        return this.type;
    }
    
    public String getInput() {
        return this.input;
    }
    
    public int getCursor() {
        return this.cursor;
    }
    
    static {
        CommandSyntaxException.ENABLE_COMMAND_STACK_TRACES = true;
        CommandSyntaxException.BUILT_IN_EXCEPTIONS = (BuiltInExceptionProvider)new BuiltInExceptions();
    }
}
