//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.exceptions;

import com.mojang.brigadier.*;

public class BuiltInExceptions implements BuiltInExceptionProvider
{
    private static final Dynamic2CommandExceptionType DOUBLE_TOO_SMALL;
    private static final Dynamic2CommandExceptionType DOUBLE_TOO_BIG;
    private static final Dynamic2CommandExceptionType FLOAT_TOO_SMALL;
    private static final Dynamic2CommandExceptionType FLOAT_TOO_BIG;
    private static final Dynamic2CommandExceptionType INTEGER_TOO_SMALL;
    private static final Dynamic2CommandExceptionType INTEGER_TOO_BIG;
    private static final Dynamic2CommandExceptionType LONG_TOO_SMALL;
    private static final Dynamic2CommandExceptionType LONG_TOO_BIG;
    private static final DynamicCommandExceptionType LITERAL_INCORRECT;
    private static final SimpleCommandExceptionType READER_EXPECTED_START_OF_QUOTE;
    private static final SimpleCommandExceptionType READER_EXPECTED_END_OF_QUOTE;
    private static final DynamicCommandExceptionType READER_INVALID_ESCAPE;
    private static final DynamicCommandExceptionType READER_INVALID_BOOL;
    private static final DynamicCommandExceptionType READER_INVALID_INT;
    private static final SimpleCommandExceptionType READER_EXPECTED_INT;
    private static final DynamicCommandExceptionType READER_INVALID_LONG;
    private static final SimpleCommandExceptionType READER_EXPECTED_LONG;
    private static final DynamicCommandExceptionType READER_INVALID_DOUBLE;
    private static final SimpleCommandExceptionType READER_EXPECTED_DOUBLE;
    private static final DynamicCommandExceptionType READER_INVALID_FLOAT;
    private static final SimpleCommandExceptionType READER_EXPECTED_FLOAT;
    private static final SimpleCommandExceptionType READER_EXPECTED_BOOL;
    private static final DynamicCommandExceptionType READER_EXPECTED_SYMBOL;
    private static final SimpleCommandExceptionType DISPATCHER_UNKNOWN_COMMAND;
    private static final SimpleCommandExceptionType DISPATCHER_UNKNOWN_ARGUMENT;
    private static final SimpleCommandExceptionType DISPATCHER_EXPECTED_ARGUMENT_SEPARATOR;
    private static final DynamicCommandExceptionType DISPATCHER_PARSE_EXCEPTION;
    
    public Dynamic2CommandExceptionType doubleTooLow() {
        return BuiltInExceptions.DOUBLE_TOO_SMALL;
    }
    
    public Dynamic2CommandExceptionType doubleTooHigh() {
        return BuiltInExceptions.DOUBLE_TOO_BIG;
    }
    
    public Dynamic2CommandExceptionType floatTooLow() {
        return BuiltInExceptions.FLOAT_TOO_SMALL;
    }
    
    public Dynamic2CommandExceptionType floatTooHigh() {
        return BuiltInExceptions.FLOAT_TOO_BIG;
    }
    
    public Dynamic2CommandExceptionType integerTooLow() {
        return BuiltInExceptions.INTEGER_TOO_SMALL;
    }
    
    public Dynamic2CommandExceptionType integerTooHigh() {
        return BuiltInExceptions.INTEGER_TOO_BIG;
    }
    
    public Dynamic2CommandExceptionType longTooLow() {
        return BuiltInExceptions.LONG_TOO_SMALL;
    }
    
    public Dynamic2CommandExceptionType longTooHigh() {
        return BuiltInExceptions.LONG_TOO_BIG;
    }
    
    public DynamicCommandExceptionType literalIncorrect() {
        return BuiltInExceptions.LITERAL_INCORRECT;
    }
    
    public SimpleCommandExceptionType readerExpectedStartOfQuote() {
        return BuiltInExceptions.READER_EXPECTED_START_OF_QUOTE;
    }
    
