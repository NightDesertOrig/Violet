//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event.events;

import org.teamviolet.violet.client.api.event.*;

public class UpdateLightmapEvent extends CancelableEvent
{
    public int[] lightmapColors;
    
    public UpdateLightmapEvent(final int[] lightmapColors) {
        this.lightmapColors = lightmapColors;
    }
}
