//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.font.effects;

import java.awt.image.*;
import java.awt.*;
import org.newdawn.slick.*;
import org.newdawn.slick.font.*;
import java.util.*;

public class ColorEffect implements ConfigurableEffect
{
    private Color color;
    
    public ColorEffect() {
        this.color = Color.white;
    }
    
    public ColorEffect(final Color color) {
        this.color = Color.white;
        this.color = color;
    }
    
    @Override
    public void draw(final BufferedImage image, final Graphics2D g, final UnicodeFont unicodeFont, final Glyph glyph) {
        g.setColor(this.color);
        g.fill(glyph.getShape());
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(final Color color) {
        if (color == null) {
            throw new IllegalArgumentException("color cannot be null.");
        }
        this.color = color;
    }
    
    @Override
    public String toString() {
        return "Color";
    }
    
    @Override
    public List getValues() {
        final List values = new ArrayList();
        values.add(EffectUtil.colorValue("Color", this.color));
        return values;
    }
    
    @Override
    public void setValues(final List values) {
        for (final Value value : values) {
            if (value.getName().equals("Color")) {
                this.setColor((Color)value.getObject());
            }
        }
    }
}
