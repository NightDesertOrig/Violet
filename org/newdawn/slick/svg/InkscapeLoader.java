//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.svg;

import java.util.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.*;
import org.xml.sax.*;
import java.io.*;
import org.newdawn.slick.geom.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.newdawn.slick.svg.inkscape.*;

public class InkscapeLoader implements Loader
{
    public static int RADIAL_TRIANGULATION_LEVEL;
    private static ArrayList processors;
    private Diagram diagram;
    
    public static void addElementProcessor(final ElementProcessor proc) {
        InkscapeLoader.processors.add(proc);
    }
    
    public static Diagram load(final String ref, final boolean offset) throws SlickException {
        return load(ResourceLoader.getResourceAsStream(ref), offset);
    }
    
    public static Diagram load(final String ref) throws SlickException {
        return load(ResourceLoader.getResourceAsStream(ref), false);
    }
    
    public static Diagram load(final InputStream in, final boolean offset) throws SlickException {
        return new InkscapeLoader().loadDiagram(in, offset);
    }
    
    private InkscapeLoader() {
    }
    
    private Diagram loadDiagram(final InputStream in) throws SlickException {
        return this.loadDiagram(in, false);
    }
    
    private Diagram loadDiagram(final InputStream in, final boolean offset) throws SlickException {
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);
            final DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setEntityResolver(new EntityResolver() {
                @Override
                public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException, IOException {
                    return new InputSource(new ByteArrayInputStream(new byte[0]));
                }
            });
            final Document doc = builder.parse(in);
            final Element root = doc.getDocumentElement();
            String widthString;
            for (widthString = root.getAttribute("width"); Character.isLetter(widthString.charAt(widthString.length() - 1)); widthString = widthString.substring(0, widthString.length() - 1)) {}
            String heightString;
            for (heightString = root.getAttribute("height"); Character.isLetter(heightString.charAt(heightString.length() - 1)); heightString = heightString.substring(0, heightString.length() - 1)) {}
            final float docWidth = Float.parseFloat(widthString);
            float docHeight = Float.parseFloat(heightString);
            this.diagram = new Diagram(docWidth, docHeight);
            if (!offset) {
                docHeight = 0.0f;
            }
            this.loadChildren(root, Transform.createTranslateTransform(0.0f, -docHeight));
            return this.diagram;
        }
        catch (Exception e) {
            throw new SlickException("Failed to load inkscape document", (Throwable)e);
        }
    }
    
    @Override
    public void loadChildren(final Element element, final Transform t) throws ParsingException {
        final NodeList list = element.getChildNodes();
        for (int i = 0; i < list.getLength(); ++i) {
            if (list.item(i) instanceof Element) {
                this.loadElement((Element)list.item(i), t);
            }
        }
    }
    
    private void loadElement(final Element element, final Transform t) throws ParsingException {
        for (int i = 0; i < InkscapeLoader.processors.size(); ++i) {
            final ElementProcessor processor = InkscapeLoader.processors.get(i);
            if (processor.handles(element)) {
                processor.process((Loader)this, element, this.diagram, t);
            }
        }
    }
    
    static {
        InkscapeLoader.RADIAL_TRIANGULATION_LEVEL = 1;
        InkscapeLoader.processors = new ArrayList();
        addElementProcessor((ElementProcessor)new RectProcessor());
        addElementProcessor((ElementProcessor)new EllipseProcessor());
        addElementProcessor((ElementProcessor)new PolygonProcessor());
        addElementProcessor((ElementProcessor)new PathProcessor());
        addElementProcessor((ElementProcessor)new LineProcessor());
        addElementProcessor((ElementProcessor)new GroupProcessor());
        addElementProcessor((ElementProcessor)new DefsProcessor());
        addElementProcessor((ElementProcessor)new UseProcessor());
    }
}
