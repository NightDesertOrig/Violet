//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.svg.inkscape;

import org.w3c.dom.*;
import org.newdawn.slick.svg.*;
import org.newdawn.slick.geom.*;

public class UseProcessor implements ElementProcessor
{
    public boolean handles(final Element element) {
        return element.getNodeName().equals("use");
    }
    
    public void process(final Loader loader, final Element element, final Diagram diagram, final Transform transform) throws ParsingException {
        final String ref = element.getAttributeNS("http://www.w3.org/1999/xlink", "href");
        final String href = Util.getAsReference(ref);
        final Figure referenced = diagram.getFigureByID(href);
        if (referenced == null) {
            throw new ParsingException(element, "Unable to locate referenced element: " + href);
        }
        final Transform local = Util.getTransform(element);
        final Transform trans = local.concatenate(referenced.getTransform());
        final NonGeometricData data = Util.getNonGeometricData(element);
        final Shape shape = referenced.getShape().transform(trans);
        data.addAttribute("fill", referenced.getData().getAttribute("fill"));
        data.addAttribute("stroke", referenced.getData().getAttribute("stroke"));
        data.addAttribute("opacity", referenced.getData().getAttribute("opacity"));
        data.addAttribute("stroke-width", referenced.getData().getAttribute("stroke-width"));
        final Figure figure = new Figure(referenced.getType(), shape, data, trans);
        diagram.addFigure(figure);
    }
}
