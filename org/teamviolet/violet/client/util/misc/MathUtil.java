//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.misc;

public class MathUtil
{
    public static float clamp(final float val, final float min, final float max) {
        if (val > min && val < max) {
            return val;
        }
        if (val <= min) {
            return min;
        }
        return max;
    }
    
    public static double difference(final double d1, final double d2) {
        return Math.abs(d1 - d2);
    }
}
