//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.svg.inkscape;

import org.w3c.dom.*;
import java.util.*;
import org.newdawn.slick.svg.*;
import org.newdawn.slick.geom.*;

public class PolygonProcessor implements ElementProcessor
{
    private static int processPoly(final Polygon poly, final Element element, final StringTokenizer tokens) throws ParsingException {
        int count = 0;
        final ArrayList pts = new ArrayList();
        boolean moved = false;
        boolean closed = false;
        while (tokens.hasMoreTokens()) {
            final String nextToken = tokens.nextToken();
            if (nextToken.equals("L")) {
                continue;
            }
            if (nextToken.equals("z")) {
                closed = true;
                break;
            }
            if (nextToken.equals("M")) {
                if (moved) {
                    return 0;
                }
                moved = true;
            }
            else {
                if (nextToken.equals("C")) {
                    return 0;
                }
                final String tokenX = nextToken;
                final String tokenY = tokens.nextToken();
                try {
                    final float x = Float.parseFloat(tokenX);
                    final float y = Float.parseFloat(tokenY);
                    poly.addPoint(x, y);
                    ++count;
                }
                catch (NumberFormatException e) {
                    throw new ParsingException(element.getAttribute("id"), "Invalid token in points list", e);
                }
            }
        }
        poly.setClosed(closed);
        return count;
    }
    
    public void process(final Loader loader, final Element element, final Diagram diagram, final Transform t) throws ParsingException {
        Transform transform = Util.getTransform(element);
        transform = new Transform(t, transform);
        String points = element.getAttribute("points");
        if (element.getNodeName().equals("path")) {
            points = element.getAttribute("d");
        }
        final StringTokenizer tokens = new StringTokenizer(points, ", ");
        final Polygon poly = new Polygon();
        final int count = processPoly(poly, element, tokens);
        final NonGeometricData data = Util.getNonGeometricData(element);
        if (count > 3) {
            final Shape shape = poly.transform(transform);
            diagram.addFigure(new Figure(5, shape, data, transform));
        }
    }
    
    public boolean handles(final Element element) {
        return element.getNodeName().equals("polygon") || (element.getNodeName().equals("path") && !"arc".equals(element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")));
    }
}
