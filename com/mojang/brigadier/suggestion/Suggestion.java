//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.mojang.brigadier.suggestion;

import com.mojang.brigadier.context.*;
import com.mojang.brigadier.*;
import java.util.*;

public class Suggestion implements Comparable<Suggestion>
{
    private final StringRange range;
    private final String text;
    private final Message tooltip;
    
    public Suggestion(final StringRange range, final String text) {
        this(range, text, null);
    }
    
    public Suggestion(final StringRange range, final String text, final Message tooltip) {
        this.range = range;
        this.text = text;
        this.tooltip = tooltip;
    }
    
    public StringRange getRange() {
        return this.range;
    }
    
    public String getText() {
        return this.text;
    }
    
    public Message getTooltip() {
        return this.tooltip;
    }
    
    public String apply(final String input) {
        if (this.range.getStart() == 0 && this.range.getEnd() == input.length()) {
            return this.text;
        }
        final StringBuilder result = new StringBuilder();
        if (this.range.getStart() > 0) {
            result.append(input.substring(0, this.range.getStart()));
        }
        result.append(this.text);
        if (this.range.getEnd() < input.length()) {
            result.append(input.substring(this.range.getEnd()));
        }
        return result.toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Suggestion)) {
            return false;
        }
        final Suggestion that = (Suggestion)o;
        return Objects.equals(this.range, that.range) && Objects.equals(this.text, that.text) && Objects.equals(this.tooltip, that.tooltip);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.range, this.text, this.tooltip);
    }
    
    @Override
    public String toString() {
        return "Suggestion{range=" + this.range + ", text='" + this.text + '\'' + ", tooltip='" + this.tooltip + '\'' + '}';
    }
    
    @Override
    public int compareTo(final Suggestion o) {
        return this.text.compareTo(o.text);
    }
    
    public int compareToIgnoreCase(final Suggestion b) {
        return this.text.compareToIgnoreCase(b.text);
    }
    
    public Suggestion expand(final String command, final StringRange range) {
        if (range.equals((Object)this.range)) {
            return this;
        }
        final StringBuilder result = new StringBuilder();
        if (range.getStart() < this.range.getStart()) {
            result.append(command.substring(range.getStart(), this.range.getStart()));
        }
        result.append(this.text);
        if (range.getEnd() > this.range.getEnd()) {
            result.append(command.substring(this.range.getEnd(), range.getEnd()));
        }
        return new Suggestion(range, result.toString(), this.tooltip);
    }
}
