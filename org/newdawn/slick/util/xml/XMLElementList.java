//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.util.xml;

import java.util.*;

public class XMLElementList
{
    private ArrayList list;
    
    public XMLElementList() {
        this.list = new ArrayList();
    }
    
    public void add(final XMLElement element) {
        this.list.add(element);
    }
    
    public int size() {
        return this.list.size();
    }
    
    public XMLElement get(final int i) {
        return this.list.get(i);
    }
    
    public boolean contains(final XMLElement element) {
        return this.list.contains(element);
    }
    
    public void addAllTo(final Collection collection) {
        collection.addAll(this.list);
    }
}