    public SimpleCommandExceptionType readerExpectedEndOfQuote() {
        return BuiltInExceptions.READER_EXPECTED_END_OF_QUOTE;
    }
    
    public DynamicCommandExceptionType readerInvalidEscape() {
        return BuiltInExceptions.READER_INVALID_ESCAPE;
    }
    
    public DynamicCommandExceptionType readerInvalidBool() {
        return BuiltInExceptions.READER_INVALID_BOOL;
    }
    
    public DynamicCommandExceptionType readerInvalidInt() {
        return BuiltInExceptions.READER_INVALID_INT;
    }
    
    public SimpleCommandExceptionType readerExpectedInt() {
        return BuiltInExceptions.READER_EXPECTED_INT;
    }
    
    public DynamicCommandExceptionType readerInvalidLong() {
        return BuiltInExceptions.READER_INVALID_LONG;
    }
    
    public SimpleCommandExceptionType readerExpectedLong() {
        return BuiltInExceptions.READER_EXPECTED_LONG;
    }
    
    public DynamicCommandExceptionType readerInvalidDouble() {
        return BuiltInExceptions.READER_INVALID_DOUBLE;
    }
    
    public SimpleCommandExceptionType readerExpectedDouble() {
        return BuiltInExceptions.READER_EXPECTED_DOUBLE;
    }
    
    public DynamicCommandExceptionType readerInvalidFloat() {
        return BuiltInExceptions.READER_INVALID_FLOAT;
    }
    
    public SimpleCommandExceptionType readerExpectedFloat() {
        return BuiltInExceptions.READER_EXPECTED_FLOAT;
    }
    
    public SimpleCommandExceptionType readerExpectedBool() {
        return BuiltInExceptions.READER_EXPECTED_BOOL;
    }
    
    public DynamicCommandExceptionType readerExpectedSymbol() {
        return BuiltInExceptions.READER_EXPECTED_SYMBOL;
    }
    
    public SimpleCommandExceptionType dispatcherUnknownCommand() {
        return BuiltInExceptions.DISPATCHER_UNKNOWN_COMMAND;
    }
    
    public SimpleCommandExceptionType dispatcherUnknownArgument() {
        return BuiltInExceptions.DISPATCHER_UNKNOWN_ARGUMENT;
    }
    
    public SimpleCommandExceptionType dispatcherExpectedArgumentSeparator() {
        return BuiltInExceptions.DISPATCHER_EXPECTED_ARGUMENT_SEPARATOR;
    }
    
    public DynamicCommandExceptionType dispatcherParseException() {
        return BuiltInExceptions.DISPATCHER_PARSE_EXCEPTION;
    }
    
