//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.suggestion;

import java.util.*;
import java.util.concurrent.*;
import com.mojang.brigadier.context.*;
import com.mojang.brigadier.*;

public class SuggestionsBuilder
{
    private final String input;
    private final String inputLowerCase;
    private final int start;
    private final String remaining;
    private final String remainingLowerCase;
    private final List<Suggestion> result;
    
    public SuggestionsBuilder(final String input, final String inputLowerCase, final int start) {
        this.result = new ArrayList<Suggestion>();
        this.input = input;
        this.inputLowerCase = inputLowerCase;
        this.start = start;
        this.remaining = input.substring(start);
        this.remainingLowerCase = inputLowerCase.substring(start);
    }
    
    public SuggestionsBuilder(final String input, final int start) {
        this(input, input.toLowerCase(Locale.ROOT), start);
    }
    
    public String getInput() {
        return this.input;
    }
    
    public int getStart() {
        return this.start;
    }
    
    public String getRemaining() {
        return this.remaining;
    }
    
    public String getRemainingLowerCase() {
        return this.remainingLowerCase;
    }
    
    public Suggestions build() {
        return Suggestions.create(this.input, (Collection)this.result);
    }
    
    public CompletableFuture<Suggestions> buildFuture() {
        return CompletableFuture.completedFuture(this.build());
    }
    
    public SuggestionsBuilder suggest(final String text) {
        if (text.equals(this.remaining)) {
            return this;
        }
        this.result.add(new Suggestion(StringRange.between(this.start, this.input.length()), text));
        return this;
    }
    
    public SuggestionsBuilder suggest(final String text, final Message tooltip) {
        if (text.equals(this.remaining)) {
            return this;
        }
        this.result.add(new Suggestion(StringRange.between(this.start, this.input.length()), text, tooltip));
        return this;
    }
    
    public SuggestionsBuilder suggest(final int value) {
        this.result.add((Suggestion)new IntegerSuggestion(StringRange.between(this.start, this.input.length()), value));
        return this;
    }
    
    public SuggestionsBuilder suggest(final int value, final Message tooltip) {
        this.result.add((Suggestion)new IntegerSuggestion(StringRange.between(this.start, this.input.length()), value, tooltip));
        return this;
    }
    
    public SuggestionsBuilder add(final SuggestionsBuilder other) {
        this.result.addAll(other.result);
        return this;
    }
    
    public SuggestionsBuilder createOffset(final int start) {
        return new SuggestionsBuilder(this.input, this.inputLowerCase, start);
    }
    
    public SuggestionsBuilder restart() {
        return this.createOffset(this.start);
    }
}
