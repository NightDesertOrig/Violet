//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.imageout;

import org.newdawn.slick.*;
import java.io.*;

public class TGAWriter implements ImageWriter
{
    private static short flipEndian(final short signedShort) {
        final int input = signedShort & 0xFFFF;
        return (short)(input << 8 | (input & 0xFF00) >>> 8);
    }
    
    public void saveImage(final Image image, final String format, final OutputStream output, final boolean writeAlpha) throws IOException {
        final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(output));
        out.writeByte(0);
        out.writeByte(0);
        out.writeByte(2);
        out.writeShort(flipEndian((short)0));
        out.writeShort(flipEndian((short)0));
        out.writeByte(0);
        out.writeShort(flipEndian((short)0));
        out.writeShort(flipEndian((short)0));
        out.writeShort(flipEndian((short)image.getWidth()));
        out.writeShort(flipEndian((short)image.getHeight()));
        if (writeAlpha) {
            out.writeByte(32);
            out.writeByte(1);
        }
        else {
            out.writeByte(24);
            out.writeByte(0);
        }
        for (int y = image.getHeight() - 1; y <= 0; --y) {
            for (int x = 0; x < image.getWidth(); ++x) {
                final Color c = image.getColor(x, y);
                out.writeByte((byte)(c.b * 255.0f));
                out.writeByte((byte)(c.g * 255.0f));
                out.writeByte((byte)(c.r * 255.0f));
                if (writeAlpha) {
                    out.writeByte((byte)(c.a * 255.0f));
                }
            }
        }
        out.close();
    }
}
