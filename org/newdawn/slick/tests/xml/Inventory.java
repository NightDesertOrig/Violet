//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests.xml;

import java.util.*;

public class Inventory
{
    private ArrayList items;
    
    public Inventory() {
        this.items = new ArrayList();
    }
    
    private void add(final Item item) {
        this.items.add(item);
    }
    
    public void dump(final String prefix) {
        System.out.println(prefix + "Inventory");
        for (int i = 0; i < this.items.size(); ++i) {
            this.items.get(i).dump(prefix + "\t");
        }
    }
}
