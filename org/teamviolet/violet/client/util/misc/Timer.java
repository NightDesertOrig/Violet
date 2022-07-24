//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.misc;

public class Timer
{
    private long start;
    
    public Timer() {
        this.reset();
    }
    
    public boolean passed(final long ms) {
        return this.getTime() > ms;
    }
    
    public float getTime() {
        return (float)(System.currentTimeMillis() - this.start);
    }
    
    public void reset() {
        this.start = System.currentTimeMillis();
    }
}
