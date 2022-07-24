//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event.events;

import net.minecraft.client.gui.*;

public class Render2DEvent
{
    private float partialTicks;
    private ScaledResolution scaledResolution;
    
    public Render2DEvent(final float partialTicks, final ScaledResolution scaledResolution) {
        this.partialTicks = partialTicks;
        this.scaledResolution = scaledResolution;
    }
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public void setPartialTicks(final float partialTicks) {
        this.partialTicks = partialTicks;
    }
    
    public ScaledResolution getScaledResolution() {
        return this.scaledResolution;
    }
    
    public void setScaledResolution(final ScaledResolution scaledResolution) {
        this.scaledResolution = scaledResolution;
    }
}
