//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render;

public class ColorController
{
    private final int id;
    public float currentBrightness;
    public boolean decreasing;
    
    public ColorController(final int id) {
        this.currentBrightness = 0.4f;
        this.decreasing = false;
        this.id = id;
    }
    
    public void updateColor(final double speed) {
        final float factor = (float)(speed * 5.0E-4);
        if (!this.decreasing) {
            if (this.currentBrightness + factor <= 1.0f) {
                this.currentBrightness += factor;
            }
            else {
                final double diff = this.currentBrightness + factor - 1.0f;
                this.currentBrightness = (float)(1.0 - diff);
                this.decreasing = true;
            }
        }
        else if (this.currentBrightness - factor >= 0.4) {
            this.currentBrightness -= factor;
        }
        else {
            final double diff = 0.4 - (this.currentBrightness - factor);
            this.currentBrightness = (float)(0.4 + diff);
            this.decreasing = false;
        }
    }
    
    public float getAddition(final int addition) {
        final float newAdd = addition * 0.1f;
        if (!this.decreasing) {
            if (this.currentBrightness + newAdd <= 1.0f) {
                return this.currentBrightness + newAdd;
            }
            final double diff = this.currentBrightness + newAdd - 1.0f;
            return (float)(1.0 - diff);
        }
        else {
            if (this.currentBrightness - newAdd >= 0.4) {
                return this.currentBrightness - newAdd;
            }
            final double diff = 0.4 - (this.currentBrightness - newAdd);
            return (float)(0.4 + diff);
        }
    }
    
    public int getId() {
        return this.id;
    }
}
