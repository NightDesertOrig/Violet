//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.svg.inkscape;

import org.w3c.dom.*;
import java.util.*;
import org.newdawn.slick.svg.*;
import org.newdawn.slick.geom.*;

public class PathProcessor implements ElementProcessor
{
    private static Path processPoly(final Element element, final StringTokenizer tokens) throws ParsingException {
        final int count = 0;
        final ArrayList pts = new ArrayList();
        boolean moved = false;
        boolean reasonToBePath = false;
        Path path = null;
        while (tokens.hasMoreTokens()) {
            try {
                final String nextToken = tokens.nextToken();
                if (nextToken.equals("L")) {
                    final float x = Float.parseFloat(tokens.nextToken());
                    final float y = Float.parseFloat(tokens.nextToken());
                    path.lineTo(x, y);
                    continue;
                }
                if (nextToken.equals("z")) {
                    path.close();
                    continue;
                }
                if (nextToken.equals("M")) {
                    if (!moved) {
                        moved = true;
                        final float x = Float.parseFloat(tokens.nextToken());
                        final float y = Float.parseFloat(tokens.nextToken());
                        path = new Path(x, y);
                        continue;
                    }
                    reasonToBePath = true;
                    final float x = Float.parseFloat(tokens.nextToken());
                    final float y = Float.parseFloat(tokens.nextToken());
                    path.startHole(x, y);
                    continue;
                }
                else {
                    if (nextToken.equals("C")) {
                        reasonToBePath = true;
                        final float cx1 = Float.parseFloat(tokens.nextToken());
                        final float cy1 = Float.parseFloat(tokens.nextToken());
                        final float cx2 = Float.parseFloat(tokens.nextToken());
                        final float cy2 = Float.parseFloat(tokens.nextToken());
                        final float x2 = Float.parseFloat(tokens.nextToken());
                        final float y2 = Float.parseFloat(tokens.nextToken());
                        path.curveTo(x2, y2, cx1, cy1, cx2, cy2);
                        continue;
                    }
                    continue;
                }
            }
            catch (NumberFormatException e) {
                throw new ParsingException(element.getAttribute("id"), "Invalid token in points list", e);
            }
            break;
        }
        if (!reasonToBePath) {
            return null;
        }
        return path;
    }
    
    public void process(final Loader loader, final Element element, final Diagram diagram, final Transform t) throws ParsingException {
        Transform transform = Util.getTransform(element);
        transform = new Transform(t, transform);
        String points = element.getAttribute("points");
        if (element.getNodeName().equals("path")) {
            points = element.getAttribute("d");
        }
        final StringTokenizer tokens = new StringTokenizer(points, ", ");
        final Path path = processPoly(element, tokens);
        final NonGeometricData data = Util.getNonGeometricData(element);
        if (path != null) {
            final Shape shape = path.transform(transform);
            diagram.addFigure(new Figure(4, shape, data, transform));
        }
    }
    
    public boolean handles(final Element element) {
        return element.getNodeName().equals("path") && !"arc".equals(element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type"));
    }
}
