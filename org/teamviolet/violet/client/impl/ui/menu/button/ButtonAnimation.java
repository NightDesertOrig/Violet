//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.ui.menu.button;

import net.minecraft.util.math.*;
import org.teamviolet.violet.client.util.render.*;

public class ButtonAnimation
{
    private double totalFactor;
    private int speed;
    private int percent;
    public Vec2f top;
    public Vec2f bottom;
    private Vec2f originalTop;
    private Vec2f originalBottom;
    
    public ButtonAnimation(final int totalFactor) {
        this(totalFactor, 1);
    }
    
    public ButtonAnimation(final double totalFactor, final int speed) {
        this.percent = 0;
        this.totalFactor = totalFactor;
        this.speed = speed;
    }
    
    public ButtonAnimation withBounds(final Vec2f top, final Vec2f bottom) {
        this.top = top;
        this.bottom = bottom;
        this.originalTop = top;
        this.originalBottom = bottom;
        return this;
    }
    
    public ButtonAnimation withPercentage(final int percent) {
        this.percent = percent;
        return this;
    }
    
    public int expand() {
        if (this.percent < 100) {
            this.percent += this.speed;
        }
        this.top = VectorUtil.minus(this.originalTop, (float)(this.totalFactor * (this.percent / 100.0)));
        this.bottom = VectorUtil.plus(this.originalBottom, (float)(2.0 * (this.totalFactor * (this.percent / 100.0))));
        return this.percent;
    }
    
    public int shrink() {
        if (this.percent <= 0) {
            return 0;
        }
        this.percent -= this.speed;
        this.top = VectorUtil.minus(this.originalTop, (float)(this.totalFactor * (this.percent / 100.0)));
        this.bottom = VectorUtil.plus(this.originalBottom, (float)(2.0 * (this.totalFactor * (this.percent / 100.0))));
        return this.percent;
    }
    
    public double getTotalFactor() {
        return this.totalFactor;
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public int getPercent() {
        return this.percent;
    }
}
