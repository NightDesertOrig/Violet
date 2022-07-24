//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.util;

import java.io.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.opengl.renderer.*;
import java.nio.*;
import java.awt.*;
import java.awt.image.*;

public class BufferedImageUtil
{
    public static Texture getTexture(final String resourceName, final BufferedImage resourceImage) throws IOException {
        final Texture tex = getTexture(resourceName, resourceImage, 3553, 6408, 9729, 9729);
        return tex;
    }
    
    public static Texture getTexture(final String resourceName, final BufferedImage resourceImage, final int filter) throws IOException {
        final Texture tex = getTexture(resourceName, resourceImage, 3553, 6408, filter, filter);
        return tex;
    }
    
    public static Texture getTexture(final String resourceName, final BufferedImage resourceimage, final int target, final int dstPixelFormat, final int minFilter, final int magFilter) throws IOException {
        final ImageIOImageData data = new ImageIOImageData();
        int srcPixelFormat = 0;
        final int textureID = InternalTextureLoader.createTextureID();
        final TextureImpl texture = new TextureImpl(resourceName, target, textureID);
        Renderer.get().glEnable(3553);
        Renderer.get().glBindTexture(target, textureID);
        final BufferedImage bufferedImage = resourceimage;
        texture.setWidth(bufferedImage.getWidth());
        texture.setHeight(bufferedImage.getHeight());
        if (bufferedImage.getColorModel().hasAlpha()) {
            srcPixelFormat = 6408;
        }
        else {
            srcPixelFormat = 6407;
        }
        final ByteBuffer textureBuffer = data.imageToByteBuffer(bufferedImage, false, false, (int[])null);
        texture.setTextureHeight(data.getTexHeight());
        texture.setTextureWidth(data.getTexWidth());
        texture.setAlpha(data.getDepth() == 32);
        if (target == 3553) {
            Renderer.get().glTexParameteri(target, 10241, minFilter);
            Renderer.get().glTexParameteri(target, 10240, magFilter);
            if (Renderer.get().canTextureMirrorClamp()) {
                Renderer.get().glTexParameteri(3553, 10242, 34627);
                Renderer.get().glTexParameteri(3553, 10243, 34627);
            }
            else {
                Renderer.get().glTexParameteri(3553, 10242, 10496);
                Renderer.get().glTexParameteri(3553, 10243, 10496);
            }
        }
        Renderer.get().glTexImage2D(target, 0, dstPixelFormat, texture.getTextureWidth(), texture.getTextureHeight(), 0, srcPixelFormat, 5121, textureBuffer);
        return (Texture)texture;
    }
    
    private static void copyArea(final BufferedImage image, final int x, final int y, final int width, final int height, final int dx, final int dy) {
        final Graphics2D g = (Graphics2D)image.getGraphics();
        g.drawImage(image.getSubimage(x, y, width, height), x + dx, y + dy, null);
    }
}
