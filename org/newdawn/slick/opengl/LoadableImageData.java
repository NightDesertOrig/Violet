//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.opengl;

import java.nio.*;
import java.io.*;

public interface LoadableImageData extends ImageData
{
    void configureEdging(final boolean p0);
    
    ByteBuffer loadImage(final InputStream p0) throws IOException;
    
    ByteBuffer loadImage(final InputStream p0, final boolean p1, final int[] p2) throws IOException;
    
    ByteBuffer loadImage(final InputStream p0, final boolean p1, final boolean p2, final int[] p3) throws IOException;
}
