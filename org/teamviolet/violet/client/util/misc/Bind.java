//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.misc;

import org.lwjgl.input.*;

public class Bind
{
    private int key;
    private Mode mode;
    private boolean lastDown;
    
    public Bind(final int key) {
        this.mode = Mode.PRESS;
        this.lastDown = false;
        this.key = key;
    }
    
    public boolean getOutput() {
        return (this.mode == Mode.PRESS) ? this.isPressed() : this.isReleased();
    }
    
    public boolean isPressed() {
        if (this.key == 0) {
            return false;
        }
        final boolean down = this.isDown();
        final boolean result = down && !this.lastDown;
        this.lastDown = down;
        return result;
    }
    
    public boolean isDown() {
        return this.key != 0 && Keyboard.isKeyDown(this.key);
    }
    
    public boolean isReleased() {
        if (this.key == 0) {
            return false;
        }
        final boolean down = this.isDown();
        final boolean result = !down && this.lastDown;
        this.lastDown = down;
        return result;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public void setKey(final int key) {
        this.key = key;
    }
    
    public Mode getMode() {
        return this.mode;
    }
    
    public void setMode(final Mode mode) {
        this.mode = mode;
    }
    
    public enum Mode
    {
        PRESS, 
        RELEASE;
    }
}
