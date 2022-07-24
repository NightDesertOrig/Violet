//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.font.effects;

import java.awt.image.*;
import org.newdawn.slick.*;
import org.newdawn.slick.font.*;
import java.awt.*;
import java.util.*;

public class OutlineEffect implements ConfigurableEffect
{
    private float width;
    private Color color;
    private int join;
    private Stroke stroke;
    
    public OutlineEffect() {
        this.width = 2.0f;
        this.color = Color.black;
        this.join = 2;
    }
    
    public OutlineEffect(final int width, final Color color) {
        this.width = 2.0f;
        this.color = Color.black;
        this.join = 2;
        this.width = (float)width;
        this.color = color;
    }
    
    public void draw(final BufferedImage image, Graphics2D g, final UnicodeFont unicodeFont, final Glyph glyph) {
        g = (Graphics2D)g.create();
        if (this.stroke != null) {
            g.setStroke(this.stroke);
        }
        else {
            g.setStroke(this.getStroke());
        }
        g.setColor(this.color);
        g.draw(glyph.getShape());
        g.dispose();
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public void setWidth(final int width) {
        this.width = (float)width;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public int getJoin() {
        return this.join;
    }
    
    public Stroke getStroke() {
        if (this.stroke == null) {
            return new BasicStroke(this.width, 2, this.join);
        }
        return this.stroke;
    }
    
    public void setStroke(final Stroke stroke) {
        this.stroke = stroke;
    }
    
    public void setJoin(final int join) {
        this.join = join;
    }
    
    @Override
    public String toString() {
        return "Outline";
    }
    
    public List getValues() {
        final List values = new ArrayList();
        values.add(EffectUtil.colorValue("Color", this.color));
        values.add(EffectUtil.floatValue("Width", this.width, 0.1f, 999.0f, "This setting controls the width of the outline. The glyphs will need padding so the outline doesn't get clipped."));
        values.add(EffectUtil.optionValue("Join", String.valueOf(this.join), new String[][] { { "Bevel", "2" }, { "Miter", "0" }, { "Round", "1" } }, "This setting defines how the corners of the outline are drawn. This is usually only noticeable at large outline widths."));
        return values;
    }
    
    public void setValues(final List values) {
        for (final ConfigurableEffect.Value value : values) {
            if (value.getName().equals("Color")) {
                this.color = (Color)value.getObject();
            }
            else if (value.getName().equals("Width")) {
                this.width = (float)value.getObject();
            }
            else {
                if (!value.getName().equals("Join")) {
                    continue;
                }
                this.join = Integer.parseInt((String)value.getObject());
            }
        }
    }
}
