//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.misc;

import org.lwjgl.opengl.*;
import net.minecraft.util.*;
import java.nio.*;
import javax.imageio.*;
import java.util.*;
import java.awt.image.*;
import java.io.*;

public class WindowUtil
{
    public static void setTitle(final String title) {
        Display.setTitle(title);
    }
    
    public static void setIcon() {
        if (Util.getOSType() != Util.EnumOS.OSX) {
            try (final InputStream inputStream16x = WindowUtil.class.getResourceAsStream("/assets/violet/logos/violet16x.png");
                 final InputStream inputStream32x = WindowUtil.class.getResourceAsStream("/assets/violet/logos/violet32x.png")) {
                final ByteBuffer[] icons = { readImageToBuffer(inputStream16x), readImageToBuffer(inputStream32x) };
                Display.setIcon(icons);
            }
            catch (Exception ex) {}
        }
    }
    
    private static ByteBuffer readImageToBuffer(final InputStream inputStream) throws IOException {
        final BufferedImage bufferedimage = ImageIO.read(inputStream);
        final int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), null, 0, bufferedimage.getWidth());
        final ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
        Arrays.stream(aint).map(i -> i << 8 | (i >> 24 & 0xFF)).forEach(bytebuffer::putInt);
        bytebuffer.flip();
        return bytebuffer;
    }
}
