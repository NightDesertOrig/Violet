//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.svg.inkscape;

import org.w3c.dom.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.svg.*;

public class RectProcessor implements ElementProcessor
{
    public void process(final Loader loader, final Element element, final Diagram diagram, final Transform t) throws ParsingException {
        Transform transform = Util.getTransform(element);
        transform = new Transform(t, transform);
        final float width = Float.parseFloat(element.getAttribute("width"));
        final float height = Float.parseFloat(element.getAttribute("height"));
        final float x = Float.parseFloat(element.getAttribute("x"));
        final float y = Float.parseFloat(element.getAttribute("y"));
        final Rectangle rect = new Rectangle(x, y, width + 1.0f, height + 1.0f);
        final Shape shape = rect.transform(transform);
        final NonGeometricData data = Util.getNonGeometricData(element);
        data.addAttribute("width", "" + width);
        data.addAttribute("height", "" + height);
        data.addAttribute("x", "" + x);
        data.addAttribute("y", "" + y);
        diagram.addFigure(new Figure(3, shape, data, transform));
    }
    
    public boolean handles(final Element element) {
        return element.getNodeName().equals("rect");
    }
}
