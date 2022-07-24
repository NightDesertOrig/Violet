//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.svg.inkscape;

import org.w3c.dom.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.svg.*;

public class EllipseProcessor implements ElementProcessor
{
    public void process(final Loader loader, final Element element, final Diagram diagram, final Transform t) throws ParsingException {
        Transform transform = Util.getTransform(element);
        transform = new Transform(t, transform);
        final float x = Util.getFloatAttribute(element, "cx");
        final float y = Util.getFloatAttribute(element, "cy");
        final float rx = Util.getFloatAttribute(element, "rx");
        final float ry = Util.getFloatAttribute(element, "ry");
        final Ellipse ellipse = new Ellipse(x, y, rx, ry);
        final Shape shape = ellipse.transform(transform);
        final NonGeometricData data = Util.getNonGeometricData(element);
        data.addAttribute("cx", "" + x);
        data.addAttribute("cy", "" + y);
        data.addAttribute("rx", "" + rx);
        data.addAttribute("ry", "" + ry);
        diagram.addFigure(new Figure(1, shape, data, transform));
    }
    
    public boolean handles(final Element element) {
        return element.getNodeName().equals("ellipse") || (element.getNodeName().equals("path") && "arc".equals(element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")));
    }
}
