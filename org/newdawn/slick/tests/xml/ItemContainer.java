//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests.xml;

import java.util.*;

public class ItemContainer extends Item
{
    private ArrayList items;
    
    public ItemContainer() {
        this.items = new ArrayList();
    }
    
    private void add(final Item item) {
        this.items.add(item);
    }
    
    private void setName(final String name) {
        this.name = name;
    }
    
    private void setCondition(final int condition) {
        this.condition = condition;
    }
    
    public void dump(final String prefix) {
        System.out.println(prefix + "Item Container " + this.name + "," + this.condition);
        for (int i = 0; i < this.items.size(); ++i) {
            this.items.get(i).dump(prefix + "\t");
        }
    }
}
