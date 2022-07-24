//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.value;

import java.util.function.*;
import org.teamviolet.violet.client.api.module.*;
import java.util.*;
import java.awt.*;
import org.teamviolet.violet.client.util.render.*;
import javax.annotation.*;

public class Value<T>
{
    private final String name;
    private final String description;
    private final Supplier<T> initialValue;
    private T value;
    private final float min;
    private final float max;
    private final int decimals;
    private final BiFunction<T, T, Boolean> action;
    private final Value<Boolean> parent;
    private final Module module;
    private final List<Value<?>> valueList;
    private boolean typing;
    private T safeValue;
    private String typedValue;
    private boolean rainbow;
    private boolean open;
    
    Value(final String name, final String description, final Supplier<T> initialValue, final float min, final float max, final int decimals, final BiFunction<T, T, Boolean> action, final Value<Boolean> parent, final Module module) {
        this.valueList = new ArrayList<Value<?>>();
        this.typing = false;
        this.rainbow = false;
        this.open = false;
        this.name = name;
        this.description = description;
        this.initialValue = initialValue;
        this.min = min;
        this.max = max;
        this.decimals = decimals;
        this.action = action;
        this.parent = parent;
        this.module = module;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public T getValue() {
        if (this.value == null) {
            this.value = this.initialValue.get();
        }
        if (this.value instanceof Color && this.rainbow) {
            return (T)ColorUtil.getRainbowA(ColorUtil.getSat((Color)this.value), ColorUtil.getBright((Color)this.value), ((Color)this.value).getAlpha());
        }
        return this.value;
    }
    
    public void setValue(final T value) {
        if (this.action != null && !this.action.apply(this.value, value)) {
            return;
        }
        this.value = value;
    }
    
    public float getMin() {
        return this.min;
    }
    
    public float getMax() {
        return this.max;
    }
    
    public int getDecimals() {
        return this.decimals;
    }
    
    @Nullable
    public Value<Boolean> getParent() {
        return this.parent;
    }
    
    public boolean hasParent() {
        return this.parent != null;
    }
    
    public Module getModule() {
        return this.module;
    }
    
    public List<Value<?>> getValueList() {
        return this.valueList;
    }
    
    public boolean isTyping() {
        return this.typing;
    }
    
    public void setTyping(final boolean typing) {
        this.typing = typing;
    }
    
    public boolean isOpen() {
        return this.open;
    }
    
    public T getSafeValue() {
        return this.safeValue;
    }
    
    public void setSafeValue(final T safeValue) {
        this.safeValue = safeValue;
    }
    
    public String getTypedValue() {
        return this.typedValue;
    }
    
    public void setTypedValue(final String typedValue) {
        this.typedValue = typedValue;
    }
    
    public void setOpen(final boolean open) {
        this.open = open;
    }
    
    public void setRainbow(final boolean rainbow) {
        this.rainbow = rainbow;
    }
    
    public boolean isRainbow() {
        return this.rainbow;
    }
}