    static {
        final LiteralMessage literalMessage;
        DOUBLE_TOO_SMALL = new Dynamic2CommandExceptionType((found, min) -> {
            new LiteralMessage("Double must not be less than " + min + ", found " + found);
            return literalMessage;
        });
        final LiteralMessage literalMessage2;
        DOUBLE_TOO_BIG = new Dynamic2CommandExceptionType((found, max) -> {
            new LiteralMessage("Double must not be more than " + max + ", found " + found);
            return literalMessage2;
        });
        final LiteralMessage literalMessage3;
        FLOAT_TOO_SMALL = new Dynamic2CommandExceptionType((found, min) -> {
            new LiteralMessage("Float must not be less than " + min + ", found " + found);
            return literalMessage3;
        });
        final LiteralMessage literalMessage4;
        FLOAT_TOO_BIG = new Dynamic2CommandExceptionType((found, max) -> {
            new LiteralMessage("Float must not be more than " + max + ", found " + found);
            return literalMessage4;
        });
        final LiteralMessage literalMessage5;
        INTEGER_TOO_SMALL = new Dynamic2CommandExceptionType((found, min) -> {
            new LiteralMessage("Integer must not be less than " + min + ", found " + found);
            return literalMessage5;
        });
        final LiteralMessage literalMessage6;
        INTEGER_TOO_BIG = new Dynamic2CommandExceptionType((found, max) -> {
            new LiteralMessage("Integer must not be more than " + max + ", found " + found);
            return literalMessage6;
        });
        final LiteralMessage literalMessage7;
        LONG_TOO_SMALL = new Dynamic2CommandExceptionType((found, min) -> {
            new LiteralMessage("Long must not be less than " + min + ", found " + found);
            return literalMessage7;
        });
        final LiteralMessage literalMessage8;
        LONG_TOO_BIG = new Dynamic2CommandExceptionType((found, max) -> {
            new LiteralMessage("Long must not be more than " + max + ", found " + found);
            return literalMessage8;
        });
        final LiteralMessage literalMessage9;
        LITERAL_INCORRECT = new DynamicCommandExceptionType(expected -> {
            new LiteralMessage("Expected literal " + expected);
            return literalMessage9;
        });
        READER_EXPECTED_START_OF_QUOTE = new SimpleCommandExceptionType(new LiteralMessage("Expected quote to start a string"));
        READER_EXPECTED_END_OF_QUOTE = new SimpleCommandExceptionType(new LiteralMessage("Unclosed quoted string"));
        final LiteralMessage literalMessage10;
        READER_INVALID_ESCAPE = new DynamicCommandExceptionType(character -> {
            new LiteralMessage("Invalid escape sequence '" + character + "' in quoted string");
            return literalMessage10;
        });
        final LiteralMessage literalMessage11;
        READER_INVALID_BOOL = new DynamicCommandExceptionType(value -> {
            new LiteralMessage("Invalid bool, expected true or false but found '" + value + "'");
            return literalMessage11;
        });
        final LiteralMessage literalMessage12;
        READER_INVALID_INT = new DynamicCommandExceptionType(value -> {
            new LiteralMessage("Invalid integer '" + value + "'");
            return literalMessage12;
        });
        READER_EXPECTED_INT = new SimpleCommandExceptionType(new LiteralMessage("Expected integer"));
        final LiteralMessage literalMessage13;
        READER_INVALID_LONG = new DynamicCommandExceptionType(value -> {
            new LiteralMessage("Invalid long '" + value + "'");
            return literalMessage13;
        });
        READER_EXPECTED_LONG = new SimpleCommandExceptionType(new LiteralMessage("Expected long"));
        final LiteralMessage literalMessage14;
        READER_INVALID_DOUBLE = new DynamicCommandExceptionType(value -> {
            new LiteralMessage("Invalid double '" + value + "'");
            return literalMessage14;
        });
        READER_EXPECTED_DOUBLE = new SimpleCommandExceptionType(new LiteralMessage("Expected double"));
        final LiteralMessage literalMessage15;
        READER_INVALID_FLOAT = new DynamicCommandExceptionType(value -> {
            new LiteralMessage("Invalid float '" + value + "'");
            return literalMessage15;
        });
        READER_EXPECTED_FLOAT = new SimpleCommandExceptionType(new LiteralMessage("Expected float"));
        READER_EXPECTED_BOOL = new SimpleCommandExceptionType(new LiteralMessage("Expected bool"));
        final LiteralMessage literalMessage16;
        READER_EXPECTED_SYMBOL = new DynamicCommandExceptionType(symbol -> {
            new LiteralMessage("Expected '" + symbol + "'");
            return literalMessage16;
        });
        DISPATCHER_UNKNOWN_COMMAND = new SimpleCommandExceptionType(new LiteralMessage("Unknown command"));
        DISPATCHER_UNKNOWN_ARGUMENT = new SimpleCommandExceptionType(new LiteralMessage("Incorrect argument for command"));
        DISPATCHER_EXPECTED_ARGUMENT_SEPARATOR = new SimpleCommandExceptionType(new LiteralMessage("Expected whitespace to end one argument, but found trailing data"));
        final LiteralMessage literalMessage17;
        DISPATCHER_PARSE_EXCEPTION = new DynamicCommandExceptionType(message -> {
            new LiteralMessage("Could not parse command: " + message);
            return literalMessage17;
        });
    }
}
