//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event;

public class CancelableEvent
{
    private boolean cancelled;
    
    protected CancelableEvent() {
        this.cancelled = false;
    }
    
    public boolean isCanceled() {
        return this.cancelled;
    }
    
    public void cancel() {
        this.cancelled = true;
    }
}
