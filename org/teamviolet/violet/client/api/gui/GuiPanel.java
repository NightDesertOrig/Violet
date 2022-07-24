//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.gui;

import java.util.*;

public abstract class GuiPanel extends GuiElement
{
    public final List<GuiElement> elements;
    public boolean dragging;
    public int offsetX;
    public int offsetY;
    public int prevPosX;
    public int prevPosY;
    
    public GuiPanel(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.offsetX = 0;
        this.offsetY = 0;
        this.elements = new ArrayList<GuiElement>();
        this.dragging = false;
        this.prevPosX = x;
        this.prevPosY = y;
    }
    
    public abstract void initElements();
}
