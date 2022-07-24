//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render;

import java.awt.*;
import com.mojang.realmsclient.gui.*;

public class ColorUtil
{
    public static Color getRainbow() {
        return getRainbow(1.0f, 1.0f);
    }
    
    public static Color getRainbowFront(final int scale) {
        return getRainbowFront(1.0f, 1.0f, (float)scale);
    }
    
    public static Color getRainbow(final float saturation, final float brightness) {
        final Color c = Color.getHSBColor(System.currentTimeMillis() % 7500L / 7500.0f, saturation, brightness);
        return new Color(c.getRed(), c.getGreen(), c.getGreen());
    }
    
    public static Color getRainbowA(final float saturation, final float brightness, final int alpha) {
        final Color c = Color.getHSBColor(System.currentTimeMillis() % 7500L / 7500.0f, saturation, brightness);
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
    }
    
    public static Color getRainbow(final float saturation, final float brightness, double speed) {
        speed = 101.0 - speed;
        final double hue = (float)(System.currentTimeMillis() % (long)(360.0 * speed)) / (360.0 * speed);
        return new Color(Color.HSBtoRGB((float)hue, saturation, brightness));
    }
    
    public static Color getRainbowFront(final float saturation, final float brightness, final float speed) {
        return Color.getHSBColor(System.currentTimeMillis() % (long)(360.0f * speed) / (360.0f * speed), saturation, brightness);
    }
    
    public static Color rainbow(final float speed, final float off) {
        double time = System.currentTimeMillis() / (double)speed;
        time += off;
        time %= 255.0;
        return Color.getHSBColor((float)(time / 255.0), 1.0f, 1.0f);
    }
    
    public static float getHue(final Color color) {
        return Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[0];
    }
    
    public static float getSat(final Color color) {
        return Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[1];
    }
    
    public static float getBright(final Color color) {
        return Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[2];
    }
    
    public static ChatFormatting getPercentColor(final float in, final float total) {
        final float health = in / total * 100.0f;
        if (health > 90.0f) {
            return ChatFormatting.GREEN;
        }
        if (health > 70.0f) {
            return ChatFormatting.DARK_GREEN;
        }
        if (health > 50.0f) {
            return ChatFormatting.YELLOW;
        }
        if (health > 30.0f) {
            return ChatFormatting.RED;
        }
        return ChatFormatting.DARK_RED;
    }
    
    private ColorUtil() {
        throw new UnsupportedOperationException();
    }
}
