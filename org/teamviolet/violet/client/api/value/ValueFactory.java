//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.value;

import java.util.function.*;
import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.util.misc.*;
import java.awt.*;

public class ValueFactory<T>
{
    private String name;
    private String description;
    private T value;
    private float min;
    private float max;
    private int decimals;
    private BiFunction<T, T, Boolean> action;
    
    public ValueFactory() {
        this.min = 0.0f;
        this.max = 0.0f;
        this.decimals = 0;
        this.action = null;
    }
    
    public ValueFactory<T> withName(final String name) {
        this.name = name;
        return this;
    }
    
    public ValueFactory<T> withDesc(final String description) {
        this.description = description;
        return this;
    }
    
    public ValueFactory<T> withVal(final T value) {
        this.value = value;
        return this;
    }
    
    public ValueFactory<T> withBounds(final float min, final float max, final int decimals) {
        this.min = min;
        this.max = max;
        this.decimals = decimals;
        return this;
    }
    
    public ValueFactory<T> withAction(final BiFunction<T, T, Boolean> action) {
        this.action = action;
        return this;
    }
    
    public Value<T> build(final Module module) {
        return this.build(null, module);
    }
    
    public Value<T> build(final Value<Boolean> parent) {
        return this.build(parent, parent.getModule());
    }
    
    private Value<T> build(final Value<Boolean> parent, final Module module) {
        final Value<T> val = (Value<T>)new Value(this.name, this.description, () -> this.value, this.min, this.max, this.decimals, (BiFunction)this.action, (Value)parent, module);
        if (parent != null) {
            parent.getValueList().add(val);
        }
        else {
            module.getValueList().add(val);
        }
        return val;
    }
    
    private static <T> ValueFactory<T> newValue() {
        return new ValueFactory<T>();
    }
    
    public static ValueFactory<Bind> bindValue() {
        return newValue();
    }
    
    public static ValueFactory<Boolean> booleanValue() {
        return newValue();
    }
    
    public static ValueFactory<Color> colorValue() {
        return newValue();
    }
    
    public static ValueFactory<Double> doubleValue() {
        return newValue();
    }
    
    public static ValueFactory<Float> floatValue() {
        return newValue();
    }
    
    public static ValueFactory<Integer> intValue() {
        return newValue();
    }
    
    public static ValueFactory<String> stringValue() {
        return newValue();
    }
}
