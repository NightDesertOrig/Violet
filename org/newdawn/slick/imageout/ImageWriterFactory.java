//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.imageout;

import java.util.*;
import org.newdawn.slick.*;
import javax.imageio.*;

public class ImageWriterFactory
{
    private static HashMap writers;
    
    public static void registerWriter(final String format, final ImageWriter writer) {
        ImageWriterFactory.writers.put(format, writer);
    }
    
    public static String[] getSupportedFormats() {
        return (String[])ImageWriterFactory.writers.keySet().toArray(new String[0]);
    }
    
    public static ImageWriter getWriterForFormat(final String format) throws SlickException {
        ImageWriter writer = ImageWriterFactory.writers.get(format);
        if (writer != null) {
            return writer;
        }
        writer = ImageWriterFactory.writers.get(format.toLowerCase());
        if (writer != null) {
            return writer;
        }
        writer = ImageWriterFactory.writers.get(format.toUpperCase());
        if (writer != null) {
            return writer;
        }
        throw new SlickException("No image writer available for: " + format);
    }
    
    static {
        ImageWriterFactory.writers = new HashMap();
        final String[] formats = ImageIO.getWriterFormatNames();
        final ImageIOWriter writer = new ImageIOWriter();
        for (int i = 0; i < formats.length; ++i) {
            registerWriter(formats[i], (ImageWriter)writer);
        }
        final TGAWriter tga = new TGAWriter();
        registerWriter("tga", (ImageWriter)tga);
    }
}
